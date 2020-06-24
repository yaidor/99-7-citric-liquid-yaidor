package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.contenders.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
  Set<IPanel> totalPanels = new HashSet<>();
  List<IContender> totalPlayers = new ArrayList<>();

  public IPanel createBonusPanel(int id){
    BonusPanel panel = new BonusPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  public IPanel createBossPanel(int id) {
    BossPanel panel = new BossPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  public IPanel createDropPanel(int id) {
    DropPanel panel = new DropPanel(id);
    totalPanels.add(panel);
    return panel;
  }

  public IPanel createEncounterPanel(int id) {
    EncounterPanel panel = new EncounterPanel(id);
    totalPanels.add(panel);
    return panel;
  }

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
}
