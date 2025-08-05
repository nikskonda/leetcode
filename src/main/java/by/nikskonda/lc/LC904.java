package by.nikskonda.lc;

import java.util.HashSet;
import java.util.Set;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC904 {

  public static void main(String[] args) throws Exception {
    System.out.println(totalFruit(new int[]{0}));
    System.out.println(totalFruit(new int[]{0, 1, 0, 2}));
    System.out.println(totalFruit(new int[]{1,2,3,2,2}));
    System.out.println(totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));
  }

  public static int totalFruit(int[] fruits) {
    int typeA = fruits[0];
    int typeB = -1;
    int firstA = 0;
    int lastA = 0;
    int firstB = 0;
    int lastB = -1;
    int result = 1;
    int cursor = 1;

    while (cursor < fruits.length) {
      int fruit = fruits[cursor];
      if (fruit != typeA && fruit != typeB) {
        result = Math.max(result, cursor - Math.min(firstA, firstB));
        if (lastA < lastB) {
           typeA = fruit;
          firstA = cursor;
          firstB = lastA + 1;
          lastA = firstA;
        } else {
          typeB = fruit;
          firstB = cursor;
          firstA = lastB + 1;
          lastB = firstB;
        }
      } else {
        if (fruit == typeA) lastA = cursor;
        else lastB = cursor;
      }
      cursor++;
    }
    if (firstA < firstB) {
      result = Math.max(result, cursor - firstA);
    } else {
      result = Math.max(result, cursor - firstB);
    }
    return result;
  }
}