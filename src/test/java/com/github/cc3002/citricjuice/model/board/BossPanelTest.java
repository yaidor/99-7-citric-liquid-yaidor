package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private BossPanel testBossPanel;
  private Jugador suguri;

  @BeforeEach
  public void setUp() {
    testBossPanel = new BossPanel(2);
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedBossPanel = new BossPanel(2);
    assertEquals(expectedBossPanel, testBossPanel);
  }

  @Test
  public void nextPanelTest() {
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
  }

  @Test
  public void playerPanelTest(){
    final var expectedSuguri1 = new Jugador("JUGADOR", 4, 1, -1,2);
    final var expectedSuguri2 = new Jugador("JUGADOR2", 4, 1, -1,2);
    assertTrue(testBossPanel.getOcupado().isEmpty());
    testBossPanel.addPla2Pan(expectedSuguri1);
    assertEquals(1,testBossPanel.getOcupado().size());
    testBossPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testBossPanel.getOcupado().size());
    testBossPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testBossPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri1,expectedSuguri2),testBossPanel.getOcupado());
    testBossPanel.leave(expectedSuguri1);
    assertEquals(1,testBossPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri2),testBossPanel.getOcupado());
  }

  @Test
  public void bossPanelTest() {
    final var expectedSuguri = suguri.copy();
    testBossPanel.action(suguri);
    assertEquals(expectedSuguri, suguri);
  }
}
