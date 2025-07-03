package by.nikskonda.lc;

import by.nikskonda.perf.PerfTest;
import by.nikskonda.perf.PerformanceResult;
import java.util.Random;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class BaseMain {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 1_000_000;
  private static final boolean PERF_TEST = false;

  public static void main(String[] args) throws Exception {

    Random random = new Random();
    PerformanceResult resMethod1 = new PerformanceResult();
    PerformanceResult resMethod2 = new PerformanceResult();

    for (int i = 0; i < ITERATIONS; i++) {
      int length = LENGTH + random.nextInt(LENGTH);
      float[] input = new float[length];
      float threshold = random.nextFloat(100);
      for (int j = 0; j < length; j++) {
        input[j] = random.nextFloat(threshold * 2);
      }

      System.out.println("---");

      if (PERF_TEST) {
        PerformanceResult result = PerfTest.measurePerformance(() -> method1());
        resMethod1.add(result);
        System.out.println("M1: " + result);

        result = PerfTest.measurePerformance(() -> method2());
        resMethod2.add(result);
        System.out.println("M2: " + result);
      } else {
        int res1 = method1();
        System.out.println("M1: " + res1);
        int res2 = method2();
        System.out.println("M2: " + res2);
        if (res1 != res2) {
          System.out.println("ERROR");
        }
      }
    }
  }

  public static int method1() {
    return 1;
  }

  public static int method2() {
    return 2;
  }
}