package com.github.cc3002.citricjuice.model;

public class Wild extends AbstractContender{
  public Wild(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
  }

  @Override
  public void attack(AbstractContender enemigo) {
    if (enemigo.getCurrentHP()!=0){
      enemigo.attackedByWild(this);
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
    enemigo.addStars(this.getStars());
    enemigo.addWins(1);
    this.reduceStarsBy(this.getStars());
    //pierde todas las estrellas y se las pasa al enemigo
    //el enemigo gana 1 victorias
  }

  @Override
  public void loseByBoss(AbstractContender enemigo) {
    enemigo.addStars(this.getStars()/2);
    enemigo.addWins(1);
    this.reduceStarsBy(this.getStars()-(this.getStars()/2));
    //debo perder la mitad de mis estrellas(y el enemigo ganarlas)
    //el enemigo gana 1 victorias
  }

  @Override
  public void loseByWild(AbstractContender enemigo) {
    enemigo.addStars(this.getStars()/2);
    enemigo.addWins(1);
    this.reduceStarsBy(this.getStars()-(this.getStars()/2));
    //debo perder la mitad de mis estrellas(y el enemigo ganarlas)
    //el enemigo gana 1 victorias
  }

  public Wild copy() {
    return new Wild(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
}
