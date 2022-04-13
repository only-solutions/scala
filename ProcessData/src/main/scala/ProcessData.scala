import java.io.{File, FileReader, BufferedReader}

object readFile {
  def getColFromLine(line: String, col: Integer):String = {
    //println("line: "+line)
    val linevals = line.split(" ")
    linevals(col)
  }

  def getcol(filedata: String): Array[String] =
  {
   filedata.split("\n").map { (x) => getColFromLine(x,0) }
  }
  def readfullfile(filename: String): String = {
     var source = scala.io.Source.fromFile(filename).mkString
     source
  }

  def readFileFunc = {
    val fileName = "/home/here/IdeaProjects/datafiles/data.txt"

    val fileReader = new BufferedReader(new FileReader(fileName))
    def processLine(currline: String): Unit = {
      if (currline != null) {
        //println("line: "+currline)
        println(getColFromLine(currline,1))
        processLine(fileReader.readLine())
      }
    }

    processLine(fileReader.readLine())
    fileReader.close()
  }
}
//find unique values in first column of a text file

object TestProg {
  def main(args: Array[String]) = {
    val fileName = "/home/here/IdeaProjects/datafiles/data.txt"

    println("TestProg started")
    //readFile.readFileFunc
    def uniqVals: Set[String] = readFile.getcol(readFile.readfullfile(fileName)).toSet
    println(uniqVals)
    println("TestProg completed")
  }
}
