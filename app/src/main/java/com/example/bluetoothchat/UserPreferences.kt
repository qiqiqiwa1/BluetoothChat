package com.example.bluetoothchat


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID


private val Context.dataStore by preferencesDataStore(
    name = "user_profile"
)


class UserPreferences(
    private val context: Context
) {


    companion object {

        private val NICKNAME =
            stringPreferencesKey("nickname")


        private val AVATAR =
            stringPreferencesKey("avatar")


        private val USER_ID =
            stringPreferencesKey("user_id")

    }



    val userProfile: Flow<UserProfile> =

        context.dataStore.data.map { data ->


            var id =
                data[USER_ID]


            if(id == null){

                id =
                    UUID.randomUUID()
                        .toString()

            }


            UserProfile(

                nickname =
                data[NICKNAME] ?: "",


                avatar =
                data[AVATAR] ?: "",


                userId = id

            )

        }



    suspend fun saveNickname(
        nickname:String
    ){

        context.dataStore.edit { data ->


            data[NICKNAME] =
                nickname


            if(data[USER_ID] == null){

                data[USER_ID] =
                    UUID.randomUUID()
                        .toString()

            }

        }

    }

}
