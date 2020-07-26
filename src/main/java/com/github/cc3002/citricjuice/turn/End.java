package com.github.cc3002.citricjuice.turn;

public class End extends State{

  void start(){
    this.changeState(new Start());
  }

  public boolean isEnd() {
    return true;
  }
}
