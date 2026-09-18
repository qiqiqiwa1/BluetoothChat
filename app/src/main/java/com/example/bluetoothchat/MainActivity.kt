package com.example.bluetoothchat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


class MainActivity :
    ComponentActivity() {


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        setContent {

            MaterialTheme {

                ProfileScreen()

            }

        }

    }

}



@Composable
fun ProfileScreen(){


    val context =
        LocalContext.current


    val store =
        remember {

            UserPreferences(context)

        }


    val scope =
        rememberCoroutineScope()


    var nickname by remember {

        mutableStateOf("")

    }


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .padding(30.dp)

    ){


        Text(

            text = "我的资料",

            style =
            MaterialTheme
                .typography
                .headlineMedium

        )


        Spacer(

            Modifier.height(30.dp)

        )


        Button(

            onClick = {}

        ){

            Text("选择头像")

        }


        Spacer(

            Modifier.height(30.dp)

        )


        OutlinedTextField(

            value = nickname,

            onValueChange = {

                nickname = it

            },

            label = {

                Text("昵称")

            }

        )


        Spacer(

            Modifier.height(20.dp)

        )


        Button(

            onClick = {


                scope.launch {


                    store.saveNickname(

                        nickname

                    )


                }


            }

        ){

            Text("保存")

        }

    }

}
