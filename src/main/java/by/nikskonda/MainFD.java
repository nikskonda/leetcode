package by.nikskonda;

import by.nikskonda.perf.PerfTest;
import by.nikskonda.perf.PerformanceResult;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class MainFD {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 100_000;
  private static final boolean PERF_TEST = true;

  public static void main(String[] args) throws Exception {

    Random random = new Random();
    PerformanceResult resMethod1 = new PerformanceResult();
    PerformanceResult resMethod2 = new PerformanceResult();

    for (int i = 0; i < ITERATIONS; i++) {
      int length = LENGTH + random.nextInt(LENGTH);
      float[] input = new float[length];
      float threshold = random.nextFloat(100);
      for (int j = 0; j < length; j++) {
        input[j] = random.nextFloat(threshold * 2);
      }

      System.out.println("---");

      if (PERF_TEST) {
        PerformanceResult result = PerfTest.measurePerformance(() -> method(input, threshold));
        resMethod1.add(result);
        System.out.println("M1: " + result);

        result = PerfTest.measurePerformance(() -> method2(input, threshold));
        resMethod2.add(result);
        System.out.println("M2: " + result);
      } else {
        int[] res1 = method(input, threshold);
        System.out.println("M1: " + res1[0] + " " + res1[1] + " " + res1[2]);
        int[] res2 = method2(input, threshold);
        System.out.println("M2: " + res2[0] + " " + res2[1] + " " + res2[2]);
        if (!Arrays.equals(res1, res2)) {
          System.out.println("ERROR");
        }
      }
    }
    System.out.println("Summary1: " + resMethod1);
    System.out.println("Summary2: " + resMethod2);

  }

  public static int[] method(float[] input, float threshold) {
    if (input.length == 0) {
      return new int[]{-1, -1, 0};
    }
    float min;
    float max;

    int start = 0;
    int maxSize = 1;

    for (int i = 0; i < input.length - 1; i++) {
      int j = i;
      min = Integer.MAX_VALUE;
      max = Integer.MIN_VALUE;
      int size = 0;
      while (j < input.length) {
        min = Math.min(min, input[j]);
        max = Math.max(max, input[j]);
        if (max - min > threshold) {
          break;
        }
        size++;
        j++;
      }
      if (size >= maxSize) {
        start = i;
        maxSize = size;
      }
    }
    return new int[]{start, start + maxSize - 1, maxSize};
  }

  public static int[] method2(float[] input, float threshold) {
    if (input.length == 0) {
      return new int[]{-1, -1, 0};
    }
    TreeSet<Pair> sequence = new TreeSet<>((p1, p2) -> {
      if (p1.value == p2.value) {
        return Integer.compare(p1.index, p2.index);
      } else {
        return Float.compare(p1.value, p2.value);
      }
    });

    int start = 0;
    int maxSize = 1;

    int left = 0;
    int right = 0;
    sequence.add(new Pair(input[left], left));

    while (right + 1 < input.length) {
      right++;
      float value = input[right];
      while (sequence.first().index < left) {
        sequence.pollFirst();
      }
      float min = sequence.first().value;
      while (sequence.last().index < left) {
        sequence.pollLast();
      }
      float max = sequence.last().value;

      if (value < min && max - value > threshold) {
        while (!sequence.isEmpty() && (sequence.last().value - value > threshold
            || left > sequence.last().index)) {
          Pair pair = sequence.pollLast();
          left = Math.max(pair.index + 1, left);
        }
        if (sequence.isEmpty()) {
          left = right;
        }

      } else if (value > max && value - min > threshold) {
        while (!sequence.isEmpty() && (value - sequence.first().value > threshold
            || left > sequence.first().index)) {
          Pair pair = sequence.pollFirst();
          left = Math.max(pair.index + 1, left);
        }
        if (sequence.isEmpty()) {
          left = right;
        }

      }
      sequence.add(new Pair(value, right));
      if (right - left + 1 >= maxSize) {
        maxSize = right - left + 1;
        start = left;
      }

    }
    return new int[]{start, start + maxSize - 1, maxSize};
  }

  public record Pair(
      float value,
      int index
  ) {

  }
}