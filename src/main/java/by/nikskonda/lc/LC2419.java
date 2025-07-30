package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC2419 {

  public static void main(String[] args) throws Exception {
    System.out.println(longestSubarray(new int[]{1, 2, 3, 3, 2, 2, 3, 3, 3, 1, 3}));
    System.out.println(longestSubarray(new int[]{3, 3, 3, 2, 2, 3}));
    System.out.println(longestSubarray(new int[]{2, 2, 2, 3}));
    System.out.println(longestSubarray(new int[]{1, 2, 3, 4}));
  }

  public static int longestSubarray(int[] nums) {
    int maxNum = 0;
    int maxLength = 0;
    int currentLength = 0;

    for (int num : nums) {
      if (num > maxNum) {
        maxNum = num;
        maxLength = currentLength = 1;
      } else if (num == maxNum) {
        maxLength = Math.max(maxLength, ++currentLength);
      } else {
        currentLength = 0;
      }
    }
    return maxLength;
  }
}