package edu.austral.ingsis.clifford;

public class Context {
  private Directory currentDirectory;

  public Context(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public void setCurrentDirectory(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }
}
