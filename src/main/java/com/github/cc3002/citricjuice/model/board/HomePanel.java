package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;

public class HomePanel extends AbstractPanel{
  public HomePanel(int idpanel) {
    super(idpanel);
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
    jugador.setCurrentHP(jugador.getCurrentHP() + 1);;
  }
}
