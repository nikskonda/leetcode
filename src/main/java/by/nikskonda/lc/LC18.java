package by.nikskonda.lc;

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
public class LC18 {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 200;
  private static final int KEK = 1000;
  private static final boolean PERF_TEST = true;


  public static void main(String[] args) throws Exception {

    System.out.println(
        fourSum3(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296));
//    System.out.println(fourSum1(new int[]{1, 3, 2, 2, 3}, 8));
//    System.out.println(fourSum1(new int[]{1, 3, 1, 3, 1, 3, 2, 2, 2, 2, 0, 4, 2, 6, 0, 3, 5}, 8));
//    System.out.println(fourSum3(new int[]{1, 3, 1, 3, 1, 3, 2, 2, 2, 2, 0, 4, 2, 6, 0, 3, 5}, 8));
//    System.out.println(fourSum1(new int[]{1, 0, -1, 0, -2, 2}, 0));
//    System.out.println(fourSum1(
//        new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
//            2, 2, 2, 2, 2, 2, 2, 2}, 8));

    Random random = new Random();
    PerformanceResult resMethod1 = new PerformanceResult();
    PerformanceResult resMethod2 = new PerformanceResult();

    for (int i = 0; i < ITERATIONS; i++) {
      int[] input = new int[LENGTH];
      int sum = random.nextInt(KEK * 2) - KEK;
      for (int j = 0; j < LENGTH; j++) {
        input[j] = random.nextInt(KEK * 2) - KEK;
      }

      System.out.println("---");

      if (PERF_TEST) {
        PerformanceResult result = PerfTest.measurePerformance(() -> fourSum1(input, sum));
        resMethod1.add(result);
        System.out.println("M1: " + result);

        result = PerfTest.measurePerformance(() -> fourSum3(input, sum));
        resMethod2.add(result);
        System.out.println("M2: " + result);
      } else {
        List<List<Integer>> res1 = fourSum1(input, sum);
//        System.out.println("M1: " + res1);
        List<List<Integer>> res2 = fourSum3(input, sum);
//        System.out.println("M2: " + res2);
        if (res1.size() != res2.size()) {
          System.out.println("ERROR");
          System.out.println(sum);
          System.out.println(Arrays.stream(input).sorted().boxed().toList());
          //        System.out.println("M1: " + res1);
//        System.out.println("M2: " + res2);

        }
      }
    }
    if (PERF_TEST) {
      System.out.println("---" + System.lineSeparator() + "SUMMARY:");
      System.out.println(resMethod1);
      System.out.println(resMethod2);
    }
  }

  public static List<List<Integer>> fourSum1(int[] nums, int target) {
    long targetL = (long) target;
    int length = nums.length;
    Set<List<Integer>> result = new HashSet<>();

    boolean hasQuattro = targetL % 4 == 0;
    int quattro = hasQuattro ? target / 4 : 0;
    int quattroCount = 0;

    List<Integer> numList = new ArrayList<>(length);
    Map<Integer, Integer> counter = new HashMap<>(length);
    for (int i = 0; i < length; i++) {
      if (nums[i] == quattro) {
        quattroCount++;
      }
      Integer num = nums[i];
      Integer count = counter.get(num);
      if (count == null) {
        counter.put(num, 1);
      } else {
        counter.put(num, count + 1);
        if (count >= 3) {
          continue;
        }
      }
      numList.add(num);
    }

    if (hasQuattro && quattroCount >= 4) {
      result.add(List.of(quattro, quattro, quattro, quattro));
    }

    Map<Long, List<Indexes>> data = new HashMap<>(numList.size());
    for (int i = 0; i < numList.size() - 1; i++) {
      for (int j = i + 1; j < numList.size(); j++) {
        Long sum = Long.valueOf(numList.get(i) + numList.get(j));
        if (!data.containsKey(sum)) {
          data.put(sum, new ArrayList<>());
        }
        data.get(sum).add(new Indexes(i, j));
      }
    }

    int halfOfSum = target / 2;
    if (targetL % 2 == 0 && data.containsKey(halfOfSum)) {
      List<Indexes> list = data.get(halfOfSum);
      for (int i = 0; i < list.size() - 1; i++) {
        Indexes pairI = list.get(i);
        for (int j = i + 1; j < list.size(); j++) {
          addToResult(numList, pairI, list.get(j), result);
        }
      }
      data.remove(halfOfSum);
    }

    for (Map.Entry<Long, List<Indexes>> entry : data.entrySet()) {
      Long sumI = entry.getKey();
      if (sumI > halfOfSum) {
        long sumJ = targetL - sumI;
        if (data.containsKey(sumJ)) {
          for (Indexes pairI : entry.getValue()) {
            for (Indexes pairJ : data.get(sumJ)) {
              addToResult(numList, pairI, pairJ, result);
            }
          }
        }
      }
    }
    return new ArrayList<>(result);
  }

  private static void addToResult(List<Integer> numList, Indexes pairI, Indexes pairJ,
      Set<List<Integer>> result) {
    Set<Integer> indexSet = new HashSet<>();

    int a = pairI.i;
    int b = pairI.j;
    int c = pairJ.i;
    int d = pairJ.j;

    if (indexSet.add(a) && indexSet.add(b)
        && indexSet.add(c) && indexSet.add(d)) {
      result.add(
          IntStream.of(numList.get(a), numList.get(b), numList.get(c), numList.get(d)).sorted()
              .boxed().toList());
    }
  }

  public static List<List<Integer>> fourSum2(int[] nums, int target) {
    Set<List<Integer>> result = new HashSet<>();
    int length = nums.length;
    for (int i1 = 0; i1 < length - 3; i1++) {
      for (int i2 = i1 + 1; i2 < length - 2; i2++) {
        for (int i3 = i2 + 1; i3 < length - 1; i3++) {
          for (int i4 = i3 + 1; i4 < length; i4++) {
            if (nums[i1] + nums[i2] + nums[i3] + nums[i4] == target) {
              result.add(Stream.of(nums[i1], nums[i2], nums[i3], nums[i4]).sorted().toList());
            }
          }
        }
      }
    }
    return new ArrayList<>(result);
  }

  public static List<List<Integer>> fourSum3(int[] nums, int target) {
    long targetL = target;
    int length = nums.length - 1;
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < length - 2; i++) {
      int pointerI = nums[i];
      if (i != 0 && pointerI == nums[i - 1]) {
        continue;
      }
      for (int j = i+1; j < length - 1; j++) {
        int pointerJ = nums[j];
        if (j != i+1 && pointerJ == nums[j - 1]) {
          continue;
        }
        int start = j+1;
        int end = length;
        long subTarget = targetL - pointerI - pointerJ;
        while (start < end) {
          int sum = nums[start] + nums[end];
          if (sum == subTarget) {
            result.add(List.of(pointerI, pointerJ, nums[start], nums[end]));
            while (start < end && nums[start] == nums[start+1]) start++;
            start++;
            while (start < end && nums[end] == nums[end-1]) end--;
            end--;
          } else if (sum > subTarget) {
            end--;
          } else {
            start++;
          }
        }
      }

    }
    return result;
  }

  record Indexes(int i, int j) {

  }

}