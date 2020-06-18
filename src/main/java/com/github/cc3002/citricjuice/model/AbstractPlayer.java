package com.github.cc3002.citricjuice.model;

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
