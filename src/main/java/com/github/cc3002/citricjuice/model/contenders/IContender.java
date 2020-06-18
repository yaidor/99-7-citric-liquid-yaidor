package com.github.cc3002.citricjuice.model.contenders;

public interface IContender {
  String getName();
  int getMaxHP();
  int getAtk();
  int getDef();
  int getEvd();
  int getCurrentHP();
  void setCurrentHP(int newHP);
  int getStars();
  void addStars(int amount);
  void reduceStarsBy(int amount);
  void setSeed(long seed);
  int roll();
  int getWins();
  void addWins(int amount);
  void attack(IContender enemigo);
  boolean decision();
  void defender(int attack);
  void dodge(int attack);
  void attackedByJugador(IContender jugador);
  void attackedByBoss(IContender boss);
  void attackedByWild(IContender wild);
  void loseByJugador(IContender jugador);
  void loseByBoss(IContender boss);
  void loseByWild(IContender wild);
}
