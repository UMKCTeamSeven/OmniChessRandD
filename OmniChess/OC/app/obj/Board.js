import _ from 'lodash'

var Piece = require('./Piece');
var Cell = require('./Cell');

class Board {
  constructor(props) {
    this.props = props
    this.board = []

    this.init.call(this)
  }
  makePawns(rIndx, color){
    for(let i=0;i<8;i++){
      this.board[rIndx][i].piece = new Piece( _.assign({player: color}, {type:"pawn"}) )
    }
  }
  makeNoble(rIndx, color){
    let pieces = ["rook", "knight", "bishop"]
    for(let i=0;i<3;i++){
      this.board[rIndx][i].piece = new Piece({player: color, type: pieces[i]})
      this.board[rIndx][7-i].piece = new Piece({player: color, type: pieces[i]})
    }

    let kIndx = (color=="white") ? 3 : 4
    let qIndx = (color=="white") ? 4 : 3

    this.board[rIndx][kIndx].piece = new Piece({type:"king", player: color})
    this.board[rIndx][qIndx].piece = new Piece({type:"queen", player: color})
  }
  init(){
    //create board
    for(let i=0;i<8;i++){
      let row = []
      for(let j=0;j<8;j++){
        row.push({board: this, cellState: {}})
      }
      this.board.push(row)
    }

    //Layout Pieces
    this.makePawns.call(this, 1, "white")
    this.makeNoble.call(this, 0, "white")

    this.makePawns.call(this, 6, "black")
    this.makeNoble.call(this, 7, "black")
  }
  getBoard(){ return this.board }
  toggleCellActive(coords){
    this.resetCellStates.call(this)

    this.board[coords.r][coords.c].cellState = {isActive: true}

    this.showCanMoveTo.call(this, coords)

    this.props.game.setState({})
  }
  resetCellStates(){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        this.board[i][j].cellState = {}
      }
    }
  }
  showCanMoveTo(coords){
    let {r, c} = coords
    let {piece} = this.board[r][c]
    let moves = piece.getMoves(r, c)

    //show where piece can move
    moves.forEach((move)=>{
      //only within board bounds
      if( -1 < move.r && move.r <8 && -1 < move.c && move.c <8 ){
        if(!this.board[move.r][move.c].piece){
          this.board[move.r][move.c].cellState.canMove = true
        }else if( piece.getPlayer() != this.board[move.r][move.c].piece.getPlayer() ){
          this.board[move.r][move.c].cellState.canTake = true
        }
      }
    })
  }
  moveCell(coords){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        if(this.board[i][j].cellState.isActive){//this is piece were moving
          this.board[coords.r][coords.c].piece = this.board[i][j].piece
          this.board[coords.r][coords.c].piece.move(coords.r, coords.c)
          delete this.board[i][j].piece
        }
      }
    }
    this.resetCellStates.call(this)
    this.props.game.setState({})
  }
  takeCell(coords){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        if(this.board[i][j].cellState.isActive){//this is piece were moving
          Object.assign(this.board[coords.r][coords.c].piece, this.board[i][j].piece)
          Object.assign(this.board[coords.r][coords.c], this.board[i][j])
          delete this.board[i][j].piece
        }
      }
    }

    this.toggleCellActive.call(this, coords)
    this.resetCellStates.call(this)
    this.props.game.setState({})
  }
}

module.exports = Board