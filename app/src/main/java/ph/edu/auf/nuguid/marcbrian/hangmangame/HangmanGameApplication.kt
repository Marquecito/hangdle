package ph.edu.auf.nuguid.marcbrian.hangmangame

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.GamePreferences

class HangmanGameApplication : Application() {
    private lateinit var _gamePreferences: GamePreferences
    val gamePreferences get() = _gamePreferences

    override fun onCreate() {
        super.onCreate()
        _gamePreferences = GamePreferences(this)
    }
}

val CreationExtras.application
    get() = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HangmanGameApplication)

val CreationExtras.gamePreferences
    get() = application.gamePreferences
