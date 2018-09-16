/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, Image, PixelRatio, Switch } from 'react-native';
import NetworkState, { Settings } from 'react-native-network-state';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' + 'Shake or press menu button for dev menu'
});

type Props = {};
export default class App extends Component<Props> {
  state = {
    layoutOne: true,
    connected: Settings.isConnected
  };

  renderNoInternet() {
    return (
      <View>
        <Image source={require('./unicorn.png')} style={styles.unicorn} />
        <Text style={styles.welcome}>No internet available</Text>
      </View>
    );
  }

  renderNormal() {
    return (
      <View>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
        <Text style={styles.instructions}>To get started, edit App.js</Text>
        <Text style={styles.instructions}>{instructions}</Text>
      </View>
    );
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={styles.switch}>
          <Text>Change style: </Text>
          <Switch
            value={this.state.layoutOne}
            onValueChange={() => this.setState(prev => ({ layoutOne: !prev.layoutOne }))}
          />
        </View>
        {this.state.connected ? this.renderNormal() : this.renderNoInternet()}
        <NetworkState
          visible={!this.state.layoutOne}
          onDisconnected={() => this.setState({ connected: false })}
          onConnected={() => this.setState({ connected: true })}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5
  },
  unicorn: {
    width: 150 * PixelRatio.getFontScale(),
    height: 150 * PixelRatio.getFontScale()
  },
  switch: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center'
  }
});
