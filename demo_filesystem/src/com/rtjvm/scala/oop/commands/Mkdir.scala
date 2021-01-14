package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.Directory
import com.rtjvm.scala.oop.filesystem.State

class Mkdir(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.workingDirectory
    if(wd.hasEntry(name)) {
      state.setMessage("Entry " + name + " already exists!")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + "must not contain separators!")
    } else if (checkIllegal(name)) {
      state.setMessage(name + ": illegal entry name!")
    } else {
      doMkdir(state, name)
    }
  }

  def checkIllegal(str: String): Boolean = {
    name.contains(".")
  }

  def doMkdir(state: State, name: String): State = {

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: Directory): Directory =
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }

    val wd = state.workingDirectory
    val fullPath = wd.path

    // all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // create new directory entry in the working directory
    val newDirectory = Directory.empty(wd.path, name)

    // update the whole directory structure starting from the root
    // the directory structure is immutable
    val newRoot = updateStructure(state.root, allDirsInPath, newDirectory)

    // find new working directory INSTANCE wd's full path in the NEW directory status
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }
}
