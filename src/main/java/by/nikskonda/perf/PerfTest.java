package by.nikskonda.perf;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-04-14
 */
public class PerfTest {

  public static PerformanceResult measurePerformance(Runnable method) {
    // Measure memory before execution
    Runtime runtime = Runtime.getRuntime();
    runtime.gc(); // Suggest garbage collection to minimize noise
    long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

    // Measure time before execution
    long startTime = System.nanoTime();

    // Execute the method
    method.run();

    // Measure time after execution
    long endTime = System.nanoTime();

    // Measure memory after execution
    long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

    // Calculate time and memory usage
    long timeTaken = endTime - startTime;
    long memoryUsed = memoryAfter - memoryBefore;

    return new PerformanceResult(timeTaken, memoryUsed);
  }

}
