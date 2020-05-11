package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import com.github.cc3002.citricjuice.model.Player;

import java.util.ArrayList;
import java.util.Set;

public class DropPanel extends AbstractPanel{
  public DropPanel(int idpanel) {
    super(idpanel);
  }
  public DropPanel(int idpanel, Set<AbstractPanel> next, java.util.Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
  }
  @Override
  public void action(Jugador jugador) {
    jugador.reduceStarsBy(jugador.roll() * jugador.getNormaLevel());
  }
}
