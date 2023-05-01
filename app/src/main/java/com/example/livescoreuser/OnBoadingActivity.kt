package com.example.livescoreuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import com.example.livescoresdu.uilibrary.values.SharedPreferencesHelper
import com.example.livescoreuser.presentation.Welcome

class OnBoadingActivity: FragmentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPreferencesHelper.init(this)
        setContent{
            if (SharedPreferencesHelper.getWelcome()){
                val intent = Intent(this@OnBoadingActivity,MainActivity::class.java)
                start(this@OnBoadingActivity,intent)
            }else {
                Welcome(onClick = {
                    val intent = Intent(this@OnBoadingActivity,MainActivity::class.java)
                    start(this@OnBoadingActivity,intent)
                },
                )
            }

        }
    }
}
private fun start(activity: OnBoadingActivity, intent: Intent) {
    Log.e("TAG", "start:DDDDDDDDDDDD ", )
    activity.startActivity(intent)
//    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    activity.finish()
}