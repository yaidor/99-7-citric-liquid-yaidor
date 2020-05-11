package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.Set;

public class NeutralPanel extends AbstractPanel {
  public NeutralPanel(int idpanel) {
    super(idpanel);
  }
  public NeutralPanel(int idpanel, Set<AbstractPanel> next, Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
  }

}
