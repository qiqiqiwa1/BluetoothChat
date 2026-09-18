package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun AboutScreen(){


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding(),

        horizontalAlignment =
        Alignment.CenterHorizontally

    ){


        Spacer(
            Modifier.height(80.dp)
        )


        Icon(

            Icons.Default.Info,

            contentDescription=null,

            modifier =
            Modifier.size(80.dp)

        )


        Spacer(
            Modifier.height(20.dp)
        )


        Text(

            "BluetoothChat",

            style =
            MaterialTheme.typography.headlineSmall

        )


        Spacer(
            Modifier.height(12.dp)
        )


        Text(
            "版本：0.1.0 Beta"
        )


        Spacer(
            Modifier.height(30.dp)
        )


        Text(
            "基于蓝牙的点对点聊天应用"
        )


    }


}
