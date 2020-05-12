package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import com.github.cc3002.citricjuice.model.Player;

import java.util.ArrayList;
import java.util.Set;

public class BossPanel extends AbstractPanel{
  public BossPanel(int idpanel) {
    super(idpanel);
  }
  public BossPanel(int idpanel, Set<AbstractPanel> next, Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
  }

  /**
   * is the action that confirms if a boss can appear
   * and combat him
   *
   * @param jugador
   *    is the player that activate the panel
   */

  @Override
  public void action(Jugador jugador) {

  }
}
