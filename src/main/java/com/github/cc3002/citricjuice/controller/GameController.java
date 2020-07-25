package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.contenders.*;
import com.github.cc3002.citricjuice.turn.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameController {
  List<IPanel> totalPanels = new ArrayList<>();
  List<Jugador> totalPlayers = new ArrayList<>();
  int turno = 1;
  int chapter = 1;
  Turn turn = new Turn();

  /**
   * Creates a BonusPanel adding this new panel
   * to the Set of all panels
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createBonusPanel(int id){
    BonusPanel panel = new BonusPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a BossPanel adding this new panel
   * to the Set of all panels
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createBossPanel(int id) {
    BossPanel panel = new BossPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a DropPanel adding this new panel
   * to the Set of all panels
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createDropPanel(int id) {
    DropPanel panel = new DropPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a EncounterPanel adding this new panel
   * to the Set of all panels
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createEncounterPanel(int id) {
    EncounterPanel panel = new EncounterPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a HomePanel adding this new panel
   * to the Set of all panels
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createHomePanel(int id) {
    HomePanel panel = new HomePanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a NeutralPanel adding this new panel
   * to the Set of all panels
   * @param id
   *    id is the value to tag a panel
   */

  public IPanel createNeutralPanel(int id) {
    NeutralPanel panel = new NeutralPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  /**
   * Creates a new player and adds him to the list of
   * all players, and places it on a specific panel and
   * returns the created player
   * @param name
   *    is the name of the player that is going to be created
   * @param hitPoints
   *    is the amount that is going to be the hit points of the player that is going to be created
   * @param attack
   *    is the amount of the attack of the player that is going to be created
   * @param defense
   *    is the amount of the defense of the player that is going to be created
   * @param evasion
   *    is the amount of the evasion of the player that is going to be created
   * @param panel
   *    is the panel where the created player is going to be set
   */

  public Jugador createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
    Jugador jugador = new Jugador(name,hitPoints,attack,defense,evasion);
    jugador.setPanel(panel);
    panel.addPla2Pan(jugador);
    totalPlayers.add(jugador);
    return jugador;
  }

  /**
   * Creates a new Wild Unit
   * @param name
   *    is the name of the Wild Unit that is going to be created
   * @param hitPoints
   *    is the amount that is going to be the hit points of the Wild Unit that is going to be created
   * @param attack
   *    is the amount of the attack of the Wild Unit that is going to be created
   * @param defense
   *    is the amount of the defense of the Wild Unit that is going to be created
   * @param evasion
   *    is the amount of the evasion of the Wild Unit that is going to be created
   * @return
   *    is the created Wild Unit
   */

  public Wild createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
    return new Wild(name,hitPoints,attack,defense,evasion);
  }

  /**
   * Creates a new Boss Unit
   * @param name
   *    is the name of the Boss Unit that is going to be created
   * @param hitPoints
   *    is the amount that is going to be the hit points of the Boss Unit that is going to be created
   * @param attack
   *    is the amount of the attack of the Boss Unit that is going to be created
   * @param defense
   *    is the amount of the defense of the Boss Unit that is going to be created
   * @param evasion
   *    is the amount of the evasion of the Boss Unit that is going to be created
   * @return
   *    is the created Boss Unit
   */

  public Boss createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
    return new Boss(name,hitPoints,attack,defense,evasion);
  }

  /**
   * A panel is add as a next panel of on other panel
   * @param origin
   *    is the panel that is going to have a new next panel
   * @param adding
   *    is the panel that is going to be added to origin
   */

  public void setNextPanel(IPanel origin, IPanel adding) {
    origin.addNextPanel(adding);
  }

  /**
   * Sets a Player as a Owner of a Home Panel
   * @param unit
   *    is the player how is going to be the owner of the panel
   * @param panel
   *    is the Home Panel that is going to have a owner
   */

  public void setPlayerHome(Jugador unit, HomePanel panel) {
    panel.setOwner(unit);
  }

  /**
   * Returns the Set of all the panels created at this moment
   */

  public List<IPanel> getPanels(){
    return totalPanels;
  }

  /**
   * Returns the owner of the turn
   * if the turn is the first turn, then sets as owner of the turn
   * the first player created
   */

  public Jugador getTurnOwner(){
    getChapter();
    Jugador jugador = totalPlayers.get(turno-1-(4*(chapter-1)));
    if (turno == 1){
      jugador.setMyTurn(true);
    }
    return jugador;
  }

  /**
   * Enters in the state of recovery and check if the player can be recovered
   * jugador.roll is the dice that can be replaced with a method when the GUI is completed
   * and value to compare is going to be high for test purpose
   */

  public void recovery(){
    Jugador jugador = getTurnOwner();
    if(jugador.roll()>=(8-jugador.getRecovery())){
      jugador.setCurrentHP(jugador.getMaxHP());
      jugador.setRecovery(0);
      turn.endRecovery();
      //endTurn();
    }
    else{
      jugador.setRecovery(jugador.getRecovery()+1);
      turn.endRecovery();
      //endTurn();
    }
  }

  /**
   * sets the new state of draw card, this method is open for future implementations
   */

  public void drawCard(){
    turn.move();
    //movePlayer();
  }

  /**
   * stars the turn of the current player and starts the flow of
   * the turn itself
   */

  public void startTurn(){
    Jugador jugador = getTurnOwner();
    if (jugador.getCurrentHP() == 0){
      turn.isKO();
      //recovery();
    }
    else{
      turn.notKO();
      //drawCard();
    }
  }

  /**
   * Returns the actual chapter
   */

  public int getChapter(){
    chapter = ((turno-1)/4)+1;
    return chapter;
  }

  /**
   * Ends the turn of the actual owner of the turn,
   * here is trying to change the state, but some test
   * for the model doesn't use the current flow, so to
   * future implementations, the change of state is going to
   * be placed here
   */

  public void endTurn() {
    Jugador termino = getTurnOwner();
    termino.setMyTurn(false);
    turno = turno + 1;
    Jugador inicio = getTurnOwner();
    inicio.setMyTurn(true);
    int chap = getChapter();
    turn.start();
  }

  /**
   * Set the goal to level up the norma level of the player
   * @param goal
   *    is the goal itself as a param of te ENUM NormaGoal that is going
   *    to be the new norma goal of the player
   */

  public void setCurrPlayerNormaGoal(NormaGoal goal) {
    Jugador jugador = getTurnOwner();
    jugador.setNormaGoal(goal);
  }

  /**
   * delegates the job to an other method to move the player
   * the actual method gets every param needed for the recursive
   * method movingPlayer.
   * jugador.roll() represents the dice, so when the GUI is implemented
   * this method is going to change por a method of the controller and the
   * GUI
   */

  public void movePlayer() {
    Jugador jugador = getTurnOwner();
    int res = jugador.roll();
    IPanel panel = jugador.getPanel();
    movingPlayer(jugador, res, panel);
  }

  /**
   * is the method that actualy moves the player, this method is a
   * recursive one. Is in develop phase, so there are some methods
   * that are need to be created to replace every case.
   * @param jugador
   *    is the player that is going to be moved
   * @param res
   *    is the number of moves left
   * @param panel
   *    is the potential panel that te player
   *    is going to be put on.
   */

  public void movingPlayer(Jugador jugador, int res, IPanel panel) {
    Set<Jugador> jugadores = panel.getOcupado();
    if (jugadores.size()>0 && getPlayerPanel(jugador) != panel){
      List<Jugador> wantFight = wantToFight(jugadores);
      if (wantFight.size()>0){
        jugador.getPanel().leave(jugador);
        jugador.setPanel(panel);
        panel.addPla2Pan(jugador);
        jugador.attack(wantFight.get(0));
        panel.action(jugador);
        endTurn();
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
          //turn.wantHome();
          endTurn();
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
    if (n>1 && jugador.getPanel() != panel){
      dest_panel = decision(panelList);
      jugador.getPanel().leave(jugador);
      jugador.setPanel(panel);
      panel.addPla2Pan(jugador);
      panel.action(jugador);
      turn.path();
    }
    if (res == 0){
      jugador.getPanel().leave(jugador);
      jugador.setPanel(panel);
      panel.addPla2Pan(jugador);
      panel.action(jugador);
      turn.stayPanel();
      endTurn();
      return;
    }
    res = res-1;
    movingPlayer(jugador,res,dest_panel);
  }

  /**
   * Returns a boolean value as result of a decision of the user
   * if the user wants to stop in home is true, in other case is
   * false
   */

  private boolean wantHome() {
    return true;
  }

  /**
   * returns a boolean value that reepresents if the user wants to fight or not
   * @param jugadores
   *    is the set of players in the panel that the user can choose one of
   *    them to battle.
   * here tries to set the state of the turn, is modeled this way to not enter
   * in conflict with modelTest, but here is going to be change the state
   */

  private List<Jugador> wantToFight(Set<Jugador> jugadores) {
    //turn.wantFight();
    int n = jugadores.size();
    List<Jugador> lista = new ArrayList<>(n);
    lista.addAll(jugadores);
    for(int j=0;j<n;j++){
      if (lista.get(j).getCurrentHP()==0){
        lista.remove(j);
      }
    }
    return lista;
  }

  /**
   * return the panel that the user choose
   * @param paneles
   *    is the list of panels that is going to be able for
   *    the user to choose from
   */

  private IPanel decision(List<IPanel> paneles) {
    int rnd = new Random().nextInt(paneles.size());
    return paneles.get(rnd);
  }

  /**
   * return the panel where the player is in
   * @param jugador
   *    is the player that has the panel
   */

  public IPanel getPlayerPanel(Jugador jugador) {
    return jugador.getPanel();
  }

  /**
   * return the id of a panel
   * @param panel
   *     is the panel to know the id
   */

  public int getPanelId(IPanel panel){
    return panel.getIdpanel();
  }

  /**
   * returns all the players in order of creation.
   */

  public List<Jugador> getPlayers(){
    return totalPlayers;
  }

  /**
   * returns the player's name
   * @param player
   *      is the player to get the name
   */

  public String getPlayerName(Jugador player){
    return player.getName();
  }

  /**
   * returns the player's attack
   * @param player
   *      is the player to get the attack
   */

  public int getPlayerAtk(Jugador player){
    return player.getAtk();
  }

  /**
   * returns the player's denfese
   * @param player
   *      is the player to get the defense
   */

  public int getPlayerDef(Jugador player){
    return player.getDef();
  }

  /**
   * returns the player's evasion
   * @param player
   *      is the player to get the evasion
   */

  public int getPlayerEvd(Jugador player){
    return player.getEvd();
  }

  /**
   * returns the player's HP
   * @param player
   *       is the player to get the HP
   */

  public int getPlayerHP(Jugador player){
    return player.getCurrentHP();
  }

  /**
   * returns the player's stars
   * @param player
   *      is the player to get the stars
   */

  public int getPlayerStars(Jugador player){
    return player.getStars();
  }

  /**
   * returns the player's norma level
   * @param player
   *      is the player to get the norma level
   */

  public int getPlayerLvl(Jugador player){
    return player.getNormaLevel();
  }

  /**
   * returns the player's wins
   * @param player
   *      is the player to get the wins
   */

  public int getPlayerWins(Jugador player){
    return player.getWins();
  }

  public int rollPlayer(Jugador player){
    return player.roll();
  }
}
