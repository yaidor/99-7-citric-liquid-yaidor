package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossPanelTest {
  private BossPanel testBossPanel;

  @BeforeEach
  public void setUp() {
    testBossPanel = new BossPanel(2);
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
}
