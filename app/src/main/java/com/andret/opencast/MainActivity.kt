package com.andret.opencast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.andret.opencast.ui.pages.PodcastPlayer
import com.andret.opencast.ui.theme.OpencastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpencastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    PodcastPlayer("https://chrt.fm/track/GD6D57/https://nerdcast.jovemnerd.com.br/nerdcast_962_perrengues_propria_carne.mp3")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpencastTheme {
        Greeting("Android")
    }
}