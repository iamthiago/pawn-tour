package com.truecaller.pawntour

import scala.annotation.tailrec

object PawnTour {

  type Position = (Int, Int)
  val INVALID_POSITION: Position = (-1, -1)
  val MIN_MOVES = 9

  /**
    * Given a position on a board, mark it with the current offset
    * and from this position, start looking for the next available moves
    * on not visited squares yet, if any.
    *
    */
  @tailrec
  def start(position: Position, board: Board, offset: Int): Array[Array[Int]] = {

    /**
      * Given the available moves, try to find the closest position to the edge of the board.
      *
      */
    @tailrec
    def findNextSquare(availableMoves: List[Position], position: Position, minMoves: Int): Position = {
      availableMoves match {
        case head :: tail =>
          val nextMoves = board.notVisited(head)

          nextMoves.length match {
            case x if x < minMoves =>
              findNextSquare(tail, head, nextMoves.length)

            case y if y == minMoves =>
              val nearestSquare = board.nearest(position, head)
              if (nearestSquare != position) {
                findNextSquare(tail, nearestSquare, nextMoves.length)
              } else findNextSquare(tail, position, minMoves)

            case _ =>
              findNextSquare(tail, position, minMoves)
          }

        case Nil => position
      }
    }

    board.squares(position._1)(position._2) = offset

    val availableMoves = board.notVisited(position)
    findNextSquare(availableMoves, INVALID_POSITION, MIN_MOVES) match {
      case INVALID_POSITION => board.squares
      case index => start(index, board, offset + 1)
    }
  }
}
