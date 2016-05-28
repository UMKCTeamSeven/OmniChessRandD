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

function makeMoves(r, c, dist, direc){
  let moves = []
  if(direc=="n"){
    for(let i=0;i<dist;i++){
      moves.push({r: r-i, c: c})
    }
  }else if(direc=="s"){
    for(let i=0;i<dist;i++){
      moves.push({r: r+i, c: c})
    }
  }else if(direc=="e"){
    for(let i=0;i<dist;i++){
      moves.push({r: r, c: c+i})
    }
  }else if(direc=="w"){
    for(let i=0;i<dist;i++){
      moves.push({r: r, c: c-i})
    }
  }else if(direc=="ne"){
    for(let i=0;i<dist;i++){
      moves.push({r: r-i, c: c+i})
    }
  }else if(direc=="se"){
    for(let i=0;i<dist;i++){
      moves.push({r: r+i, c: c+i})
    }
  }else if(direc=="nw"){
    for(let i=0;i<dist;i++){
      moves.push({r: r-i, c: c-i})
    }
  }else if(direc=="sw"){
    for(let i=0;i<dist;i++){
      moves.push({r: r+i, c: c-i})
    }
  }
  
  return moves
}
var moves = {
    pawn(r, c){
      let d = (this.props.player=="white") ? 's' : 'n'

      if(this.state.moves.length == 0){
        return makeMoves(r, c, 3, d)
      }else{
        return makeMoves(r, c, 2, d)
      }
    },
    rook(r, c){
      return [].concat(
        makeMoves(r, c, r, "n"),
        makeMoves(r, c, 7-r, "s"),
        makeMoves(r, c, 7-c, "e"),
        makeMoves(r, c, c, "w")
      )
    },
    knight(r, c){
      let moves = []
        moves.push({r: r-2, c: c+1})
        moves.push({r: r-2, c: c-1})

        moves.push({r: r-1, c: c+2})
        moves.push({r: r-1, c: c-2})

        moves.push({r: r+1, c: c+2})
        moves.push({r: r+1, c: c-2})

        moves.push({r: r+2, c: c+1})
        moves.push({r: r+2, c: c-1})
        return moves
    },
    bishop(r, c){
      return [].concat(
        makeMoves(r, c, 8, "ne"),
        makeMoves(r, c, 8, "sw"),
        makeMoves(r, c, 8, "nw"),
        makeMoves(r, c, 8, "se")
      )
    },
    king(r, c){
      return [].concat(
        makeMoves(r, c, 1, "n"),
        makeMoves(r, c, 1, "s"),
        makeMoves(r, c, 1, "e"),
        makeMoves(r, c, 1, "w"),
        makeMoves(r, c, 1, "ne"),
        makeMoves(r, c, 1, "sw"),
        makeMoves(r, c, 1, "nw"),
        makeMoves(r, c, 1, "sw")
      )
    },
    queen(r, c){
      return [].concat(
        makeMoves(r, c, 8, "n"),
        makeMoves(r, c, 8, "s"),
        makeMoves(r, c, 8, "e"),
        makeMoves(r, c, 8, "w"),
        makeMoves(r, c, 8, "ne"),
        makeMoves(r, c, 8, "sw"),
        makeMoves(r, c, 8, "nw"),
        makeMoves(r, c, 8, "sw")
      )
    },
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