package com.github.cc3002.citricjuice.gui.board;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.contenders.Jugador;

import java.util.ArrayList;
import java.util.List;

public class BoardManagement {

  /**
   * creates a specific board, it was made this way
   * for usage. This way we can make any board
   * @param controller
   *      is the controller created in the GUI and unique
   *      for the game
   * is important to note that the id in every panel
   * represents the coordinate in the grid from de GUI
   * adding +1 to the columns for representation of the
   * column 0, int only saves the number
   */

  public BoardManagement(GameController controller){
    IPanel homepiranha = controller.createHomePanel(32);
    IPanel homegoomba = controller.createHomePanel(72);
    IPanel homechain = controller.createHomePanel(36);
    IPanel homeboo = controller.createHomePanel(76);
    IPanel boss1 = controller.createBossPanel(35);
    IPanel boss2 = controller.createBossPanel(42);
    IPanel boss3 = controller.createBossPanel(66);
    IPanel boss4 = controller.createBossPanel(73);
    IPanel wild1 = controller.createEncounterPanel(14);
    IPanel wild2 = controller.createEncounterPanel(50);
    IPanel wild3 = controller.createEncounterPanel(58);
    IPanel wild4 = controller.createEncounterPanel(94);
    IPanel bonus1 = controller.createBonusPanel(23);
    IPanel bonus2 = controller.createBonusPanel(34);
    IPanel bonus3 = controller.createBonusPanel(15);
    IPanel bonus4 = controller.createBonusPanel(47);
    IPanel bonus5 = controller.createBonusPanel(56);
    IPanel bonus6 = controller.createBonusPanel(68);
    IPanel bonus7 = controller.createBonusPanel(85);
    IPanel bonus8 = controller.createBonusPanel(74);
    IPanel bonus9 = controller.createBonusPanel(93);
    IPanel bonus10 = controller.createBonusPanel(61);
    IPanel bonus11 = controller.createBonusPanel(52);
    IPanel bonus12 = controller.createBonusPanel(40);
    IPanel drop1 = controller.createDropPanel(33);
    IPanel drop2 = controller.createDropPanel(46);
    IPanel drop3 = controller.createDropPanel(75);
    IPanel drop4 = controller.createDropPanel(62);
    IPanel neutral1 = controller.createNeutralPanel(13);
    IPanel neutral2 = controller.createNeutralPanel(25);
    IPanel neutral3 = controller.createNeutralPanel(48);
    IPanel neutral4 = controller.createNeutralPanel(67);
    IPanel neutral5 = controller.createNeutralPanel(95);
    IPanel neutral6 = controller.createNeutralPanel(83);
    IPanel neutral7 = controller.createNeutralPanel(60);
    IPanel neutral8 = controller.createNeutralPanel(41);
    Jugador piranha = controller.createPlayer("Piranha Plant",20,5,4,4,homepiranha);
    Jugador goomba = controller.createPlayer("Goomba",19,5,4,5,homegoomba);
    Jugador chain = controller.createPlayer("Chain Chomp",21,5,4,3,homechain);
    Jugador boo = controller.createPlayer("Boo",18,4,6,5,homeboo);
    controller.setPlayerHome(piranha, (HomePanel)homepiranha);
    controller.setPlayerHome(goomba, (HomePanel)homegoomba);
    controller.setPlayerHome(chain, (HomePanel)homechain);
    controller.setPlayerHome(boo, (HomePanel)homeboo);

    /**
     * left side of board
     */
    controller.setNextPanel(homepiranha,drop1);
    controller.setNextPanel(drop1,bonus1);
    controller.setNextPanel(drop1,bonus2);
    controller.setNextPanel(bonus1,neutral1);
    controller.setNextPanel(neutral1,wild1);
    controller.setNextPanel(wild1,bonus3);
    controller.setNextPanel(bonus3,neutral2);
    controller.setNextPanel(neutral2,boss1);
    controller.setNextPanel(bonus2,boss1);
    controller.setNextPanel(boss1, homechain);

    /**
     * bottom side of board
     */
    controller.setNextPanel(homechain,drop2);
    controller.setNextPanel(drop2,bonus4);
    controller.setNextPanel(drop2,bonus5);
    controller.setNextPanel(bonus4,neutral3);
    controller.setNextPanel(neutral3,wild3);
    controller.setNextPanel(wild3,bonus6);
    controller.setNextPanel(bonus6,neutral4);
    controller.setNextPanel(neutral4,boss3);
    controller.setNextPanel(bonus5,boss3);
    controller.setNextPanel(boss3, homeboo);

    /**
     * right side of board
     */
    controller.setNextPanel(homeboo,drop3);
    controller.setNextPanel(drop3,bonus7);
    controller.setNextPanel(drop3,bonus8);
    controller.setNextPanel(bonus7,neutral5);
    controller.setNextPanel(neutral5,wild4);
    controller.setNextPanel(wild4,bonus9);
    controller.setNextPanel(bonus9,neutral6);
    controller.setNextPanel(neutral6,boss4);
    controller.setNextPanel(bonus8,boss4);
    controller.setNextPanel(boss4, homegoomba);

    /**
     * top side of board
     */
    controller.setNextPanel(homegoomba,drop4);
    controller.setNextPanel(drop4,bonus10);
    controller.setNextPanel(drop4,bonus11);
    controller.setNextPanel(bonus10,neutral7);
    controller.setNextPanel(neutral7,wild2);
    controller.setNextPanel(wild2,bonus12);
    controller.setNextPanel(bonus12,neutral8);
    controller.setNextPanel(neutral8,boss2);
    controller.setNextPanel(bonus11,boss2);
    controller.setNextPanel(boss2, homepiranha);
  }

  /**
   * Return a list with the number of the column of
   * each player, in order of creation of the players
   * @param controller
   *      is the controller created in the GUI, we uses
   *      this parameter because is unique in the game
   */

  public List<Integer> currentColumnPlayers(GameController controller){
    List<Integer> columns = new ArrayList<>();
    List<Jugador> players = controller.getPlayers();
    for(Jugador j: players){
      IPanel panel = controller.getPlayerPanel(j);
      int id = controller.getPanelId(panel);
      int column = Integer.parseInt(Integer.toString(id).substring(0, 1));
      columns.add(column-1);
    }
    return columns;
  }

  /**
   * Return a list with the number of the row of
   * each player, in order of creation of the players
   * @param controller
   *      is the controller created in the GUI, we uses
   *      this parameter because is unique in the game
   */

  public List<Integer> currentRowPlayers(GameController controller){
    List<Integer> rows = new ArrayList<>();
    List<Jugador> players = controller.getPlayers();
    for(Jugador j: players){
      IPanel panel = controller.getPlayerPanel(j);
      int id = controller.getPanelId(panel);
      int row = id%10;
      rows.add(row);
    }
    return rows;
  }

}
