# BasisID iOS SDK

## Table of contents
*   [Description](#description)
*   [Getting started](#getting-started)
	*   [Requirements](#requirements)
	*   [SDK keys](#sdk-keys)
	*   [Camera usage description](#camera-usage-description)
*   [Installation](#installation)
*   [Usage](#usage)
*   [Localization](#localization)

## Description
BasisID SDK for realtime verification.

## Getting started
### Requirements
- Xcode 11+
- Swift 5.1+
- iOS 13+

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

### Camera usage description
The app’s Info.plist must contain the NSCameraUsageDescription key with a string value explaining to the user how the app uses this data. Example: “This will allow to take video and photos of your documents."

## Installation

SDK is available through [CocoaPods](http://cocoapods.org). To install
it, simply add the following line to your `Podfile`:
```ruby
pod 'BasisIDSdk', '~> 1.0.0'
```

## Usage

At first, import `BasisIDSdk` to a `.swift` file from which you plan to start the verification flow.
```swift
import BasisIDSdk
```

Then call `BasisIDSdk.initBasisID` method from the place in your code that responds to starting the verification flow.

```swift
let sdkBasisID = BasisIDSdk.initBasisID(
	apiKey: "{API_key}",
	apiFormToken: "{api_form_token}",
	region: "europe",
	callback: callback
)
self.present(sdkBasisID, animated: true, completion: nil)
```

For receiving results from SDK you need to create a callback function:

```swift
func callback(_ status: String, _ code: String) {
    print(status, code)

    switch status {
    // ok status
    case "ok":
    	switch code {
    	case "video":
    		print("first step completed")
    	case "full":
    		print("verification completed")
    	default:
    		break
    	}
    // error status
    case "error":
    	switch code {
    	case "api":
    		print("api system error")
    	case "step_timeout":
    		print("verification step timeout exceed")
    	case "manual_review":
    		print("profile sent to manual review")
    	default:
    		break
    	}
    default:
    	break
    }
}
```


### Callback values

Status can has 2 values: "ok" and "error".


Error codes:

| `Error code` | Description |
| ----- | ----- |
| `step_timeout` | The user spent too much time on the second step of verification. |
| `manual_review` | The user was unable to pass verification and was sent for manual verification.  |
| `api` | BASIS ID API response error. |

### Localization

User can choose SDK language on SDK start.

Supported languages:

English (en)
Spanish (es)
Russian (ru)
French (fr)
Vietnamese (vi)
Chinese (zh)
Polish (pl)
Japanese (ja)
Korean (ko)
Deutsch (de)
Hebrew (he)
Portuguese (pt)


