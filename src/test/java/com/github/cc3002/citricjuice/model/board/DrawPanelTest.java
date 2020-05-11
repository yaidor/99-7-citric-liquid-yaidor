package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawPanelTest {
  private DrawPanel testDrawPanel;

  @BeforeEach
  public void setUp() {
    testDrawPanel = new DrawPanel(7);
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
}
