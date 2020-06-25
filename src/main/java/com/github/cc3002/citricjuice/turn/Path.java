package com.github.cc3002.citricjuice.turn;

public class Path extends State{

  void backToTrack(){
    this.changeState(new Move());
  }

  public boolean isPath() {
    return true;
  }
}
