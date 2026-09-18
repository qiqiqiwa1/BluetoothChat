package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun ProfileScreen(){


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),

        horizontalAlignment =
        Alignment.CenterHorizontally

    ){


        Icon(

            imageVector =
            Icons.Default.Person,

            contentDescription = null,

            modifier =
            Modifier.size(90.dp)

        )


        Spacer(

            Modifier.height(20.dp)

        )


        Text(

            text = "未设置昵称",

            style =
            MaterialTheme.typography.headlineSmall

        )


        Spacer(

            Modifier.height(30.dp)

        )


        Card(

            modifier =
            Modifier.fillMaxWidth()

        ){


            Column(

                modifier =
                Modifier.padding(20.dp)

            ){


                Text(
                    "设备名称：Android 手机"
                )


                Spacer(
                    Modifier.height(10.dp)
                )


                Text(
                    "蓝牙状态：未连接"
                )


            }


        }


    }

}
