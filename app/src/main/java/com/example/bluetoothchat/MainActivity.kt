package com.example.bluetoothchat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.*
import com.example.bluetoothchat.theme.BluetoothChatTheme
import com.example.bluetoothchat.ui.*



class MainActivity :
    ComponentActivity(){


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



data class BottomItem(

    val name:String,

    val route:String,

    val icon:ImageVector

)



@Composable
fun AppNavigation(){


    val navController =
        rememberNavController()



    val items = listOf(

        BottomItem(
            "首页",
            "home",
            Icons.Default.Home
        ),


        BottomItem(
            "联系人",
            "contacts",
            Icons.Default.People
        ),


        BottomItem(
            "我的",
            "profile",
            Icons.Default.Person
        )

    )



    Scaffold(

        bottomBar = {


            NavigationBar {


                items.forEach {


                    item ->


                    NavigationBarItem(

                        selected = false,


                        onClick = {

                            navController
                                .navigate(item.route)

                        },


                        icon = {

                            Icon(

                                item.icon,

                                contentDescription = null

                            )

                        },


                        label = {

                            Text(item.name)

                        }

                    )

                }


            }


        }


    ){


        NavHost(

            navController,

            startDestination = "home"

        ){


            composable("home"){

                HomeScreen()

            }


            composable("contacts"){

                ContactScreen()

            }


            composable("profile"){

                ProfileScreen()

            }

        }


    }


}
