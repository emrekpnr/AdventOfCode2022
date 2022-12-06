import java.io.File
import java.util.stream.IntStream
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val line: String = readInput()

    val partOne: Int = IntStream.range(0, line.length - 5)
      .filter(index => isMarker(line.substring(index, index + 4)))
      .findFirst()
      .orElse(-1) + 4

    val partTwo: Int = IntStream.range(0, line.length - 15)
      .filter(index => isMessage(line.substring(index, index + 14)))
      .findFirst()
      .orElse(-1) + 14

    println("Part One: " + partOne)
    println("Part Two: " + partTwo)

  }

  def isMarker(string: String): Boolean = !string.exists(char => string.count(_ == char) > 1)

  def isMessage(string: String): Boolean = string.chars().distinct().toArray.length == string.length

  def readInput(): String = {
    val file = new File(getClass.getClassLoader.getResource("input.txt").getPath)
    val source = Source.fromFile(file)
    source.getLines().toList.head
  }
}