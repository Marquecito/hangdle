package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlayViewModel : ViewModel() {
    private val solveDamage = 100

    private val _gameState = MutableStateFlow(getInitialState())
    val gameState = _gameState.asStateFlow()

    private val _currentGuess = MutableStateFlow("")
    val currentGuess = _currentGuess.asStateFlow()

    fun resetGame() {
        _gameState.value = getInitialState()
    }

    fun onCurrentGuessChange(guess: String) {
        val gameState = _gameState.value
        if (gameState is OngoingGameState && guess.length <= gameState.wordToGuess.length) {
            _currentGuess.value = guess
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

                    var enemyHealth = enemy.health
                    var health = health
                    var wordToGuess = wordToGuess
                    var guesses = guesses
                    var level = level
                    var enemy = enemy
                    var wordsGuessed = wordsGuessed

                    if (validatedGuess.complete) {
                        enemyHealth = (enemyHealth - solveDamage).coerceAtLeast(0)
                        enemy = enemy.copy(health = enemyHealth)
                        health += 30

                        if (enemyHealth <= 0) {
                            enemy = Enemies.getOrElse(level) { return@update GameWonState }
                            level++
                            health += 120
                        }
                        wordsGuessed = wordsGuessed + wordToGuess
                        wordToGuess = (enemy.words - wordsGuessed.toSet()).random().uppercase()
                        guesses = emptyList()
                    } else {
                        health = (health - enemy.attack).coerceAtLeast(0)
                        if (health <= 0) {
                            return@update GameLostState(wordToGuess)
                        }
                        guesses = guesses + listOf(validatedGuess)
                    }

                    OngoingGameState(
                        level = level,
                        enemy = enemy,
                        health = health,
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
        wordsGuessed = emptyList(),
        wordToGuess = wordToGuess,
        guesses = emptyList(),
    )
}