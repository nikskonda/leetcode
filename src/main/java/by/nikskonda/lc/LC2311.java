package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC2311 {

  public static int longestSubsequence(String s, int k) {
    char[] array = s.toCharArray();
    int result = 0;

    boolean flag = false;
    int value = 0;
    for (int i = array.length-1; i >=0; i--) {
      if (array[i] == '0') {
        result++;
      } else {
        if (flag) continue;

        long add = (long) Math.pow(2, result);
        if (add + value <= k) {
          value+=add;
          result++;
        } else {
          flag = true;
        }
      }
    }

    return result;
  }

}