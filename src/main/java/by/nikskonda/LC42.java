package by.nikskonda;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC42 {

  public static void main(String[] args) throws Exception {
    System.out.println(trap2(new int[]{0, 2, 4, 2, 0, 3, 2, 5, 2, 1, 3}));
    System.out.println(trap(new int[]{0, 2, 4, 2, 0, 3, 2, 5, 2, 1, 3}));

    System.out.println(trap2(new int[]{6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3}));
    System.out.println(trap(new int[]{6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3}));
  }

  public static int trap2(int[] height) {
    Deque<Height> list = new LinkedList<>();
    int start = 0;
    while (start + 1 < height.length) {
      if (height[start + 1] > height[start]) {
        start++;
      } else {
        break;
      }
    }
    if (start == height.length - 1) {
      return 0;
    }
    int prevValue = height[start];
    list.add(new Height(prevValue, start));
    for (int i = start + 1; i < height.length; i++) {
      int currentValue = height[i];
      if (prevValue < currentValue) {
        while (list.size() > 1) {
          Height prev = list.removeLast();
          Height prevPrev = list.getLast();
          if (prevPrev.value > prev.value && prev.value <= currentValue) {
            continue;
          } else {
            list.add(prev);
            break;
          }
        }
        list.add(new Height(currentValue, i));
      }
      prevValue = currentValue;
    }
    if (list.size() == 1) {
      return 0;
    }

    int result = 0;
    Height prevHeight = list.removeFirst();
    while (!list.isEmpty()) {
      Height currentHeight = list.removeFirst();
      int level = Math.min(prevHeight.value, currentHeight.value);
      for (int i = prevHeight.position + 1; i < currentHeight.position; i++) {
        int temp = level - height[i];
        result += temp > 0 ? temp : 0;
      }
      prevHeight = currentHeight;
    }
    return result;
  }

  public static int trap(int[] height) {
    if (height.length < 3) return 0;
    Deque<Integer> list = new LinkedList<>();
    int left = 0;
    int right = 1;

    while (left < right) {
      if (height[right] < height[right + 1]) {
        right++;
      } else {

      }
    }



  }

  public record Height(int value, int position) {

  }
}