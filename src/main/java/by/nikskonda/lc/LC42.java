package by.nikskonda.lc;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC42 {

  public static void main(String[] args) throws Exception {
    System.out.println(trap2(new int[]{5,4,1,2}));
    System.out.println(trap(new int[]{5,4,1,2}));

    System.out.println(trap2(new int[]{5,5,1,7,1,1,5,2,7,6}));
    System.out.println(trap(new int[]{5,5,1,7,1,1,5,2,7,6}));

    System.out.println(trap2(new int[]{0, 2, 4, 2, 0, 3, 2, 5, 2, 1, 3}));
    System.out.println(trap(new int[]{0, 2, 4, 2, 0, 3, 2, 5, 2, 1, 3}));

    System.out.println(trap2(new int[]{6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3}));
    System.out.println(trap(new int[]{6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3}));
  }

  public static int trap2(int[] height) {
    Deque<Height> list = new LinkedList<>();
    int[] volumes = new int[height.length];
    int start = 0;
    volumes[0] = height[0];
    while (start + 1 < height.length) {
      if (height[start + 1] > height[start]) {
        start++;
        volumes[start] = volumes[start-1]+height[start];
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
      volumes[i] = volumes[i-1]+currentValue;
      if (prevValue < currentValue) {
        while (list.size() > 1) {
          Height prev = list.removeLast();
          Height prevPrev = list.getLast();
          if (prevPrev.value >= prev.value && prev.value <= currentValue) {
            continue;
          } else {
            list.add(prev);
            break;
          }
        }
        list.add(new Height(currentValue, i));
      } else {
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
      int positions = currentHeight.position - 1 - prevHeight.position;
      if (positions > 0) {
        int level = Math.min(prevHeight.value, currentHeight.value);
        int volume = volumes[currentHeight.position-1] - volumes[prevHeight.position];
        result += positions * level - volume;
      }
      prevHeight = currentHeight;
    }
    return result;
  }
  public record Height(int value, int position) { }

  public static int trap(int[] height) {
    if (height.length < 3) return 0;

    int result = 0;
    int left = 0;
    int right = height.length - 1;

    while (left+1 <= right && height[left] <= height[left + 1]) {
      left++;
    }
    while (right-1 >= left && height[right - 1] >= height[right]) {
      right--;
    }
    int level = Math.min(height[left], height[right]);

    int coursor = 0;

    if (height[left] < height[right]) {
      coursor = left+1;
    } else {
      coursor = right-1;
    }

    boolean needRecalc = false;
    while (left < coursor && coursor < right) {
      if (height[left] < height[right] && height[coursor] >= height[left]) {
        left = coursor;
        needRecalc = true;
      } else if (height[left] >= height[right] && height[coursor] >= height[right]) {
        right = coursor;
        needRecalc = true;
      } else {
        int temp = height[coursor] > level ? 0 : level - height[coursor];
        result += temp;
      }
      if (needRecalc) {
        level = Math.min(height[left], height[right]);
        if (height[left] < height[right]) {
          coursor = left+1;
        } else {
          coursor = right-1;
        }
        needRecalc = false;
      } else {
        if (height[left] < height[right]) {
          coursor++;
        } else {
          coursor--;
        }
      }
    }
    return result;
  }


}