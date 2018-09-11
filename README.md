# react-native-network-state

[![NPM](https://nodei.co/npm/react-native-network-state.png)](https://nodei.co/npm/react-native-network-state/)

## Features

- [x] Detect network connection instancely
- [x] Support `onConnected`, `onDisconnected` callback
- [x] Highly customizable UI
- [x] Open Wifi setting
- [ ] Android 8.0+ => Bug

## Demo

- <img src="https://media.giphy.com/media/8FrypHU22FFYF52s7x/giphy.gif" width="480" />

## Getting started

`$ npm install react-native-network-state --save`

or

`$ yarn add react-native-network-state`

### Automatic installation

`$ react-native link react-native-network-state`

### Manual installation

#### iOS (Install via Cocoapods is not supported righ now)

1.  In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2.  Go to `node_modules` ➜ `react-native-network-state` and add `RNNetworkState.xcodeproj`
3.  In XCode, in the project navigator, select your project. Add `libRNNetworkState.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4.  Run your project (`Cmd+R`)<

#### Android

1.  Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import com.reactnativevietnam.RNNetworkStatePackage;` to the imports at the top of the file
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

### Extra setup (Very important)

#### iOS

1.  Add `SystemConfiguration.framework` to your `Linked Frameworks and Libraries`, see images bellow:

    <p>1.1</p>

    <img src="https://image.prntscr.com/image/-HO3fqNmSfK9QbAW1Ti1gA.png" width="320"><br/>

    <p>1.2</p>
    <img src="https://image.prntscr.com/image/UTYP9HT1QUSrLfTaDHEFJg.png" width="320">

#### Android

1.  Insert these lines to `AndroidManifest.xml`

        ```
        // ask permissions
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

        <application ...>
            ...
            // insert these lines
            <receiver android:name="com.reactnativevietnam.NetworkReceiver" >
                <intent-filter>
                    <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
                </intent-filter>
            </receiver>

            <!-- FIX 18:9 screen ratio -->
            <meta-data android:name="android.max_aspect" android:value="2.1" />
        </application>
        ```

2.  Upgrade to build tool `3.1.3`:

        ```
        // android/build.gradle

        buildscript {
            dependencies {
                classpath 'com.android.tools.build:gradle:3.1.3' // HERE
                ...
            }
            ...
        }
        ```

3.  Upgrade gradle to `4.4`

    ```
    // android/gradle/wrapper/gradle-wrapper.properties

    distributionUrl=https\://services.gradle.org/distributions/gradle-4.4-all.zip
    ```

4.  Upgrade compileSdkVersion to `27`, buildToolsVersion to `27.0.3`, targetSdkVersion to 27

5.  Run script fix android module gradle

```
node node_modules/react-native-network-state/fixAndroid.js
```

#### Fix Android Gradle

## Usage

Please see `example` project

```javascript
import React from 'react'
import NetworkState, { Settings } from 'react-native-network-state'
import { View, Text } from 'react-native'

export default class YourView extends React.PureComponent {
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>This is Your View</Text>
        <NetworkState onDisconnected={() => Settings.openWifi()} />
      </View>
    )
  }
}
```

## Props

```javascript
type Props = {
  debound?: number,
  txtConnected?: string,
  txtDisconnected?: string,
  styleConnected?: Object | Number,
  styleDisconnected?: Object | Number,
  onConnected?: Function,
  onDisconnected?: Function,
  ...ViewProperties
}
```

## Settings Utils (iOS bellow 10 can open Wifi setting, equal or greater than 10 will open general settings)

| Functions | iOS | Android |
| --------- | :-: | :-----: |
| openWifi  | [x] |   [x]   |

```javascript
//Example: Open wifi setting

import NetworkState, { Settings } from 'react-native-network-state'

Settings.openWifi()
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
