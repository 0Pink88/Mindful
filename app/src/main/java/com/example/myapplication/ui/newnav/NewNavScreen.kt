package com.example.myapplication.ui.newnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import org.json.JSONArray
import java.util.Calendar

@Composable
fun NewNavScreen(navController: NavController) {
    val context = LocalContext.current
    val quote = remember {
        val json = context.assets.open("Quotes.json").bufferedReader().readText()
        val array = JSONArray(json)
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        array.getString(dayOfYear % array.length())
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    label = { Text("Pet") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("journal") },
                    icon = { Icon(Icons.Filled.Book, contentDescription = null) },
                    label = { Text("Journal") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("mood") },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    label = { Text("Mood") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("goals") },
                    icon = { Icon(Icons.Filled.CheckCircle, contentDescription = null) },
                    label = { Text("Goals") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("forum") },
                    icon = { Icon(Icons.Filled.ChatBubble, contentDescription = null) },
                    label = { Text("Forum") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = "\"$quote\"",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp).fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Pet: Fox",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.virtual_pet),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Level: 1")
            Text("XP: 0")
            Text("Happiness: 100")

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { }) { Text("Feed") }
                Button(onClick = { }) { Text("Play") }
                Button(onClick = { }) { Text("Rest") }
            }
        }
    }
}
