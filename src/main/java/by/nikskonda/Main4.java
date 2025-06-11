package by.nikskonda;

import java.time.Instant;
import java.util.Random;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main4 {

  public static void main(String[] args) {
    int[] n1 = new int[]{};
    int[] n2 = new int[]{2, 4};
    System.out.println(findMedianSortedArrays(n1, n2));
    System.out.println(findMedianSortedArrays2(n1, n2));
    n1 = new int[]{0, 0, 0};
    n2 = new int[]{-1, 0, 0, 0, 1};
    System.out.println(findMedianSortedArrays(n1, n2));
    System.out.println(findMedianSortedArrays2(n1, n2));
    n1 = new int[]{1, 3};
    n2 = new int[]{2, 4};
    System.out.println(findMedianSortedArrays(n1, n2));
    System.out.println(findMedianSortedArrays2(n1, n2));
    n1 = new int[]{1, 2};
    n2 = new int[]{3, 4};
    System.out.println(findMedianSortedArrays(n1, n2));
    System.out.println(findMedianSortedArrays2(n1, n2));
    n1 = new int[]{1, 2, 3, 4, 5};
    n2 = new int[]{6,7,8,9,10,11,12,13,14,15,16,17};
    System.out.println(findMedianSortedArrays(n1, n2));
    System.out.println(findMedianSortedArrays2(n1, n2));
    n1 = new int[]{2};
    n2 = new int[]{1, 3, 4};
    System.out.println(findMedianSortedArrays(n1, n2));
    System.out.println(findMedianSortedArrays2(n1, n2));

    Random random = new Random();
    long diff1 = 0;
    long diff2 = 0;

    for (int i = 0; i < 100; i++) {
      int n = 100000000;
      int length1 = n + random.nextInt(900);
      int length2 = n + random.nextInt(900);
      int[] nums1 = new int[length1];
      int[] nums2 = new int[length2];
      int index1 = 0;
      int index2 = 0;
      int start = (length1 + length2) / -2;
      for (int j = 0; j < length1 + length2; j++) {
        int value = start + j;
        if (index1 == length1) {
          nums2[index2++] = value;
        } else if (index2 == length2) {
          nums1[index1++] = value;
        } else if (random.nextBoolean()) {
          nums1[index1++] = value;
        } else {
          nums2[index2++] = value;
        }
      }

      System.out.println("---");
//      System.out.println(str);

      Instant startTime = Instant.now();
      double res1 = findMedianSortedArrays(nums1, nums2);
      long diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff);
      diff1 = diff1 + diff;

      startTime = Instant.now();
      double res2 = findMedianSortedArrays2(nums1, nums2);
      diff = Instant.now().toEpochMilli() - startTime.toEpochMilli();
      System.out.println(diff);
      diff2 = diff2 + diff;

//      System.out.println(res1);
//      System.out.println(res2);
      if (res1 != res2) {
        System.out.println("ERROR");
      } else {
        System.out.println("result: " + res1);
      }
    }
    System.out.println(diff1);
    System.out.println(diff2);
  }

  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int index1 = 0;
    int index2 = 0;
    int length1 = nums1.length;
    int length2 = nums2.length;

    index2 = (length1 + length2) / 2;
    if ((length1 + length2) % 2 == 0) {
      index1 = index2 - 1;
    } else {
      index1 = index2;
    }

    int index = 0;
    int res1 = Integer.MIN_VALUE;
    int res2 = Integer.MIN_VALUE;
    int i1 = 0;
    int i2 = 0;
    while (index < index2 && i1 < length1 && i2 < length2) {
      index = i1 + i2;
      if (nums1[i1] > nums2[i2]) {
        if (index == index1) {
          res1 = nums2[i2];
        }
        if (index == index2) {
          res2 = nums2[i2];
        }
        i2++;
      } else {
        if (index == index1) {
          res1 = nums1[i1];
        }
        if (index == index2) {
          res2 = nums1[i1];
        }
        i1++;
      }

    }
    if (res1 == Integer.MIN_VALUE) {
      if (length1 == i1) {
        res1 = nums2[index1 - length1];
      } else if (length2 == i2) {
        res1 = nums1[index1 - length2];
      }
    }
    if (res2 == Integer.MIN_VALUE) {
      if (length1 == i1) {
        res2 = nums2[index2 - length1];
      } else if (length2 == i2) {
        res2 = nums1[index2 - length2];
      }
    }
    return ((double) (res1 + res2)) / 2;
  }

  public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
    int length1 = nums1.length;
    int length2 = nums2.length;
    int[] nums = new int[length1 + length2];
    int i1 = 0;
    int i2 = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i1 == length1) {
        nums[i] = nums2[i2++];
      } else if (i2 == length2) {
        nums[i] = nums1[i1++];
      } else if (nums1[i1] > nums2[i2]) {
        nums[i] = nums2[i2++];
      } else {
        nums[i] = nums1[i1++];
      }
    }

    i2 = (length1 + length2) / 2;
    if ((length1 + length2) % 2 == 0) {
      i1 = i2 - 1;
    } else {
      i1 = i2;
    }
    return ((double) (nums[i1] + nums[i2])) / 2;
  }

//  public static int binS(int[] nums, int start, int end, int element) {
//
//  }
}