package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;

public class NeutralPanel extends AbstractPanel {
  public NeutralPanel(int idpanel) {
    super(idpanel);
  }

  /**
   * This action doesn't do anything
   */

  @Override
  public void action(Jugador jugador) {

  }

}
