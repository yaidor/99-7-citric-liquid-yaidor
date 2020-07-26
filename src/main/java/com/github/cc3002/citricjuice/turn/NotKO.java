package com.github.cc3002.citricjuice.turn;

public class NotKO extends State{

  void playCard(){
    this.changeState(new PlayCard());
  }

  public boolean isNotKO(){
    return true;
  }
}
