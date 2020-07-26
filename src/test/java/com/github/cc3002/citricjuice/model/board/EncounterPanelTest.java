package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.contenders.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncounterPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private EncounterPanel testEncounterPanel;
  private Jugador suguri;

  @BeforeEach
  public void setUp() {
    testEncounterPanel = new EncounterPanel(4);
    suguri = new Jugador(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    final var expectedEncounterPanel = new EncounterPanel(4);
    assertEquals(expectedEncounterPanel, testEncounterPanel);
  }

  @Test
  public void nextPanelTest() {
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
  }

  @Test
  public void playerPanelTest(){
    final var expectedSuguri1 = new Jugador("JUGADOR", 4, 1, -1,2);
    final var expectedSuguri2 = new Jugador("JUGADOR2", 4, 1, -1,2);
    assertTrue(testEncounterPanel.getOcupado().isEmpty());
    testEncounterPanel.addPla2Pan(expectedSuguri1);
    assertEquals(1,testEncounterPanel.getOcupado().size());
    testEncounterPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testEncounterPanel.getOcupado().size());
    testEncounterPanel.addPla2Pan(expectedSuguri2);
    assertEquals(2,testEncounterPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri1,expectedSuguri2),testEncounterPanel.getOcupado());
    testEncounterPanel.leave(expectedSuguri1);
    assertEquals(1,testEncounterPanel.getOcupado().size());
    assertEquals(Set.of(expectedSuguri2),testEncounterPanel.getOcupado());
  }

  @Test
  public void encounterPanelTest() {
    final var expectedSuguri = suguri.copy();
    testEncounterPanel.action(suguri);
    assertEquals(expectedSuguri, suguri);
  }
}
