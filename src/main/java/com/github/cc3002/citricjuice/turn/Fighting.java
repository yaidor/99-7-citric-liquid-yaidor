package com.github.cc3002.citricjuice.turn;

public class Fighting extends State{

  void dOrD(){this.changeState(new DOrD());}
  void endFight(){
    this.changeState(new StayPanel());
  }

  public boolean isFighting() {
    return true;
  }
}
