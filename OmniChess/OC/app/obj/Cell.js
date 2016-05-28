class Cell {
  constructor(props) {
    this.props = props
    this.canMoveTo = false
    this.hasPiece = false
  }
  get CanMoveTo(){
    return this.canMoveTo
  }
  set CanMoveTo(val){
    return this.canMoveTo = val
  }
  get HasPiece(){
    return this.hasPiece
  }
  set HasPiece(val){
    return this.hasPiece = val
  }
}

module.exports = Cell