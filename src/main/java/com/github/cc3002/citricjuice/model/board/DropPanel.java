package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;

public class DropPanel extends AbstractPanel{
  public DropPanel(int idpanel) {
    super(idpanel);
  }

  /**
   * reduce stars from target player
   *
   * @param jugador
   *    is the target player
   */

  @Override
  public void action(Jugador jugador) {
    jugador.reduceStarsBy(jugador.roll() * jugador.getNormaLevel());
  }
}
