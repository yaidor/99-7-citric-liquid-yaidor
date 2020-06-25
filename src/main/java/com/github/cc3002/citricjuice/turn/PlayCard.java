package com.github.cc3002.citricjuice.turn;

public class PlayCard extends State {

  void move(){
    this.changeState(new Move());
  }

  public boolean isPlayCard() {
    return true;
  }
}
