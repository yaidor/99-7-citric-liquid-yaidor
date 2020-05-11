package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPanel {
  private int idpanel;
  private Set<AbstractPanel> next;
  private Set<Jugador> ocupado;

  public AbstractPanel (int idpanel) {
    this.idpanel = idpanel;
    this.next = new HashSet<>();
    this.ocupado = new HashSet<>();
  }
  public AbstractPanel (int idpanel, Set<AbstractPanel> next,Set<Jugador> ocupado) {
    this.idpanel = idpanel;
    this.next = next;
    this.ocupado = ocupado;
  }

  public Set<Jugador> getOcupado() {
    return this.ocupado;
  }

  public Set<AbstractPanel> getNext() {
    return this.next;
  }

  public void addPla2Pan(final Jugador jugador){
    this.ocupado.add(jugador);
  }

  public void leave(final Jugador jugador){
    this.ocupado.remove(jugador);
  }

  public void addNextPanel(final AbstractPanel panel){
    this.next.add(panel);
  }

  public void action(final Jugador jugador){
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractPanel that = (AbstractPanel) o;
    return idpanel == that.idpanel &&
            next.equals(that.next) &&
            ocupado.equals(that.ocupado);
  }

}
