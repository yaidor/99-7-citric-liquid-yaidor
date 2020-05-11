package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private BonusPanel testBonusPanel;
  private Jugador suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel(1);
    testSeed = new Random().nextLong();
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedBonusPanel = new BonusPanel(1);
    assertEquals(expectedBonusPanel, testBonusPanel);
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testBonusPanel.getNext().isEmpty());
    final var expectedBonPanel1 = new BonusPanel(2);
    final var expectedBonPanel2 = new BonusPanel(3);
    testBonusPanel.addNextPanel(expectedBonPanel1);
    assertEquals(1, testBonusPanel.getNext().size());
    testBonusPanel.addNextPanel(expectedBonPanel2);
    assertEquals(2, testBonusPanel.getNext().size());
    testBonusPanel.addNextPanel(expectedBonPanel2);
    assertEquals(2, testBonusPanel.getNext().size());
    assertEquals(Set.of(expectedBonPanel1, expectedBonPanel2),
            testBonusPanel.getNext());
  }

  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.action(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
              "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
}
