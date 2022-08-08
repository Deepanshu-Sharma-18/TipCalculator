package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.calculations.calculation
import com.example.tipcalculator.components.RoundedButton
import com.example.tipcalculator.components.inputfield
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()

        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MainApp() {

    TipCalculatorTheme() {
        androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFCC89C0)
        ) {
            val contri = remember {
                mutableStateOf(0.0)
            }
            Column(
                modifier = Modifier.padding(2.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                TopPart(contri)
                middlesection(contri)
            }

        }
    }
}

@Composable
fun TopPart(contri: MutableState<Double>) {
    val editcontri = "%.2f".format(contri.value)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),

        color = Color(0xFFF387E1),
        elevation = 12.dp
    ) {


        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Split Amount", modifier = Modifier.padding(5.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,

                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.size(5.dp))


            Text(
                text = "$$editcontri",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.h4
            )
        }
    }
}


@ExperimentalComposeUiApi

@Composable
fun middlesection(contri: MutableState<Double>) {
    //val range : ClosedFloatingPointRange
    val textstate = remember {
        mutableStateOf("")
    }
    val validstate = remember(textstate.value) {
        textstate.value.toString().isNotEmpty()

    }
    val splitstate = remember {
        mutableStateOf(2)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val sliderstate = remember {
        mutableStateOf(0.0f)
    }
    val tippercentage = (sliderstate.value * 100).toInt()

    val tipamount = remember {
        mutableStateOf(0.0)
    }






    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        backgroundColor = Color(0xFFF387E1),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(3.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            inputfield(
                lableid = "Enter Bill",
                valuestate = textstate,
                enabled = true,
                singleline = true,
                keyboardtype = KeyboardType.Number,
                onAction = KeyboardActions {
                    if (!validstate) return@KeyboardActions
                    sliderstate.value = 0.0f
                    tipamount.value = 0.0
                    splitstate.value = 2
                    contri.value =
                        (tipamount.value) + (textstate.value).toDouble() / splitstate.value
                    keyboardController?.hide()

                }
            )

            Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "Split", modifier = Modifier.padding(5.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.width(150.dp))

                RoundedButton(imageVector = Icons.Rounded.Remove, onclick = {
                    if (splitstate.value > 1) {
                        splitstate.value = splitstate.value - 1
                    } else
                        splitstate.value = splitstate.value


                    contri.value =
                        (tipamount.value) + (textstate.value).toDouble() / splitstate.value
                })
                Text(
                    text = "${splitstate.value}", modifier = Modifier.padding(5.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
                RoundedButton(imageVector = Icons.Rounded.Add, onclick = {

                    splitstate.value = splitstate.value + 1
                    contri.value =
                        (tipamount.value) + (textstate.value).toDouble() / splitstate.value

                })

            }

            Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {


                Text(
                    text = "Tip", modifier = Modifier.padding(5.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.width(200.dp))
                Text(
                    text = "${tipamount.value}", modifier = Modifier.padding(5.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )


            }

            Column(
                modifier = Modifier.padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "$tippercentage%", modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )

                Slider(value = sliderstate.value, onValueChange = {
                    sliderstate.value = it
                    tipamount.value = calculation(tippercentage, textstate.value.toDouble())
                    contri.value =
                        (tipamount.value) + (textstate.value).toDouble() / splitstate.value
                }, modifier = Modifier.padding(5.dp))


            }


        }
    }
}





