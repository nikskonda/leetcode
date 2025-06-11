package by.nikskonda;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main3 {

  public static void main(String[] args) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 32; i<=32+94; i++) {
      map.put(Character.valueOf((char)i), i);
    }
    map.clear();
    Random random = new Random();
    long diff1 = 0;
    long diff2 = 0;

    for (int i = 0; i<100; i++) {

      int length = 10000000 + random.nextInt(1000);
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j<length; j++) {
        sb.append((char)(32 + random.nextInt(94)));
      }
      String str = sb.toString();

      System.out.println("---");
//      System.out.println(str);


      Instant startTime = Instant.now();
      int res1 = lengthOfLongestSubstring(str, map);
      long diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff);
      diff1=diff1+diff;
      map.clear();

      startTime = Instant.now();
      int res2 = lengthOfLongestSubstring2(str);
      diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff);
      diff2=diff2+diff;


//      System.out.println(res1);
//      System.out.println(res2);
      if (res1 != res2) {
        System.out.println("ERROR");
      } else {
        System.out.println("result: " + res1);
      }
    }
    System.out.println(diff1);
    System.out.println(diff2);
  }
//  12322435
//  01234567
//  123
//    32
//      2435

  public static int lengthOfLongestSubstring(String s, Map<Character, Integer> map) {
    int l = 0;
    int result = 0;
    char[] chars = s.toCharArray();
    for (int r = 0; r < s.length(); r++) {
      Character key = Character.valueOf(chars[r]);
      if (map.containsKey(key)) {
        result = Math.max(result, r-l);
        l = Math.max(l, map.get(key) + 1);
      }
      map.put(key, r);
    }
    return Math.max(result, s.length() - l);
  }

  public static int lengthOfLongestSubstring2(String s) {
    int result = 0;
    int start = 0;
    int i = 0;
    for (char c : s.toCharArray()) {
      int indexOf = s.indexOf(c, start);
      if (indexOf < i) {
        result = Math.max(i - start, result);
        start = indexOf + 1;
      }
      i++;
    }
    return Math.max(result, s.length() - start);
  }
}