package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.board.*;

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
}
