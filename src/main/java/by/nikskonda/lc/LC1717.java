package by.nikskonda.lc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC1717 {

  public static void main(String[] args) throws Exception {
    System.out.println(maximumGain("aabbaaxybbaabb", 5, 4));
    System.out.println(maximumGain("cdbcbbaaabab", 4, 5));
    System.out.println(maximumGain("cbaabwbbbabbwaaq", 4074, 9819));
  }

  public static int maximumGain(String s, int x, int y) {
    int result = 0;
    int length = s.length();

    String morePriority = x > y ? "ab" : "ba";
    char morePriority0 = morePriority.charAt(0);
    char morePriority1 = morePriority.charAt(1);
    int morePriorityNum = Math.max(x, y);

    String lessPriority = x > y ? "ba" : "ab";
    int lessPriorityNum = Math.min(x, y);
    char lessPriority0 = lessPriority.charAt(0);
    char lessPriority1 = lessPriority.charAt(1);

    int finish = 0;
    while (true) {
      int indexX = s.indexOf(morePriority, finish);
      int indexY = s.indexOf(lessPriority, finish);

      if (indexX <0 && indexY < 0) {
        break;
      }

      int start = 0;
      if (indexX < 0) {
        start = indexY;
      } else if (indexY < 0) {
        start = indexX;
      } else {
        start = Math.min(indexX, indexY);
      }
      int end = start + 1;

      while (start > 0 && end < length - 1) {
        if (s.charAt(start) == morePriority0 && s.charAt(end) == morePriority1) {
          result += morePriorityNum;
        } else if (s.charAt(start) == lessPriority0 && s.charAt(end) == lessPriority1) {
          result += lessPriorityNum;
        } else {
          break;
        }
        start--;
        end++;

      }
      finish = end + 1;

    }
    return result;
  }

}