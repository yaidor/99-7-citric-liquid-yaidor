package com.github.cc3002.citricjuice.model.contenders;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.BonusPanel;
import com.github.cc3002.citricjuice.model.board.DropPanel;
import com.github.cc3002.citricjuice.model.board.DropPanelTest;
import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.contenders.Boss;
import com.github.cc3002.citricjuice.model.contenders.Jugador;
import com.github.cc3002.citricjuice.model.contenders.Wild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
  protected final static String JUGADOR = "Suguri";
  protected final static String MALON = "Suguri";
  protected final static String MALULO = "Suguri";

  private Jugador suguri;
  private Boss malote;
  private Wild malito;

  @BeforeEach
  public void setUp(){
    suguri = new Jugador(JUGADOR,4,1,-1,2);
    malote = new Boss(MALON, 4, 1, -1,2);
    malito = new Wild(MALULO, 4, 1, -1, 2);
  }

  @Test
  public void constructorTest() {
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1, 2);
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void testEquals(){
    final var o = new Object();
    assertNotEquals(suguri,o);
    assertNotEquals(malito,o);
    assertNotEquals(malote,o);
    assertNotEquals(suguri,malito);
    assertNotEquals(suguri,malote);
    assertNotEquals(malito,malote);
    assertEquals(suguri,suguri);
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1,2);
    assertEquals(expectedSuguri,suguri);
  }

  @Test
  public void hitPointsTest(){
    assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
    suguri.setCurrentHP(2);
    assertEquals(2, suguri.getCurrentHP());
    suguri.setCurrentHP(-1);
    assertEquals(0, suguri.getCurrentHP());
    suguri.setCurrentHP(5);
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void atkTest(){
    assertEquals(1,suguri.getAtk());
    suguri.setAtk(3);
    assertEquals(3,suguri.getAtk());
    suguri.setAtk(-1);
    assertEquals(0,suguri.getAtk());

  }

  @Test
  public void defTest(){
    assertEquals(-1,suguri.getDef());
    suguri.setDef(3);
    assertEquals(3,suguri.getDef());
    suguri.setDef(-1);
    assertEquals(0,suguri.getDef());
  }

  @Test
  public void defEvd(){
    assertEquals(2,suguri.getEvd());
    suguri.setEvd(3);
    assertEquals(3,suguri.getEvd());
    suguri.setEvd(-1);
    assertEquals(0,suguri.getEvd());
  }

  @Test
  public void starsTest(){
    assertEquals(0, suguri.getStars());
    suguri.addStars(4);
    assertEquals(4, suguri.getStars());
    suguri.addStars(-8);
    assertEquals(4, suguri.getStars());
  }

  @Test
  public void whereAreWeTest(){
    final var currentPanel = new HomePanel(2);
    final var moveToPanel = new BonusPanel(1);
    final var lastPanel = new DropPanel(3);
    suguri.setPanel(currentPanel);
    assertEquals(currentPanel,suguri.getPanel());
    suguri.setPanel(moveToPanel);
    assertEquals(moveToPanel,suguri.getPanel());
    suguri.setPanel(lastPanel);
    assertEquals(lastPanel,suguri.getPanel());
  }

  @Test
  public void normaClearTest() {
    //only for Jugadores, but could be implemented for Boss and Wild as well
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
  }

  @Test
  public void setNormaGoal(){
    assertEquals(NormaGoal.STARS,suguri.getNormaGoal());
    suguri.setNormaGoal(NormaGoal.WINS);
    assertEquals(NormaGoal.WINS,suguri.getNormaGoal());
  }

  @Test
  public void turnTest(){
    assertEquals(false, suguri.getMyTurn());
    suguri.setMyTurn(true);
    assertEquals(true,suguri.getMyTurn());
  }

  @Test
  public void winsTest(){
    assertEquals(NormaGoal.STARS,suguri.getNormaGoal());
    suguri.setNormaGoal(NormaGoal.WINS);
    suguri.addWins(4);
    suguri.normaCheck();
    assertEquals(1,suguri.getNormaLevel());
    suguri.normaClear();
    suguri.normaCheck();
    assertEquals(3,suguri.getNormaLevel());
    suguri.normaCheck();
    assertEquals(3,suguri.getNormaLevel());
    suguri.addWins(10);
    suguri.normaCheck();
    assertEquals(4,suguri.getNormaLevel());
    suguri.normaCheck();
    assertEquals(5,suguri.getNormaLevel());
    suguri.normaCheck();
    assertEquals(6,suguri.getNormaLevel());
    suguri.normaCheck();
    assertEquals(6,suguri.getNormaLevel());
  }

  @Test
  public void recoveryTest(){
    assertEquals(0,suguri.getRecovery());
    suguri.setRecovery(-6);
    assertEquals(0,suguri.getRecovery());
    suguri.setRecovery(6);
    assertEquals(6,suguri.getRecovery());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Jugador(JUGADOR, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(expectedSuguri, actualSuguri);
  }

  @RepeatedTest(100)
  public void HPJugadorConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
            - 2 * suguri.getMaxHP();
    suguri.setCurrentHP(testHP);
    assertTrue(0 <= suguri.getCurrentHP()
                    && suguri.getCurrentHP() <= suguri.getMaxHP(),
            suguri.getCurrentHP() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    //again, this is tested only for Player class, but could be implemented for Boss and Wild classes
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
            "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyJugadorTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void attack(){
    final var matonAttac = new Jugador(MALON, 5, 100, 1, 1);
    final var bossAttac = new Boss(MALON, 5, 100, 1, 1);
    suguri.addStars(9);
    bossAttac.addStars(10);
    bossAttac.attack(suguri);
    assertEquals(5,suguri.getStars());
    assertEquals(14,bossAttac.getStars());
    matonAttac.attack(bossAttac);
    assertEquals(14,matonAttac.getStars());
    assertEquals(3,matonAttac.getWins());
  }
  @RepeatedTest(50)
  public void losses(){
    final var loser1 = new Jugador("perdedor1", 4, 1, 0, 1);
    final var loser2 = new Jugador("perdedor2", 4, 1, 0, 1);
    final var loser3 = new Jugador("perdedor3", 4, 1, 0, 1);
    final var megaBoss = new Boss("megaBoss", 100, 100, 100, 100);
    final var megaJugador = new Jugador("megaJugador", 100, 100, 100, 100);
    final var megaWild = new Wild("megaWild", 100, 100, 100, 100);
    megaBoss.attack(loser1);
    assertEquals(2,megaBoss.getWins());
    megaJugador.attack(loser2);
    assertEquals(2,megaJugador.getWins());
    megaWild.attack(loser3);
    assertEquals(2,megaWild.getWins());
  }

}
