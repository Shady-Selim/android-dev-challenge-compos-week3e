/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.androiddevchallenge.ui.main.MainViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.compose.ui.text.font.FontWeight
import com.example.androiddevchallenge.ui.theme.transparentWhite
import com.google.android.exoplayer2.Player

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(model: MainViewModel = MainViewModel()) {
    val context = LocalContext.current
    val contextResourses = context.resources
    val weather by model.getWeather(
        "${contextResourses.getString(R.string.latEg)},${contextResourses.getString(R.string.logEg)}"
    ).observeAsState()
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxHeight()
    ) {
        Box {
/*            Image(
                painter = painterResource(R.raw.cloudy),
                contentDescription = "Content description for visually impaired"
            )*/
            VideoPlayer()
            Card(
                elevation = 0.dp,
                modifier = Modifier
                    .padding(96.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                backgroundColor = transparentWhite
            ) {
                Row(modifier = Modifier.padding(0.dp, 16.dp)) {
                    weather?.let {
                        CoilImage(
                            data = "https://darksky.net/images/weather-icons/${it.currently.icon}.png",
                            contentDescription = "Weather icon status: ${it.currently.icon}",
                            modifier = Modifier
                                .size(128.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(16.dp, 0.dp)
                                .width(250.dp)
                        ) {

                            Text(
                                text = it.timezone,
                                style = typography.h5
                            )
                            Text(text = it.daily.summary)
                            Text(text = it.currently.summary, fontWeight = FontWeight.Bold)
                            Text(
                                text = contextResourses.getString(
                                    R.string.high,
                                    ceil(it.daily.data[0].temperatureHigh).toInt().toString()
                                )
                            )
                            Text(
                                text = contextResourses.getString(
                                    R.string.low,
                                    floor(it.daily.data[0].temperatureLow).toInt().toString()
                                )
                            )

                        }
                        Column {
                            Text(
                                text = contextResourses.getString(
                                    R.string.temp,
                                    it.currently.temperature.roundToInt().toString()
                                ),
                                style = typography.h3
                            )
                            Text(
                                text = contextResourses.getString(
                                    R.string.feels_like,
                                    it.currently.apparentTemperature.roundToInt().toString()
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@Composable
fun VideoPlayer() {
    val context = LocalContext.current
    val mov = getUriFromRawFile(context, R.raw.sunny)
    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, context.packageName)
            )

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(
                    MediaItem.fromUri(mov!!) //"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                )
            this.setMediaSource(source)
            this.prepare()
            this.repeatMode = Player.REPEAT_MODE_ONE
            this.play()
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
                useController = false
            }
        }
    )
}

fun getUriFromRawFile(context: Context, rawResourceId: Int): Uri? {
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.getPackageName())
        .path(rawResourceId.toString())
        .build()
}
