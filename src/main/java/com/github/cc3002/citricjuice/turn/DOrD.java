package com.github.cc3002.citricjuice.turn;

public class DOrD extends State{

  void decided() {
    this.changeState(new Fighting());
  }

  public boolean isDOrD(){
    return true;
  }
}
