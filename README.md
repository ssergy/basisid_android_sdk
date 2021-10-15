# BasisID Android SDK

## Table of contents
*   [Description](#description)
*   [Getting started](#getting-started)
        *   [Requirements](#Requirements)   
	*   [SDK keys](#sdk-keys)
*   [Installation](#installation)
*   [Usage](#usage)

## Description
BasisID Android for realtime verification.

## Getting started

### Requirements
- Android sdk version 21+

### SDK keys
Before you start please get credentials (API KEY and Secret 1) in BASIS ID CRM panel.

For starting the SDK you need to receive `api_form_token key` on your backend and place it to SDK request.
Backend request example:

```bash
$ curl \
-H "Content-Type: application/json" \
-X POST -d '{"key": "API_Key", "secret": "Secret_key_1"}' \
https://api.basisid.com/auth/base-check
```

Response:

```json
{"api_form_token":"622c8db2-dc49-47b6-b199-35e50a709b56","status":"ok","user_hash":"3fa11465-2678-4311-b9eb-4654c936424c","user_id":1482783}
```


## Installation

```
Add in settings.gradle
maven { url "https://jitpack.io" } 

Add dependencies in build.gradle

implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
implementation 'com.github.ssergy:basisid_android_sdk_jitpack:1.0.1'
```


## Usage

For receiving results from SDK you need to create a callback function:
```
import com.sdk.basis.SdkBasisIDCallback

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
```



Then call `SdkBasisID` method from the place in your code that responds to starting the verification flow.

```
val cb = CallbackSdk(::goToEnd) // your callback function
com.sdk.basis.SdkBasisID.initBasisID("{API_key}", "{api_form_token}", "europe", cb)
val intent = Intent(this, com.sdk.basis.SdkBasisIDMainActivity::class.java)
startActivity(intent)
```


### Callback values

Status can has 2 values: "ok" and "error".

OK codes:

| `OK code` | Description |
| ----- | ----- |
| `video` | first step completed |
| `full` | verification completed |
| `finish` | finish screen is show |




Error codes:

| `Error code` | Description |
| ----- | ----- |
| `step_timeout` | The user spent too much time on the second step of verification. |
| `manual_review` | The user was unable to pass verification and was sent for manual verification.  |
| `api` | BASIS ID API response error. |
