package by.nikskonda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class MainBS {

  public static void main(String[] args) throws Exception {
    List<Integer> list = List.of(1, 3, 5, 7, 9);
    for (int i = 0; i < 11; i++) {
      System.out.println(i + " " + binarySearch(0, list.size()-1, i, list));
    }

  }
  private static int binarySearch(int start, int end, int value, List<Integer> list) {
    if (list.get(start) > value) return start - 1;
    if (list.get(end) < value) return end;

    if (start == end){
     return start;
    }

    int mid = (start + end)/2;
    if (list.get(mid) == value) return mid;

    if (list.get(mid) > value) {
      return binarySearch(start, mid-1, value, list);
    } else {
      return binarySearch(mid+1, end, value, list);
    }
  }
}