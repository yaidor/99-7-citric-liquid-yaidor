package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.Set;

public class NeutralPanel extends AbstractPanel {
  public NeutralPanel(int idpanel) {
    super(idpanel);
  }

  @Override
  public void addNextPanel(InterfacePanel panel) {

  }

  /**
   * This action doesn't do anything
   */

  @Override
  public void action(Jugador jugador) {

  }

}
