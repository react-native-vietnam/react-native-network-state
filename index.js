import React from 'react';
import PropType from 'prop-types';
import {
  View,
  Text,
  StyleSheet,
  LayoutAnimation,
  UIManager,
  NativeModules,
  NativeEventEmitter
} from 'react-native';
import I18n from 'react-native-i18n';

UIManager.setLayoutAnimationEnabledExperimental &&
  UIManager.setLayoutAnimationEnabledExperimental(true);
const { RNNetworkState } = NativeModules;
const NetworkEventEmitter = new NativeEventEmitter(RNNetworkState);

type Props = {
  onDisconnected: Function,
  onConnected: Function,
  connectedText: String,
  disconnectedText: String,
  interval: Number, //second
  delayHide: Number //second,
  disconnectedStyle: Object,
  connectedStyle: Object,
};
export default class NetworkState extends React.PureComponent<Props> {
  static defaultProps = {
    onDisconnected: undefined,
    onConnected: undefined,
    connectedText: 'Connected',
    disconnectedText: 'No internet connection',
    interval: 3, //second
    delayHide: 2 //second,
    disconnectedStyle: {},
    connectedStyle: {}
  };

  constructor(props) {
    super(props);
    this.state = {
      isConnected: true,
      hide: true
    };
    this.pingPongSubcription = NetworkEventEmitter.addListener('pingPong', this.onConnectionChange);
    if(this.props.interval <= 0) {
      throw Error('interval must greater than 0 second');
    }
    if (this.props.delayHide < 0) {
      throw Error('delayHide must equal or greater than 0 second');
    }
  }

  componentDidMount() {
    RNNetworkState.startPing(this.props.interval);
  }

  componentWillUnmount() {
    RNNetworkState.stopPing();
    this.pingPongSubcription.remove();
  }

  onConnectionChange = ({ connected }) => {
    if (connected === this.state.isConnected) {
      return;
    }

    // callback function
    const { onConnected, onDisconnected, delayHide } = this.props;

    LayoutAnimation.configureNext(LayoutAnimation.Presets.easeInEaseOut);
    this.setState({ isConnected: connected, hide: false });
    if (connected) {
      onConnected && onConnected();
      this.timeout && clearTimeout(this.timeout);
      if (delayHide === 0) {
        this.setState({ hide: true });
      } else {
        this.timeout = setTimeout(() => {
          this.setState({ hide: true });
        }, (delayHide * 1000));
      }
      return;
    }
    onDisconnected && onDisconnected();
  };

  render() {
    const { connectedStyle, disconnectedStyle } = this.props;
    return (
      <View
        style={[
          this.state.isConnected
            ? { backgroundColor: '#8BC34A', ...connectedStyle }
            : { backgroundColor: '#FDAE3A', ...disconnectedStyle },
          this.state.hide ? { height: 0 } : { height: 'auto' }
        ]}
      >
        <Text style={styles.connectionText}>
          {this.state.isConnected ? this.props.connectedText : this.props.disconnectedText}
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  connectionText: {
    alignSelf: 'center',
    color: '#fff',
    paddingVertical: 3
  }
});
