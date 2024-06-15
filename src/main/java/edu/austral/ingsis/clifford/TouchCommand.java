package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class TouchCommand implements Command {
  private final Context context;
  private final List<String> outputs; // To store command outputs

  public TouchCommand(Context context) {
    this.context = context;
    this.outputs = new ArrayList<>();
  }

  @Override
  public void execute(String... args) {
    String fileName = args[0];
    Directory currentDirectory = context.getCurrentDirectory();
    File newFile = new File(fileName, currentDirectory);
    currentDirectory.addItem(newFile);
    outputs.add("'" + fileName + "' file created");
  }

  @Override
  public List<String> getOutputs() {
    List<String> outputs = new ArrayList<>(this.outputs); // Return a copy to prevent modification
    this.outputs.clear(); // Clear the outputs
    return outputs;
  }
}
