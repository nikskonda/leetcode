package by.nikskonda;

import java.util.ArrayList;
import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC91 {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 200;
  private static final int KEK = 1000;
  private static final boolean PERF_TEST = true;


  public static void main(String[] args) throws Exception {
    System.out.println(numDecodings2("1111022222"));
    System.out.println(numDecodings("1111022222"));
//    System.out.println(numDecodings("226"));
//    System.out.println(numDecodings("06"));
  }

  public static int numDecodings2(String s) {
    if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;

    int prev = 1;  // dp[i - 2]
    int curr = 1;  // dp[i - 1]

    for (int i = 1; i < s.length(); i++) {
      int temp = 0;

      // Check 1-digit (s[i])
      if (s.charAt(i) != '0') {
        temp = curr;
      }

      // Check 2-digit (s[i-1]s[i])
      int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
      if (twoDigit >= 10 && twoDigit <= 26) {
        temp += prev;
      }

      // Shift values
      prev = curr;
      curr = temp;

      if (curr == 0) return 0; // early exit if invalid
    }

    return curr;

  }
  public static int numDecodings(String s) {
    final int ZERO = 48;
    final int ONE = 49;
    final int TWO = 50;
    final int SIX = 54;
    List<Integer> list = new ArrayList<>();

    int pointer = 0;
    char[] array = s.toCharArray();

    int max = 2;
    int n = 0;
    while (pointer < array.length) {
      if (array[pointer] == ZERO && (pointer == 0 || array[pointer - 1] != ONE || array[pointer - 1] != TWO)) {
        return 0;
      }
      if (array[pointer] == ONE || array[pointer] == TWO) {
        n++;
        while (pointer + 1 < array.length) {
          pointer++;
          if (array[pointer] == ONE || array[pointer] == TWO) {
            n++;
          } else if (array[pointer] == ZERO) {
            n--;
            break;
          } else {
            if (array[pointer-1] == TWO && array[pointer] > SIX) {
              n--;
            }
            n++;
            break;
          }
        }
        if (n > 1) {
          if (n > max) { max = n;}
          list.add(n);
        }
        n = 0;
      }
      pointer++;
    }

    List<Integer> fib = new ArrayList<>(max);
    fib.add(1);
    fib.add(2);
    for (int i = 2; i < max; i++) {
      fib.add(i, fib.get(i-1)+ fib.get(i-2));
    }
    int result = 1;
    for (Integer num : list) {
      result*=fib.get(num-1);
    }
    return result;
  }


}