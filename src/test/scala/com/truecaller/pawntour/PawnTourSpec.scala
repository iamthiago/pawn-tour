package com.truecaller.pawntour

import org.scalacheck.Gen

class PawnTourSpec extends BaseSpec {

  def random8x8: Int = Gen.chooseNum(0, 7).sample.get
  def random10x10: Int = Gen.chooseNum(0, 9).sample.get
  def random12x12: Int = Gen.chooseNum(0, 11).sample.get
  def random24x24: Int = Gen.chooseNum(0, 23).sample.get

  "A pawn" should "move on all squares of a 8x8 board with random start position" in {
    val position = (random8x8, random8x8)
    val tour = PawnTour.find(position, Board(8), 1)
    forAll(tour) { squares =>
      forAll(squares) {sqr => sqr should be > 0 }
    }
  }

  it should "move on all squares of a 10x10 board with random start position" in {
    val position = (random10x10, random10x10)
    val tour = PawnTour.find(position, Board(10), 1)
    forAll(tour) { squares =>
      forAll(squares) {sqr => sqr should be > 0 }
    }
  }

  it should "move on all squares of a 12x12 board with random start position" in {
    val position = (random12x12, random12x12)
    val tour = PawnTour.find(position, Board(12), 1)
    forAll(tour) { squares =>
      forAll(squares) {sqr => sqr should be > 0 }
    }
  }

  it should "move on all squares of a 24x24 board with random start position" in {
    val position = (random24x24, random24x24)
    val tour = PawnTour.find(position, Board(24), 1)
    forAll(tour) { squares =>
      forAll(squares) {sqr => sqr should be > 0 }
    }
  }

  "A board" should "give valid moves for a pawn in position 3x2 on a 10x10 board" in {
    val board = Board(10)
    val moves = board.moves(3, 2)
    moves should contain allOf((1,0), (0,2), (1,4), (3,5), (5,4), (6,2), (5,0))
  }
}
