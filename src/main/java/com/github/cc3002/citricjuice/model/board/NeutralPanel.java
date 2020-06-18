package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;

public class NeutralPanel extends AbstractPanel {
  public NeutralPanel(int idpanel) {
    super(idpanel);
  }

  @Override
  public void addNextPanel(IPanel panel) {

  }

  /**
   * This action doesn't do anything
   */

  @Override
  public void action(Jugador jugador) {

  }

}
