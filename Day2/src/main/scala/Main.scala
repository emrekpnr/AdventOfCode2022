import java.io.File
import java.util.stream.IntStream
import scala.io.Source

object Main {
  val loseDrawWinMap: Map[String, Map[String, String]] = Map(
    "A" -> Map(
      "X" -> "Z",
      "Y" -> "X",
      "Z" -> "Y",
    ),
    "B" -> Map(
      "X" -> "X",
      "Y" -> "Y",
      "Z" -> "Z",
    ),
    "C" -> Map(
      "X" -> "Y",
      "Y" -> "Z",
      "Z" -> "X",
    )
  )

  val scoringMap: Map[String, Map[String, Integer]] = Map(
    "A" -> Map(
      "X" -> 4,
      "Y" -> 8,
      "Z" -> 3,
    ),
    "B" -> Map(
      "X" -> 1,
      "Y" -> 5,
      "Z" -> 9,
    ),
    "C" -> Map(
      "X" -> 7,
      "Y" -> 2,
      "Z" -> 6,
    )
  )

  def main(args: Array[String]): Unit = {

    val roundScores = readInput()
      .map(line => line.split(" "))
      .map(letters => Array(scoringMap(letters(0))(letters(1)), scoringMap(letters(0))(loseDrawWinMap(letters(0))(letters(1)))))
      .toList

    val totalScores = IntStream.range(0, 2)
      .mapToObj(i => roundScores.map(round => round(i).toInt).sum)
      .toList

    println("Total Score of Part 1: " + totalScores.get(0))
    println("Total Score of Part 2: " + totalScores.get(1))
  }

  def readInput(): Iterator[String] = {
    val file = new File(getClass.getClassLoader.getResource("input.txt").getPath)
    val source = Source.fromFile(file)
    source.getLines()
  }
}