package com.example.bluetoothchat


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun ChatScreen(){


    var input by remember {

        mutableStateOf("")

    }


    val messages =
        remember {

            mutableStateListOf(

                Message(
                    text = "你好",
                    isMine = false
                ),

                Message(
                    text = "你好，这是蓝牙聊天测试",
                    isMine = true
                )

            )

        }


    val listState =
        rememberLazyListState()



    Column(

        modifier =
        Modifier
            .fillMaxSize()

    ){


        Text(

            text = "聊天",

            style =
            MaterialTheme.typography.headlineMedium,

            modifier =
            Modifier.padding(16.dp)

        )



        LazyColumn(

            modifier =
            Modifier
                .weight(1f)
                .fillMaxWidth(),

            state = listState

        ){

            items(messages){

                msg ->

                MessageBubble(msg)

            }

        }



        Row(

            modifier =
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),

            verticalAlignment =
            Alignment.CenterVertically

        ){


            OutlinedTextField(

                value = input,

                onValueChange = {

                    input = it

                },

                modifier =
                Modifier.weight(1f),

                placeholder = {

                    Text("输入消息")

                }

            )


            Spacer(
                Modifier.width(8.dp)
            )


            Button(

                onClick = {


                    if(input.isNotBlank()){


                        messages.add(

                            Message(

                                text=input,

                                isMine=true

                            )

                        )


                        input=""

                    }

                }

            ){

                Text("发送")

            }

        }

    }

}





@Composable
fun MessageBubble(

    message: Message

){


    Row(

        modifier =
        Modifier
            .fillMaxWidth()
            .padding(8.dp),

        horizontalArrangement =

        if(message.isMine)

            Arrangement.End

        else

            Arrangement.Start

    ){


        Surface(

            shape =
            RoundedCornerShape(16.dp),

            color =

            if(message.isMine)

                MaterialTheme.colorScheme.primary

            else

                MaterialTheme.colorScheme.surfaceVariant

        ){

            Text(

                text = message.text,

                modifier =
                Modifier.padding(12.dp)

            )

        }

    }

}
