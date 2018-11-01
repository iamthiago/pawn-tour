package com.truecaller.pawntour

import java.util.Scanner

object Boot extends App {

  val scanner = new Scanner(System.in)

  println("Enter the Board size")
  val boardSize = scanner.nextInt()

  println("Enter the Row start position in the Board")
  val x = scanner.nextInt()

  println("Enter the Column start position in the Board")
  val y = scanner.nextInt()

  val tour = PawnTour.start((x, y), new Board(boardSize), 1)

  println(s"Here is the Pawn Tour for a board size of $boardSize and position $x,$y")
  for (i <- tour.indices) {
    for (j <- tour.indices) {
      print(tour(i)(j) + "\t")
    }
    println()
  }
}
