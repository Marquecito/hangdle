package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

sealed interface GameState

data object GameWonState : GameState

data class GameLostState(val wordToGuess: String) : GameState

data class OngoingGameState(
    val level: Int,
    val health: Int,
    val enemy: Enemy,
    val enemyHealth: Int,
    val wordsGuessed: List<String>,
    val wordToGuess: String,
    val guesses: List<Guess>,
) : GameState

data class Enemy(
    val name: String,
    val attack: Int,
    val maxHealth: Int,
    val words: List<String>,
)

typealias Guess = List<WordleTile>

data class WordleTile(
    val letter: Char?,
    val validation: GuessValidation? = null,
)

enum class GuessValidation {
    Correct,
    Present,
    Absent,
}

fun checkGuess(
    wordToGuess: String,
    guess: String,
): Guess {
    val remainingLettersToGuess = wordToGuess.map<Char?> { it }.toTypedArray()
    val validatedGuess = Array<WordleTile?>(wordToGuess.length) { null }
    for ((index, guessedLetter) in guess.withIndex()) {
        if (guessedLetter == remainingLettersToGuess[index]) {
            validatedGuess[index] = WordleTile(
                letter = guessedLetter,
                validation = GuessValidation.Correct,
            )
            remainingLettersToGuess[index] = null
        }
    }
    for ((index, guessedLetter) in guess.withIndex()) {
        if (guessedLetter in remainingLettersToGuess && validatedGuess[index] == null) {
            validatedGuess[index] = WordleTile(
                letter = guessedLetter,
                validation = GuessValidation.Present,
            )
            val indexOfRemainingLetter = remainingLettersToGuess.indexOf(guessedLetter)
            remainingLettersToGuess[indexOfRemainingLetter] = null
        }
    }
    for ((index, guessedLetter) in guess.withIndex()) {
        if (validatedGuess[index] == null) {
            validatedGuess[index] = WordleTile(
                letter = guessedLetter,
                validation = GuessValidation.Absent,
            )
        }
    }
    return validatedGuess.toList().filterNotNull()
}

val Guess.complete get() = all { it.validation == GuessValidation.Correct }