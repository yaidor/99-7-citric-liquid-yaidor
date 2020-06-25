package com.github.cc3002.citricjuice.turn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {
  private Turn turn;

  @BeforeEach
  public void setUp(){
    turn = new Turn();
  }

  @Test
  public void testCreation(){
    assertTrue(turn.isStart());
  }
}
