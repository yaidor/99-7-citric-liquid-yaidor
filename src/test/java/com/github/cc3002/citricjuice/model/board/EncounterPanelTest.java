package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncounterPanelTest {
  private EncounterPanel testEncounterPanel;

  @BeforeEach
  public void setUp() {
    testEncounterPanel = new EncounterPanel(4);
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
}
