package com.example.partie1_corentin_leys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partie1_corentin_leys.ui.theme.PARTIE1CORENTINLEYSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PARTIE1CORENTINLEYSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CompteurIntelligent()
                }
            }
        }
    }
}

@Composable
fun CompteurIntelligent() {
    var compteur by rememberSaveable { mutableIntStateOf(0) }
    var limiteUtilisateur by rememberSaveable { mutableStateOf("") }
    val limiteMax = limiteUtilisateur.toIntOrNull() ?: 10

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Compteur : $compteur",
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            fontSize = 32.sp)

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Button(
                onClick = {
                    if (compteur < limiteMax) {
                        compteur++
                    }
                },
                enabled = compteur < limiteMax
            ) {
                Text("+")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { compteur = 0 }) {
                Text("Réinitialiser")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Limite maximale actuelle: $limiteMax", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = limiteUtilisateur,
            onValueChange = { limiteUtilisateur = it },
            label = { Text("Modifier la limite") },
            singleLine = true
        )

        if (compteur == limiteMax) {
            Text(
                text = "Limite atteinte",
                fontSize = 16.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
