package com.github.cc3002.citricjuice.turn;

public class StayPanel extends State{

  void end(){
    this.changeState(new End());
  }

  public boolean isStayPanel(){
    return true;
  }
}
