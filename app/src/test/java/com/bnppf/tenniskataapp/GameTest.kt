package com.bnppf.tenniskataapp

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameTest {

    lateinit var playerOne: Player
    lateinit var playerTwo: Player
    lateinit var game: Game

    @Before
    fun init() {
        playerOne = Player("testPlayer1")
        playerTwo = Player("testPlayer2")
        game = Game(playerOne, playerTwo)
    }

    @Test
    fun increasePoint() {
        game.increaseScore(playerOne)

        Assert.assertEquals(1, playerOne.score)
        Assert.assertEquals(0, playerTwo.score)

        game.increaseScore(playerOne)

        Assert.assertEquals(2, playerOne.score)
    }

    @Test
    fun checkStatusWin() {
        game.increaseScore(playerOne)
        game.increaseScore(playerOne)
        game.increaseScore(playerOne)
        game.increaseScore(playerOne)

        var gameStatus = game.checkGameStatus()

        Assert.assertEquals(GameStatus.WIN, gameStatus)

        playerOne.score = 0
        game.increaseScore(playerTwo)
        game.increaseScore(playerTwo)
        game.increaseScore(playerTwo)
        game.increaseScore(playerTwo)
        gameStatus = game.checkGameStatus()
        Assert.assertEquals(GameStatus.WIN, gameStatus)

        playerOne.score = 6
        playerTwo.score = 4
        gameStatus = game.checkGameStatus()
        Assert.assertEquals(GameStatus.WIN, gameStatus)

        playerOne.score = 4
        playerTwo.score = 6
        gameStatus = game.checkGameStatus()
        Assert.assertEquals(GameStatus.WIN, gameStatus)
    }

    @Test
    fun checkStatusDeuce() {
        playerOne.score = 4
        playerTwo.score = 4
        var gameStatus = game.checkGameStatus()

        Assert.assertEquals(GameStatus.DEUCE, gameStatus)

        playerOne.score = 8
        playerTwo.score = 8
        gameStatus = game.checkGameStatus()

        Assert.assertEquals(GameStatus.DEUCE, gameStatus)
    }

    @Test
    fun checkStatusAdvantage() {
        playerOne.score = 10
        playerTwo.score = 9
        var gameStatus = game.checkGameStatus()

        Assert.assertEquals(GameStatus.ADVANTAGE, gameStatus)
        playerOne.score = 4
        playerTwo.score = 5
        gameStatus = game.checkGameStatus()

        Assert.assertEquals(GameStatus.ADVANTAGE, gameStatus)
    }

    @Test
    fun checkStatusScore() {
        playerOne.score = 2
        playerTwo.score = 3
        val gameStatus = game.checkGameStatus()

        Assert.assertEquals(GameStatus.SCORE, gameStatus)
    }

    @Test()
    fun getWinner() {
        playerOne.score = 4
        playerTwo.score = 2

        var winner = game.getWinner()

        Assert.assertEquals(playerOne, winner)

        playerOne.score = 6
        playerTwo.score = 8

        winner = game.getWinner()
        Assert.assertEquals(playerTwo, winner)
    }

    @Test(expected = RuntimeException::class)
    fun getWinnerWhenNoWinner() {
        playerOne.score = 1
        playerTwo.score = 0
        game.getWinner()
    }

    @Test
    fun getScoreWhenPlayerOneWins() {
        playerOne.score = 8
        playerTwo.score = 6

        val score = game.getScore()

        Assert.assertEquals("testPlayer1 won", score)
    }

    @Test
    fun getScoreWhenPlayerTwoWins() {
        playerOne.score = 2
        playerTwo.score = 4

        val score = game.getScore()

        Assert.assertEquals("testPlayer2 won", score)
    }

    @Test
    fun getScoreWhenAdvantage() {
        playerOne.score = 6
        playerTwo.score = 5

        val score = game.getScore()

        Assert.assertEquals("Advantage", score)
    }

    @Test
    fun getScoreWhenDeuce() {
        playerOne.score = 4
        playerTwo.score = 4
        val score = game.getScore()

        Assert.assertEquals("Deuce", score)
    }

    @Test
    fun getScore() {
        playerOne.score = 1
        playerTwo.score = 2
        val score = game.getScore()

        Assert.assertEquals("15 / 30", score)
    }
}