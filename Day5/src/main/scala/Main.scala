import java.io.File
import java.util.stream.IntStream
import scala.collection.mutable
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val lines: List[String] = readInput()
    val intervalIndex = lines.indexOf("")

    val firstInputPart = lines.slice(0, intervalIndex)
    val secondInputPart = lines.slice(intervalIndex + 1, lines.length)

    val stacksFirstPart: List[mutable.Stack[String]] = convertInputToStack(firstInputPart)
    val stacksSecondPart: List[mutable.Stack[String]] = convertInputToStack(firstInputPart)

    val moveOrders = convertInputToMoveOrders(secondInputPart)

    moveOrders
      .foreach(line => IntStream.range(0, line(0)).forEach(_ =>
        stacksFirstPart(line(2) - 1).push(stacksFirstPart(line(1) - 1).pop())
      ))

    moveOrders
      .foreach(line => {
        var listOfMovedItems: List[String] = List()
        IntStream.range(0, line(0)).forEach(_ => listOfMovedItems = listOfMovedItems :+ stacksSecondPart(line(1) - 1).pop())
        listOfMovedItems.reverse.foreach(s => stacksSecondPart(line(2) - 1).push(s))
      })

    println("Part One: " + stacksFirstPart.map(stack => stack.pop()).mkString("(", ", ", ")"))
    println("Part Two: " + stacksSecondPart.map(stack => stack.pop()).mkString("(", ", ", ")"))
  }

  def convertInputToStack(lines: List[String]): List[mutable.Stack[String]] = {
    var stack: List[mutable.Stack[String]] = List()
    val stackSize = Integer.parseInt(lines.last.split(" ").last)
    (0 until stackSize)
      .foreach(i => stack = stack :+ mutable.Stack.empty[String])

    lines.slice(0, lines.length - 1)
      .reverse
      .map(line => line.grouped(4).toList)
      .map(line => line.map(group => group.slice(1, 2)))
      .foreach(line => line.zipWithIndex.foreach({ case (e, i) => if (!e.equals(" ")) stack(i).push(e) else None }))

    stack
  }

  def convertInputToMoveOrders(lines: List[String]): List[Array[Int]] = {
    lines
      .map(line => line.split(" "))
      .map(splitLine => splitLine.filter(part => !(part.equals("move") || part.equals("from") || part.equals("to"))))
      .map(filtered => filtered.map(str => Integer.parseInt(str)))
  }

  def readInput(): List[String] = {
    val file = new File(getClass.getClassLoader.getResource("input.txt").getPath)
    val source = Source.fromFile(file)
    source.getLines().toList
  }
}