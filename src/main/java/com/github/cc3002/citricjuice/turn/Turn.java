package com.github.cc3002.citricjuice.turn;

public class Turn {
  private State state;

  public Turn(){
    this.setState(new Start());
  }

  void setState(State aState){
    state = aState;
    state.setTurn(this);
  }

  public void start() {
    state.start();
  }
  public void isKO() {
    state.isKO();
  }
  public void endRecovery() {
    state.endRecovery();
  }
  public void notKO(){
    state.notKO();
  }
  public void playCard(){
    state.playCard();
  }
  public void move(){
    state.move();
  }
  public void path(){
    state.path();
  }
  public void backToTrack(){
    state.backToTrack();
  }
  public void wantFight(){
    state.wantFight();
  }
  public void noFight(){
    state.noFight();
  }
  public void fighting(){
    state.fighting();
  }
  public void dOrD(){
    state.dOrD();
  }
  public void decided(){
    state.decided();
  }
  public void endFight(){
    state.endFight();
  }
  public void stayPanel(){
    state.stayPanel();
  }
  public void end(){
    state.end();
  }
  public void wantHome(){
    state.wantHome();
  }
  public void noHome() {
    state.noHome();
  }
  public void home() {
    state.home();
  }

  public boolean isStart(){
    return state.isStart();
  }
  public boolean isIsKO(){
    return state.isIsKO();
  }
  public boolean isNotKO(){
    return state.isNotKO();
  }
  public boolean isPlayCard(){
    return state.isPlayCard();
  }
  public boolean isMove(){
    return state.isMove();
  }
  public boolean isWantFight(){
    return state.isWantFight();
  }
  public boolean isFighting(){
    return state.isFighting();
  }
  public boolean isDOrD(){
    return state.isDOrD();
  }
  public boolean isPath(){
    return state.isPath();
  }
  public boolean isStayPanel(){
    return state.isStayPanel();
  }
  public boolean isEnd(){
    return state.isEnd();
  }
  public boolean isWantHome(){
    return state.isWantHome();
  }


}
