# basisid_android_sdk

Add SdkBasisID-release.aar in libs

add dependencies in build.gradle
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
implementation files('libs/SdkBasisID-release.aar')



For example
package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sdk.basis.SdkBasisIDCallback
import android.content.Intent
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.button_start)
        btn.setOnClickListener {
            val cb = CallbackSdk()


            var token: EditText = findViewById(R.id.token)

            com.sdk.basis.SdkBasisID.initBasisID("", token.text.toString(), "europe", cb)
            val intent = Intent(this, com.sdk.basis.SdkMainActivity::class.java)
            startActivity(intent)
        }
    }

    class CallbackSdk: SdkBasisIDCallback {
        override fun send(status: String, code: String)     {
            println("DEBUG callbackSdk status = $status  code = $code")
        }
    }
}
