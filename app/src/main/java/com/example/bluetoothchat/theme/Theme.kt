package com.example.bluetoothchat.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


private val LightColors =
    lightColorScheme(

        primary = BluePrimary,

        background = BackgroundLight

    )


private val DarkColors =
    darkColorScheme(

        primary = BlueDark,

        background = BackgroundDark

    )



@Composable
fun BluetoothChatTheme(

    content: @Composable () -> Unit

){

    MaterialTheme(

        colorScheme =
        if(isSystemInDarkTheme())

            DarkColors

        else

            LightColors,


        content = content

    )

}
