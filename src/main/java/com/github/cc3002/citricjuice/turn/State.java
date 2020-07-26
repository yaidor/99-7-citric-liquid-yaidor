package com.github.cc3002.citricjuice.turn;

public class State {
  private Turn turn;

  public void setTurn(Turn turn){
    this.turn = turn;
  }

  protected void changeState(State state){
    turn.setState(state);
  }

  /**
   * for later
  void error(){
    throw new AssertionError("Wrong state");
  }
   for later
   */

  void start(){
    return;
  }
  void isKO(){
    return;
  }
  void endRecovery(){
    return;
  }
  void notKO(){
    return;
  }
  void playCard(){
    return;
  }
  void move(){
    return;
  }
  void path(){
    return;
  }
  void backToTrack(){
    return;
  }
  void wantFight(){
    return;
  }
  void noFight(){
    return;
  }
  void fighting(){
    return;
  }
  void dOrD(){
    return;
  }
  void decided() {
    return;
  }
  void endFight(){
    return;
  }
  void stayPanel(){
    return;
  }
  void end(){
    return;
  }
  void wantHome(){
    return;
  }
  void noHome(){
    return;
  }
  void home(){
    return;
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
  public boolean isWantHome(){
    return false;
  }
}
