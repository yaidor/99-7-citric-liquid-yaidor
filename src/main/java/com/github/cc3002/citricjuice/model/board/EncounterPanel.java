package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.Set;

public class EncounterPanel extends AbstractPanel{
  public EncounterPanel(int idpanel) {
    super(idpanel);
  }
  public EncounterPanel(int idpanel, Set<AbstractPanel> next, Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
  }

  /**
   * player encounter a wild unit
   *
   * @param jugador
   *    is the player who encounters the wild unit
   */

  @Override
  public void action(Jugador jugador) {

  }
}
