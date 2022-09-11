package com.ua.exception.common;

public class ExecutionFailed extends RuntimeException {

  public ExecutionFailed() {
    super("Execution failed");
  }

  public ExecutionFailed(String message) {
    super(message);
  }
}
