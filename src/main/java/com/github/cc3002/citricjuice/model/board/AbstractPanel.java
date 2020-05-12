package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPanel {
  private int idpanel;
  private Set<AbstractPanel> next;
  private Set<Jugador> ocupado;

  /**
   * Creates a new panel.
   *
   * @param idpanel
   *     the id of the panel.
   *
   */

  public AbstractPanel (int idpanel) {
    this.idpanel = idpanel;
    this.next = new HashSet<>();
    this.ocupado = new HashSet<>();
  }

  /**
   * Creates a new panel.
   *
   * @param idpanel
   *     the id of the panel.
   * @param next
   *     the set of next panles atatched to this one
   * @param ocupado
   *     the set of players on the panel
   */

  public AbstractPanel (int idpanel, Set<AbstractPanel> next,Set<Jugador> ocupado) {
    this.idpanel = idpanel;
    this.next = next;
    this.ocupado = ocupado;
  }

  /**
   * Returns the set of players on the panel
   */

  public Set<Jugador> getOcupado() {
    return this.ocupado;
  }

  /**
   * Returns the set of the next panels
   */

  public Set<AbstractPanel> getNext() {
    return this.next;
  }

  /**
   * add a player onto the panel to represent
   * that is on that panel
   *
   * @param jugador
   *    is the player to add on the panel
   */

  public void addPla2Pan(final Jugador jugador){
    this.ocupado.add(jugador);
  }

  /**
   * remove a player from the set to represent
   * the player moves out of the panel
   *
   * @param jugador
   *    is the playes that leaves the panel
   */

  public void leave(final Jugador jugador){
    this.ocupado.remove(jugador);
  }

  /**
   * add a panel to the set of next panels
   *
   * @param panel
   *    is the panel to add to next panels
   */

  public void addNextPanel(final AbstractPanel panel){
    this.next.add(panel);
  }

  /**
   * is the action to make of a specific panel
   */

  public abstract void action(final Jugador jugador);

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
