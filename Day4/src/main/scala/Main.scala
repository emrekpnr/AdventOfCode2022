import java.io.File
import java.util.stream.IntStream
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val lines: List[String] = readInput()

    val partOne = convertInputToRangeStreams(lines)
      .count(streamTuple => doesFullyContainOther(streamTuple))

    val partTwo = convertInputToRangeStreams(lines)
      .count(streamTuple => streamTuple._1.exists(assignment => streamTuple._2.contains(assignment)))

    println("Total Score of Part 1: " + partOne)
    println("Total Score of Part 2: " + partTwo)
  }

  def doesFullyContainOther(streamTuple: (Array[Int], Array[Int])): Boolean = {
    streamTuple._1.forall(n => streamTuple._2.contains(n)) ||
      streamTuple._2.forall(n => streamTuple._1.contains(n))
  }

  def convertInputToRangeStreams(lines: List[String]): List[(Array[Int], Array[Int])] = {
    lines
      .map(line => line.split(',').map(ranges => ranges.split('-')))
      .map(rangeStringsList => rangeStringsList.map(rangeStrings => rangeStrings.map(str => Integer.parseInt(str))))
      .map(ranges => (convertRangesToStream(ranges(0)), convertRangesToStream(ranges(1))))
  }

  def convertRangesToStream(ranges: Array[Int]): Array[Int] = {
    IntStream.range(ranges(0), ranges(1) + 1).toArray
  }

  def readInput(): List[String] = {
    val file = new File(getClass.getClassLoader.getResource("input.txt").getPath)
    val source = Source.fromFile(file)
    source.getLines().toList
  }

}