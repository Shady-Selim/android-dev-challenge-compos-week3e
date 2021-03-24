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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.data.models.Weather
import com.example.androiddevchallenge.ui.main.MainViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

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
    val weather by model.getWeather("${context.resources.getString(R.string.lat)},${context.resources.getString(R.string.log)}").observeAsState()
    Surface(color = MaterialTheme.colors.background) {
        CoilImage(
            data = "https://darksky.net/images/weather-icons/${weather?.currently?.icon}.png",
            contentDescription = "My content description"
        )
        /*Image(
            imageResource(R.drawable.actual_png_file),
            contentDescription = "description of the image"
        )*/
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
    /*val context = LocalContext.current
    val mov = getResource(context.resources.assets.locales[R.raw.cloudy])  //context.resources.assets.locales[context.resources.getIdentifier("cloudy", "raw", context.packageName)] // .assets.[R.raw.cloudy] // resources.getString() getResource.getStr (id = R.raw.cloudy)

    val exoPlayer = remember(context) {
        Builder(context).build().apply {
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.packageName))

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(
                    MediaItem.fromUri(mov)
                )
            this.setMediaSource(source)
            this.prepare()
            this.play()
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        }
    )*/
}
