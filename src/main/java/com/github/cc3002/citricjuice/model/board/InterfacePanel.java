package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.Set;

public interface InterfacePanel {
  Set<Jugador> getOcupado();
  Set<InterfacePanel> getNext();
  void addPla2Pan(Jugador jugador);
  void leave(Jugador jugador);
  void addNextPanel(InterfacePanel panel);
  void action(Jugador jugador);
}
