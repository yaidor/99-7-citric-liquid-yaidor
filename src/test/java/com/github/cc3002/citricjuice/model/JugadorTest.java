package com.github.cc3002.citricjuice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
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
  public void constructorTest() {
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1, 2);
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void testEquals(){
    final var o = new Object();
    assertNotEquals(suguri,o);
    assertNotEquals(malito,o);
    assertNotEquals(malote,o);
    assertNotEquals(suguri,malito);
    assertNotEquals(suguri,malote);
    assertNotEquals(malito,malote);
    assertEquals(suguri,suguri);
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1,2);
    assertEquals(expectedSuguri,suguri);
  }

  @Test
  public void hitPointsTest(){
    assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
    suguri.setCurrentHP(2);
    assertEquals(2, suguri.getCurrentHP());
    suguri.setCurrentHP(-1);
    assertEquals(0, suguri.getCurrentHP());
    suguri.setCurrentHP(5);
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void starsTest(){
    assertEquals(0, suguri.getStars());
    suguri.addStars(4);
    assertEquals(4, suguri.getStars());
    suguri.addStars(-8);
    assertEquals(4, suguri.getStars());
  }

  @Test
  public void normaClearTest() {
    //only for Jugadores, but could be implemented for Boss and Wild as well
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(expectedSuguri, actualSuguri);
  }

  @RepeatedTest(100)
  public void HPJugadorConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
            - 2 * suguri.getMaxHP();
    suguri.setCurrentHP(testHP);
    assertTrue(0 <= suguri.getCurrentHP()
                    && suguri.getCurrentHP() <= suguri.getMaxHP(),
            suguri.getCurrentHP() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    //again, this is tested only for Player class, but could be implemented for Boss and Wild classes
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
            "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyJugadorTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }

}
