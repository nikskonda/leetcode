package by.nikskonda.lc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.w3c.dom.ls.LSOutput;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC898 {

  public static void main(String[] args) throws Exception {
    System.out.println(subarrayBitwiseORs(new int[]{1, 2, 5}));
    System.out.println(subarrayBitwiseORs(new int[]{1, 2, 3, 5}));
    System.out.println(subarrayBitwiseORs(new int[]{1, 2, 3, 4}));
    System.out.println(subarrayBitwiseORs(new int[]{1, 2, 4}));
    System.out.println(subarrayBitwiseORs(new int[]{1}));
  }

  public static int subarrayBitwiseORs(int[] nums) {
    Set<Integer> answer = new HashSet<>();
    Set<Integer> active = new HashSet<>();
    Set<Integer> temp;

    for (int num : nums) {
      temp = new HashSet<>();
      for (Integer act : active) {
        Integer or = act | num;
        if (!answer.contains(or)) {
          temp.add(or);
        }
      }
      temp.add(num);
      active = temp;
      answer.addAll(temp);
    }
    return answer.size();
  }
}