package by.nikskonda.lc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main {

  public static void main(String[] args) throws Exception {
    List<Subtitle> lang1 = readFromFile("input/Silicon.Valley.S02E01.1080p.BluRay.x264.no.ads.srt");
//    lang1.forEach(subtitle -> subtitle.text = subtitle.text.stream().map(txt -> "<font size=\"30px\">"+txt).toList());
    lang1.forEach(subtitle -> subtitle.text.set(0, "<font size=\"30px\" style=\"line-height: 20px;\"> " + subtitle.text.get(0)));
    List<Subtitle> lang2 = readFromFile("input/rus.srt");
//    lang2.forEach(subtitle -> subtitle.text = subtitle.text.stream().map(txt -> "<font size=\"15px\">"+txt).toList());
    lang2.forEach(subtitle -> subtitle.text.set(0, "<font size=\"15px\" style=\"line-height: 10px;\">" + subtitle.text.get(0)));

    List<Subtitle> result = new ArrayList<>();
    int index1 = 0;
    int index2 = 0;
    int index = 1;

    while (true) {
      Subtitle subtitle1 = index1 == lang1.size() ? null : lang1.get(index1);
      Subtitle subtitle2 = index2 == lang2.size() ? null : lang2.get(index2);

      if (subtitle1 == null && subtitle2 == null) {
        break;
      }

      Subtitle subtitle = new Subtitle();
      if (subtitle1 != null && subtitle2 != null && subtitle1.time.equals(subtitle2.time)) {
        subtitle.number = "" + index++;
        subtitle.time = subtitle1.time;
        subtitle.text.addAll(subtitle1.text);
        subtitle.text.addAll(subtitle2.text);
        index1++;
        index2++;
      } else if (subtitle2 == null || (subtitle1 != null && subtitle1.time.compareTo(subtitle2.time) < 0)) {
        subtitle.number = "" + index++;
        subtitle.time = subtitle1.time;
        subtitle.text.addAll(subtitle1.text);
        index1++;
      } else {
        subtitle.number = "" + index++;
        subtitle.time = subtitle2.time;
        subtitle.text.addAll(subtitle2.text);
        index2++;
      }
      result.add(subtitle);
    }

    StringBuilder sb = new StringBuilder();
    result.forEach(subtitle ->
        sb.append(subtitle.number).append(System.lineSeparator())
            .append(subtitle.time).append(System.lineSeparator())
            .append(subtitle.text.stream().collect(Collectors.joining(System.lineSeparator())))
            .append(System.lineSeparator())
            .append(System.lineSeparator())
    );
    sb.append(System.lineSeparator());

    System.out.println("Hello world!");
    Files.writeString(Path.of("result.srt"), sb.toString());
  }

  private static List<Subtitle> readFromFile(String fileName) throws IOException {
    List<Subtitle> result = new ArrayList<>();
    List<String> lines = Files.readAllLines(Paths.get(fileName));
    int num = 0;
    while (true) {
      if (lines.get(num).length() == 0) {
        break;
      }
      Subtitle subtitle = new Subtitle();
      try {
        String str = lines.get(num).length()>1 ? lines.get(num).substring(1) : lines.get(num);
        Integer.parseInt(str);
      } catch (Exception ex) {
        System.out.println("InvalidFormat Indicator");
      }
      subtitle.number = lines.get(num++);
      subtitle.time = lines.get(num++);
      while (true) {
        if (lines.size() == num || lines.get(num).length() == 0) {
          break;
        }
        subtitle.text.add(lines.get(num++));
      }
      result.add(subtitle);
      if (lines.size() == num) {
        break;
      }
      num++;
    }
    return result;
  }

  private static class Subtitle {

    String number;
    String time;
    List<String> text = new ArrayList<>();

    public Subtitle() {
    }
  }
}