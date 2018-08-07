# react-native-network-state

## Features

- [x] Detect network connection instancely
- [x] Support `onConnected`, `onDisconnected` callback
- [x] Highly customizable UI

## Getting started

`$ npm install react-native-network-state --save`

### Mostly automatic installation

`$ react-native link react-native-network-state`

### Manual installation

#### iOS

1.  In XCode, in the project navigator, right-click `Libraries` ➜ `Add Files to [your project's name]`
2.  Go to `node_modules` ➜ `react-native-network-state` and add `RNNetworkState.xcodeproj`
3.  In XCode, in the project navigator, select your project. Add `libRNNetworkState.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4.  Run your project (`Cmd+R`)<

#### Android

1.  Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import com.reactnativevietnam.networkstate.RNNetworkStatePackage;` to the imports at the top of the file
- Add `new RNNetworkStatePackage()` to the list returned by the `getPackages()` method

2.  Append the following lines to `android/settings.gradle`:
    ```
    include ':react-native-network-state'
    project(':react-native-network-state').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-network-state/android')
    ```
3.  Insert the following lines inside the dependencies block in `android/app/build.gradle`:

    ```
      compile project(':react-native-network-state')
    ```

### Extra setup (Required)

#### iOS

1.  Create and paste these line into `ios/Podfile`

        ```
            source 'https://github.com/CocoaPods/Specs.git'
            platform :ios, '9.0'
            target 'TargetName' do
                pod 'AFNetworking', '~> 3.0' //This line
            end
        ````

2.  Run `pod install` or `pod update`

#### Android

1.  Insert these lines to `AndroidManifest.xml`

    ```
    // ask permissions
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

    <application ...>
        ...
        // these lines
        <receiver android:name="com.reactnativevietnam.NetworkReceiver" >
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>
    </application>
    ```

## Usage

```javascript
import RNNetworkState from "react-native-network-state"

// TODO: What to do with the module?
RNNetworkState
```

## LICENSE

MIT License

Copyright (c) 2018-present, ReactNativeVietnam.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
