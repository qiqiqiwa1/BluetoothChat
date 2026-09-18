package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluetoothchat.data.getDeviceInfo



@Composable
fun ProfileScreen(

    navController: NavController

){


    val device =
        getDeviceInfo()



    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)

    ){


        Text(
            "我的",
            style =
            MaterialTheme.typography.headlineMedium
        )



        Spacer(
            Modifier.height(30.dp)
        )



        Column(

            modifier =
            Modifier.fillMaxWidth(),

            horizontalAlignment =
            Alignment.CenterHorizontally

        ){


            Icon(

                Icons.Default.Person,

                null,

                modifier =
                Modifier.size(90.dp)

            )


            Spacer(
                Modifier.height(12.dp)
            )


            Text(
                "未设置昵称"
            )


        }



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


                Text("设备信息")


                Text(
                    "品牌：${device.manufacturer}"
                )

                Text(
                    "型号：${device.model}"
                )

                Text(
                    "Android：${device.androidVersion}"
                )


            }


        }



        Spacer(
            Modifier.height(20.dp)
        )



        Button(

            modifier =
            Modifier.fillMaxWidth(),

            onClick = {

                navController.navigate(
                    "settings"
                )

            }

        ){


            Icon(
                Icons.Default.Settings,
                null
            )


            Spacer(
                Modifier.width(8.dp)
            )


            Text("设置")


        }




        Button(

            modifier =
            Modifier.fillMaxWidth(),

            onClick = {

                navController.navigate(
                    "about"
                )

            }

        ){


            Icon(
                Icons.Default.Info,
                null
            )


            Spacer(
                Modifier.width(8.dp)
            )


            Text("关于")


        }


    }


}
