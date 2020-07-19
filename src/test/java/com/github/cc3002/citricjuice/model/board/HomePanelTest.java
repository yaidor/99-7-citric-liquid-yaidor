package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private HomePanel testHomePanel;
  private Jugador suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testHomePanel = new HomePanel(5);
    testSeed = new Random().nextLong();
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedHomePanel = new HomePanel(5);
    assertEquals(expectedHomePanel, testHomePanel);
  }

  @Test
  public void nextPanelTest() {
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
  }

  @Test
  public void playerPanelTest(){
    final var expectedSuguri1 = new Jugador("JUGADOR", 4, 1, -1,2);
    final var expectedSuguri2 = new Jugador("JUGADOR2", 4, 1, -1,2);
    assertTrue(testHomePanel.getOcupado().isEmpty());
    testHomePanel.addPla2Pan(expectedSuguri1);
    assertEquals(1,testHomePanel.getOcupado().size());
    testHomePanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testHomePanel.getOcupado().size());
    testHomePanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testHomePanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri1,expectedSuguri2),testHomePanel.getOcupado());
    testHomePanel.leave(expectedSuguri1);
    assertEquals(1,testHomePanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri2),testHomePanel.getOcupado());
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
  public void ownership(){
    assertEquals(null,testHomePanel.getOwner());
    testHomePanel.setOwner(suguri);
    assertEquals(suguri,testHomePanel.getOwner());
  }
}
