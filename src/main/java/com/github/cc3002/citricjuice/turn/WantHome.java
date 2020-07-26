package com.github.cc3002.citricjuice.turn;

public class WantHome extends State{
  void noHome(){
    this.changeState(new Move());
  }
  void home(){
    this.changeState(new StayPanel());
  }

  public boolean isWantHome() {
    return true;
  }
}
