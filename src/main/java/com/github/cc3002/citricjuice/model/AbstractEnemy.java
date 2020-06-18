package com.github.cc3002.citricjuice.model;

public abstract class AbstractEnemy extends AbstractContender {
  /**
   * Creates a new character.
   *
   * @param name the character's name.
   * @param hp   the initial (and max) hit points of the character.
   * @param atk  the base damage the character does.
   * @param def  the base defense of the character.
   * @param evd  the base evasion of the character.
   */
  public AbstractEnemy(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
  }

  public  boolean decision(){
    //I'm doing this decision randmoly until user can choose from defend or dodge
    int rollme = this.roll();
    return rollme % 2 == 0;
  }
}
