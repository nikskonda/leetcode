package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC3201 {

  public static void main(String[] args) throws Exception {
    System.out.println(maximumLength(new int[]{1, 2, 3, 4}));
    System.out.println(maximumLength(new int[]{1,2,1,1,2,1,2}));
  }


  public static int maximumLength(int[] nums) {
    int case0 = 0;
    int case1 = 0;
    int case2 = 0;

    int nextCase2 = nums[0] % 2;

    for (int num : nums) {
      int value = num % 2;
      if (value == 0) {
        case0++;
        if (value == nextCase2) {
          case2++;
          nextCase2 = 1;
        }
      }
      else {
        case1++;
        if (value == nextCase2) {
          case2++;
          nextCase2 = 0;
        }
      }
    }
    return Math.max(Math.max(case0, case1), case2);

  }
}