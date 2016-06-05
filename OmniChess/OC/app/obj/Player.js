import _ from 'lodash'

class Player {
  /*
    props:
      player - black, white
  */
  constructor(props) {
    this.props = props
    this.state = {
      moves: [],
      taken: [],
      activeTurn: false
    }
  }
  getTaken(){
    return this.state.taken
  }
  addTaken(piece){
    this.state.taken.push(piece)
  }
  isActiveTurn(){
    return this.state.activeTurn
  }
  getPlayer(){
    return this.props.player
  }
  toggleTurn(){
    return this.state.activeTurn = !this.state.activeTurn
  }
}

module.exports = Player