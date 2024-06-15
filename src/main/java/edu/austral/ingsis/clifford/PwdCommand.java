package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class PwdCommand implements Command {
  private final Context context;
  private final List<String> outputs; // To store command outputs

  public PwdCommand(Context context) {
    this.context = context;
    this.outputs = new ArrayList<>();
  }

  @Override
  public void execute(String... args) {
    if (args.length > 0)
      throw new IllegalArgumentException("Pwd command does not support arguments");
    outputs.add(context.getCurrentDirectory().getPath());
  }

  @Override
  public List<String> getOutputs() {
    List<String> outputs = new ArrayList<>(this.outputs); // Return a copy to prevent modification
    this.outputs.clear(); // Clear the outputs
    return outputs;
  }
}
