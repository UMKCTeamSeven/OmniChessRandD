import _ from 'lodash'

var Player = require('./Player');
var Piece = require('./Piece');
var Portal = require('./Portal');
var Cell = require('./Cell');

class Board {
  constructor(props) {
    this.props = props
    this.board = []
    this.players = []
    this.promotionScreen = false

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

    //Setup Players
    this.players.push( new Player({player: "white"}) )
    this.players.push( new Player({player: "black"}) )
    this.players[0].toggleTurn()

    //Add Portals
    this.board[3][0].portal = new Portal({ type: "black",
                                            coords: [{r:3,c:0}, {r:5,c:6}]
                                          })
    this.board[5][6].portal = new Portal({ type: "black",
                                            coords: [{r:5,c:6}, {r:3,c:0}]
                                          })
  }
  setPromotedPiece(piece){
    let {r, c} = this.getCellPromoted.call(this)

    delete this.board[r][c].piece
    this.board[r][c].piece = piece

    this.promotionScreen = false
    this.resetCellStates.call(this)
    this.togglePlayersTurn.call(this)
    this.props.game.setState({})
  }
  getScreen(){
    if(this.promotionScreen)
      return "promotionScreen"
    else
      return "gameBoard"
  }
  getBoard(){
    return this.board
  }
  getPlayers(){
    return this.players
  }
  currentPlayerTurn(){
    return this.players[0].isActiveTurn() ? 0 : 1
  }
  togglePlayersTurn(){
    this.players[0].toggleTurn()
    this.players[1].toggleTurn()
  }
  togglePortalActive(coords){
    //not same piece clicked
    if(!this.board[coords.r][coords.c].cellState.isActive){
      this.resetCellStates.call(this)
      this.board[coords.r][coords.c].cellState = {isActive: true}
      this.showPossiblePortalMoves.call(this, coords)
    }else if(this.getCellActive.call(this)){//already active
      this.resetCellStates.call(this)
    }

    this.props.game.setState({})
  }
  toggleCellActive(coords){
    //not current player
    if(this.board[coords.r][coords.c].piece.getPlayer() != 
        this.players[this.currentPlayerTurn.call(this)].getPlayer())
      return

    //not same piece clicked
    if(!this.board[coords.r][coords.c].cellState.isActive){
      this.resetCellStates.call(this)
      this.board[coords.r][coords.c].cellState = {isActive: true}
      this.showPossibleMoves.call(this, coords)
    }else if(this.getCellActive.call(this)){//already active
      this.resetCellStates.call(this)
    }


    this.props.game.setState({})
  }
  getCellActive(){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        if(this.board[i][j].cellState.isActive){
          return {r: i, c: j}
        }
      }
    }
  }
  getCellPortal(){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        if(this.board[i][j].portal){
          return {r: i, c: j}
        }
      }
    }
  }
  getCellPromoted(){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        if(this.board[i][j].cellState.canPromote){
          return {r: i, c: j}
        }
      }
    }
  }
  resetCellStates(){
    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        this.board[i][j].cellState = {}
      }
    }
  }

  buildMoves(origin, coords, direction, length){
    let {r, c} = coords
    let gotoCoords = {r: r, c: c}

    if(length > 0){ //we have possible spaces to travel
      for(direc of direction ){
        switch(direc){ //compute moves coords
          case "n": gotoCoords.r--; break;
          case "s": gotoCoords.r++; break;
          case "e": gotoCoords.c++; break;
          case "w": gotoCoords.c--; break;
        }
      }
      //only within board bounds
      if( -1 < gotoCoords.r && gotoCoords.r < 8 &&
          -1 < gotoCoords.c && gotoCoords.c < 8){
        if(!this.board[gotoCoords.r][gotoCoords.c].piece && // has no piece
            !this.board[gotoCoords.r][gotoCoords.c].portal){ // has no portal
          this.board[gotoCoords.r][gotoCoords.c].cellState.canMove = true
          this.buildMoves.call(this, origin, {r:gotoCoords.r, c:gotoCoords.c}, direction, length-1)
        }else if(this.board[gotoCoords.r][gotoCoords.c].portal){// go through portal
          let portalCoords = this.board[gotoCoords.r][gotoCoords.c].portal.getCoords()
          let portalJumpIndx = 0

          if(portalCoords[0].r == gotoCoords.r &&
            portalCoords[0].c == gotoCoords.c){
            portalJumpIndx = 1
          }

          this.buildMoves.call(this, origin,
                              portalCoords[portalJumpIndx],
                              direction, length)
        }
      }
    }
  }
  buildAttacks(origin, coords, direction, length){
    let {r, c} = coords
    let gotoCoords = {r: r, c: c}

    if(length > 0){ //we have possible spaces to travel
      for(direc of direction ){
        switch(direc){ //compute moves coords
          case "n": gotoCoords.r--; break;
          case "s": gotoCoords.r++; break;
          case "e": gotoCoords.c++; break;
          case "w": gotoCoords.c--; break;
        }
      }
      //only within board bounds
      if( -1 < gotoCoords.r && gotoCoords.r < 8 &&
          -1 < gotoCoords.c && gotoCoords.c < 8){
        if(this.board[gotoCoords.r][gotoCoords.c].piece){ //has a piece
          if(this.board[origin.r][origin.c].piece.getPlayer() !=
              this.board[gotoCoords.r][gotoCoords.c].piece.getPlayer()){ //other player piece
            this.board[gotoCoords.r][gotoCoords.c].cellState.canTake = true
          }else{
            return //my piece
          }
        }else if(this.board[gotoCoords.r][gotoCoords.c].portal){ //has a portal
          let portalCoords = this.board[gotoCoords.r][gotoCoords.c].portal.getCoords()
          let portalJumpIndx = 0

          if(portalCoords[0].r == gotoCoords.r &&
            portalCoords[0].c == gotoCoords.c){
            portalJumpIndx = 1
          }

          this.buildAttacks.call(this, origin,
                              portalCoords[portalJumpIndx],
                              direction, length)
        }else{
          this.buildAttacks.call(this, origin, {r:gotoCoords.r, c:gotoCoords.c}, direction, length-1)
        }
      }
    }
  }
  showPossiblePortalMoves(coords){
    let {r, c} = coords
    let {portal, piece} = this.board[r][c]

    for(let i=0;i<8;i++){
      for(let j=0;j<8;j++){
        let {portal, piece} = this.board[i][j]

        if(!(portal || piece)){
          this.board[i][j].cellState.canMovePortal = true
        }
      }
    }
  }
  showPossibleMoves(coords){
    let {r, c} = coords
    let {piece} = this.board[r][c]
    let moves = piece.getMoves(r, c)

    for(let direc in moves.direction){ //go through each move
      this.buildMoves.call(this, coords, coords, direc, moves.direction[direc])
    }
    for(let direc in moves.attack){ //go through each attack
      this.buildAttacks.call(this, coords, coords, direc, moves.attack[direc])
    }
  }
  promotePiece(coords){
    let {r, c} = coords
    if(this.board[coords.r][coords.c].piece.getType() != "pawn")
      return false
    if(this.players[this.currentPlayerTurn.call(this)].getPlayer() == "white"){
      return r==7
    }else{
      return r==0
    }
  }
  moveCell(coords){
    let {r, c} = this.getCellActive()

    this.board[coords.r][coords.c].piece = this.board[r][c].piece
    this.board[coords.r][coords.c].piece.move(coords.r, coords.c)
    
    delete this.board[r][c].piece

    this.resetCellStates.call(this)
    if(this.promotePiece(coords)){
      this.board[coords.r][coords.c].cellState.canPromote = true
      this.promotionScreen = true
    }else{
      this.togglePlayersTurn.call(this)
    }
    this.props.game.setState({})
  }
  takeCell(coords){
    let {r, c} = this.getCellActive()
    this.players[this.currentPlayerTurn.call(this)]
        .addTaken(this.board[coords.r][coords.c].piece)
    
    delete this.board[coords.r][coords.c].piece
    this.moveCell(coords)
  }
  movePortal(coords){
    let {r, c} = this.getCellActive()

          let portalCoords = this.board[r][c].portal.getCoords()
          let portalJumpIndx = 0

          if(portalCoords[0].r == r &&
              portalCoords[0].c == c){
            portalJumpIndx = 1
          }
          let altCoords = portalCoords[portalJumpIndx]
    //update portals coords
    this.board[r][c].portal.updateCoords(this.getCellActive(), coords)
    this.board[altCoords.r][altCoords.c].portal.updateCoords(this.getCellActive(), coords)

    this.board[coords.r][coords.c].portal = this.board[r][c].portal
    delete this.board[r][c].portal

    this.resetCellStates.call(this)
    this.togglePlayersTurn.call(this)

    this.props.game.setState({})
  }
}

module.exports = Board