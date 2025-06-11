package by.nikskonda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC39 {

  public static void main(String[] args) throws Exception {
    System.out.println(combinationSum(new int[]{3, 2, 5, 8}, 8));

  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);

    List<List<Integer>> result = new ArrayList<>();

    int length = candidates.length - 1;
    recurcy(candidates, length, target, new ArrayList<>(), result);
    return result;
  }

  private static void recurcy(int[] candidates, int length, int tempTarget, List<Integer> temp,
      List<List<Integer>> result) {
    int curr = candidates[length];
    int div = tempTarget / curr;
    if (length == 0) {
      if (tempTarget % curr == 0) {
        List<Integer> newList = new ArrayList<>(temp);
        addNumbers(div, newList, curr);
        result.add(newList);
      }
      return;
    }
    for (int j = div; j >= 0; j--) {
      List<Integer> newList = new ArrayList<>(temp);
      addNumbers(j, newList, curr);
      int newTarget = tempTarget - j * curr;
      if (newTarget == 0) {
        result.add(newList);
      } else {
        recurcy(candidates, length - 1, newTarget, newList, result);
      }
    }
  }

  private static void addNumbers(int count, List<Integer> newList, int num) {
    int counter = 0;
    while (counter < count) {
      newList.add(num);
      counter++;
    }
  }


}