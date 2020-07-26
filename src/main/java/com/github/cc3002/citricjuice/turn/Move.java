package com.github.cc3002.citricjuice.turn;

public class Move extends State{

  void wantFight(){
    this.changeState(new WantFight());
  }
  void path(){
    this.changeState(new Path());
  }
  void stayPanel(){
    this.changeState(new StayPanel());
  }
  void wantHome(){
    this.changeState(new WantHome());
  }

  public boolean isMove() {
    return true;
  }
}
