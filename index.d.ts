declare module 'react-native-network-state' {
  import * as React from 'react';
  import * as ReactNative from 'react-native';

  export interface NetworkStateProps extends ReactNative.ViewProps {
    visible?: boolean,
    debound?: number,
    txtConnected?: string,
    txtDisconnected?: string,
    styleConnected?: Object | Number,
    styleDisconnected?: Object | Number,
    onConnected?: Function,
    onDisconnected?: Function,
    style?: number | Object | Array<number | Object>
  }

  export interface Settings {
    openWifi(): void;
  }
  export class NetworkState extends React.PureComponent<NetworkStateProps> { }

  export default NetworkState;
}
