package com.github.cc3002.citricjuice.gui;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.gui.board.BoardManagement;
import com.github.cc3002.citricjuice.model.contenders.Jugador;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class CitricLiquid extends Application {
  private final GameController controller = new GameController();
  BoardManagement internal = new BoardManagement(controller);
  private final Label resdice = new Label();
  Stage window;
  int DICE = 0;
  private static final String BOO = "boo";
  private static final String CHAIN = "chain";
  private static final String GOOMBA = "goomba";
  private static final String PIRANHA = "piranha";
  private static final String RESOURCE_PATH = "src/main/resources/";

  @Override
  public void start(@NotNull Stage stage) throws FileNotFoundException {
    window = stage;
    window.setTitle("99.7% Citric Liquid");
    Group game = new Group();
    BorderPane players = new BorderPane();
    StackPane playersStats = playersStats();
    players.setBottom(playersStats);
    players.setCenter(board());
    players.setTop(buttons());
    int width = 1000;
    int height = 1000;
    Scene scene = new Scene(game, width, height);
    var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "backgroundmap.png")));
    background.setFitHeight(1000);
    background.setFitWidth(1000);
    game.getChildren().add(background);
    game.getChildren().add(players);
    setupTimer();
    window.setScene(scene);
    window.show();
  }

  private @NotNull Button setupButtonBoo() {
    Button button = new Button("Play sound");
    button.setFocusTraversable(false);
    button.setOnAction(CitricLiquid::playSoundBoo);
    return button;
  }

  private @NotNull Button setupButtonChain() {
    Button button = new Button("Play sound");
    button.setFocusTraversable(false);
    button.setOnAction(CitricLiquid::playSoundChain);
    return button;
  }

  private @NotNull Button setupButtonGoomba() {
    Button button = new Button("Play sound");
    button.setFocusTraversable(false);
    button.setOnAction(CitricLiquid::playSoundGoomba);
    return button;
  }

  private @NotNull Button roll(){
    Button button = new Button("dado");
    button.setFocusTraversable(false);
    button.setPrefWidth(100);
    button.setPrefHeight(50);
    button.setOnAction(actionEvent -> DICE=(controller.rollPlayer(controller.getTurnOwner())));
    return button;
  }

  private @NotNull Button setupButtonPiranha() {
    Button button = new Button("Play sound");
    button.setFocusTraversable(false);
    button.setOnAction(CitricLiquid::playSoundPiranha);
    return button;
  }

  private static void playSoundBoo(ActionEvent event) {
    String audioFilePath = RESOURCE_PATH + "boo.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
          new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void playSoundChain(ActionEvent event) {
    String audioFilePath = RESOURCE_PATH + "chain.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
              new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void playSoundGoomba(ActionEvent event) {
    String audioFilePath = RESOURCE_PATH + "goomba.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
              new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void playSoundPiranha(ActionEvent event) {
    String audioFilePath = RESOURCE_PATH + "piranha.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
              new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private StackPane playersStats() throws FileNotFoundException{
    StackPane playersWithButtons = new StackPane();
    HBox players = new HBox();
    HBox buttons = new HBox();
    HBox allStats = new HBox();

    allStats.getChildren().addAll(playerStats(0),playerStats(1),playerStats(2),playerStats(3));

    buttons.setPadding(new Insets(5,77,5,77));
    buttons.setSpacing(160);

    var boo = playerImagen(BOO);
    var chain = playerImagen(CHAIN);
    var piranha = playerImagen(PIRANHA);
    var goomba = playerImagen(GOOMBA);

    players.getChildren().addAll(piranha,goomba,chain,boo);
    buttons.getChildren().addAll(setupButtonPiranha(),setupButtonGoomba(),setupButtonChain(),setupButtonBoo());
    playersWithButtons.getChildren().addAll(players,allStats);

    return playersWithButtons;
  }

  private StackPane buttons(){
    StackPane res = new StackPane();
    HBox buttons = new HBox();
    HBox show = new HBox();

    show.setPadding(new Insets(25, 0,25,280));
    show.setSpacing(175);
    resdice.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    resdice.setTextFill(Color.WHITE);
    show.getChildren().add(resdice);
    buttons.setPadding(new Insets(25,175,25,175));
    buttons.setSpacing(175);

    Button dice = roll();

    Button dodge = new Button("esquivar");
    dodge.setPrefWidth(100);
    dodge.setPrefHeight(50);

    Button defend = new Button("defender");
    defend.setPrefWidth(100);
    defend.setPrefHeight(50);

    show.setStyle("-fx-background-color: rgba(21,45,4,0.88);");

    buttons.getChildren().addAll(dice,dodge,defend);
    res.getChildren().addAll(show,buttons);
    return res;
  }

  private void setupTimer() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        resdice.setText("Resultado: "+DICE);
      }
    };
    timer.start();
  }

  private GridPane board() throws FileNotFoundException{
    GridPane board = new GridPane();
    board.setPrefSize(560,560);
    board.setPadding(new Insets(10, 185, 10, 185));
    int chap = controller.getChapter();
    String owne = controller.getPlayerName(controller.getTurnOwner());
    Label chapter = new Label("Chapter:");
    Label own = new Label("Playing:");
    Label chaps = new Label(" "+chap);
    Label owner = new Label(""+owne);
    owner.setWrapText(true);
    own.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    owner.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    chapter.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    chaps.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    var booblock = block(BOO);
    var booToken = playerToken(BOO);
    var piranhablock = block(PIRANHA);
    var piranhaToken = playerToken(PIRANHA);
    var chainblock = block(CHAIN);
    var chainToken = playerToken(CHAIN);
    var goombablock = block(GOOMBA);
    var goombaToken = playerToken(GOOMBA);

    ImageView[] drop = blocks(4,"drop");
    ImageView[] wild = blocks(4,"wild");
    ImageView[] coin = blocks(8,"coin");
    ImageView[] boss = blocks(4,"boss");
    ImageView[] bonus = blocks(12,"star");

    List<Integer> columns = internal.currentColumnPlayers(controller);
    List<Integer> rows = internal.currentRowPlayers(controller);

    board.add(bonus[0],3,0);
    board.add(wild[0],4,0);
    board.add(coin[0],5,0);
    board.add(coin[1],3,1);
    board.add(bonus[1],5,1);
    board.add(piranhablock,2,2);
    board.add(boss[0],3,2);
    board.add(bonus[2],4,2);
    board.add(drop[3],5,2);
    board.add(goombablock,6,2);
    board.add(coin[2],0,3);
    board.add(bonus[3],1,3);
    board.add(drop[0],2,3);
    board.add(boss[1],6,3);
    board.add(coin[3],7,3);
    board.add(bonus[4],8,3);
    board.add(wild[1],0,4);
    board.add(bonus[5],2,4);
    board.add(bonus[6],6,4);
    board.add(wild[2],8,4);
    board.add(bonus[7],0,5);
    board.add(coin[4],1,5);
    board.add(boss[2],2,5);
    board.add(drop[2],6,5);
    board.add(bonus[8],7,5);
    board.add(coin[5],8,5);
    board.add(chainblock,2,6);
    board.add(drop[1],3,6);
    board.add(bonus[9],4,6);
    board.add(boss[3],5,6);
    board.add(booblock,6,6);
    board.add(bonus[10],3,7);
    board.add(coin[6],5,7);
    board.add(coin[7],3,8);
    board.add(wild[3],4,8);
    board.add(bonus[11],5,8);
    board.add(chapter,0,0);
    board.add(chaps,1,0);
    board.add(own,7,0);
    board.add(owner,8,0);
    board.add(piranhaToken, columns.get(0),rows.get(0));
    board.add(goombaToken,columns.get(1),rows.get(1));
    board.add(chainToken,columns.get(2),rows.get(2));
    board.add(booToken,columns.get(3),rows.get(3));

    return board;
  }

  private HBox playerStats(int x){
    List<Jugador> jugadores = controller.getPlayers();
    HBox container = new HBox();
    VBox Stats = new VBox();
    VBox LSW = new VBox();

    Label LVL = new Label("Norma LVL: "+controller.getPlayerLvl(jugadores.get(x)));
    LVL.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      LVL.setTextFill(Color.WHITE);
    }
    else {
      LVL.setTextFill(Color.BLACK);
    }
    Label stars = new Label("Stars: "+controller.getPlayerStars(jugadores.get(x)));
    stars.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      stars.setTextFill(Color.WHITE);
    }
    else {
      stars.setTextFill(Color.BLACK);
    }
    Label wins = new Label("Wins: "+controller.getPlayerWins(jugadores.get(x)));
    wins.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      wins.setTextFill(Color.WHITE);
    }
    else {
      wins.setTextFill(Color.BLACK);
    }
    LSW.getChildren().addAll(LVL,stars,wins);
    Label hp = new Label("HP: "+controller.getPlayerHP(jugadores.get(x)));
    hp.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      hp.setTextFill(Color.WHITE);
    }
    else {
      hp.setTextFill(Color.BLACK);
    }
    Label atk = new Label("ATK: "+controller.getPlayerAtk(jugadores.get(x)));
    atk.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      atk.setTextFill(Color.WHITE);
    }
    else {
      atk.setTextFill(Color.BLACK);
    }
    Label def = new Label("DEF: "+controller.getPlayerDef(jugadores.get(x)));
    def.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      def.setTextFill(Color.WHITE);
    }
    else {
      def.setTextFill(Color.BLACK);
    }
    Label evd = new Label("EVD: "+controller.getPlayerEvd(jugadores.get(x)));
    evd.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    if(x!=3){
      evd.setTextFill(Color.WHITE);
    }
    else {
      evd.setTextFill(Color.BLACK);
    }
    Stats.getChildren().addAll(hp,atk,def,evd);
    container.getChildren().addAll(LSW,Stats);
    container.setSpacing(90);
    return container;
  }

  private ImageView playerImagen(String name) throws FileNotFoundException {
    var imagen = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + name +".png")));
    imagen.setFitHeight(250);
    imagen.setFitWidth(250);
    return imagen;
  }

  protected ImageView block(String name) throws FileNotFoundException{
    var block = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + name +"block.png")));
    block.setFitHeight(70);
    block.setFitWidth(70);
    return block;
  }

  private ImageView playerToken(String name) throws FileNotFoundException{
    var token = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + name +"_token.png")));
    token.setFitHeight(70);
    token.setFitWidth(70);
    return token;
  }

  private ImageView[] blocks(int x, String name) throws FileNotFoundException {
    ImageView[] result = new ImageView[x];
    for (int i=0; i<x; i++) {
      result[i] = block(name);
    }
    return result;
  }

}
