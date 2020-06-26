package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.contenders.*;
import com.github.cc3002.citricjuice.turn.Turn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
  Set<IPanel> totalPanels = new HashSet<>();
  List<Jugador> totalPlayers = new ArrayList<>();
  int turno = 1;
  int chapter = 1;
  Turn turn;

  /**
   * Creates a BonusPanel
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createBonusPanel(int id){
    BonusPanel panel = new BonusPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a BossPanel
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createBossPanel(int id) {
    BossPanel panel = new BossPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a DropPanel
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createDropPanel(int id) {
    DropPanel panel = new DropPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a EncounterPanel
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createEncounterPanel(int id) {
    EncounterPanel panel = new EncounterPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a HomePanel
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createHomePanel(int id) {
    HomePanel panel = new HomePanel(id);
    totalPanels.add(panel);
    return panel;
  }

  public IPanel createNeutralPanel(int id) {
    NeutralPanel panel = new NeutralPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  public Jugador createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
    Jugador jugador = new Jugador(name,hitPoints,attack,defense,evasion);
    jugador.setPanel(panel);
    panel.addPla2Pan(jugador);
    totalPlayers.add(jugador);
    return jugador;
  }

  public Wild createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
    return new Wild(name,hitPoints,attack,defense,evasion);
  }

  public Boss createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
    return new Boss(name,hitPoints,attack,defense,evasion);
  }

  public void setNextPanel(IPanel origin, IPanel adding) {
    origin.addNextPanel(adding);
  }

  public void setPlayerHome(Jugador unit, HomePanel panel) {
    panel.setOwner(unit);
  }

  public Set<IPanel> getPanels(){
    return totalPanels;
  }

  public Jugador getTurnOwner(){
    getChapter();
    Jugador jugador = totalPlayers.get(turno-1-(4*(chapter-1)));
    if (turno == 1){
      jugador.setMyTurn(true);
    }
    return jugador;
  }

  public void recovery(){
    Jugador jugador = getTurnOwner();
    jugador.getRecovery();
    if(jugador.roll()>=(6-jugador.getRecovery())){
      jugador.setCurrentHP(jugador.getMaxHP());
      jugador.setRecovery(0);
      turn.endRecovery();
      endTurn();
    }
    else{
      jugador.setRecovery(jugador.getRecovery()+1);
      turn.endRecovery();
      endTurn();
    }
  }

  public void startTurn(){
    Jugador jugador = getTurnOwner();
    if (jugador.getCurrentHP() == 0){
      turn.isKO();
      recovery();
    }
    else{
      turn.notKO();

    }
  }

  public int getChapter(){
    chapter = ((turno-1)/4)+1;
    return chapter;
  }

  public void endTurn() {
    Jugador termino = getTurnOwner();
    termino.setMyTurn(false);
    turno = turno + 1;
    Jugador inicio = getTurnOwner();
    inicio.setMyTurn(true);
    int chap = getChapter();
    try{
    turn.end();
    }
    catch (Exception e){

    }
  }

  public void setCurrPlayerNormaGoal(NormaGoal goal) {
    Jugador jugador = getTurnOwner();
    jugador.setNormaGoal(goal);
  }

  public void movePlayer() {
    Jugador jugador = getTurnOwner();
    int res = jugador.roll();
    IPanel panel = jugador.getPanel();
    movingPlayer(jugador, res, panel);
  }

  private void movingPlayer(Jugador jugador, int res, IPanel panel) {
    if (res == 0){
      jugador.getPanel().leave(jugador);
      jugador.setPanel(panel);
      panel.addPla2Pan(jugador);
      panel.action(jugador);
      return;
    }
    Set<Jugador> jugadores = panel.getOcupado();
    if (jugadores.size()>0 && jugador.getPanel() != panel){
      boolean wantFight = wantToFight(jugadores);
      if (wantFight){
        jugador.getPanel().leave(jugador);
        jugador.setPanel(panel);
        panel.addPla2Pan(jugador);
        panel.action(jugador);
        return;
      }
    }
    if (panel instanceof HomePanel && jugador.getPanel() != panel){
      if(((HomePanel) panel).getOwner()==jugador) {
        boolean wantHome = wantHome();
        if (wantHome) {
          jugador.getPanel().leave(jugador);
          jugador.setPanel(panel);
          panel.addPla2Pan(jugador);
          panel.action(jugador);
          return;
        }
      }
    }
    Set<IPanel> paneles = panel.getNext();
    int n = paneles.size();
    List<IPanel> panelList = new ArrayList<>(n);
    for (IPanel p : paneles) {
      panelList.add(p);
    }
    IPanel dest_panel = panelList.get(0);
    if (n>1){
      dest_panel = desition(panelList);
      jugador.getPanel().leave(jugador);
      jugador.setPanel(panel);
      panel.addPla2Pan(jugador);
      panel.action(jugador);
      return;
    }
    res = res-1;
    movingPlayer(jugador,res,dest_panel);
  }

  private boolean wantHome() {
    return true;
  }

  private boolean wantToFight(Set<Jugador> jugadores) {
    return true;
  }

  private IPanel desition(List<IPanel> paneles) {
    return paneles.get(0);
  }

  public IPanel getPlayerPanel(Jugador jugador) {
    return jugador.getPanel();
  }
}
