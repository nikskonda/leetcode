package by.nikskonda.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC1865 {

  public static void main(String[] args) throws Exception {
    FindSumPairs test = new FindSumPairs(
        new int[]{1, 1, 2, 2, 2, 3},
        new int[]{1, 4, 5, 2, 5, 4}
    );
    System.out.println(test.count(7));
    test.add(3, 2);
    System.out.println(test.count(8));
    System.out.println(test.count(4));
    test.add(0, 1);
    test.add(1, 1);
    System.out.println(test.count(7));

    test = new FindSumPairs(
        new int[]{9, 70, 14, 9, 76},
        new int[]{26, 26, 58, 23, 74, 68, 68, 78, 58, 26}
    );
    test.add(6, 10);
    test.add(5, 6);
    System.out.println(test.count(32));
    test.add(3, 55);
    test.add(9, 32);
    test.add(9, 16);
    test.add(1, 48);
    test.add(1, 4);
    test.add(0, 52);
    test.add(8, 20);
    test.add(9, 4);
    System.out.println(test.count(88));
  }


  public static class FindSumPairs {

    private int[] array1;
    private int[] array2;
    //value -> index
    private Map<Integer, Integer> data1 = new HashMap<>();
    private Map<Integer, Integer> data2 = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
      array1 = nums1;
      array2 = nums2;
      initMap(array1, data1);
      initMap(array2, data2);
    }

    private void initMap(int[] array, Map<Integer, Integer> dataMap) {
      for (int num : array) {
        addToMap(dataMap, num);
      }
    }

    public void add(int index, int val) {
      Integer value = array2[index];
      Integer newValue = value + val;
      array2[index] = newValue;
      Integer set = data2.get(value);
      if (set == 1) {
        data2.remove(value);
      } else {
        data2.put(value, set - 1);
      }
      addToMap(data2, newValue);
    }

    public int count(int tot) {
      int result = 0;
      Map<Integer, Integer> smallMap = data1.size() > data2.size() ? data2 : data1;
      Map<Integer, Integer> bigMap = data1.size() > data2.size() ? data1 : data2;

      for (Entry<Integer, Integer> entry : smallMap.entrySet()) {
        Integer value2 = entry.getKey();
        Integer value1 = tot - value2;
        if (bigMap.containsKey(value1)) {
          result += (bigMap.get(value1) * entry.getValue());
        }
      }
      return result;
    }


    private void addToMap(Map<Integer, Integer> dataMap, Integer value) {
      if (!dataMap.containsKey(value)) {
        dataMap.put(value, Integer.valueOf(0));
      }
      dataMap.put(value, dataMap.get(value) + 1);
    }
  }

  public static class FindSumPairs2 {

    private int[] array1;
    private int[] array2;
    //value -> index
    private Map<Integer, Integer> data2;

    public FindSumPairs2(int[] nums1, int[] nums2) {
      data2 = new HashMap<>(nums2.length);
      array1 = nums1;
      array2 = nums2;
      for (int num : array2) {
        data2.put(num, data2.getOrDefault(num, 0) + 1);
      }
    }


    public void add(int index, int val) {
      Integer value = array2[index];
      array2[index] = value + val;
      data2.put(value, data2.get(value) - 1);
      data2.put(array2[index], data2.getOrDefault(array2[index], 0) + 1);
    }

    public int count(int tot) {
      int result = 0;
      for (int num : array1) {
        Integer value1 = tot - num;
        result += data2.getOrDefault(value1, 0);
      }
      return result;
    }
  }
}