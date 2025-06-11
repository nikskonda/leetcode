package by.nikskonda;

import by.nikskonda.perf.PerfTest;
import by.nikskonda.perf.PerformanceResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC58 {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 200;
  private static final int KEK = 1000;
  private static final boolean PERF_TEST = true;


  public static void main(String[] args) throws Exception {
    System.out.println(lengthOfLastWord("hellow world"));
    System.out.println(lengthOfLastWord("hellow world!"));
    System.out.println(lengthOfLastWord("hellow world! "));
    System.out.println(lengthOfLastWord("hellow world  qwr qwre q"));
    System.out.println(lengthOfLastWord("hellow                   "));
  }

  public static int lengthOfLastWord(String str) {
    char[] charArray = str.toCharArray();
    int answer = 0;
    for (int i = charArray.length-1; i >= 0; i--) {
      if (charArray[i] == 32) {
        if (answer!=0) {
          break;
        }
      } else {
        answer++;
      }
    }
    return answer;
  }

}