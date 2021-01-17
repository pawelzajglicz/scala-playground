package com.rtjvm.scala.oop.files

import com.rtjvm.scala.oop.filesystem.FileSystemException

abstract class DirEntry(val parentPath: String, val name: String) {

  def path: String = parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory
  def asFile: File = throw new FileSystemException("A directory cannot be converted to a file")

  def getType: String
}
