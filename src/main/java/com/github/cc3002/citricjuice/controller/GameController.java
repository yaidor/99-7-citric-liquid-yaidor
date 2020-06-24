package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.contenders.*;

public class GameController {

  public IPanel createBonusPanel(int id){
    return new BonusPanel(id);
  }

  public IPanel createBossPanel(int id) {
    return new BossPanel(id);
  }

  public IPanel createDropPanel(int id) {
    return new DropPanel(id);
  }

  public IPanel createEncounterPanel(int id) {
    return new EncounterPanel(id);
  }

  public IPanel createHomePanel(int id) {
    return new HomePanel(id);
  }

  public IPanel createNeutralPanel(int id) {
    return new NeutralPanel(id);
  }

  public Jugador createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
    Jugador jugador = new Jugador(name,hitPoints,attack,defense,evasion);
    jugador.setPanel(panel);
    panel.addPla2Pan(jugador);
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


}
