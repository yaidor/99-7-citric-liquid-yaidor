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
  public void increaseStarsBy(final int amount) {
    stars += amount;
  }
  public int getStars() {
    return this.stars;
  }
  public int getWins() {
    return this.win;
  }
  public void addWins(final int amount) {
    this.win = this.win + Math.max(0,amount);
  }
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }
  public int roll() {
    return random.nextInt(6) + 1;
  }
  public String getName() {
    return name;
  }
  public int getMaxHP() {
    return maxHP;
  }
  public int getAtk() {
    return atk;
  }
  public int getDef() {
    return def;
  }
  public int getEvd() {
    return evd;
  }
  public int getCurrentHP() {
    return currentHP;
  }
  public void setCurrentHP(final int newHP) {
    this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
  }
  public void reduceStarsBy(final int amount) {
    this.stars = Math.max(0, this.stars - amount);
  }
  public void addStars(final int amount){
    this.stars = this.stars + Math.max(0,amount);
  }
  public abstract void attack(AbstractContender enemigo);
  public abstract boolean decision();
  public void defender(int attack){
    int HPfinal = this.getCurrentHP() - Math.max(1,attack-(this.roll()+this.getDef()));
    this.setCurrentHP(HPfinal);
  }
  public void dodge(int attack){
    if (this.roll()+this.getEvd()<=attack){
      int HPfinal = this.getCurrentHP() - attack;
      this.setCurrentHP(HPfinal);
    }
    else{
      this.setCurrentHP(this.getCurrentHP());
    }
  }
  public abstract void loseByJugador(AbstractContender enemigo);
  public abstract void loseByBoss(AbstractContender enemigo);
  public abstract void loseByWild(AbstractContender enemigo);
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
