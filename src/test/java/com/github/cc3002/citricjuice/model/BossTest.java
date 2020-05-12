package com.github.cc3002.citricjuice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class BossTest {
  protected final static String JUGADOR = "Suguri";
  protected final static String MALON = "Suguri";
  protected final static String MALULO = "Suguri";

  private Jugador suguri;
  private Boss malote;
  private Wild malito;


  @BeforeEach
  public void setUp() {
    suguri = new Jugador(JUGADOR,4,1,-1,2);
    malote = new Boss(MALON, 4, 1, -1,2);
    malito = new Wild(MALULO, 4, 1, -1, 2);
  }

  @Test
  public void constructorTest() {
    final var expectedmalote = new Boss(MALON, 4, 1, -1, 2);
    assertEquals(expectedmalote, malote);
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
    final var expectedSuguri = new Boss(MALON, 4, 1, -1,2);
    assertEquals(expectedSuguri,malote);
  }

  @Test
  public void hitPointsTest(){
    assertEquals(malote.getMaxHP(),malote.getCurrentHP());
    malote.setCurrentHP(2);
    assertEquals(2, malote.getCurrentHP());
    malote.setCurrentHP(-1);
    assertEquals(0, malote.getCurrentHP());
    malote.setCurrentHP(5);
    assertEquals(4, malote.getCurrentHP());
  }

  @Test
  public void starsTest(){
    assertEquals(0, malote.getStars());
    malote.addStars(4);
    assertEquals(4, malote.getStars());
    malote.addStars(-8);
    assertEquals(4, malote.getStars());
  }

  @Test
  public void copyTest() {
    final var expectedMalote = new Boss(MALON, 4, 1, -1,2);
    final var actualMalote = malote.copy();
    assertEquals(expectedMalote, actualMalote);
    assertNotSame(expectedMalote, actualMalote);
  }

  @RepeatedTest(100)
  public void HPBossConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * malote.getMaxHP() + 1)
            - 2 * malote.getMaxHP();
    malote.setCurrentHP(testHP);
    assertTrue(0 <= malote.getCurrentHP()
                    && malote.getCurrentHP() <= malote.getMaxHP(),
            malote.getCurrentHP() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyBossTest() {
    final long testSeed = new Random().nextLong();
    malote.setSeed(testSeed);
    final int roll = malote.roll();
    assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void attack(){
    final var matonAttac = new Jugador(MALON, 5, 100, 1, 1);
    final var bossAttac = new Boss(MALON, 5, 100, 1, 1);
    malote.addStars(9);
    bossAttac.addStars(10);
    bossAttac.attack(malote);
    assertEquals(5,malote.getStars());
    assertEquals(14,bossAttac.getStars());
    matonAttac.attack(bossAttac);
    assertEquals(14,matonAttac.getStars());
    assertEquals(3,matonAttac.getWins());
  }
  @RepeatedTest(50)
  public void losses(){
    final var loser1 = new Boss("perdedor1", 4, 1, 0, 1);
    final var loser2 = new Boss("perdedor2", 4, 1, 0, 1);
    final var loser3 = new Boss("perdedor3", 4, 1, 0, 1);
    final var megaBoss = new Boss("megaBoss", 100, 100, 100, 100);
    final var megaJugador = new Jugador("megaJugador", 100, 100, 100, 100);
    final var megaWild = new Wild("megaWild", 100, 100, 100, 100);
    megaBoss.attack(loser1);
    assertEquals(3,megaBoss.getWins());
    megaJugador.attack(loser2);
    assertEquals(3,megaJugador.getWins());
    megaWild.attack(loser3);
    assertEquals(3,megaWild.getWins());
  }
}