package com.bnppf.tenniskataapp

class Game(private val playerOne: Player, private val playerTwo: Player) {

    fun increaseScore(player: Player) {
        player.score++
    }

    fun getScore(): String {
        return when (checkGameStatus()) {
            GameStatus.WIN -> "${getWinner().playerName} won"
            GameStatus.ADVANTAGE -> "Advantage ${getWinner().playerName}"
            GameStatus.DEUCE -> "Deuce"
            GameStatus.SCORE -> {
                "${convertScore(playerOne.score)} / ${convertScore(playerTwo.score)}"
            }
        }
    }

    private fun convertScore(score: Int): String {
        return when (score) {
            0 -> "0"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            else -> throw RuntimeException("An issue occurred")
        }
    }

    fun checkGameStatus(): GameStatus {
        val scorePlayerOne = playerOne.score
        val scorePlayerTwo = playerTwo.score

        if (hasWon(scorePlayerOne, scorePlayerTwo)) {
            return GameStatus.WIN
        } else if (isDeuce(scorePlayerOne, scorePlayerTwo)) {
            return GameStatus.DEUCE
        } else if (isAdvantage(scorePlayerOne, scorePlayerTwo)) {
            return GameStatus.ADVANTAGE
        }
        return GameStatus.SCORE
    }

    private fun hasWon(scorePlayerOne: Int, scorePlayerTwo: Int): Boolean {
        return (hasMorePoints(scorePlayerOne, scorePlayerTwo)
                || hasMorePoints(scorePlayerTwo, scorePlayerOne)
                || hasTwoPointsMore(scorePlayerOne, scorePlayerTwo)
                || hasTwoPointsMore(scorePlayerTwo, scorePlayerOne))
    }

    private fun hasTwoPointsMore(scorePlayerOne: Int, scorePlayerTwo: Int) =
            scorePlayerOne > 4 && scorePlayerOne - scorePlayerTwo == 2

    private fun hasMorePoints(scorePlayerOne: Int, scorePlayerTwo: Int) =
            scorePlayerOne == 4 && scorePlayerTwo < 3

    private fun isAdvantage(scorePlayerOne: Int, scorePlayerTwo: Int): Boolean {
        return (testAdvantage(scorePlayerOne, scorePlayerTwo))
                || testAdvantage(scorePlayerTwo, scorePlayerOne)
    }

    private fun testAdvantage(scorePlayerOne: Int, scorePlayerTwo: Int) =
            scorePlayerOne >= 4 && scorePlayerTwo >= 3 && scorePlayerOne - scorePlayerTwo == 1

    private fun isDeuce(scorePlayerOne: Int, scorePlayerTwo: Int) =
            scorePlayerOne >= 3 && scorePlayerOne == scorePlayerTwo

    fun getWinner(): Player {
        if (!hasWon(playerOne.score, playerTwo.score)) {
            throw RuntimeException("No winner")
        }

        return if (playerOne.score > playerTwo.score) {
            playerOne
        } else {
            playerTwo
        }
    }

    fun resetPlayersScore() {
        playerOne.score = 0
        playerTwo.score = 0
    }
}