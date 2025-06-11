package by.nikskonda;

import by.nikskonda.perf.PerfTest;
import by.nikskonda.perf.PerformanceResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main2215 {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 10;
  private static final boolean PERF_TEST = false;

  public static void main(String[] args) throws Exception {

    Random random = new Random();
    PerformanceResult resMethod1 = new PerformanceResult();
    PerformanceResult resMethod2 = new PerformanceResult();

    for (int i = 0; i < ITERATIONS; i++) {
      int length = LENGTH + random.nextInt(LENGTH);
      int[] input1 = new int[length];
      int[] input2 = new int[length];
      for (int j = 0; j < length; j++) {
        input1[j] = random.nextInt(LENGTH);
        input2[j] = random.nextInt(LENGTH);
      }

      System.out.println("---");

      if (PERF_TEST) {
        PerformanceResult result = PerfTest.measurePerformance(() -> method1(input1, input2));
        resMethod1.add(result);
        System.out.println("M1: " + result);

        result = PerfTest.measurePerformance(() -> method2());
        resMethod2.add(result);
        System.out.println("M2: " + result);
      } else {
        List<List<Integer>> res1 = method1(input1, input2);
        System.out.println("M1: " + res1);
//        int res2 = method2();
//        System.out.println("M2: " + res2);
//        if (res1 != res2) {
//          System.out.println("ERROR");
//        }
      }
    }
  }

  public static List<List<Integer>> method1(int[] nums1, int[] nums2) {
    Set<Integer> set1 = Arrays.stream(nums1)
        .boxed()
        .collect(Collectors.toSet());
    Set<Integer> set2 = Arrays.stream(nums2)
        .boxed()
        .collect(Collectors.toSet());
    List<Integer> list2 = set2.stream().filter(num -> {
      if (set1.contains(num)) {
        set1.remove(num);
        return false;
      } else return true;
    }).toList();

    return Arrays.asList(new ArrayList<>(set1), list2);
  }

  public static int method2() {
    return 2;
  }
}