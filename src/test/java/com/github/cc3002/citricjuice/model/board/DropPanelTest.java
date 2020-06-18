package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private DropPanel testDropPanel;
  private Jugador suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testDropPanel = new DropPanel(3);
    testSeed = new Random().nextLong();
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedDropPanel = new DropPanel(3);
    assertEquals(expectedDropPanel, testDropPanel);
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testDropPanel.getNext().isEmpty());
    final var expectedDroPanel1 = new DropPanel(2);
    final var expectedDroPanel2 = new DropPanel(3);
    testDropPanel.addNextPanel(expectedDroPanel1);
    assertEquals(1, testDropPanel.getNext().size());
    testDropPanel.addNextPanel(expectedDroPanel2);
    assertEquals(2, testDropPanel.getNext().size());
    testDropPanel.addNextPanel(expectedDroPanel2);
    assertEquals(2, testDropPanel.getNext().size());
    assertEquals(Set.of(expectedDroPanel1, expectedDroPanel2),
            testDropPanel.getNext());
  }

  @Test
  public void playerPanelTest(){
    final var expectedSuguri1 = new Jugador("JUGADOR", 4, 1, -1,2);
    final var expectedSuguri2 = new Jugador("JUGADOR2", 4, 1, -1,2);
    assertTrue(testDropPanel.getOcupado().isEmpty());
    testDropPanel.addPla2Pan(expectedSuguri1);
    assertEquals(1,testDropPanel.getOcupado().size());
    testDropPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testDropPanel.getOcupado().size());
    testDropPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testDropPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri1,expectedSuguri2),testDropPanel.getOcupado());
    testDropPanel.leave(expectedSuguri1);
    assertEquals(1,testDropPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri2),testDropPanel.getOcupado());
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.addStars(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.action(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
              "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
}
