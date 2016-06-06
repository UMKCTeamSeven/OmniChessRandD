import React, { Component } from 'react';
import {
	Dimensions,
  Text,
  View
} from 'react-native';
import _ from 'lodash';

var {width} = Dimensions.get('window');
var Square = require('./Square');
var PromotionScreen = require('./PromotionScreen');

class Board extends Component {
  /*
    props:
      board
  */
  constructor(props) {
    super(props)
    this.state = {
    }
  }
  render() {
    if(this.props.board.getScreen() == "promotionScreen")
      return (<PromotionScreen board={this.props.board}></PromotionScreen>)
    else
      return this.gameBoard.call(this)
  }
  gameBoard(){
    return (
      <View style={{height: width, flexDirection: 'column', margin: 3 }}>
        { _.map(this.props.board.getBoard(), this.makeRows.bind(this)) }
      </View>
    );
  }
  makeRows(row, rIndx) {
    return (
      <View key={rIndx} style={{flex: 1, flexDirection: 'row'}}>
      	{ _.map(row, this.makeCols.bind(this, rIndx)) }
      </View>
    );
  }
  makeCols(rIndx, cell, cIndx) {
    return (
      <View key={cIndx} style={{backgroundColor:'lightgray', flex: 1, flexDirection: 'column'}}>
      	<Square square={cell} coords={{r:rIndx, c:cIndx}}/>
      </View>
    );
  }
}

module.exports = Board