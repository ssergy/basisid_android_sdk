# basisid_android_sdk

Add SdkBasisID-release.aar in libs

add dependencies in build.gradle<br/>
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")<br/>
implementation files('libs/SdkBasisID-release.aar')<br/>



For example


package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity<br/>
import android.os.Bundle<br/>
import android.widget.Button<br/>
import com.sdk.basis.SdkBasisIDCallback<br/>
import android.content.Intent<br/>
import android.widget.EditText<br/>


class MainActivity : AppCompatActivity() {<br/>
    override fun onCreate(savedInstanceState: Bundle?) {<br/>
        super.onCreate(savedInstanceState)<br/>
        setContentView(R.layout.activity_main)<br/>

        val btn: Button = findViewById(R.id.button_start)<br/>
        btn.setOnClickListener {<br/>
            val cb = CallbackSdk()<br/>
            var token: EditText = findViewById(R.id.token)<br/>
            com.sdk.basis.SdkBasisID.initBasisID("", token.text.toString(), "europe", cb)<br/>
            val intent = Intent(this, com.sdk.basis.SdkMainActivity::class.java)<br/>
            startActivity(intent)<br/>
        }<br/>
    }<br/>

    class CallbackSdk: SdkBasisIDCallback {<br/>
        override fun send(status: String, code: String)     {<br/>
            println("DEBUG callbackSdk status = $status  code = $code")<br/>
        }<br/>
    }<br/>
}<br/>
