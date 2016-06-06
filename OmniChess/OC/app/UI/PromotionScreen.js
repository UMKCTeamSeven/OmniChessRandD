import React, { Component } from 'react';
import {
	Dimensions,
  Text,
  View,
  Image,
  TouchableOpacity
} from 'react-native';
import _ from 'lodash';

var {width} = Dimensions.get('window');
var Piece = require('../obj/Piece');

class PromotionScreen extends Component {
  /*
    props:
      board
  */
  constructor(props) {
    super(props)
    this.init()
    this.state = {
    }
  }
  init(){
  	let players = this.props.board.getPlayers()
  	let color = players[this.props.board.currentPlayerTurn()].getPlayer()
  	this.pieces = [
	  	new Piece({type:"queen", player: color}),
	  	new Piece({type:"rook", player: color}),
	  	new Piece({type:"knight", player: color}),
	  	new Piece({type:"bishop", player: color}),
	  	new Piece({type:"pawn", player: color})
  	]
  }
  render() {
    return (
      <View style={{height: width, flexDirection: 'column', margin: 3, backgroundColor: "blue"}}>
	      <View sytle={{flex: 1, backgroundColor: "green"}}>
	        <Text style={{color:"red"}}>
	          Upgrade Piece
	        </Text>
	      </View>
	      <View style={{flexDirection: 'row', backgroundColor: "yellow", flex: 1}}>
		      { this.pieces.map(this.showPiece.bind(this)) }
      	</View>
      </View>
    )
  }
  promote(piece){
  	this.props.board.setPromotedPiece.call(this.props.board, piece)
  }
  showPiece(piece, indx){
  	return(
      <TouchableOpacity 
		  		key={indx}
		  		style={{flex: 1}} onPress={ this.promote.bind(this, piece)}>
		  	<Image
		      resizeMode="cover"
		      style={{ width: 50, height: 50 }}
		      source={ piece.getPic() } />
      </TouchableOpacity>
    )
  }
}

module.exports = PromotionScreen