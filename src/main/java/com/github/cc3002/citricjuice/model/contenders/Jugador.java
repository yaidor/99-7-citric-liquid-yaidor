package com.github.cc3002.citricjuice.model.contenders;

public class Jugador extends AbstractPlayer{
  public Jugador(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
  }


  public void attack(IContender enemigo) {
    if (enemigo.getCurrentHP()!=0){
      enemigo.attackedByJugador(this);
    }
  }

  @Override
  public void loseByJugador(IContender jugador) {
    jugador.addStars(this.getStars()/2);
    jugador.addWins(2);
    this.reduceStarsBy(this.getStars()/2);
  }

  @Override
  public void loseByBoss(IContender boss) {
    boss.addStars(this.getStars()/2);
    boss.addWins(2);
    this.reduceStarsBy(this.getStars()/2);
  }

  @Override
  public void loseByWild(IContender wild) {
    wild.addStars(this.getStars()/2);
    wild.addWins(2);
    this.reduceStarsBy(this.getStars()/2);
  }

  public Jugador copy() {
    return new Jugador(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
}
