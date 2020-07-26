package com.github.cc3002.citricjuice.turn;

public class IsKO extends State{

  void endRecovery(){
    this.changeState(new End());
  }

  public boolean isIsKO() {
    return true;
  }
}
