//@flow

import React from "react"
import {
  View,
  Text,
  TouchableOpacity,
  NetInfo,
  ViewProperties,
  StyleSheet,
  NativeModules,
  NativeEventEmitter
} from "react-native"

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

type State = {
  isConnected: boolean,
  type: string,
  isFast: boolean,
  visible: boolean
}
type NetworkData = {
  isConnected: boolean,
  type: string,
  isFast: boolean
}

const RNNetworkStatusEventEmitter = new NativeEventEmitter(
  NativeModules.RNNetworkStatus
)

export default class NetworkState extends React.PureComponent<Props> {
  static defaultProps = {
    debound: 1500,
    txtConnected: "Connected",
    txtDisconnected: "No Internet Connection",
    onConnected: () => {},
    onDisconnected: () => {}
  }

  state: State = {
    visible: false,
    isConnected: true,
    type: "unknown",
    isFast: true
  }

  _TIMEOUT = null
  _listener: any = null

  constructor(props: Props) {
    super(props)

    const { onConnected, onDisconnected } = this.props
    this._listener = RNNetworkStatusEventEmitter.addListener(
      "networkChanged",
      (data: NetworkData) => {
        if (this.state.isConnected !== data.isConnected) {
          data.isConnected ? onConnected(data) : onDisconnected(data)
          this.setState({ ...data, visible: true })
        }
      }
    )
  }

  componentWillUnmount() {
    this._listener.remove()
  }

  render() {
    const {
      txtConnected,
      txtDisconnected,
      styleConnected,
      styleDisconnected,
      debound,
      ...viewProps
    } = this.props

    if (this.state.visible && this.state.isConnected) {
      this._TIMEOUT && clearTimeout(this._TIMEOUT)
      this._TIMEOUT = setTimeout(() => {
        this.setState({ visible: false })
      }, debound)
    }
    if (!this.state.visible) {
      return <View />
    }
    return (
      <View style={styles.container} {...viewProps}>
        <Text
          style={[
            this.state.isConnected ? styles.txtSuccess : styles.txtError,
            this.state.isConnected && styleConnected && styleConnected,
            !this.state.isConnected && styleDisconnected && styleDisconnected
          ]}
        >
          {this.state.isConnected
            ? txtConnected || "Connected"
            : txtDisconnected || "No Internet Connection"}
        </Text>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0
  },
  txtSuccess: {
    paddingVertical: 5,
    color: "#fff",
    backgroundColor: "#4caf50",
    textAlign: "center"
  },
  txtError: {
    paddingVertical: 5,
    color: "#fff",
    backgroundColor: "#f44336",
    textAlign: "center"
  }
})
