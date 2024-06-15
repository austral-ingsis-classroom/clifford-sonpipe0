package edu.austral.ingsis.clifford;

import java.util.List;

public interface Command {
  void execute(String... args);

  List<String> getOutputs();
}
