package com.truecaller.pawntour

case class Board(squares: Array[Array[Int]]) {
  private val size = squares(0).length
  private val range = 0 until size

  def this(size: Int) = this(Array.ofDim[Int](size, size))

  def moves(column: Int, row: Int): List[(Int, Int)] = {
    List(
      (column, row - 3), //N
      (column - 2, row - 2), //NW
      (column - 3, row), //W
      (column - 2, row + 2), //SW
      (column, row + 3), //S
      (column + 2, row + 2), //SE
      (column + 3, row), //E
      (column + 2, row - 2) //NE
    ).filter(n => range.contains(n._1) && range.contains(n._2))
  }

  def notVisited(position: (Int, Int)): List[(Int, Int)] = {
    moves(position._1, position._2).filter(move => squares(move._1)(move._2) == 0)
  }

  def nearest(x: (Int, Int), y: (Int, Int)): (Int, Int) = {
    val minX = List(size - x._1, x._1, x._2, size - x._2).min
    val minY = List(size - y._1, y._1, y._2, size - y._2).min
    if (minY < minX) y else x
  }
}
