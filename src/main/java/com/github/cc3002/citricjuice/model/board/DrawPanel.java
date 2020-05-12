package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import com.github.cc3002.citricjuice.model.Player;

import java.util.ArrayList;
import java.util.Set;

public class DrawPanel extends AbstractPanel{
  public DrawPanel(int idpanel) {
    super(idpanel);
  }
  public DrawPanel(int idpanel, Set<AbstractPanel> next, Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
  }

  /**
   * Draw a cart
   *
   * @param jugador
   *    is the player who draws it
   */

  @Override
  public void action(Jugador jugador) {

  }
}
