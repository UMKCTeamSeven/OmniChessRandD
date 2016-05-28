import React, { Component } from 'react';
import {
  Text,
  View
} from 'react-native';

var BoardUI = require('./UI/Board');
var Controls = require('./Controls');


var Board = require('./obj/Board');

class Game extends Component {
  constructor(props) {
    super(props)
    this.state = {
      board: new Board({game:this})
    }
  }
  render() {
    return (
      <View style={{ backgroundColor:'black', marginTop: 18, flex: 1, flexDirection: 'column'}}>
      	<BoardUI board={this.state.board}/>
      	<Controls board={this.state.board} />
      </View>
    );
  }
}

module.exports = Game