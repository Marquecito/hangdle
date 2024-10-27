package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

class PlayEventHandler(
    val onCurrentGuessChange: (String) -> Unit,
    val onGuessSubmit: () -> Unit,
    val onPlayAgain: () -> Unit,
    val onGoToMainMenu: () -> Unit,
)