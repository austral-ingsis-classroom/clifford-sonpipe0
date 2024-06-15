package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LsCommand implements Command {
  private final Context context;
  private final List<String> outputs;

  public LsCommand(Context context) {
    this.context = context;
    this.outputs = new ArrayList<>();
  }

  @Override
  public void execute(String... args) {
    // Parse arguments
    // --ord=asc|desc
    String order = null;
    for (String arg : args) {
      if (arg.startsWith("--ord=")) {
        order = arg.substring(6);
      }
    }
    Directory currentDirectory = context.getCurrentDirectory();
    List<FileSystemItem> items = currentDirectory.getItems();

    // Sort items based on order if specified
    if (order != null) {
      if (order.equals("asc")) {
        items.sort(Comparator.comparing(FileSystemItem::getName));
      } else if (order.equals("desc")) {
        items.sort(Comparator.comparing(FileSystemItem::getName).reversed());
      }
    }

    // Collect names of items for output
    StringBuilder sb = new StringBuilder();
    for (FileSystemItem item : items) {
      sb.append(item.getName()).append(" ");
    }
    outputs.add(sb.toString().trim()); // Store the output
  }

  @Override
  public List<String> getOutputs() {
    List<String> outputs = new ArrayList<>(this.outputs); // Return a copy to prevent modification
    this.outputs.clear(); // Clear the outputs
    return outputs;
  }
}
