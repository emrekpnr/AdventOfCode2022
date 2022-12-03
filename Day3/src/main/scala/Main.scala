import java.io.File
import java.util.stream.IntStream
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {

    val lines: List[String] = readInput()

    val partOne = lines
      .map(line => Array(line.substring(0, line.length / 2), line.substring(line.length / 2, line.length)))
      .map(rucksack => rucksack(0).filter(letter => rucksack(1).contains(letter)))
      .map(commonChars => commonChars.toList.distinct)
      .map(distinctCommonChars => distinctCommonChars.map(commonChar => commonChar.toInt))
      .map(asciiValues => asciiValues.map(asciiValue => convertAsciiToScore(asciiValue)))
      .map(scores => scores.sum)
      .sum

    println("Total Score of Part 1: " + partOne)

    val partTwo = IntStream.range(0, readInput().length / 3)
      .mapToObj(index => Array(lines(index * 3), lines(index * 3 + 1), lines(index * 3 + 2)))
      .map(group => group(0).filter(letter => group(1).contains(letter) && group(2).contains(letter)))
      .map(commonChars => commonChars.toList.distinct)
      .map(distinctCommonChars => distinctCommonChars.map(commonChar => commonChar.toInt))
      .map(asciiValues => asciiValues.map(asciiValue => convertAsciiToScore(asciiValue)))
      .map(scores => scores.sum)
      .mapToInt(score => score.toInt)
      .sum()

    println("Total Score of Part 12: " + partTwo)

  }

  def readInput(): List[String] = {
    val file = new File(getClass.getClassLoader.getResource("input.txt").getPath)
    val source = Source.fromFile(file)
    source.getLines().toList
  }

  def convertAsciiToScore(asciiValue: Integer): Int = {
    if (asciiValue < 91) asciiValue - 38 else asciiValue - 96
  }
}