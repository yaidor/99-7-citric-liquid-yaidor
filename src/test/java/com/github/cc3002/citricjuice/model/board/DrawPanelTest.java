package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private DrawPanel testDrawPanel;
  private Jugador suguri;

  @BeforeEach
  public void setUp() {
    testDrawPanel = new DrawPanel(7);
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedDrawPanel = new DrawPanel(7);
    assertEquals(expectedDrawPanel, testDrawPanel);
  }

  @Test
  public void nextPanelTest() {
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
  public void playerPanelTest(){
    final var expectedSuguri1 = new Jugador("JUGADOR", 4, 1, -1,2);
    final var expectedSuguri2 = new Jugador("JUGADOR2", 4, 1, -1,2);
    assertTrue(testDrawPanel.getOcupado().isEmpty());
    testDrawPanel.addPla2Pan(expectedSuguri1);
    assertEquals(1,testDrawPanel.getOcupado().size());
    testDrawPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testDrawPanel.getOcupado().size());
    testDrawPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testDrawPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri1,expectedSuguri2),testDrawPanel.getOcupado());
    testDrawPanel.leave(expectedSuguri1);
    assertEquals(1,testDrawPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri2),testDrawPanel.getOcupado());
  }

  @Test
  public void drawPanelTest() {
    final var expectedSuguri = suguri.copy();
    testDrawPanel.action(suguri);
    assertEquals(expectedSuguri, suguri);
  }
}
