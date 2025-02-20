package com.andret.opencast.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun PodcastPlayer(audioUrl: String) {
    // Initialize ExoPlayer
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    val mediaItem = MediaItem.fromUri(audioUrl)

    // Observe changes in ExoPlayer's play state using snapshotFlow
    val isPlayerPlaying = remember { mutableStateOf(exoPlayer.isPlaying) }

    exoPlayer.addListener(object: Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            isPlayerPlaying.value = isPlaying
        }
    })
    LaunchedEffect(audioUrl) {
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Log.d("PodcastPlayer", "isPlaying: ${isPlayerPlaying.value}")


    // Display your player UI here, for example, play/pause button
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { if (isPlayerPlaying.value) exoPlayer.pause() else exoPlayer.play() }) {
            Text(text = if (isPlayerPlaying.value) "Pause" else "Play")
        }
    }
}