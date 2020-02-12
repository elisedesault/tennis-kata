package com.bnppf.tenniskataapp

data class Game(val playerOne: Player, val playerTwo: Player) {

    fun increaseScore(player: Player) {
        player.score++
    }

    fun getScore(): String {
        return when (checkGameStatus()) {
            GameStatus.WIN -> "${getWinner().playerName} won"
            GameStatus.ADVANTAGE -> "Advantage"
            GameStatus.DEUCE -> "Deuce"
            GameStatus.SCORE -> {
                "${convertScore(playerOne.score)} / ${convertScore(playerTwo.score)}"
            }
        }
    }

    private fun convertScore(score: Int):String {
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

    private fun isAdvantage(scorePlayerOne: Int, scorePlayerTwo: Int): Boolean {
        return (scorePlayerOne >= 4 && scorePlayerOne - scorePlayerTwo == 1)
                || (scorePlayerTwo >= 4 && scorePlayerTwo - scorePlayerOne == 1)
    }

    private fun isDeuce(scorePlayerOne: Int, scorePlayerTwo: Int) =
            scorePlayerOne > 3 && scorePlayerOne == scorePlayerTwo

    private fun hasWon(scorePlayerOne: Int, scorePlayerTwo: Int): Boolean {
        return ((scorePlayerOne == 4 && scorePlayerTwo < 4)
                || (scorePlayerTwo == 4 && scorePlayerOne < 4)
                || (scorePlayerOne > 4 && scorePlayerOne - scorePlayerTwo == 2)
                || (scorePlayerTwo > 4 && scorePlayerTwo - scorePlayerOne == 2))
    }

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
}