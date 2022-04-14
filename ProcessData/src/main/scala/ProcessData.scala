import java.io.{BufferedReader, FileNotFoundException, FileReader}
import scala.annotation.tailrec

class readFile(fileToProcess: String) {
  def getColFromLine(line: String, col: Integer): String = {
    val linevals = line.split(" ")
    linevals(col)
  }

  def getIntFromLine(line: String, col: Integer): Integer = {
    val linevals = line.split(" ")
    linevals(col).toInt
  }

  def filterString(line: String, filterstr: String, strcol: Integer): Boolean = {
    getColFromLine(line, strcol) == filterstr
  }

  def filterData(filterstr: String, filedata: String): Array[String] = {
    filedata.split("\n").filter { x => filterString(x, filterstr, 0) }
  }

  def getcol(filedata: String): Array[String] = {
    filedata.split("\n").map { x => getColFromLine(x, 0) }
  }

  @throws(classOf[Exception])
  def readfullfile(): String = {
    try {
      val infile = scala.io.Source.fromFile(fileToProcess)
      val source = infile.mkString
      infile.close()
      source
    } catch {
      case _: FileNotFoundException =>
        println("Error opening file " + fileToProcess); throw new Exception
      case _: Throwable =>
        println("Error processing file " + fileToProcess); throw new Exception
    }
  }

  @throws(classOf[Exception])
  def readFileFunc(): Unit = {
    try {
      val fileReader = new BufferedReader(new FileReader(fileToProcess))

      @tailrec
      def processLine(currline: String): Unit = {
        if (currline != null) {
          processLine(fileReader.readLine())
        }
      }

      processLine(fileReader.readLine())
      fileReader.close()
    } catch {
      case _: FileNotFoundException =>
        println("Error opening file " + fileToProcess); throw new Exception
      case _: Throwable =>
        println("Error processing file " + fileToProcess); throw new Exception
    }
  }

  def sumcoln(contents: Array[String], x: Integer): Integer = {
    val valstosum = contents.map(s => getIntFromLine(s, x))
    val sumval = valstosum.reduce { (a, b) => a + b }
    sumval
  }

  @throws(classOf[Exception])
  def processFile(): Unit = {
    try {
      val colstoprocess = List(1, 2)
      val contents = readfullfile()

      def itemtypesvec: Set[String] = getcol(contents).toSet

      itemtypesvec.foreach(x => {
        colstoprocess.foreach(y => {
          val linesofitemtype = filterData(x, contents)
          val sumofitemtype = sumcoln(linesofitemtype, y)
          println("Type " + x + " Column " + y + " sum is " + sumofitemtype)
        })
      })
    } catch {
      case _: Throwable =>
        println("Error processing file " + fileToProcess); throw new Exception
    }
  }
}

object ProcessFileApp {
  def main(args: Array[String]): Unit = {
    println("ProcessFileApp started")

    val fileName = "/home/here/IdeaProjects/datafiles/data.txt"
    val readFileObj = new readFile(fileName)
    readFileObj.processFile()

    println("ProcessFileApp completed")
  }
}
