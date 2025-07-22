package by.nikskonda.perf;

import lombok.Data;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-04-14
 */
@Data
public class PerformanceResult {
  private long time;   // Time in nanoseconds
  private long memory; // Memory in bytes

  public PerformanceResult(long time, long memory) {
    this.time = time;
    this.memory = memory;
  }

  public PerformanceResult() {
    this.time = 0;
    this.memory = 0;
  }

  public void add (PerformanceResult result) {
    time+=result.time;
    memory+=result.memory;
  }

  @Override
  public String toString() {
    return String.format("Perf Result: Time %d Memory %d", time, memory);
  }
}
