package by.nikskonda.lc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC56 {

  public static void main(String[] args) throws Exception {
    int[][] array = new int[][]{
        new int[]{1, 3},
        new int[]{2, 6},
        new int[]{8, 10},
        new int[]{15, 18}
    };
        Arrays.stream(merge(array))
        .forEach(a -> System.out.println(Arrays.stream(a).boxed().toList()));
  }

  public static int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt((ar) -> ar[0]));

    int pointer = 0;
    for (int i = 1; i < intervals.length; i++) {
      int endValue = intervals[pointer][1];
      int startValue = intervals[i][0];
      if (endValue < startValue) {
        pointer++;
        intervals[pointer] = intervals[i];

      } else {
        intervals[pointer][1] = Math.max(intervals[i][1], intervals[pointer][1]);
      }
    }
    return Arrays.copyOf(intervals, pointer+1);
  }

}