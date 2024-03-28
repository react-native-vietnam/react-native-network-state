# react-native-network-state-connect-internet

[![NPM](https://nodei.co/npm/react-native-network-state.png)](https://nodei.co/npm/react-native-network-state/)

## Features

- [x] Detect network connection instantly
- [x] Support `onConnected`, `onDisconnected` callback
- [x] Highly customizable UI
- [x] Open Wifi setting
- [x] Android 8+ supported

## Demo

- <img src="https://media.giphy.com/media/8FrypHU22FFYF52s7x/giphy.gif" width="480" />

## Getting started

`$ npm install react-native-network-state --save`

or

`$ yarn add react-native-network-state-connect-internet`

### Automatic installation

`$ react-native link react-native-network-state-connect-internet`

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

1.  Add `SystemConfiguration.framework` to your `Linked Frameworks and Libraries`, see images below:

    <p>1.1</p>

    <img src="https://image.prntscr.com/image/-HO3fqNmSfK9QbAW1Ti1gA.png" width="320"><br/>

    <p>1.2</p>
    <img src="https://image.prntscr.com/image/UTYP9HT1QUSrLfTaDHEFJg.png" width="320">

#### Android

1.  Insert these lines to `AndroidManifest.xml`

        ```
        // add this permissions
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        ```

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
        <NetworkState
          style={{ position: 'absolute', top: 0, right: 0, bottom: 0, left: 0 }}
          onConnected={() => console.log('connected')}
          onDisconnected={() => Settings.openWifi()}
        />
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

## Settings Utils (iOS below 10 can open Wifi setting, equal or greater than 10 will open general settings)

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
