package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class CdCommand implements Command {
  private final Context context;
  private final Directory root;
  private final List<String> outputs; // To store command outputs

  public CdCommand(Context context, Directory root) {
    this.context = context;
    this.root = root;
    this.outputs = new ArrayList<>();
  }

  @Override
  public void execute(String... args) {
    Directory currentDirectory = context.getCurrentDirectory();
    String path = args[0];
    if (path.equals("..")) {
      currentDirectory = currentDirectory.getParent();
      context.setCurrentDirectory(currentDirectory);
      if (currentDirectory == null) outputs.add("moved to directory '/'");
      else if (currentDirectory.getName().isEmpty()) outputs.add("moved to directory '/'");
      else outputs.add("moved to directory '" + currentDirectory.getName() + "'");
    } else if (path.equals(".")) {
      // Do nothing if current directory is selected
    } else {
      Directory targetDirectory = navigateToDirectory(path);
      if (targetDirectory != null) {
        currentDirectory = targetDirectory;
        context.setCurrentDirectory(currentDirectory);
        if (path.equals("/")) outputs.add("moved to directory '/'");
        else outputs.add("moved to directory '" + currentDirectory.getName() + "'");
      } else {
        outputs.add("'" + path + "'" + " directory does not exist");
      }
    }
  }

  @Override
  public List<String> getOutputs() {
    List<String> outputs = new ArrayList<>(this.outputs); // Return a copy to prevent modification
    this.outputs.clear(); // Clear the outputs
    return outputs;
  }

  private Directory navigateToDirectory(String path) {
    Directory currentDirectory = context.getCurrentDirectory();
    String[] parts = path.split("/");
    Directory tempDir = path.startsWith("/") ? root : currentDirectory;

    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) continue;
      if (part.equals("..")) {
        if (tempDir.getParent() != null) {
          tempDir = tempDir.getParent();
        }
      } else {
        boolean found = false;
        for (FileSystemItem item : tempDir.getItems()) {
          if (item instanceof Directory && item.getName().equals(part)) {
            tempDir = (Directory) item;
            found = true;
            break;
          }
        }
        if (!found) return null;
      }
    }
    return tempDir;
  }
}
