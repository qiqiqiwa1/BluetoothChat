package com.example.bluetoothchat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.bluetoothchat.theme.BluetoothChatTheme
import com.example.bluetoothchat.ui.*


class MainActivity : ComponentActivity(){


    override fun onCreate(
        savedInstanceState: Bundle?
    ){

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()


        setContent {

            BluetoothChatTheme {

                AppNavigation()

            }

        }

    }

}



@Composable
fun AppNavigation(){


    val navController =
        rememberNavController()



    val items = listOf(

        Triple(
            "首页",
            "home",
            Icons.Default.Home
        ),

        Triple(
            "联系人",
            "contacts",
            Icons.Default.People
        ),

        Triple(
            "我的",
            "profile",
            Icons.Default.Person
        )

    )



    Scaffold(

        bottomBar = {


            NavigationBar {


                items.forEach { item ->


                    NavigationBarItem(

                        selected =
                        false,


                        onClick = {


                            navController.navigate(
                                item.second
                            )


                        },


                        icon = {


                            Icon(
                                item.third,
                                null
                            )


                        },


                        label = {


                            Text(
                                item.first
                            )


                        }


                    )


                }


            }


        }


    ){


        NavHost(

            navController = navController,

            startDestination = "home"

        ){


            composable("home"){

                HomeScreen()

            }


            composable("contacts"){

                ContactScreen()

            }


            composable("profile"){

                ProfileScreen(
                    navController
                )

            }



            composable("settings"){

                SettingsScreen(
                    navController
                )

            }



            composable("about"){

                AboutScreen(
                    navController
                )

            }


        }


    }


}
