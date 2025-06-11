package by.nikskonda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC35 {

  public static void main(String[] args) throws Exception {
    System.out.println(searchInsert(new int[]{0, 1, 2, 3, 5}, 6));

  }

  public static int searchInsert(int[] nums, int target) {
     return binSearch(nums, target, 0, nums.length - 1);
  }

  private static int binSearch(int[] nums, int target, int start, int end) {
    if (target <= nums[start]) return start;
    if (target > nums[end]) return end+1;

    int mid = (start + end) / 2;
    if (target == nums[mid]) return mid;

    if (target < nums[mid]) {
      return binSearch(nums, target, start + 1, mid - 1);
    } else {
      return binSearch(nums, target, mid + 1, end);
    }
  }
}