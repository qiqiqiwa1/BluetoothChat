package com.example.bluetoothchat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.bluetoothchat.ui.ContactScreen
import com.example.bluetoothchat.ui.HomeScreen
import com.example.bluetoothchat.ui.ProfileScreen


class MainActivity :
    ComponentActivity() {


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        setContent {

            MaterialTheme {

                BluetoothChatApp()

            }

        }

    }

}



@Composable
fun BluetoothChatApp() {


    val navController =
        rememberNavController()


    Scaffold(

        bottomBar = {


            NavigationBar {


                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate("home")

                    },

                    icon = {},

                    label = {

                        Text("首页")

                    }

                )


                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate("contacts")

                    },

                    icon = {},

                    label = {

                        Text("联系人")

                    }

                )


                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate("profile")

                    },

                    icon = {},

                    label = {

                        Text("我的")

                    }

                )


            }

        }

    ) {


        NavHost(

            navController = navController,

            startDestination = "home"

        ) {


            composable("home") {

                HomeScreen()

            }


            composable("contacts") {

                ContactScreen()

            }


            composable("profile") {

                ProfileScreen()

            }


        }

    }

}
