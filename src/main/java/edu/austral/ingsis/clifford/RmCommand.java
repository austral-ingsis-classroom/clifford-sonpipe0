package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class RmCommand implements Command {
  private final Context context;
  private final List<String> outputs; // To store command outputs

  public RmCommand(Context context) {
    this.context = context;
    this.outputs = new ArrayList<>();
  }

  @Override
  public void execute(String... args) {
    boolean recursive = false;
    for (String arg : args) {
      if (arg.equals("--recursive")) {
        recursive = true;
        break;
      }
    }
    String itemName = null;
    if (recursive) itemName = args[1];
    else itemName = args[0];
    FileSystemItem itemToRemove = null;
    Directory currentDirectory = context.getCurrentDirectory();
    for (FileSystemItem item : currentDirectory.getItems()) {
      if (item.getName().equals(itemName)) {
        itemToRemove = item;
        break;
      }
    }

    if (itemToRemove == null) {
      outputs.add("Item not found: " + itemName);
      return;
    }

    if (itemToRemove instanceof Directory) {
      if (recursive) {
        currentDirectory.removeItem(itemToRemove);
        outputs.add("'" + itemName + "' removed");
      } else {
        outputs.add("cannot remove '" + itemName + "', is a directory");
      }
    } else {
      currentDirectory.removeItem(itemToRemove);
      outputs.add("'" + itemName + "' removed");
    }
  }

  @Override
  public List<String> getOutputs() {
    List<String> outputs = new ArrayList<>(this.outputs); // Return a copy to prevent modification
    this.outputs.clear(); // Clear the outputs
    return outputs;
  }
}
