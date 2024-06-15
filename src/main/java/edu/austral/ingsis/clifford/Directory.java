package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemItem {
  private final String name;
  private final Directory parent;
  private final List<FileSystemItem> items;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.items = new ArrayList<>();
  }

  public Directory getParent() {
    return parent;
  }

  public void addItem(FileSystemItem item) {
    items.add(item);
  }

  public void removeItem(FileSystemItem item) {
    items.remove(item);
  }

  public List<FileSystemItem> getItems() {
    return items;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPath() {
    if (parent == null) {
      return name;
    } else {
      return parent.getPath() + "/" + name;
    }
  }
}
