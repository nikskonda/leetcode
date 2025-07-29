package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC2044 {

  public static void main(String[] args) throws Exception {
    System.out.println(countMaxOrSubsets(new int[]{3, 2, 1, 5}));
  }

  public static int countMaxOrSubsets(int[] nums) {
    int maxValue = 0;
    for (int num : nums) {
      maxValue |= num;
    }

    int result = 1;


    return maxValue;
  }
}