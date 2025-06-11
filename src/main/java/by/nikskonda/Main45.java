package by.nikskonda;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main45 {

  public static void main(String[] args) {
//    System.out.println(jump(new int[]{2,3,1,1,4}));
//    System.out.println(jump(new int[]{2,3,0,1,4}));
//    System.out.println(jump(new int[]{2,3,0,1,4}));
//    System.out.println(jump(new int[]{2,3,0,1,4}));
//    System.out.println(jump(new int[]{2,1,2,2,2,1,2,1,2,1,1}));
    System.out.println(jump(new int[]{0}));
    System.out.println(jump(new int[]{2,1,3,1,2,4,1,1,1}));
    System.out.println(jump(new int[]{2,4,2,1,10,5,2,1,2,1,2,1}));
    System.out.println(jump(new int[]{2,2,3,2,1,4,1,1,1,1}));
    System.out.println(jump(new int[]{1}));
    System.out.println(jump(new int[]{1,1,1,1}));
    System.out.println(jump(new int[]{2,3,1,1,4}));
  }

  public static int jump(int[] nums) {
    int size = nums.length;
    int step = 0;
    int stepPrev = 0;
    int stepNext = 0;
    if (size == 1) {
      return 0;
    }
    for (int i = 0; i < size; i++) {
      int length = nums[i] + i;
      if (nums[i]>0 && length >= size-1) {
        return step+1;
      }
      if (stepNext < length) {
        stepNext = length;
      }
      if (i>=stepPrev) {
        stepPrev = stepNext;
        stepNext = 0;
        step++;
      }
    }
    return 0;
  }
  {

  }

}