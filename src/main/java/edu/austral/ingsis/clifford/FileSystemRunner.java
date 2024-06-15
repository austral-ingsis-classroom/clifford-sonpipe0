package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FileSystemRunner {
  private final CLI cli;

  public FileSystemRunner() {
    this.cli = new CLI();
  }

  public List<String> executeCommands(List<Map.Entry<String, String>> commandsAndResults) {
    List<String> results = new ArrayList<>();
    for (Map.Entry<String, String> entry : commandsAndResults) {
      String command = entry.getKey();
      String expectedResult = entry.getValue();

      // Reset CLI state before each command

      // Execute the command
      String output = cli.executeCommand(command);

      // Capture the output
      results.add(output);

      // Validate against expected result
      validateResult(command, Collections.singletonList(output), expectedResult);
    }
    return results;
  }

  private void validateResult(String command, List<String> actualOutputs, String expectedResult) {
    if (!actualOutputs.contains(expectedResult)) {
      System.out.println("Test failed for command: " + command);
      System.out.println("Expected: " + expectedResult);
      System.out.println("Actual:   " + String.join(" ", actualOutputs));
    }
  }
}
