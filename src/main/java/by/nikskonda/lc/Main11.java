package by.nikskonda.lc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main11 {

  public static void main(String[] args) {
    System.out.println(maxArea2(new int[]{1,1000,1000,6,2,5,4,8,3,7}));

    Random random = new Random();
    long diff1 = 0;
    long diff2 = 0;

    for (int i = 0; i < 100; i++) {
//      int length = 100000 + random.nextInt(100);
      int length = 100000;

      int[] array = new int[length];
      for (int j = 0; j < length; j++) {
        array[j] = random.nextInt(1000) + 1;
      }

      System.out.println("---");
//      System.out.println(str);

      Instant startTime = Instant.now();
      int res1 = maxArea(array);
      long diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff);
      diff1 = diff1 + diff;

      startTime = Instant.now();
      int res2 = maxArea2(array);
      diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff);
      diff2 = diff2 + diff;

//      System.out.println(res1);
//      System.out.println(res2);
      if (res1 != res2) {
        System.out.printf("ERROR: %d != %d %s", res1, res2, System.lineSeparator());
      } else {
        System.out.println("result: " + res1);
      }
    }
    System.out.println(diff1);
    System.out.println(diff2);
  }
//  12322435
//  01234567
//  123
//    32
//      2435

  public static int maxArea(int[] height) {
    int result = 0;
    int size = height.length;
    List<Data> list = new ArrayList<>(size);
    IndexList indexList = new IndexList(size);

    for (int i = 0; i < size; i++) {
      list.add(new Data(height[i], i));
    }
    list.sort((o1, o2) -> {
      int diff = o1.value - o2.value;
      return diff != 0 ? diff : o1.index - o2.index;
    });

    for (Data data : list) {
      int maxRange = Math.max(data.index - indexList.getLeftIndex(),
          indexList.getRightIndex() - data.index);
      result = Math.max(result, data.value * maxRange);
      indexList.remove(data.index);
    }
    return result;
  }

  public static int maxArea2(int[] height) {
    int result = 0;

    int left = 0;
    int right = height.length - 1;

    while (left<right) {
      int minHeight = Math.min(height[left], height[right]);
      result = Math.max(result, minHeight * (right - left));

      if (height[left] > height[right]) {
        right--;
      } else {
        left++;
      }
    }
    return result;
  }

  private static int calc(Data left, Data right) {
    int height = Math.min(left.value, right.value);
    return height * (right.index - left.index);
  }

  public static class Data {

    int value;
    int index;

    public Data(int value, int index) {
      this.value = value;
      this.index = index;
    }

    @Override
    public String toString() {
      return String.format("%d: %d", index, value);
    }
  }

  public static class IndexList {

    private boolean[] array;
    private int leftIndex;
    private int rightIndex;

    public IndexList(int size) {
      array = new boolean[size];
      leftIndex = 0;
      rightIndex = size - 1;
    }

    public int getLeftIndex() {
      while (array[leftIndex]) {
        leftIndex++;
      }
      return leftIndex;
    }

    public int getRightIndex() {
      while (array[rightIndex]) {
        rightIndex--;
      }
      return rightIndex;
    }

    public void remove(int index) {
      array[index] = true;
    }
  }
}