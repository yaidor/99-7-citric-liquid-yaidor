package com.github.cc3002.citricjuice.model.contenders;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.IPanel;

public abstract class AbstractPlayer extends AbstractContender{
  private int normaLevel;
  private IPanel panel;
  private NormaGoal normaGoal;
  private boolean myTurn;
  private int recovery;

  /**
   * Creates a new character.
   *
   * @param name the character's name.
   * @param hp   the initial (and max) hit points of the character.
   * @param atk  the base damage the character does.
   * @param def  the base defense of the character.
   * @param evd  the base evasion of the character.
   */

  public AbstractPlayer(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
    this.normaLevel = 1;
    this.panel = null;
    this.normaGoal = NormaGoal.STARS;
    this.myTurn = false;
    this.recovery = 0;
  }

  /**
   * This is the decision to dodge or defend
   * and it depends on the type of contender we are
   * looking at
   */

  public boolean decision() {
    //I'm doing this decision randmoly until user can choose from defend or dodge
    int rollme = this.roll();
    return rollme % 2 == 0;
  }

  /**
   * sets the attack parameter of a player,
   * with a minimum of 0 attack
   *
   * @param amount
   *    is the new possible attack
   */

  public void setAtk(int amount){
    this.atk = (Math.max(amount, 0));
  }

  /**
   * sets the defense parameter of a player,
   * with a minimum of 0 defense
   *
   * @param amount
   *    is the new possible defense
   */

  public void setDef(int amount){
    this.def = (Math.max(0, amount));
  }

  /**
   * sets the evasion parameter of a player,
   * with a minimum of 0 evasion
   *
   * @param amount
   *    is the new possible evasion
   */

  public void setEvd(int amount){
    this.evd = (Math.max(amount, 0));
  }

  /**
   * Returns the current norma level
   */

  public int getNormaLevel() {
    return this.normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */

  public void normaClear() {
    this.normaLevel += 1;
  }

  /**
   * sets the current panel of a player
   *
   * @param panel
   *    is the panel that is going to be set to the player
   */

  public void setPanel(IPanel panel){
    this.panel=panel;
  }

  /**
   * Returns the current panel of the player
   */

  public IPanel getPanel(){
    return this.panel;
  }

  /**
   * Sets the norma goal
   *
   * @param valor
   *      is the boolean goal that represents
   *      if the player wants to reach one goal
   *      or the other goal
   *
   * false is going to be the goal of stars and
   * true is the value to represents the goal fo wins
   */

  public void setNormaGoal(NormaGoal valor){
    this.normaGoal = valor;
  }

  /**
   * Returns the current norma goal
   */

  public NormaGoal getNormaGoal(){
    return this.normaGoal;
  }

  /**
   * sets the turn as true, to confirm that is the turn of
   * the player
   */

  public void setMyTurn(boolean valor){
    this.myTurn = valor;
  }

  /**
   * Returns the value of the turn.
   */

  public boolean getMyTurn(){
    return this.myTurn;
  }

  /**
   * checks if the player can level up his norma level
   * knowing the norma goal of the player
   */

  public void normaCheck(){
    NormaGoal goal = this.getNormaGoal();
    if (goal == NormaGoal.STARS){
      this.checkNormaStars();
    }
    else {
      this.checkNormaWins();
    }
  }

  /**
   * checks if the player can level up his norma level knowing
   * that the goal was WINS
   */

  protected void checkNormaWins(){
    int level = this.getNormaLevel();
    if (level == 1){
      return;
    }
    int need = 0;
    for (int x = 1; x<level; x++){
      need = need + (x+1);
    }
    if (need <= this.getWins()){
      this.normaClear();
    }
  }

  /**
   * checks if the player can level up his norma level knowing
   * that the goal was STARS
   */

  protected void checkNormaStars(){
    int level = this.getNormaLevel();
    if(level == 5 && this.getStars() >= 200) {
      this.normaClear();
    }
    if(level == 4 && this.getStars() >= 120) {
      this.normaClear();
    }
    if(level == 3 && this.getStars() >= 70) {
      this.normaClear();
    }
    if(level == 2 && this.getStars() >= 30) {
      this.normaClear();
    }
    if(level == 1 && this.getStars() >= 10) {
      this.normaClear();
    }
  }

  /**
   * Returns the recovery value, this value is the number
   * of chapter has been knock up the player
   */

  public int getRecovery(){
    return this.recovery;
  }

  /**
   * sets the value of recovery
   * @param amount
   *      amount is going to be the new value
   *      of recovery, with a minimum of 0
   */

  public void setRecovery(int amount){
    this.recovery = (Math.max(amount, 0));
  }
}
