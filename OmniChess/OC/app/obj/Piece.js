import _ from 'lodash'

var pics = {
  white:{
    pawn: require("../drawable-xxhdpi/ni_pawnw.png"),
    rook: require("../drawable-xxhdpi/ni_rookw.png"),
    knight: require("../drawable-xxhdpi/ni_wknight.png"),
    bishop: require("../drawable-xxhdpi/ni_bishopw.png"),
    king: require("../drawable-xxhdpi/ni_wking.png"),
    queen: require("../drawable-xxhdpi/ni_queenw.png")
  },
  black:{
    pawn: require("../drawable-xxhdpi/ni_pawn.png"),
    rook: require("../drawable-xxhdpi/ni_rook.png"),
    knight: require("../drawable-xxhdpi/ni_bknight.png"),
    bishop: require("../drawable-xxhdpi/ni_bishop.png"),
    king: require("../drawable-xxhdpi/ni_bking.png"),
    queen: require("../drawable-xxhdpi/ni_queen.png")
  }
}
var moves = {
  pawn(){
    let d = (this.props.player=="white") ? -1 : 1
    let moves = {direction:{},attack:{ne: d, nw: d, se: -d, sw: -d}}

    if(this.state.moves.length == 0){
      moves.direction = {n: 2*d, s: 2*-d}
    }else{
      moves.direction = {n: d, s: -d}
    }
    return moves
  },
  rook(){
    return{
      direction: {n: 8, s: 8, e: 8, w: 8},
      attack: {n: 8, s: 8, e: 8, w: 8}
    }
  },
  knight(){
    return{
      direction: {nne:1, nnw:1, sse:1, ssw:1, een:1, ees:1, wwn:1, wws:1},
      attack: {nne:1, nnw:1, sse:1, ssw:1, een:1, ees:1, wwn:1, wws:1}
    }
  },
  bishop(){
    return{
      direction: {ne: 8, se: 8, nw: 8, sw: 8},
      attack: {ne: 8, se: 8, nw: 8, sw: 8}
    }
  },
  king(){
    return{
      direction: {n: 1, s: 1, e: 1, w: 1, ne: 1, se: 1, nw: 1, sw: 1},
      attack: {n: 1, s: 1, e: 1, w: 1, ne: 1, se: 1, nw: 1, sw: 1}
    }
  },
  queen(){
    return{
      direction: {n: 8, s: 8, e: 8, w: 8, ne: 8, se: 8, nw: 8, sw: 8},
      attack: {n: 8, s: 8, e: 8, w: 8, ne: 8, se: 8, nw: 8, sw: 8}
    }
  }
}


class Piece {
  /*
    props:
      type - pawn, knight,...
      player - black, white
  */
  constructor(props) {
    this.props = props
    this.state = {
      moves: []
    }
  }
  getPic(){
    return pics[this.props.player][this.props.type]
  }
  getType(){
    return this.props.type
  }
  getPlayer(){
    return this.props.player
  }
  getMoves(r, c){
    return moves[this.props.type].call(this, r, c)
  }
  move(r, c){
    this.state.moves.push({r: r, c: c})
  }
}

module.exports = Piece