# react-native-network-state

## Features

- [x] Detect network connection instancely
- [x] Support `onConnected`, `onDisconnected` callback
- [x] Highly customizable UI

## Getting started

`$ npm install react-native-network-state --save`

### Manual installation

#### iOS

1.  `cd ios && pod init`
2.  Paste these line to your `Podfile`

```
# Uncomment the next line to define a global platform for your project
platform :ios, '9.0'

target 'example' do
  # Uncomment the next line if you're using Swift or would like to use dynamic frameworks
  # use_frameworks!

  # Paste these lines
  pod 'Reachability', '3.2'
  pod 'RNNetworkState', path: '../node_modules/react-native-network-state/ios'

  target 'example-tvOSTests' do
    inherit! :search_paths
    # Pods for testing
  end

  target 'exampleTests' do
    inherit! :search_paths
    # Pods for testing
  end

end

# Paste these lines at very bottom of Podfile
post_install do |installer|
    installer.pods_project.targets.each do |target|
        if target.name == "React"
          target.remove_from_project
        end
    end
end
```

3.  Run `pod install`
4.  Add `SystemConfiguration.framework` to your `Linked Frameworks and Libraries`, see images bellow:

    <p>1.1</p>

    <img src="https://image.prntscr.com/image/-HO3fqNmSfK9QbAW1Ti1gA.png" width="320"><br/>

    <p>1.2</p>
    <img src="https://image.prntscr.com/image/UTYP9HT1QUSrLfTaDHEFJg.png" width="320">

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

4.  Insert these lines to `AndroidManifest.xml`

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

## Usage

```javascript
import React from "react"
import NetworkState from "react-native-network-state"
import { View, Text } from "react-native"

export default class YourView extends React.PureComponent {
  render() {
    return (
      <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
        <Text>This is Your View</Text>
        <NetworkState />
      </View>
    )
  }
}
```

## Props

```
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
