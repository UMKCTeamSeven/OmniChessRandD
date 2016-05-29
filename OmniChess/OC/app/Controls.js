import React, { Component } from 'react';
import {
  Text,
  View
} from 'react-native';

class Controls extends Component {
  render() {
    return (
      <View style={{ backgroundColor:'orange', flex: 1}}>
      	<Text>
      		controls here
      	</Text>
      </View>
    );
  }
}

module.exports = Controls