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
  @Override
  public void action(Jugador jugador) {
    jugador.setCurrentHP(jugador.getCurrentHP() + 1);;
  }
}
