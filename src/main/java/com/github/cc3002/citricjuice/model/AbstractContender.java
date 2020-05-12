package com.github.cc3002.citricjuice.model;


import java.util.Random;

public abstract class AbstractContender {
  private final Random random;
  private final String name;
  private final int maxHP;
  private final int atk;
  private final int def;
  private final int evd;
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
   * encrease the playe's stars
   *
   * @param amount
   *     the amount to encrease the stars
   *
   */

  public void increaseStarsBy(final int amount) {
    stars += amount;
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
   * Set's the seed for this player's random number generator.
   * <p>
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
   * the player is going to attack another contender
   *
   * @param enemigo
   *    this is the target that he player is going
   *    to attact to
   */

  public abstract void attack(AbstractContender enemigo);

  /**
   * This is the decision to dodge or defend
   * and it depends on the type of contender we are
   * looking at
   */

  public abstract boolean decision();

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
   * de player dodges and verify if the dodges is a
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
   * the player loses by a jugador type
   * @param enemigo
   *    is the enemy who defeat the player
   */

  public abstract void loseByJugador(AbstractContender enemigo);

  /**
   * the player loses by a Boss type
   * @param enemigo
   *    is the Boss who defeat the player
   */

  public abstract void loseByBoss(AbstractContender enemigo);

  /**
   * the player loses by a Wild Unit type
   * @param enemigo
   *    is the Wild Unit who defeat the player
   */

  public abstract void loseByWild(AbstractContender enemigo);

  /**
   * the player is attacked by a jugador type
   * @param enemigo
   *    is the jugador who attacks the player
   */

  public void attackedByJugador(AbstractContender enemigo){
    int totalAttack = enemigo.roll() + enemigo.getAtk();
    if (this.decision()){
      this.defender(totalAttack);
    }
    else{
      this.dodge(totalAttack);
    }
    if(this.getCurrentHP()==0){
      this.loseByJugador(enemigo);
    }
  }

  /**
   * the player is attacked by a Boss type
   * @param enemigo
   *    is the Boss who attacks the player
   */

  public void attackedByBoss(AbstractContender enemigo){
    int totalAttack = enemigo.roll() + enemigo.getAtk();
    if (this.decision()){
      this.defender(totalAttack);
    }
    else{
      this.dodge(totalAttack);
    }
    if(this.getCurrentHP()==0){
      this.loseByBoss(enemigo);
    }
  }

  /**
   * the player is attacked by a Wild Unit type
   * @param enemigo
   *    is the Wild Unit who attacks the player
   */

  public void attackedByWild(AbstractContender enemigo){
    int totalAttack = enemigo.roll() + enemigo.getAtk();
    if (this.decision()){
      this.defender(totalAttack);
    }
    else{
      this.dodge(totalAttack);
    }
    if(this.getCurrentHP()==0){
      this.loseByWild(enemigo);
    }
  }

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
