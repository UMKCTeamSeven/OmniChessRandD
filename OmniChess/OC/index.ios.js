import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

var Game = require('./app/Game');

class OC extends Component {
  render() {
    return (
      <View style={{ flexDirection: 'column', flex: 1}}>
          <Game />
      </View>
    );
  }
}

AppRegistry.registerComponent('OC', () => OC);
