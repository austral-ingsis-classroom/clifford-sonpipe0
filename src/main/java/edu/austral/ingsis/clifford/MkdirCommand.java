package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class MkdirCommand implements Command {
  private final Context context;
  private final List<String> outputs; // To store command outputs

  public MkdirCommand(Context context) {
    this.context = context;
    this.outputs = new ArrayList<>();
  }

  @Override
  public void execute(String... args) {
    String dirName = args[0];
    Directory currentDirectory = context.getCurrentDirectory();
    Directory newDirectory = new Directory(dirName, currentDirectory);
    currentDirectory.addItem(newDirectory);
    outputs.add("'" + dirName + "' directory created");
  }

  @Override
  public List<String> getOutputs() {
    List<String> outputs = new ArrayList<>(this.outputs); // Return a copy to prevent modification
    this.outputs.clear(); // Clear the outputs
    return outputs;
  }
}
