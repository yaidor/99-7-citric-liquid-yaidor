package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private HomePanel testHomePanel;
  private NeutralPanel testNeutralPanel;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private DrawPanel testDrawPanel;
  private Jugador suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel(1);
    testBossPanel = new BossPanel(2);
    testDropPanel = new DropPanel(3);
    testEncounterPanel = new EncounterPanel(4);
    testHomePanel = new HomePanel(5);
    testNeutralPanel = new NeutralPanel(6);
    testDrawPanel = new DrawPanel(7);
    testSeed = new Random().nextLong();
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedBonusPanel = new BonusPanel(1);
    assertEquals(expectedBonusPanel, testBonusPanel);
    final var expectedBossPanel = new BossPanel(2);
    assertEquals(expectedBossPanel, testBossPanel);
    final var expectedDropPanel = new DropPanel(3);
    assertEquals(expectedDropPanel, testDropPanel);
    final var expectedEncounterPanel = new EncounterPanel(4);
    assertEquals(expectedEncounterPanel, testEncounterPanel);
    final var expectedHomePanel = new HomePanel(5);
    assertEquals(expectedHomePanel, testHomePanel);
    final var expectedNeutralPanel = new NeutralPanel(6);
    assertEquals(expectedNeutralPanel, testNeutralPanel);
    final var expectedDrawPanel = new DrawPanel(7);
    assertEquals(expectedDrawPanel, testDrawPanel);
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNext().isEmpty());
    final var expectedPanel1 = new NeutralPanel(2);
    final var expectedPanel2 = new NeutralPanel(3);
    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNext().size());
    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNext().size());
    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNext().size());
    assertEquals(Set.of(expectedPanel1, expectedPanel2),
            testNeutralPanel.getNext());

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

    assertTrue(testBossPanel.getNext().isEmpty());
    final var expectedBosPanel1 = new BossPanel(2);
    final var expectedBosPanel2 = new BossPanel(3);
    testBossPanel.addNextPanel(expectedBosPanel1);
    assertEquals(1, testBossPanel.getNext().size());
    testBossPanel.addNextPanel(expectedBosPanel2);
    assertEquals(2, testBossPanel.getNext().size());
    testBossPanel.addNextPanel(expectedBosPanel2);
    assertEquals(2, testBossPanel.getNext().size());
    assertEquals(Set.of(expectedBosPanel1, expectedBosPanel2),
            testBossPanel.getNext());

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

    assertTrue(testEncounterPanel.getNext().isEmpty());
    final var expectedEncPanel1 = new EncounterPanel(2);
    final var expectedEncPanel2 = new EncounterPanel(3);
    testEncounterPanel.addNextPanel(expectedEncPanel1);
    assertEquals(1, testEncounterPanel.getNext().size());
    testEncounterPanel.addNextPanel(expectedEncPanel2);
    assertEquals(2, testEncounterPanel.getNext().size());
    testEncounterPanel.addNextPanel(expectedEncPanel2);
    assertEquals(2, testEncounterPanel.getNext().size());
    assertEquals(Set.of(expectedEncPanel1, expectedEncPanel2),
            testEncounterPanel.getNext());

    assertTrue(testHomePanel.getNext().isEmpty());
    final var expectedHomPanel1 = new HomePanel(2);
    final var expectedHomPanel2 = new HomePanel(3);
    testHomePanel.addNextPanel(expectedHomPanel1);
    assertEquals(1, testHomePanel.getNext().size());
    testHomePanel.addNextPanel(expectedHomPanel2);
    assertEquals(2, testHomePanel.getNext().size());
    testHomePanel.addNextPanel(expectedHomPanel2);
    assertEquals(2, testHomePanel.getNext().size());
    assertEquals(Set.of(expectedHomPanel1, expectedHomPanel2),
            testHomePanel.getNext());

    assertTrue(testDrawPanel.getNext().isEmpty());
    final var expectedDraPanel1 = new DrawPanel(2);
    final var expectedDraPanel2 = new DrawPanel(3);
    testDrawPanel.addNextPanel(expectedDraPanel1);
    assertEquals(1, testDrawPanel.getNext().size());
    testDrawPanel.addNextPanel(expectedDraPanel2);
    assertEquals(2, testDrawPanel.getNext().size());
    testDrawPanel.addNextPanel(expectedDraPanel2);
    assertEquals(2, testDrawPanel.getNext().size());
    assertEquals(Set.of(expectedDraPanel1, expectedDraPanel2),
            testDrawPanel.getNext());
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    testHomePanel.action(suguri);
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());

    suguri.setCurrentHP(1);
    testHomePanel.action(suguri);
    assertEquals(2, suguri.getCurrentHP());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.action(suguri);
    assertEquals(expectedSuguri, suguri);
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
