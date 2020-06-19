package com.github.cc3002.citricjuice.model.contenders;

public abstract class AbstractPlayer extends AbstractContender{
  private int normaLevel;
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

}
