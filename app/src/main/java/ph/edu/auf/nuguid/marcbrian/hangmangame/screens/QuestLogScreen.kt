package ph.edu.auf.nuguid.marcbrian.hangmangame.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ph.edu.auf.nuguid.marcbrian.hangmangame.misc.Quest

@Composable
fun QuestLogScreen() {

    val quests = listOf(
        Quest(1, "Quest 1", completed = true),
        Quest(2,"Quest 2", completed = false),
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {

        items(quests) { quest ->
            QuestItem(quest)
        }
    }
}

@Composable
fun QuestItem(quest: Quest) {

    Row(modifier = Modifier.padding(8.dp)) {
        Text(text = quest.description)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (quest.completed) "Completed" else "Incomplete")
    }
}