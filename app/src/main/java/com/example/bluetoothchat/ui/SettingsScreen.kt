package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun SettingsScreen(

    navController: NavController

){


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)

    ){


        Row{


            IconButton(

                onClick = {

                    navController.popBackStack()

                }

            ){

                Icon(
                    Icons.Default.ArrowBack,
                    null
                )

            }



            Text(

                "设置",

                style =
                MaterialTheme.typography.headlineMedium

            )

        }



        Spacer(
            Modifier.height(20.dp)
        )


        SettingItem(
            Icons.Default.DarkMode,
            "深色模式",
            "跟随系统"
        )


        SettingItem(
            Icons.Default.Notifications,
            "消息通知",
            "开启"
        )


        SettingItem(
            Icons.Default.Bluetooth,
            "蓝牙设置",
            "管理设备连接"
        )


        SettingItem(
            Icons.Default.Lock,
            "隐私设置",
            "数据管理"
        )


    }

}
