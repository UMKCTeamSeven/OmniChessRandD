import _ from 'lodash'

class Player {
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
  getPlayer(){
    return this.props.player
  }
}

module.exports = Player