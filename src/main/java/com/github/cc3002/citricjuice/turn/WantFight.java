package com.github.cc3002.citricjuice.turn;

public class WantFight extends State{

  void noFight(){
    this.changeState(new Move());
  }
  void fighting(){
    this.changeState(new Fighting());
  }

  public boolean isWantFight(){
    return true;
  }
}
