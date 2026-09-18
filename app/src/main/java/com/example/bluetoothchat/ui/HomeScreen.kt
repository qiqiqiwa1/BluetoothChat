package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun HomeScreen(){


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)

    ){


        Text(

            text = "BluetoothChat",

            style =
            MaterialTheme.typography.headlineMedium

        )


        Spacer(

            modifier =
            Modifier.height(24.dp)

        )


        Card(

            modifier =
            Modifier
                .fillMaxWidth(),

            elevation =
            CardDefaults.cardElevation(
                4.dp
            )

        ){


            Column(

                modifier =
                Modifier.padding(24.dp),

                horizontalAlignment =
                Alignment.CenterHorizontally

            ){


                Icon(

                    imageVector =
                    Icons.Default.Chat,

                    contentDescription = null,

                    modifier =
                    Modifier.size(60.dp)

                )


                Spacer(

                    Modifier.height(16.dp)

                )


                Text(

                    text = "暂无聊天记录",

                    style =
                    MaterialTheme.typography.titleMedium

                )


                Spacer(

                    Modifier.height(8.dp)

                )


                Text(

                    text = "连接附近设备开始聊天"

                )


            }


        }


    }

}
