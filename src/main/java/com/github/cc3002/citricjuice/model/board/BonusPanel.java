package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.Set;

public class BonusPanel extends AbstractPanel{
  public BonusPanel(int idpanel) {
    super(idpanel);
  }
  public BonusPanel(int idpanel, Set<AbstractPanel> next,Set<Jugador> ocupado) {
    super(idpanel,next, ocupado);
  }

  @Override
  public void action(Jugador jugador) {
    jugador.increaseStarsBy(jugador.roll() * Math.min(jugador.getNormaLevel(), 3));
  }
}
