package com.debduttapanda.multistatemotionlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.layoutId
import com.debduttapanda.multistatemotionlayout.ui.theme.MultiStateMotionLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiStateMotionLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MultiMotion()
                }
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MultiMotion(){
    var stateSet by remember {
        mutableStateOf(
            StateSet(
                0,
                1,
                StateSet.Animation(key = "01")
            )
        )
    }
    val progress = remember {
        mutableStateOf(0f)
    }
    var sliderEnabled by remember {
        mutableStateOf(false)
    }
    CollapsibleBox(
        modifier = Modifier.fillMaxSize()
    ) {
        MultiStateMotionLayout(
            modifier = Modifier
                .fillMaxSize(),
            stateSet = stateSet,
            stateConstraints = mapOf(
                0 to
                        ConstraintSet {
                            val red = createRefFor("red")
                            val green = createRefFor("green")
                            val blue = createRefFor("blue")
                            val slider = createRefFor("slider")
                            val button = createRefFor("button")

                            constrain(button){
                                bottom.linkTo(slider.top,24.dp)
                            }

                            constrain(red){
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }

                            constrain(green){
                                top.linkTo(red.bottom)
                                start.linkTo(parent.start)
                            }

                            constrain(blue){
                                top.linkTo(parent.top)
                                start.linkTo(red.end)
                            }

                            constrain(slider){
                                bottom.linkTo(parent.bottom)
                            }
                        },
                1 to
                        ConstraintSet {
                            val red = createRefFor("red")
                            val green = createRefFor("green")
                            val blue = createRefFor("blue")
                            val slider = createRefFor("slider")
                            val button = createRefFor("button")

                            constrain(button){
                                bottom.linkTo(slider.top,24.dp)
                            }

                            constrain(red){
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }

                            constrain(blue){
                                top.linkTo(red.bottom)
                                start.linkTo(parent.start)
                            }

                            constrain(green){
                                top.linkTo(parent.top)
                                start.linkTo(red.end)
                            }

                            constrain(slider){
                                bottom.linkTo(parent.bottom)
                            }
                        },
                2 to
                        ConstraintSet {
                            val red = createRefFor("red")
                            val green = createRefFor("green")
                            val blue = createRefFor("blue")
                            val slider = createRefFor("slider")
                            val button = createRefFor("button")

                            constrain(button){
                                bottom.linkTo(slider.top,24.dp)
                            }

                            constrain(green){
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }

                            constrain(blue){
                                top.linkTo(green.bottom)
                                start.linkTo(parent.start)
                            }

                            constrain(red){
                                top.linkTo(parent.top)
                                start.linkTo(green.end)
                            }

                            constrain(slider){
                                bottom.linkTo(parent.bottom)
                            }
                        },
            )
        ){
            Box(
                modifier = Modifier
                    .layoutId("red")
                    .size(100.dp)
                    .clickable {
                        sliderEnabled = false
                        val start = ((stateSet.startState as Int) + 1) % 3
                        val end = ((stateSet.endState as Int) + 1) % 3
                        stateSet = StateSet(
                            start,
                            end,
                            StateSet.Animation(key = "$start$end")
                        )
                    }
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .layoutId("blue")
                    .clickable {
                        sliderEnabled = true
                        val start = ((stateSet.startState as Int) + 1) % 3
                        val end = ((stateSet.endState as Int) + 1) % 3
                        stateSet = StateSet(
                            start,
                            end,
                            StateSet.State(progress)
                        )
                    }
                    .size(100.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .layoutId("green")
                    .clickable {
                        sliderEnabled = false
                        val start = ((stateSet.startState as Int) + 1) % 3
                        val end = ((stateSet.endState as Int) + 1) % 3
                        stateSet = StateSet(
                            start,
                            end,
                            StateSet.State(it)
                        )
                    }
                    .size(100.dp)
                    .background(Color.Green)
            )
            Slider(
                enabled = sliderEnabled,
                value = progress.value,
                onValueChange = {
                    progress.value = it
                },
                modifier = Modifier
                    .layoutId("slider")
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .layoutId("button")
            ){
                Text("CollapsibleBoxProgress:${it.value}")
                Row{
                    Button(
                        onClick = {
                            sliderEnabled = false
                            val start = 0
                            val end = 1
                            stateSet = StateSet(
                                start,
                                end,
                                StateSet.Value(0.3f)
                            )
                        },

                        ) {
                        Text("0 to 1 on 0.3")
                    }
                    Button(
                        onClick = {
                            fun start(){
                                sliderEnabled = false
                                val start = ((stateSet.startState as Int) + 1) % 3
                                val end = ((stateSet.endState as Int) + 1) % 3
                                stateSet = StateSet(
                                    start,
                                    end,
                                    StateSet.Animation(
                                        key = "$start$end",
                                        onFinishedListener = {
                                            start()
                                        }
                                    ),
                                )
                            }
                            start()
                        }
                    ) {
                        Text("Continuous")
                    }
                }
            }
        }
    }
}