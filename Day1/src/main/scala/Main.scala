import java.io.File
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {

    val file = new File(getClass.getClassLoader.getResource("input.txt").getPath)
    val source = Source.fromFile(file)

    var elf: Integer = 0
    var elfToCalorieMap: Map[Integer, Integer] = Map.empty[Integer, Integer]

    for (line: String <- source.getLines()) {
      if (line.equals("")) {
        elf += 1
      } else {
        val currentCalorie: Option[Integer] = elfToCalorieMap.get(elf)
        val newCalorie: Integer = Integer.parseInt(line)

        if (currentCalorie.isEmpty) {
          elfToCalorieMap += (elf -> newCalorie)
        } else {
          val totalCalorie: Integer = currentCalorie.get + newCalorie
          if (currentCalorie.nonEmpty) {
            elfToCalorieMap += (elf -> totalCalorie)
          }
        }
      }
    }

    val sortedValues: List[Integer] = elfToCalorieMap.values.toList.sorted.reverse

    if (sortedValues.nonEmpty) {
      val topOne: Integer = sortedValues.head
      println("Top One: " + topOne)

      if (sortedValues.length > 2) {
        val topThree: Integer = topOne + sortedValues(1) + sortedValues(2)
        println("Top Three: " + topThree)
      }
    }

    source.close()
  }
}