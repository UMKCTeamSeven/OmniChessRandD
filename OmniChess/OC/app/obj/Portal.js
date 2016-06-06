import _ from 'lodash'

var pics = {
  white: require("../drawable-xxhdpi/ni_wportal.png"),
  black: require("../drawable-xxhdpi/ni_portal.png")
}
class Portal {
  /*
    props:
      type - white, black
  */
  constructor(props) {
    this.props = props
    this.state = {
      moves: []
    }
  }
  getCoords(){
    return this.props.coords
  }
  updateCoords(from, to){
    let portalCoords = this.getCoords()
    let portalJumpIndx = 0

    if(portalCoords[0].r == from.r &&
        portalCoords[0].c == from.c){
      portalJumpIndx = 1
    }
    this.props.coords[portalJumpIndx] = to
  }
  getPic(){
    return pics[this.props.type]
  }
  getType(){
    return this.props.type
  }
}

module.exports = Portal