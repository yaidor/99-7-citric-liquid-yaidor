package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import com.github.cc3002.citricjuice.model.Player;

import java.util.ArrayList;
import java.util.Set;

public class HomePanel extends AbstractPanel{
  public HomePanel(int idpanel) {
    super(idpanel);
  }
  public HomePanel(int idpanel, Set<AbstractPanel> next, Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
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
