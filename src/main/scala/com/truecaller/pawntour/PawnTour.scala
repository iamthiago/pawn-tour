package com.truecaller.pawntour

import scala.annotation.tailrec

object PawnTour {

  type Position = (Int, Int)

  /**
    * Given a position on a board, mark it with the current offset
    * and from this position, start looking for the next available moves
    * on not visited squares yet, if any.
    *
    * For every available square, find it's available moves recursively
    *
    *
    * @param position current position on the board
    * @param board the board
    * @param offset current offset, starts with value 1
    * @return a bi-directional array
    */
  @tailrec
  def find(position: Position, board: Board, offset: Int): Array[Array[Int]] = {

    /**
      * Given the available moves, try to find the square with the fewest available moves
      * so the pawn can visit the squares around the edges first, preventing
      * going to the middle of the board early
      *
      * When it reaches the end of the available moves, it sort the accumulated result
      * and get the first occurrence, the position with the fewest available moves.
      * In case the accumulated is empty, returns None to signal the recursion to end
      *
      * @param availableMoves current available moves
      * @param acc accumulates the squares and it's possible moves
      * @return an optional position
      */
    @tailrec
    def findNextSquare(availableMoves: List[Position], acc: List[(Position, Int)]): Option[Position] = {
      availableMoves match {
        case head :: tail =>
          val size = board.notVisited(head).length
          findNextSquare(tail, acc :+ (head, size))

        case Nil =>
          if (acc.isEmpty) None
          else Some(acc.sortBy(_._2).map(_._1).head)
      }
    }

    board.squares(position._1)(position._2) = offset
    val availableMoves = board.notVisited(position)
    findNextSquare(availableMoves, List()) match {
      case Some(index) => find(index, board, offset + 1)
      case None => board.squares
    }
  }
}
