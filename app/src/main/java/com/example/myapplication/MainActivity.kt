package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sdk.basis.SdkBasisIDCallback
import android.content.Intent



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.button_start)
        btn.setOnClickListener {
            val cb = CallbackSdk(::goToEnd)
            com.sdk.basis.SdkBasisID.initBasisID("{API_key}", "{api_form_token}", "europe", cb)
            val intent = Intent(this, com.sdk.basis.SdkBasisIDMainActivity::class.java)
            startActivity(intent)
        }
    }

    fun goToEnd() {
        val intent = Intent(this, End::class.java)
        startActivity(intent)
    }

    class CallbackSdk(val f: () -> Unit) : SdkBasisIDCallback {
        override fun send(status: String, code: String)     {
            println("DEBUG callbackSdk status = $status  code = $code")
            if (status == "ok") {
                when (code) {
                    "finish" -> {
                        f() // show your activity
                    }
                    "video" -> println("first step completed")
                    "full" -> println("verification completed")
                }
            }else {
                when (code) {
                    "step_timeout" -> println("verification step timeout exceed")
                    "manual_review" -> println("profile sent to manual review")
                    "api" -> println("api system error")
                }
            }
        }
    }
}