import java.io.{File, FileReader, BufferedReader}

object readFile {
  def getColFromLine(line: String, col: Integer):String = {
    val linevals = line.split(" ")
    linevals(col)
  }

  def getIntFromLine(line: String, col: Integer):Integer = {
    val linevals = line.split(" ")
    linevals(col).toInt
  }

  def filterString(line: String, filterstr: String, strcol: Integer): Boolean = {
    (getColFromLine(line, strcol) == filterstr)
  }

  def filterData(filterstr : String, filedata: String): Array[String] = {
    filedata.split("\n").filter{ (x) => filterString(x, filterstr, 0)}
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
        processLine(fileReader.readLine())
      }
    }

    processLine(fileReader.readLine())
    fileReader.close()
  }

  def sumcoln(contents: Array[String], x: Integer): Integer =
  {
    val valstosum=contents.map(s => getIntFromLine(s,x))
    val sumval = valstosum.reduce{(a,b) => a + b}
    sumval
  }
}

object TestProg {
  def main(args: Array[String]) = {
    println("TestProg started")

    val fileName = "/home/here/IdeaProjects/datafiles/data.txt"
    val colstoprocess = List(1,2)
    var contents = readFile.readfullfile(fileName)

    def itemtypesvec: Set[String] = readFile.getcol(contents).toSet
    def filterresults = readFile.filterData("lineb", contents)

    itemtypesvec.foreach(x => {
      colstoprocess.foreach(y => {
        var linesofitemtype = readFile.filterData(x,contents)
        var sumofitemtype = readFile.sumcoln(linesofitemtype, y)
        println ("Type "  + x  + " Column " +  y  + " sum is "  + sumofitemtype)
      })
    })

    println("TestProg completed")
  }
}
