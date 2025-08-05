package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC3477 {

  public static void main(String[] args) throws Exception {
    System.out.println(numOfUnplacedFruits(new int[]{1,8,9,8,5,6,5}, new int[]{6,3,6,1,1,8,2}));
  }

  public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    int result = 0;
    int left = 0;

    for (int fruit : fruits) {
      boolean found = false;
      for (int i = left; i < baskets.length; i++) {
        int basket = baskets[i];
        if (basket >= fruit) {
          baskets[i] = 0;
          found = true;
          break;
        }
      }
      if (!found)
        result++;
    }

    return result;
  }
}