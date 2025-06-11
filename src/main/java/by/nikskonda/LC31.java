package by.nikskonda;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC31 {

  public static void main(String[] args) throws Exception {

    int[] nums = {1, 3, 2, 1};
    nextPermutation(nums);
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + " ");
    }
  }

  public static void nextPermutation(int[] nums) {
    int pointer = nums.length - 1;

    int prev = nums[pointer];
    while (pointer >= 1 && nums[pointer - 1] >= prev) {
      pointer -= 1;
      prev = nums[pointer];
    }
    pointer--;
    if (pointer < 0) {
      revense(nums, 0, nums.length - 1);
    } else {
      int value = nums[pointer];
      int min = Integer.MAX_VALUE;
      int minIndex = -1;
      for (int i = pointer + 1; i < nums.length; i++) {
        if (nums[i] > value && nums[i] <= min) {
          minIndex = i;
          min = nums[i];
        }
      }
      swap(nums, pointer, minIndex);
      revense(nums, pointer + 1, nums.length - 1);
    }
  }

  private static void revense(int[] nums, int start, int end) {
    while (start < end) {
      swap(nums, start, end);
      start++;
      end--;
    }
  }

  private static void swap(int[] nums, int index1, int index2) {
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
  }
}