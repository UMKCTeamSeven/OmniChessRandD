import React, { Component } from 'react';
import {
  Text,
  View,
  Image
} from 'react-native';
import _ from 'lodash'

class Controls extends Component {
  constructor(props) {
    super(props)
    this.props = props
    this.board = props.board.getBoard()
    this.players = props.board.getPlayers()
  }
  currentPlayerTurn(){
    return this.players[0].isActiveTurn() ? 0 : 1
  }
  showPiece(piece, indx){
    return(
      <Image
        key={indx}
        resizeMode="cover"
        style={{ flex:1, width: 25, height: 25 }}
        source={ piece.getPic() } />
    )
  }
  render() {
    return (
      <View style={{ backgroundColor:'orange', flex: 1}}>
        <Text>
          Player: {this.players[this.currentPlayerTurn.call(this)].getPlayer()}
        </Text>
        <Text>
          White: { this.players[0].getTaken().map(this.showPiece) }
        </Text>
        <Text>
          Black: { this.players[1].getTaken().map(this.showPiece) }
        </Text>
      </View>
    );
  }
}

module.exports = Controls