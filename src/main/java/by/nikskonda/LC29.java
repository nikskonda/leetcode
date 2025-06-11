package by.nikskonda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC29 {

  public static void main(String[] args) throws Exception {
    String k1 = new String("key");
    String k2 = new String("key");

    System.out.println(System.identityHashCode(k1));
    System.out.println(System.identityHashCode(k2));


    System.out.println(divide(64, 2));
    System.out.println(32);
    System.out.println("0---");
    System.out.println(divide(-64, 2));
    System.out.println(-32);
    System.out.println("1---");
    System.out.println(divide(64, -2));
    System.out.println(-32);
    System.out.println("2---");
    System.out.println(divide(-64, -2));
    System.out.println(32);
    System.out.println("3---");
    System.out.println(divide(67, -2));
    System.out.println(-33);
    System.out.println("4---");
    System.out.println(divide(-66, 2));
    System.out.println(-33);
    System.out.println("5---");

    System.out.println(divide(Integer.MIN_VALUE, -1));
    System.out.println(Integer.MAX_VALUE);
    System.out.println("6---");

    System.out.println(divide(Integer.MAX_VALUE, -1));
    System.out.println(Integer.MAX_VALUE/-1);
    System.out.println("7---");

    System.out.println(divide(Integer.MIN_VALUE, 2));
    System.out.println(Integer.MIN_VALUE/2);
    System.out.println("8---");

    System.out.println(divide(Integer.MIN_VALUE, -2));
    System.out.println(Integer.MIN_VALUE/-2);
    System.out.println("9---");

    System.out.println(divide(1100540749, -1090366779));
    System.out.println(-1);
  }

  public static int divide(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    return dividend/divisor;
  }


    public static int divide2(int dividend, int divisor) {
    if (divisor == 1) return prepareAnswer(dividend, dividend<0);
    if (divisor == -1) return prepareAnswer(dividend, dividend>0);

    boolean isNegativeAnswer = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
    if (dividend > 0) dividend = -dividend;
    if (divisor > 0) divisor = -divisor;

    if (divisor < dividend) return 0;

    int[] sum = new int[32];
    int[] count = new int[32];

    sum[0] = divisor;
    count[0] = 1;

    int i = 0;
    int nextSum = sum[i] + sum[i];
    while (sum[i] > -1073741824 && nextSum >= dividend) {
      i++;
      sum[i] = nextSum;
      count[i] = count[i-1] + count[i-1];
      nextSum = sum[i] + sum[i];
    }
    if (sum[i] == dividend) return prepareAnswer(count[i], isNegativeAnswer);

    int result = 0;
    int prevIndex = i;
    while (dividend <= divisor) {
      int index = binarySearch(dividend, sum, 0, prevIndex);
      result += count[index];
      dividend -= sum[index];
      prevIndex = index;
    }
    return prepareAnswer(result, isNegativeAnswer);
  }

  private static int prepareAnswer(int answer, boolean isNegative) {
      if (answer > 0 && !isNegative) return answer;
      if (answer < 0 && isNegative) return answer;
      if (answer < 0 && !isNegative) return answer == Integer.MIN_VALUE ? Integer.MAX_VALUE : -answer;
      return -answer;
  }

  //return index!!!
  private static int binarySearch(int target, int[] data, int start, int end) {
    if (target < data[end]) return end;
    if (target > data[start]) return start-1;

    int mid = (start + end) / 2;
    if (data[mid] == target) return mid;
    if (data[mid] > target) return binarySearch(target, data, mid + 1, end);
    else return binarySearch(target, data, start, mid -1);
  }
}