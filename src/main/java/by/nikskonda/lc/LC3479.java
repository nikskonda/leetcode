package by.nikskonda.lc;

import java.util.LinkedList;
import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC3479 {

  public static void main(String[] args) throws Exception {
    System.out.println(numOfUnplacedFruits(new int[]{1,8,9,8,5,6,5}, new int[]{6,3,6,1,1,8,2}));
    System.out.println(numOfUnplacedFruits(new int[]{96, 27}, new int[]{17, 44}));
  }

  public static int numOfUnplacedFruits0(int[] fruits, int[] baskets) {
    int result = 0;

    int cacheSize = Math.min(baskets.length, 64);
    int[] cache = new int[cacheSize];
    int segmentSize = (baskets.length % cacheSize == 0 ? 0 : 1) + baskets.length / cacheSize;

    for (int i = 0; i < baskets.length; i++) {
      int cell = i / segmentSize;
      cache[cell] = Math.max(cache[cell], baskets[i]);
    }

    for (int fruit : fruits) {
      boolean found = false;
      int seg = -1;
      for (int i = 0; i < cache.length; i++) {
        if (cache[i] >= fruit) {
          seg = i;
          break;
        }
      }
      if (seg >= 0) {
        cache[seg] = 0;
        int len = Math.min(baskets.length, segmentSize * (seg + 1));
        int maxValue = 0;
        for (int i = segmentSize * seg; i < len; i++) {
          int basket = baskets[i];
          if (basket >= fruit && !found) {
            baskets[i] = 0;
            found = true;
          } else {
            maxValue = Math.max(maxValue, basket);
          }
        }
        cache[seg] = maxValue;
      }

      if (!found) {
        result++;
      }
    }

    return result;
  }

  public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    int result = 0;

    int cacheSize = Math.min(baskets.length, 300);
    int[] cacheMax = new int[cacheSize];
    List<Integer>[] cachedBackets = new LinkedList[cacheSize];
    int segmentSize = (baskets.length % cacheSize == 0 ? 0 : 1) + baskets.length / cacheSize;

    for (int i = 0; i < cacheSize; i++) {
      cachedBackets[i] = new LinkedList<>();
    }
    for (int i = 0; i < baskets.length; i++) {
      int cell = i / segmentSize;
      int basketValue = baskets[i];
      cachedBackets[cell].add(basketValue);
      cacheMax[cell] = Math.max(cacheMax[cell], basketValue);
    }

    for (int fruit : fruits) {
      int seg = -1;
      for (int i = 0; i < cacheMax.length; i++) {
        if (cacheMax[i] >= fruit) {
          seg = i;
          break;
        }
      }
      if (seg >= 0) {
        cacheMax[seg] = 0;
        int maxValue = 0;
        int index = -1;
        int i = 0;
        for (Integer b : cachedBackets[seg]) {
          if (b >= fruit && index == -1) {
            index = i;
          } else {
            maxValue = Math.max(maxValue, b);
          }
          i++;
        }
        cachedBackets[seg].remove(index);
        cacheMax[seg] = maxValue;
      } else {
        result++;
      }
    }

    return result;
  }
}