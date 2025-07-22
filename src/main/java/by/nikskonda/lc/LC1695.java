package by.nikskonda.lc;

import java.util.HashSet;
import java.util.Set;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC1695 {

  public static void main(String[] args) throws Exception {
    System.out.println(maximumUniqueSubarray(new int[]{1, 3, 2, 4, 2, 3, 5, 3, 4, 2}));
  }


  public static int maximumUniqueSubarray(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length / 2);
    int i = 0;
    int j = 1;
    set.add(nums[i]);
    int result = nums[i];
    int counter = result;
    while (j < nums.length) {
      //the end of uniques
      if (set.contains(nums[j])) {
        result = Math.max(result, counter);
        while (true) {
          counter -= nums[i];
          set.remove(nums[i]);
          i++;
          if (nums[i-1] == nums[j]) break;
        }
      } else {
        set.add(nums[j]);
        counter += nums[j];
        j++;
      }
    }
    result = Math.max(result, counter);
    return result;
  }

}