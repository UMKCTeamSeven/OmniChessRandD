import React, { Component } from 'react';
import {
	Dimensions,
  Text,
  Image,
  View,
  TouchableOpacity
} from 'react-native';
import _ from 'lodash';

class Square extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }
  shouldComponentUpdate(){
    return true
  }
  render() {
    return this.showBorderHighlight.call(this)
  }
  cellAction(){
    let {cellState, piece, board} = this.props.square

    if( (_.isEmpty(cellState) && !piece)){
      //do nothing
    }else if(cellState.canMove){
      board.moveCell.call(board, this.props.coords)
    }else if(cellState.canTake){
      board.takeCell.call(board, this.props.coords)
    }else{
      board.toggleCellActive.call(board, this.props.coords)
    }
  }
  showBorderHighlight() {
    let w, color
    let {cellState} = this.props.square

    if(cellState.isActive){
      w = 3;
      color = 'blue';
    }else if(cellState.canMove){
      w = 3;
      color = 'green';
    }else if(cellState.canTake){
      w = 3;
      color = 'red';
    }else{
      w = 1;
      color = 'lightgray';
    }

    return (
      <View style={{shadowColor: 'lightgray',shadowOffset:{height:1,width:0},
                borderRadius:4, margin:1, borderWidth:w, borderColor: color,
                    backgroundColor: color, flex: 1, flexDirection: 'column'}}>
          { this.showCell.call(this) }
      </View>
    )
  }
  showCell() {
    let {r, c} = this.props.coords
    let color = 'white'

    if( (r%2==0 && c%2==1) || (r%2==1 && c%2==0) )
      color = '#000000'

    return (
      <View style={{backgroundColor: color, flex: 1, alignItems: 'stretch'}}>
        <TouchableOpacity style={{flex: 1}} onPress={this.cellAction.bind(this)}>
          <View style={{flex: 1}}>
            { this.showPiece.call(this) }
          </View>
        </TouchableOpacity>
      </View>
    )
  }
  showPiece(){
    if(this.props.square.piece){
      let {piece} = this.props.square

      return(
          <Image
            resizeMode="cover"
            style={{ flex:1, width: null, height: null }}
            source={ piece.getPic() } />
      )
    }
  }

}

module.exports = Square