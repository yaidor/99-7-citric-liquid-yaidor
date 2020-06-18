package com.github.cc3002.citricjuice.model;

public interface IContender {
  void increaseStarsBy(int x);
  int getStars();
  int getWins();
  void addWins(int i);
  void setSeed(long seed);
  int roll();
  String getName();
  int getMaxHP();
  int getAtk();
  int getDef();
  int getEvd();
  int getCurrentHP();
  void setCurrentHP(int newHP);
}
