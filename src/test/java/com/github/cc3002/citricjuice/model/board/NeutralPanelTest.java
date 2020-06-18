package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NeutralPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private NeutralPanel testNeutralPanel;
  private Jugador suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testNeutralPanel = new NeutralPanel(6);
    testSeed = new Random().nextLong();
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedNeutralPanel = new NeutralPanel(6);
    assertEquals(expectedNeutralPanel, testNeutralPanel);
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
  }

  @Test
  public void playerPanelTest(){
    final var expectedSuguri1 = new Jugador("JUGADOR", 4, 1, -1,2);
    final var expectedSuguri2 = new Jugador("JUGADOR2", 4, 1, -1,2);
    assertTrue(testNeutralPanel.getOcupado().isEmpty());
    testNeutralPanel.addPla2Pan(expectedSuguri1);
    assertEquals(1,testNeutralPanel.getOcupado().size());
    testNeutralPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testNeutralPanel.getOcupado().size());
    testNeutralPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testNeutralPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri1,expectedSuguri2),testNeutralPanel.getOcupado());
    testNeutralPanel.leave(expectedSuguri1);
    assertEquals(1,testNeutralPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri2),testNeutralPanel.getOcupado());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.action(suguri);
    assertEquals(expectedSuguri, suguri);
  }
}
