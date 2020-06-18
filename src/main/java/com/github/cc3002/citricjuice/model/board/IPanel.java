package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;
import java.util.Set;

public interface IPanel {
  Set<Jugador> getOcupado();
  Set<IPanel> getNext();
  void addPla2Pan(Jugador jugador);
  void leave(Jugador jugador);
  void addNextPanel(IPanel panel);
  void action(Jugador jugador);
}
