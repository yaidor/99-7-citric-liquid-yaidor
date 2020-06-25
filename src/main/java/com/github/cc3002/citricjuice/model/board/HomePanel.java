package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;

public class HomePanel extends AbstractPanel{
  private Jugador jugador;

  public HomePanel(int idpanel) {
    super(idpanel);
    this.jugador = null;
  }

  /**
   * the player gets +1 hp
   *
   * @param jugador
   *    is the player who get the +1hp
   *
   */

  @Override
  public void action(Jugador jugador) {
    jugador.setCurrentHP(jugador.getCurrentHP() + 1);
    jugador.normaCheck();
  }

  public void setOwner(Jugador jugador){
    this.jugador = jugador;
  }

  public Jugador getOwner(){
    return this.jugador;
  }
}
