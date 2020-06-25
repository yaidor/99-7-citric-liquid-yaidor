package com.github.cc3002.citricjuice.turn;

public class State {
  private Turn turn;

  public void setTurn(Turn turn){
    this.turn = turn;
  }

  protected void changeState(State state){
    turn.setState(state);
  }

  void error(){
    throw new AssertionError("Wrong state");
  }

  void start(){
    error();
  }
  void isKO(){
    error();
  }
  void endRecovery(){
    error();
  }
  void notKO(){
    error();
  }
  void playCard(){
    error();
  }
  void move(){
    error();
  }
  void path(){
    error();
  }
  void backToTrack(){
    error();
  }
  void wantFight(){
    error();
  }
  void noFight(){
    error();
  }
  void fighting(){
    error();
  }
  void dOrD(){
    error();
  }
  void decided() {
    error();
  }
  void endFight(){
    error();
  }
  void stayPanel(){
    error();
  }
  void end(){
    error();
  }


  public boolean isStart(){
    return false;
  }
  public boolean isIsKO(){
    return false;
  }
  public boolean isNotKO(){
    return false;
  }
  public boolean isPlayCard(){
    return false;
  }
  public boolean isMove(){
    return false;
  }
  public boolean isWantFight(){
    return false;
  }
  public boolean isFighting(){
    return false;
  }
  public boolean isDOrD(){
    return false;
  }
  public boolean isPath(){
    return false;
  }
  public boolean isStayPanel(){
    return false;
  }
  public boolean isEnd(){
    return false;
  }
}
