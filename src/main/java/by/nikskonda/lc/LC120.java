package by.nikskonda.lc;

import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC120 {

  public static void main(String[] args) throws Exception {
    System.out.println(minimumTotal(
        List.of(List.of(-7),
            List.of(-2, 1),
            List.of(-5, -5, 9),
            List.of(-4, -5, 4, 4),
            List.of(-6, -6, 2, -1, -5),
            List.of(3, 7, 8, -3, 7, -9),
            List.of(-9, -1, -9, 6, 9, 0, 7),
            List.of(-7, 0, -6, -8, 7, 1, -4, 9),
            List.of(-3, 2, -6, -9, -7, -6, -9, 4, 0),
            List.of(-8, -6, -3, -9, -2, -6, 7, -5, 0, 7),
            List.of(-9, -1, -2, 4, -2, 4, 4, -1, 2, -5, 5),
            List.of(1, 1, -6, 1, -2, -4, 4, -2, 6, -6, 0, 6),
            List.of(-3, -3, -6, -2, -6, -2, 7, -9, -5, -7, -5, 5, 1)
    )));
  }

  public static int minimumTotal(List<List<Integer>> triangle) {
    int size = triangle.size();
    Integer[] array = new Integer[size];
    array[0] = triangle.get(0).get(0);

    Integer min = array[0];

    for (int i = 1; i < size; i++) {
      List<Integer> curLine = triangle.get(i);
      int firstElem = curLine.get(0) + array[0];
      int lastElem = curLine.get(i) + array[i - 1];
      min = Math.min(firstElem, lastElem);
      int temp = 0;
      for (int j = 1; j < i; j++) {
        Integer curValue = curLine.get(j) + Math.min(array[j], array[j - 1]);
        min = Math.min(curValue, min);
        if (j>1) {
          array[j-1] = temp;
        }
        temp = curValue;
      }
      array[i-1] = temp;
      array[0] = firstElem;
      array[i] = lastElem;
    }
    return min;
  }

}