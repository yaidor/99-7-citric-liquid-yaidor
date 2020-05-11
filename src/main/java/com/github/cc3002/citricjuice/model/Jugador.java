package com.github.cc3002.citricjuice.model;

public class Jugador extends AbstractContender{
  private int normaLevel;
  public Jugador(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
    this.normaLevel = 1;
  }

  @Override
  public void attack(AbstractContender enemigo) {
    if (enemigo.getCurrentHP()!=0){
      enemigo.attackedByJugador(this);
    }
  }

  @Override
  public boolean decision() {
    //I'm doing this decision randmoly until user can choose from defend or dodge
    int rollme = this.roll();
    return rollme % 2 == 0;
  }

  @Override
  public void loseByJugador(AbstractContender enemigo) {
    enemigo.addStars(this.getStars()/2);
    enemigo.addWins(2);
    this.reduceStarsBy(this.getStars()/2);
    //debo perder la mitad de mis estrellas(y el enemigo ganarlas)
    //debe ganar el enemigo 2 victorias
  }

  @Override
  public void loseByBoss(AbstractContender enemigo) {
    enemigo.addStars(this.getStars()/2);
    enemigo.addWins(2);
    this.reduceStarsBy(this.getStars()/2);
    //debo perder la mitad de mis estrellas(y el enemigo ganarlas)
    //debe ganar el enemigo 2 victorias
  }

  @Override
  public void loseByWild(AbstractContender enemigo) {
    enemigo.addStars(this.getStars()/2);
    enemigo.addWins(2);
    this.reduceStarsBy(this.getStars()/2);
    //debo perder la mitad de mis estrellas(y el enemigo ganarlas)
    //debe ganar el enemigo 2 victorias
  }


  public int getNormaLevel() {
    return this.normaLevel;
  }
  public void normaClear() {
    this.normaLevel += 1;
  }

  public Jugador copy() {
    return new Jugador(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
}
