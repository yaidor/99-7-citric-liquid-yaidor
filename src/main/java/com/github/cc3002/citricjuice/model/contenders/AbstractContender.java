package com.github.cc3002.citricjuice.model.contenders;


import java.util.Random;

public abstract class AbstractContender implements IContender {
  private final Random random;
  private final String name;
  private final int maxHP;
  protected int atk;
  protected int def;
  protected int evd;
  private int stars;
  private int win;
  private int currentHP;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */

  public AbstractContender(final String name, final int hp, final int atk, final int def, final int evd) {
    this.name = name;
    this.maxHP = currentHP = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    this.stars = 0;
    this.win = 0;
    random = new Random();
  }

  /**
   * get the total of stars of the player at that moment
   */

  public int getStars() {
    return this.stars;
  }

  /**
   * get the total of wins of the player at that moment
   */

  public int getWins() {
    return this.win;
  }

  /**
   * encrease the playe's wins
   *
   * @param amount
   *     the amount to encrease the player's wins
   *
   */

  public void addWins(final int amount) {
    this.win = this.win + Math.max(0,amount);
  }

  /**
   * Sets the seed for this player's random number generator.
   * The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */

  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  /**
   * Returns a uniformly distributed random value in [1, 6]
   */

  public int roll() {
    return random.nextInt(6) + 1;
  }

  /**
   * Returns the character's name.
   */

  public String getName() {
    return name;
  }

  /**
   * Returns the character's max hit points.
   */

  public int getMaxHP() {
    return maxHP;
  }

  /**
   * Returns the current character's attack points.
   */

  public int getAtk() {
    return atk;
  }

  /**
   * Returns the current character's defense points.
   */

  public int getDef() {
    return def;
  }

  /**
   * Returns the current character's evasion points.
   */

  public int getEvd() {
    return evd;
  }

  /**
   * Returns the current hit points of the character.
   */

  public int getCurrentHP() {
    return currentHP;
  }

  /**
   * Sets the current character's hit points.
   * <p>
   * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
   */

  public void setCurrentHP(final int newHP) {
    this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
  }

  /**
   * Reduces this player's star count by a given amount.
   * <p>
   * The star count will must always be greater or equal to 0
   */

  public void reduceStarsBy(final int amount) {
    this.stars = Math.max(0, this.stars - amount);
  }

  /**
   * Encreases this player's star count by a given amount.
   * <p>
   * The amount will must always be greater or equal to 0
   */

  public void addStars(final int amount){
    this.stars = this.stars + Math.max(0,amount);
  }

  /**
   * this verify how much a player it would defend
   * from an attack
   *
   * @param attack
   *    is the amount of the attack that the player
   *    receives
   */

  public void defender(int attack){
    int HPfinal = this.getCurrentHP() - Math.max(1,attack-(this.roll()+this.getDef()));
    this.setCurrentHP(HPfinal);
  }

  /**
   * the player dodges and verify if the dodges is a
   * success or a failure
   *
   * @param attack
   *    is the amount of the attack that the player
   *    receives
   */

  public void dodge(int attack){
    if (this.roll()+this.getEvd()<=attack){
      int HPfinal = this.getCurrentHP() - attack;
      this.setCurrentHP(HPfinal);
    }
    else{
      this.setCurrentHP(this.getCurrentHP());
    }
  }

  /**
   * the contender attacks an enemy
   * @param enemigo
   *    is the enemy who is being attacked by the contender
   */

  public abstract void attack(IContender enemigo);

  /**
   * the player is attacked by a jugador type
   * @param jugador
   *    is the jugador who attacks the contender
   */

  public void attackedByJugador(IContender jugador){
    int totalAttack = jugador.roll() + jugador.getAtk();
    if (this.decision()){
      this.defender(totalAttack);
    }
    else{
      this.dodge(totalAttack);
    }
    if(this.getCurrentHP()==0){
      this.loseByJugador(jugador);
    }
    else{
      this.attack(jugador);
    }
  }

  /**
   * the player is attacked by a Boss type
   * @param boss
   *    is the Boss who attacks the player
   */

  public void attackedByBoss(IContender boss){
    int totalAttack = boss.roll() + boss.getAtk();
    if (this.decision()){
      this.defender(totalAttack);
    }
    else{
      this.dodge(totalAttack);
    }
    if(this.getCurrentHP()==0){
      this.loseByBoss(boss);
    }
  }

  /**
   * the player is attacked by a Wild Unit type
   * @param wild
   *    is the Wild Unit who attacks the player
   */

  public void attackedByWild(IContender wild){
    int totalAttack = wild.roll() + wild.getAtk();
    if (this.decision()){
      this.defender(totalAttack);
    }
    else{
      this.dodge(totalAttack);
    }
    if(this.getCurrentHP()==0){
      this.loseByWild(wild);
    }
  }

  /**
   * the player loses by a jugador type
   * @param jugador
   *    is the enemy who defeat the contender
   */

  public abstract void loseByJugador(IContender jugador);

  /**
   * the player loses by a Boss type
   * @param boss
   *    is the Boss who defeat the contender
   */

  public abstract void loseByBoss(IContender boss);

  /**
   * the player loses by a Wild Unit type
   * @param wild
   *    is the Wild Unit who defeat the contender
   */

  public abstract void loseByWild(IContender wild);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractContender that = (AbstractContender) o;
    return maxHP == that.maxHP &&
            atk == that.atk &&
            def == that.def &&
            evd == that.evd &&
            stars == that.stars &&
            currentHP == that.currentHP &&
            name.equals(that.name);
  }
}
