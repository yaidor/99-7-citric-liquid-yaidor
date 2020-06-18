package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;

public class BonusPanel extends AbstractPanel{
  public BonusPanel(int idpanel) {
    super(idpanel);
  }

  /**
   * increases the player's stars
   *
   * @param jugador
   *    is the player who increases the stars
   */

  @Override
  public void action(Jugador jugador) {
    jugador.addStars(jugador.roll() * Math.min(jugador.getNormaLevel(), 3));
  }
}
