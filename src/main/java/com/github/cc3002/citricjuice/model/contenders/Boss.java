package com.github.cc3002.citricjuice.model.contenders;

public class Boss extends AbstractEnemy {

  public Boss(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
  }

  @Override
  public void attack(IContender enemigo) {
    if (enemigo.getCurrentHP()!=0){
      enemigo.attackedByBoss(this);
    }
  }
  /**
   * This is the decision to dodge or defend
   * and it depends on the type of contender we are
   * looking at
   */

  @Override
  public void loseByJugador(IContender jugador) {
    jugador.addStars(this.getStars());
    jugador.addWins(3);
    this.reduceStarsBy(this.getStars());
    //pierde todas las estrellas y se las pasa al jugador
    //el jugador gana 3 victorias
  }

  @Override
  public void loseByBoss(IContender boss) {
    boss.addStars(this.getStars()/2);
    boss.addWins(3);
    this.reduceStarsBy(this.getStars()/2);
    //debo perder la mitad de mis estrellas(y el boss ganarlas)
    //el boss gana 3 victorias
  }

  @Override
  public void loseByWild(IContender wild) {
    wild.addStars(this.getStars()/2);
    wild.addWins(3);
    this.reduceStarsBy(this.getStars()/2);
    //debo perder la mitad de mis estrellas(y el wild ganarlas)
    //el wild gana 3 victorias
  }

  public Boss copy() {
    return new Boss(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
}
