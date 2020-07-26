package com.github.cc3002.citricjuice.model.contenders;

import com.github.cc3002.citricjuice.model.contenders.Boss;
import com.github.cc3002.citricjuice.model.contenders.Jugador;
import com.github.cc3002.citricjuice.model.contenders.Wild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleTest {
  protected final static String JUGADOR = "Suguri";
  protected final static String MALON = "Suguri";
  protected final static String MALULO = "Suguri";

  private Jugador suguri;
  private Boss malote;
  private Wild malito;

  @BeforeEach
  public void setUp(){
    suguri = new Jugador(JUGADOR,4,1,-1,2);
    malote = new Boss(MALON, 4, 1, -1,2);
    malito = new Wild(MALULO, 4, 1, -1, 2);
  }

  @Test
  public void battleKOTest(){
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1, 2);
    final var jugadorAttacked = new Jugador(JUGADOR, 0, 1, -1, 2);
    final var bossAttacked = new Boss(MALON, 0, 1, -1, 2);
    final var wildAttacked = new Wild(MALULO, 0, 1, -1, 2);
    suguri.attack(jugadorAttacked);
    assertEquals(expectedSuguri, suguri);
    suguri.attack(bossAttacked);
    assertEquals(expectedSuguri, suguri);
    suguri.attack(wildAttacked);
    assertEquals(expectedSuguri, suguri);
    assertEquals(0,suguri.getWins());
    assertEquals(0,suguri.getStars());
  }

  @Test
  public void winStarNWin(){
    final var superJugador = new Jugador("Hulk",100,50,50,50);
    final var jugadorAttacked = new Jugador(JUGADOR, 1, 1, 0, 2);
    final var bossAttacked = new Boss(MALON, 1, 1, 0, 2);
    final var wildAttacked = new Wild(MALULO, 1, 1, 0, 2);
    superJugador.addStars(5);
    superJugador.addWins(5);

    jugadorAttacked.addStars(9);
    bossAttacked.addStars(3);
    wildAttacked.addStars(7);
    superJugador.attack(jugadorAttacked);
    assertEquals(9, superJugador.getStars());
    assertEquals(7, superJugador.getWins());
    superJugador.attack(bossAttacked);
    assertEquals(12, superJugador.getStars());
    assertEquals(10, superJugador.getWins());
    superJugador.attack(wildAttacked);
    assertEquals(19, superJugador.getStars());
    assertEquals(11, superJugador.getWins());
  }

  @Test
  public void evilWin(){
    final var matonAttac = new Jugador(MALON, 5, 100, 1, 1);
    final var bossAttac = new Boss(MALON, 5, 100, 1, 1);
    suguri.addStars(9);
    bossAttac.addStars(10);
    bossAttac.attack(suguri);
    assertEquals(5,suguri.getStars());
    assertEquals(14,bossAttac.getStars());
    matonAttac.attack(bossAttac);
    assertEquals(14,matonAttac.getStars());
    assertEquals(3,matonAttac.getWins());
  }
}
