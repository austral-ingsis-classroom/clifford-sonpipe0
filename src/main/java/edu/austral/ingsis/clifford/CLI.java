package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CLI {
  private final Directory root;
  private Directory currentDirectory;
  private final Map<String, Command> commands;
  private final List<String> outputs;
  private boolean running;
  private final Context context;

  public CLI() {
    root = new Directory("", null);
    context = new Context(root);
    currentDirectory = root;
    commands = new HashMap<>();
    outputs = new ArrayList<>();
    running = true;
    registerDefaultCommands();
  }

  private void registerDefaultCommands() {

    registerCommand("ls", new LsCommand(context));
    registerCommand("cd", new CdCommand(context, root));
    registerCommand("touch", new TouchCommand(context));
    registerCommand("mkdir", new MkdirCommand(context));
    registerCommand("rm", new RmCommand(context));
    registerCommand("pwd", new PwdCommand(context));
  }

  public void registerCommand(String commandName, Command command) {
    commands.put(commandName, command);
  }

  public String executeCommand(String command) {
    String[] parts = command.split(" ");
    String commandName = parts[0];
    List<String> args = parts.length > 1 ? List.of(parts).subList(1, parts.length) : null;
    Command action = commands.get(commandName);
    if (action != null) {
      if ((args != null)) {
        action.execute(args.toArray(new String[0]));
      } else {
        action.execute();
      }
      return action.getOutputs().get(0);
    } else {
      System.out.println("Command not found: " + commandName);
    }
    return null;
  }

  public List<String> getOutputs() {
    return new ArrayList<>(outputs);
  }
}
