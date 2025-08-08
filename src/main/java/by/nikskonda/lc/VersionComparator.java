package by.nikskonda.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Todo add class description.
 *
 * @author Mikita Shkonda Date: 2025-07-30
 */
public class VersionComparator {

  public static void main(String[] args) {
    // Versions in logical ascending order
    List<String> versionStrings = Arrays.asList(
        "0.1", "1", "1.0.1", "1.1", "1.01", "1.001", "7.5.2.4", "7.5.3"
    );

    List<Version> expectedOrder = new ArrayList<>();
    for (String version : versionStrings) {
      expectedOrder.add(new Version(version));
    }

    // Shuffle to simulate unsorted input
    List<Version> shuffledList = new ArrayList<>(expectedOrder);
    Collections.shuffle(shuffledList);

    // Sort the shuffled list
    Collections.sort(shuffledList);

    // Compare result with expected
    boolean isCorrect = expectedOrder.equals(shuffledList);

    System.out.println("Sorted correctly: " + isCorrect);
    if (!isCorrect) {
      System.out.println("Expected:");
      expectedOrder.forEach(System.out::println);
      System.out.println("Got:");
      shuffledList.forEach(System.out::println);
    }
  }

  public static class Version implements Comparable<Version> {

    private List<Integer> value;
    private String initValue;

    public Version(String version) {
      initValue = version;
      String[] splited = version.split("\\.");
      value = new ArrayList(splited.length);
      for (String num : splited) {
        value.add(Integer.valueOf(num));
      }
    }

    public int getSegmentsNumber() {
      return value.size();
    }

    public Integer getSegmentValue(int segmentNumber) {
      return value.get(segmentNumber);
    }

    @Override
    public int compareTo(Version version) {
      int length = Math.min(this.getSegmentsNumber(), version.getSegmentsNumber());
      for (int i = 0; i < length; i++) {
        int compareResult = this.getSegmentValue(i).compareTo(version.getSegmentValue(i));
        if (compareResult != 0) {
          return compareResult;
        }
      }
      if (this.getSegmentsNumber() < version.getSegmentsNumber()) {
        for (int i = length; i < version.getSegmentsNumber(); i++) {
          if (version.getSegmentValue(i) > 0) {
            return -1;
          }
        }
      } else {
        for (int i = length; i < this.getSegmentsNumber(); i++) {
          if (this.getSegmentValue(i) > 0) {
            return 1;
          }
        }
      }
      return 0;
    }

    @Override
    public String toString() {
      return initValue;
    }
  }
}