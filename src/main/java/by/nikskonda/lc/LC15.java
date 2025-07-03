package by.nikskonda.lc;

import by.nikskonda.perf.PerfTest;
import by.nikskonda.perf.PerformanceResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC15 {

  private static final int ITERATIONS = 1000;
  private static final int LENGTH = 10;
  private static final int KEK = 10;
  private static final boolean PERF_TEST = false;


  public static void main(String[] args) throws Exception {

    fourSum1(new int[] {-10, -3, -2, -1, 1, 2, 4, 4, 4, 9});

    Random random = new Random();
    PerformanceResult resMethod1 = new PerformanceResult();
    PerformanceResult resMethod2 = new PerformanceResult();
    int errorCount = 0;

    for (int i = 0; i < ITERATIONS; i++) {
      int[] input = new int[LENGTH];
      int sum = random.nextInt(KEK * 2) - KEK;
      for (int j = 0; j < LENGTH; j++) {
        input[j] = random.nextInt(KEK * 2) - KEK;
      }

      System.out.println("--- " + i);
      if (PERF_TEST) {
        PerformanceResult result = PerfTest.measurePerformance(() -> fourSum1(input));
        resMethod1.add(result);
        System.out.println("M1: " + result);

        result = PerfTest.measurePerformance(() -> fourSum2(input));
        resMethod2.add(result);
        System.out.println("M2: " + result);
      } else {
        List<List<Integer>> res1 = fourSum1(input);
//        System.out.println("M1: " + res1);
        List<List<Integer>> res2 = fourSum2(input);
//        System.out.println("M2: " + res2);
        if (res1.size() != res2.size()) {
          System.out.println("ERROR (M1: " + res1.size() + "M2: " + res2.size() + ")");
          errorCount++;
          System.out.println(sum);
          System.out.println(Arrays.stream(input).sorted().boxed().toList());
          //        System.out.println("M1: " + res1);
//        System.out.println("M2: " + res2);
        } else {
          System.out.println("MATCH");
        }
      }
    }
    if (PERF_TEST) {
      System.out.println("---" + System.lineSeparator() + "SUMMARY:");
      System.out.println(resMethod1);
      System.out.println(resMethod2);
    } else {
      System.out.println("ERROR COUNT: " + errorCount);
    }
  }

  public static List<List<Integer>> fourSum1(int[] nums) {
    int length = nums.length - 1;
    List<List<Integer>> result = new ArrayList<>(length *3);
    Arrays.sort(nums);
    for (int i = 0; i < length - 1; i++) {
      int pointer = nums[i];
      if (i != 0 && pointer == nums[i - 1]) {
        continue;
      }

      int start = i+1;
      int end = length;
      int target = -pointer;

      while (start < end) {
        int sum = nums[start] + nums[end];
        if (sum == target) {
          result.add(List.of(pointer, nums[start], nums[end]));
          while (start < end && nums[start] == nums[start+1]) start++;
          start++;
          while (start < end && nums[end] == nums[end-1]) end--;
          end--;
        } else if (sum > target) {
          end--;
        } else {
          start++;
        }
      }
    }
    return result;
  }

  private static int decreaseEnd(int[] list, int start, int end) {
    do {
      end--;
    } while (start < end && list[end] == list[end + 1]);
    return end;
  }

  private static int increaseStart(int[] list, int start, int end) {
    do {
      start++;
    } while (start < end && list[start] == list[start - 1]);
    return start;
  }

  public static List<List<Integer>> fourSum2(int[] nums) {
    Set<List<Integer>> result = new HashSet<>();
    int length = nums.length;
    for (int i1 = 0; i1 < length - 2; i1++) {
      for (int i2 = i1 + 1; i2 < length - 1; i2++) {
        for (int i3 = i2 + 1; i3 < length; i3++) {
          if (nums[i1] + nums[i2] + nums[i3] == 0) {
            result.add(new ArrayList<>(Stream.of(nums[i1], nums[i2], nums[i3]).sorted().toList()));

          }
        }
      }
    }
    return new ArrayList<>(result);
  }

}