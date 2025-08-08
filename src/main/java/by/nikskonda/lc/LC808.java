package by.nikskonda.lc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC808 {

  public static void main(String[] args) throws Exception {
    System.out.println(soupServings(1));
    System.out.println(soupServings(200));
    System.out.println(soupServings(660295675));

  }
  private static Map<Integer, Map<Integer, Double>> cache = new HashMap<>();

  public static double soupServings(int n) {
    n = n%25 == 0 ? n : (n+25)/25*25;
    cache.put(0, new HashMap<>());
    cache.get(0).put(0, 0.5);
    for (int a = 25; a <= n; a+=25) {
      cache.put(a, new HashMap<>());
      cache.get(0).put(a, 1.0);
      cache.get(a).put(0, 0.0);

      for (int b = 25; b <= a; b+=25) {
        cache.get(a).put(b, calc(a, b));
        cache.get(b).put(a, calc(b, a));
      }
      if (cache.get(a).get(a) > 0.999995) {
        return 1;
      }
    }
    return cache.get(n).get(n);
  }

  private static double calc(int a, int b) {
    return
        (cache.get(Math.max(0, a-100)).get(b) +
            cache.get(Math.max(0, a-75)).get(Math.max(b-25, 0)) +
            cache.get(Math.max(0, a-50)).get(Math.max(b-50, 0)) +
            cache.get(Math.max(0, a-25)).get(Math.max(b-75, 0)))
            /4;
  }



}