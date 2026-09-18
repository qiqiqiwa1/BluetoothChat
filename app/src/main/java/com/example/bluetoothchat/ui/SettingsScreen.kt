package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun SettingsScreen(){


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)

    ){


        Text(

            text = "设置",

            style =
            MaterialTheme.typography.headlineMedium

        )


        Spacer(
            Modifier.height(24.dp)
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
            "管理连接"
        )


        SettingItem(
            Icons.Default.Lock,
            "隐私设置",
            "数据安全"
        )


        SettingItem(
            Icons.Default.Delete,
            "清除数据",
            "删除本地聊天记录"
        )


    }

}



@Composable
fun SettingItem(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title:String,

    subtitle:String

){


    Card(

        modifier =
        Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)

    ){


        Row(

            modifier =
            Modifier.padding(16.dp)

        ){


            Icon(

                icon,

                contentDescription=null

            )


            Spacer(
                Modifier.width(16.dp)
            )


            Column {


                Text(

                    title,

                    style =
                    MaterialTheme.typography.titleMedium

                )


                Text(

                    subtitle

                )

            }


        }


    }


}
