package by.nikskonda.lc;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main0 {

  public static void main(String[] args) {
    //Test case 1
    float[] input1 = new float[]{1.0f, 1.4f, 1.1f, 1.6f, 1.2f};
    float threshold1 = 0.4f;
    System.out.println(Arrays.equals(new int[]{0, 2, 3}, method2(input1, threshold1)));

    //Test case 2
    float[] input2 = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    float threshold2 = 0.1f;
    System.out.println(Arrays.equals(new int[]{0, 3, 4}, method2(input2, threshold2)));

    //Test case 3
    float[] input3 = new float[]{1.5f};
    float threshold3 = 0.01f;
    System.out.println(Arrays.equals(new int[]{0, 0, 1}, method2(input3, threshold3)));

    //Test case 4
    float[] input4 = new float[]{};
    float threshold4 = 0f;
    System.out.println(Arrays.equals(new int[]{-1, -1, 0}, method2(input4, threshold4)));

    //Test case 5
    float[] input5 = new float[]{100.0f, 102.0f, 101.0f, 105.0f, 104.0f, 103.0f, 107.0f, 106.0f};
    float threshold5 = 5.0f;
    System.out.println(Arrays.equals(new int[]{0, 5, 6}, method2(input5, threshold5)));

    //Test case 6
    float[] input6 = new float[]{1.0f, 1.2f, 1.4f, 1.6f, 1.8f};
    float threshold6 = 0.4f;
    System.out.println(Arrays.equals(new int[]{2, 4, 3}, method2(input6, threshold6)));

    //Test case 7
    float[] input7 = new float[]{1.0f, 1.2f, 1.4f, 2.0f, 1.0f, 1.2f, 1.4f};
    float threshold7 = 0.4f;
    System.out.println(Arrays.equals(new int[]{4, 6, 3}, method2(input7, threshold7)));

    //Test case 8
    float[] input8 = new float[]{1.0f, 1.2f, 1.5f, 1.9f, 2.3f, 1.0f, 1.3f, 1.5f, 2.5f};
    float threshold8 = 0.5f;
    System.out.println(Arrays.equals(new int[]{5, 7, 3}, method2(input8, threshold8)));

    //Test case 9
    float[] input9 = new float[]{1.0f, 1.2f, 1.5f, 1.9f, 2.3f, 1.0f, 1.3f, 1.5f, 2.5f, 1.0f, 1.1f,
        1.2f};
    float threshold9 = 0.5f;
    System.out.println(Arrays.equals(new int[]{9, 11, 3}, method2(input9, threshold9)));

    //Test case 10
    float[] input10 = new float[]{100.0f, 102.0f, 101.0f, 105.0f, 104.0f, 103.0f, 107.0f, 106.0f,
        104.44f, 103.24f, 102.01f, 104.2f, 112.02f, 115f, 114.2f};
    float threshold0 = 5.0f;
    System.out.println(Arrays.equals(new int[]{3, 11, 9}, method2(input10, threshold0)));


    Random random = new Random();
    long diff1 = 0;
    long diff2 = 0;

    for (int i = 0; i<100; i++) {
      int length = 1000000 + random.nextInt(100000);
      float[] input = new float[length];
      float threshold = random.nextFloat(100);

      for (int j = 0; j<length; j++) {
        input[j] = random.nextFloat(threshold * 2);
      }

      System.out.println("---");

      Instant startTime = Instant.now();
      int[] res1 = method(input, threshold);
      long diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff + "   result: " + res1[0] + " " + res1[1] + " " + res1[2]);
      diff1=diff1+diff;

      startTime = Instant.now();
      int[] res2 = method2(input, threshold);
      diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff + "   result: " + res2[0] + " " + res2[1] + " " + res2[2]);
      diff2=diff2+diff;

      if (!Arrays.equals(res1, res2)) {
        System.out.println("ERROR");
      }
    }
    System.out.println(diff1);
    System.out.println(diff2);
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
        while (!sequence.isEmpty() && (sequence.last().value - value > threshold || left > sequence.last().index)) {
          Pair pair = sequence.pollLast();
          left = Math.max(pair.index+1, left);
        }
        if (sequence.isEmpty()) left = right;

      } else
      if (value > max && value - min > threshold) {
        while (!sequence.isEmpty() && (value - sequence.first().value > threshold || left > sequence.first().index)) {
          Pair pair = sequence.pollFirst();
          left = Math.max(pair.index + 1, left);
        }
        if (sequence.isEmpty()) left = right;

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