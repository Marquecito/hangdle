package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.GamePreferences
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.HighScore

class PlayViewModel(
    private val gamePreferences: GamePreferences,
) : ViewModel() {
    private val solveDamage = 100

    private var solvedWords = 0

    private val _gameState = MutableStateFlow(getInitialState())
    val gameState = _gameState.asStateFlow()

    private val _currentGuess = MutableStateFlow("")
    val currentGuess = _currentGuess.asStateFlow()

    fun resetGame() {
        solvedWords = 0
        _gameState.value = getInitialState()
        _currentGuess.value = ""
    }

    fun onCurrentGuessChange(guess: String) {
        val gameState = _gameState.value
        if (gameState is OngoingGameState &&
            guess.length <= gameState.wordToGuess.length &&
            guess.all { it.isLetter() }
        ) {
            _currentGuess.value = guess.uppercase()
        }
    }

    fun onGuessSubmit() {
        val gameState = _gameState.value
        if (gameState is OngoingGameState &&
            _currentGuess.value.length < gameState.wordToGuess.length
        ) return

        _gameState.update { oldGameState ->
            if (oldGameState is OngoingGameState) {
                with(oldGameState) {
                    val currentGuess = _currentGuess.value
                    val validatedGuess = checkGuess(
                        wordToGuess,
                        currentGuess,
                    )

                    var enemyHealth = enemyHealth
                    var health = health
                    var wordToGuess = wordToGuess
                    var guesses = guesses
                    var level = level
                    var enemy = enemy
                    var wordsGuessed = wordsGuessed

                    if (validatedGuess.complete) {
                        enemyHealth = (enemyHealth - solveDamage).coerceAtLeast(0)
                        health += 30
                        solvedWords++

                        if (enemyHealth <= 0) {
                            enemy = Enemies.getOrElse(level) { return@update GameWonState }
                            enemyHealth = enemy.maxHealth
                            level++
                            health += 120
                        }
                        wordsGuessed = wordsGuessed + wordToGuess
                        wordToGuess = (enemy.words - wordsGuessed.toSet()).random().uppercase()
                        guesses = emptyList()
                    } else {
                        health = (health - enemy.attack).coerceAtLeast(0)
                        if (health <= 0) {
                            val highScore = HighScore(level = level, solvedWords = solvedWords)
                            gamePreferences.addHighScore(highScore)
                            return@update GameLostState(wordToGuess)
                        }
                        guesses = guesses + listOf(validatedGuess)
                    }

                    OngoingGameState(
                        level = level,
                        health = health,
                        enemy = enemy,
                        enemyHealth = enemyHealth,
                        wordsGuessed = wordsGuessed,
                        wordToGuess = wordToGuess,
                        guesses = guesses,
                    )
                }
            } else oldGameState
        }
        _currentGuess.value = ""
    }
}

private fun getInitialState(): GameState {
    val enemy = Enemies.first()
    val wordToGuess = enemy.words.random().uppercase()

    return OngoingGameState(
        level = 1,
        health = 150,
        enemy = enemy,
        enemyHealth = enemy.maxHealth,
        wordsGuessed = emptyList(),
        wordToGuess = wordToGuess,
        guesses = emptyList(),
    )
}