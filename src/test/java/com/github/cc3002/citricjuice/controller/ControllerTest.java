package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.contenders.Boss;
import com.github.cc3002.citricjuice.model.contenders.Jugador;
import com.github.cc3002.citricjuice.model.contenders.Wild;
import com.github.cc3002.citricjuice.turn.PlayCard;
import org.intellij.lang.annotations.JdkConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
  private GameController gameController;
  protected final static String JUGADOR = "Suguri";
  protected final static String MALON = "Suguri";
  protected final static String MALULO = "Suguri";
  private DropPanel testDropPanel;
  private BossPanel testBossPanel;
  private BonusPanel testBonusPanel;
  private HomePanel testHomePanel;
  private Jugador suguri;
  private Boss malote;
  private Wild malito;

  @BeforeEach
  public void setUp(){
    gameController = new GameController();
    testHomePanel = new HomePanel(5);
    testDropPanel = new DropPanel(3);
    testBossPanel = new BossPanel(2);
    testBonusPanel = new BonusPanel(1);
    suguri = new Jugador(JUGADOR,4,1,-1,2);
    malote = new Boss(MALON, 4, 1, -1,2);
    malito = new Wild(MALULO, 4, 1, -1, 2);
  }

  @Test
  public void constructorTest(){
    assertEquals(0,gameController.totalPlayers.size());
    assertEquals(0,gameController.totalPanels.size());
    assertEquals(1,gameController.turno);
    assertEquals(1,gameController.chapter);
    assertEquals(true,gameController.turn.isStart());
  }

  @Test
  public void createPanelsTest(){
    List<IPanel> testPanels = new ArrayList<>();
    testPanels.add(gameController.createBonusPanel(1));
    testPanels.add(gameController.createBossPanel(2));
    testPanels.add(gameController.createDropPanel(3));
    testPanels.add(gameController.createEncounterPanel(4));
    testPanels.add(gameController.createHomePanel(5));
    testPanels.add(gameController.createNeutralPanel(6));
    testPanels.add(gameController.createBonusPanel(7));
    assertEquals(testPanels,gameController.totalPanels);
  }

  @Test
  public void createPlayersTest(){
    List<Jugador> jugadoresTest = new ArrayList<>();
    jugadoresTest.add(gameController.createPlayer("hola",1,1,1,1,gameController.createBonusPanel(1)));
    jugadoresTest.add(gameController.createPlayer("soy",1,1,1,1,gameController.createHomePanel(2)));
    jugadoresTest.add(gameController.createPlayer("matias",1,1,1,1,gameController.createNeutralPanel(3)));
    jugadoresTest.add(gameController.createPlayer("cansado",1,1,1,1,gameController.createEncounterPanel(4)));
    assertEquals(jugadoresTest,gameController.totalPlayers);
    Set<Jugador> prueba = new HashSet<>();
    prueba.add(gameController.totalPlayers.get(0));
    assertEquals(prueba,gameController.totalPanels.get(0).getOcupado());
    Set<Jugador> prueba2 = new HashSet<>();
    prueba2.add(gameController.totalPlayers.get(1));
    assertEquals(prueba2,gameController.totalPanels.get(1).getOcupado());
  }

  @Test
  public void createWildTest(){
    Wild wildTest = new Wild("malito", 1, 1,1,1);
    assertEquals(wildTest,gameController.createWildUnit("malito",1,1,1,1));
    assertNotEquals(wildTest,gameController.createWildUnit("otroMalito",1,1,1,1));
  }

  @Test
  public void createBossTest(){
    Boss wildTest = new Boss("malote", 1, 1,1,1);
    assertEquals(wildTest,gameController.createBossUnit("malote",1,1,1,1));
    assertNotEquals(wildTest,gameController.createBossUnit("otroMalote",1,1,1,1));
  }

  @Test
  public void setPlayereHomeTest(){
    HomePanel prueba = new HomePanel(5);
    Jugador jugador = new Jugador("Suguri",4,1,-1,2);
    prueba.setOwner(jugador);
    gameController.setPlayerHome(suguri,testHomePanel);
    assertEquals(prueba.getOwner(),testHomePanel.getOwner());
  }

  @Test
  public void getPanelsTest(){
    List<IPanel> testPanels = new ArrayList<>();
    testPanels.add(gameController.createBonusPanel(1));
    testPanels.add(gameController.createBossPanel(2));
    testPanels.add(gameController.createDropPanel(3));
    testPanels.add(gameController.createEncounterPanel(4));
    testPanels.add(gameController.createHomePanel(5));
    testPanels.add(gameController.createNeutralPanel(1));
    testPanels.add(gameController.createBonusPanel(1));
    assertEquals(testPanels,gameController.getPanels());
  }

  @Test
  public void getTurnOwnerTest(){
    gameController.createPlayer("Suguri",4,1,-1,2,testHomePanel);
    gameController.createPlayer("JUGADOR",4,1,-1,2,testDropPanel);
    gameController.createPlayer("JUGADOR2",4,1,-1,2,testBossPanel);
    gameController.createPlayer("JUGADOR3",4,1,-1,2,testBonusPanel);
    assertEquals(suguri,gameController.getTurnOwner());
    gameController.turno = gameController.turno + 1;
    assertEquals("JUGADOR",gameController.getTurnOwner().getName());
    gameController.turno = gameController.turno + 1;
    assertEquals("JUGADOR2",gameController.getTurnOwner().getName());
    gameController.turno = gameController.turno + 1;
    assertEquals("JUGADOR3",gameController.getTurnOwner().getName());
    gameController.turno = gameController.turno + 1;
    assertEquals(suguri,gameController.getTurnOwner());
    gameController.turno = gameController.turno + 1;
    assertEquals(gameController.totalPlayers.get(1),gameController.getTurnOwner());
    gameController.turno = gameController.turno + 1;
    assertEquals(gameController.totalPlayers.get(2),gameController.getTurnOwner());
    gameController.turno = gameController.turno + 1;
    assertEquals(gameController.totalPlayers.get(3),gameController.getTurnOwner());
  }

  @Test
  public void recoveryTest(){
    gameController.createPlayer("Suguri",4,1,-1,2,testHomePanel);
    Jugador jugador = gameController.getTurnOwner();
    jugador.setCurrentHP(0);
    gameController.recovery();
    assertEquals(1,jugador.getRecovery());
    assertEquals(0,jugador.getCurrentHP());
    gameController.recovery();
    assertEquals(2,jugador.getRecovery());
    assertEquals(0,jugador.getCurrentHP());
    jugador.setRecovery(7);
    gameController.recovery();
    assertEquals(0,jugador.getRecovery());
    assertEquals(4,jugador.getCurrentHP());
  }

  @Test
  public void drawCard(){
    gameController.turn.notKO();
    gameController.turn.playCard();
    assertTrue(gameController.turn.isPlayCard());
    assertFalse(gameController.turn.isMove());
    gameController.drawCard();
    assertFalse(gameController.turn.isPlayCard());
    assertTrue(gameController.turn.isMove());
  }

  @Test
  public void startTrunTest(){
    gameController.createPlayer("Suguri",4,1,-1,2,testHomePanel);
    Jugador jugador = gameController.getTurnOwner();
    assertTrue(gameController.turn.isStart());
    gameController.startTurn();
    assertTrue(gameController.turn.isNotKO());
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.turn.stayPanel();
    gameController.turn.end();
    gameController.turn.start();
    jugador.setCurrentHP(0);
    gameController.startTurn();
    assertTrue(gameController.turn.isIsKO());
  }

  @Test
  public void endTurnTest(){
    gameController.createPlayer("Suguri",4,1,-1,2,testHomePanel);
    gameController.createPlayer("JUGADOR",4,1,-1,2,testDropPanel);
    gameController.createPlayer("JUGADOR2",4,1,-1,2,testBossPanel);
    gameController.createPlayer("JUGADOR3",4,1,-1,2,testBonusPanel);
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.turn.stayPanel();
    gameController.turn.end();
    assertEquals(1, gameController.turno);
    assertEquals(1, gameController.chapter);
    assertEquals(gameController.totalPlayers.get(0),gameController.getTurnOwner());
    gameController.endTurn();
    assertEquals(2,gameController.turno);
    assertEquals(1,gameController.chapter);
    assertEquals(gameController.totalPlayers.get(1),gameController.getTurnOwner());
    assertTrue(gameController.turn.isStart());
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.turn.stayPanel();
    gameController.turn.end();
    gameController.endTurn();
    assertEquals(3,gameController.turno);
    assertEquals(1,gameController.chapter);
    assertEquals(gameController.totalPlayers.get(2),gameController.getTurnOwner());
    assertTrue(gameController.turn.isStart());
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.turn.stayPanel();
    gameController.turn.end();
    gameController.endTurn();
    assertEquals(4,gameController.turno);
    assertEquals(1,gameController.chapter);
    assertEquals(gameController.totalPlayers.get(3),gameController.getTurnOwner());
    assertTrue(gameController.turn.isStart());
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.turn.stayPanel();
    gameController.turn.end();
    gameController.endTurn();
    assertEquals(5,gameController.turno);
    assertEquals(2,gameController.chapter);
    assertEquals(gameController.totalPlayers.get(0),gameController.getTurnOwner());
    assertTrue(gameController.turn.isStart());
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.turn.stayPanel();
    gameController.turn.end();
  }

  @Test
  public void setNormaGoalTest(){
    gameController.createPlayer("Suguri",4,1,-1,2,testHomePanel);
    assertEquals(NormaGoal.STARS,gameController.totalPlayers.get(0).getNormaGoal());
    gameController.setCurrPlayerNormaGoal(NormaGoal.WINS);
    assertNotEquals(NormaGoal.STARS,gameController.totalPlayers.get(0).getNormaGoal());
    assertEquals(NormaGoal.WINS,gameController.totalPlayers.get(0).getNormaGoal());
    gameController.setCurrPlayerNormaGoal(NormaGoal.WINS);
    assertNotEquals(NormaGoal.STARS,gameController.totalPlayers.get(0).getNormaGoal());
    assertEquals(NormaGoal.WINS,gameController.totalPlayers.get(0).getNormaGoal());
    gameController.setCurrPlayerNormaGoal(NormaGoal.STARS);
    assertNotEquals(NormaGoal.WINS,gameController.totalPlayers.get(0).getNormaGoal());
    assertEquals(NormaGoal.STARS,gameController.totalPlayers.get(0).getNormaGoal());
  }

  @Test
  public void movePlayerTest(){
    Set<Jugador> test = new HashSet<>();
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    gameController.createBonusPanel(1);
    gameController.createPlayer("Suguri",4,1,-1,2,gameController.totalPanels.get(0));
    test.add(gameController.totalPlayers.get(0));
    test.add(suguri);
    gameController.createBossPanel(2);
    gameController.totalPanels.get(1).addPla2Pan(suguri);
    gameController.createHomePanel(3);
    HomePanel panel = (HomePanel) gameController.totalPanels.get(2);
    gameController.setPlayerHome(gameController.getTurnOwner(),panel);
    gameController.createDropPanel(4);
    gameController.createEncounterPanel(5);
    gameController.createNeutralPanel(6);
    gameController.createNeutralPanel(7);
    gameController.createNeutralPanel(8);
    gameController.createNeutralPanel(9);
    gameController.createNeutralPanel(10);
    gameController.createNeutralPanel(11);
    gameController.createNeutralPanel(12);
    int j = 0;
    for(int x=0; x<gameController.totalPanels.size(); x++){
      if(x+1 == gameController.totalPanels.size()){
        j = 0;
      }
      else{
        j = x+1;
      }
      gameController.totalPanels.get(x).addNextPanel(gameController.totalPanels.get(j));
    }
    gameController.setNextPanel(gameController.totalPanels.get(3),gameController.totalPanels.get(9));
    gameController.turn.notKO();
    gameController.turn.playCard();
    gameController.turn.move();
    assertTrue(gameController.turn.isMove());
    gameController.movePlayer();
    assertTrue(gameController.turn.isWantFight());
    assertEquals(gameController.totalPanels.get(1),gameController.getTurnOwner().getPanel());
    assertEquals(test,gameController.getTurnOwner().getPanel().getOcupado());
    assertEquals(0,gameController.totalPanels.get(0).getOcupado().size());
    assertEquals(2,gameController.totalPanels.get(1).getOcupado().size());
    gameController.turn.noFight();
    assertTrue(gameController.turn.isMove());
    gameController.movePlayer();
    assertTrue(gameController.turn.isWantHome());
    assertEquals(gameController.totalPanels.get(2),gameController.getTurnOwner().getPanel());
    assertEquals(1,gameController.totalPanels.get(1).getOcupado().size());
    gameController.turn.noHome();
    assertTrue(gameController.turn.isMove());
    gameController.movePlayer();
    assertTrue(gameController.turn.isPath());
    gameController.turn.backToTrack();
    assertTrue(gameController.turn.isMove());
    gameController.movePlayer();
    assertFalse(gameController.turn.isMove());
    assertFalse(gameController.turn.isPath());
    assertFalse(gameController.turn.isWantHome());
    assertFalse(gameController.turn.isWantFight());
    assertTrue(gameController.turn.isStayPanel());
  }
}
