package com.example.bluetoothchat.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun ContactScreen(){


    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)

    ){


        Text(

            text = "联系人",

            style =
            MaterialTheme.typography.headlineMedium

        )


        Spacer(

            Modifier.height(24.dp)

        )


        Card(

            modifier =
            Modifier.fillMaxWidth()

        ){


            Column(

                modifier =
                Modifier.padding(24.dp),

                horizontalAlignment =
                Alignment.CenterHorizontally

            ){


                Icon(

                    imageVector =
                    Icons.Default.People,

                    contentDescription = null,

                    modifier =
                    Modifier.size(60.dp)

                )


                Spacer(

                    Modifier.height(16.dp)

                )


                Text(

                    text = "暂无联系人",

                    style =
                    MaterialTheme.typography.titleMedium

                )


                Spacer(

                    Modifier.height(8.dp)

                )


                Text(

                    text = "搜索附近蓝牙设备"

                )


            }


        }


    }

}
