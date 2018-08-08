
# react-native-network-state

## Getting started

`$ npm install react-native-network-state --save`

### Mostly automatic installation

`$ react-native link react-native-network-state`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-network-state` and add `RNNetworkState.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNNetworkState.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNNetworkStatePackage;` to the imports at the top of the file
  - Add `new RNNetworkStatePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-network-state'
  	project(':react-native-network-state').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-network-state/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-network-state')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNNetworkState.sln` in `node_modules/react-native-network-state/windows/RNNetworkState.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Network.State.RNNetworkState;` to the usings at the top of the file
  - Add `new RNNetworkStatePackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNNetworkState from 'react-native-network-state';

// TODO: What to do with the module?
RNNetworkState;
```
  