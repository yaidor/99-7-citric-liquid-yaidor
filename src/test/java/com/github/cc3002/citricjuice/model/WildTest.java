package com.github.cc3002.citricjuice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


public class WildTest {
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
  public void constructorTest(){
    final var expectedmalulo = new Wild(MALULO, 4, 1, -1,2);
    assertEquals(expectedmalulo,malito);
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
    final var expectedSuguri = new Wild(MALULO, 4, 1, -1,2);
    assertEquals(expectedSuguri,malito);
  }

  @Test
  public void hitPointsTest(){
    assertEquals(malito.getMaxHP(),malito.getCurrentHP());
    malito.setCurrentHP(2);
    assertEquals(2, malito.getCurrentHP());
    malito.setCurrentHP(-1);
    assertEquals(0, malito.getCurrentHP());
    malito.setCurrentHP(5);
    assertEquals(4, malito.getCurrentHP());
  }

  @Test
  public void starsTest(){
    assertEquals(0, malito.getStars());
    malito.addStars(4);
    assertEquals(4, malito.getStars());
    malito.addStars(-8);
    assertEquals(4, malito.getStars());
  }

  @Test
  public void copyTest() {
    final var expectedMalulo = new Wild(MALULO, 4, 1, -1,2);
    final var actualMalulo = malito.copy();
    assertEquals(expectedMalulo, actualMalulo);
    assertNotSame(expectedMalulo, actualMalulo);
  }

  @RepeatedTest(100)
  public void HPWildConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * malito.getMaxHP() + 1)
            - 2 * malito.getMaxHP();
    malito.setCurrentHP(testHP);
    assertTrue(0 <= malito.getCurrentHP()
                    && malito.getCurrentHP() <= malito.getMaxHP(),
            malito.getCurrentHP() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyWildTest() {
    final long testSeed = new Random().nextLong();
    malito.setSeed(testSeed);
    final int roll = malito.roll();
    assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }
}
