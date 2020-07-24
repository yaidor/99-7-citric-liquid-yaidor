package com.github.cc3002.citricjuice.gui;

import com.github.cc3002.citricjuice.gui.nodes.MovableNodeBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CitricLiquid extends Application {
  private static final String RESOURCE_PATH = "src/main/resources/";

  @Override
  public void start(@NotNull Stage stage) throws FileNotFoundException {
    stage.setTitle("99.7% Citric Liquid");
    Group root = new Group();
    BorderPane players = new BorderPane();
    StackPane playersStats = playersStats();
    players.setBottom(playersStats);
    players.setCenter(board());
    players.setTop(buttons());
    int width = 1000;
    int height = 1000;
    Scene scene = new Scene(root, width, height);
    var sprite = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "boo_token.png").setPosition(100, 300).setSize(50, 50).build();
    var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "backgroundmap.png")));
    background.setFitHeight(1000);
    background.setFitWidth(1000);
    root.getChildren().add(background);
    root.getChildren().add(players);
    root.getChildren().add(sprite.getNode());
    stage.setScene(scene);
    stage.show();
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

    buttons.setPadding(new Insets(5,77,5,77));
    buttons.setSpacing(160);

    var boo = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "boo.png")));
    boo.setFitHeight(250);
    boo.setFitWidth(250);

    var chain = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "chain.png")));
    chain.setFitHeight(250);
    chain.setFitWidth(250);

    var piranha = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "prianha.png")));
    piranha.setFitHeight(250);
    piranha.setFitWidth(250);

    var goomba = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "goomba.png")));
    goomba.setFitHeight(250);
    goomba.setFitWidth(250);

    players.getChildren().addAll(piranha,goomba,chain,boo);
    buttons.getChildren().addAll(setupButtonPiranha(),setupButtonGoomba(),setupButtonChain(),setupButtonBoo());
    playersWithButtons.getChildren().addAll(players,buttons);

    return playersWithButtons;
  }

  private HBox buttons(){
    HBox buttons = new HBox();

    buttons.setPadding(new Insets(25,175,25,175));
    buttons.setSpacing(175);

    Button dice = new Button("dado");
    dice.setPrefWidth(100);
    dice.setPrefHeight(50);

    Button dodge = new Button("esquivar");
    dodge.setPrefWidth(100);
    dodge.setPrefHeight(50);

    Button defend = new Button("defender");
    defend.setPrefWidth(100);
    defend.setPrefHeight(50);

    buttons.setStyle("-fx-background-color: rgba(21,45,4,0.88);");

    buttons.getChildren().addAll(dice,dodge,defend);

    return buttons;
  }

  private GridPane board() throws FileNotFoundException{
    GridPane board = new GridPane();
    board.setPrefSize(560,560);
    board.setPadding(new Insets(10, 185, 10, 185));

    var booblock = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "booblock.png")));
    booblock.setFitHeight(70);
    booblock.setFitWidth(70);

    var piranhablock = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "piranhablock.png")));
    piranhablock.setFitHeight(70);
    piranhablock.setFitWidth(70);

    var chainblock = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "chainblock.png")));
    chainblock.setFitHeight(70);
    chainblock.setFitWidth(70);

    var goombablock = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "goombablock.png")));
    goombablock.setFitHeight(70);
    goombablock.setFitWidth(70);

    ImageView drop[] = new ImageView[4];
    for (int i=0; i<4; i++) {
      drop[i] = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "dropblock.png")));
      drop[i].setFitHeight(70);
      drop[i].setFitWidth(70);
    }

    ImageView wild[] = new ImageView[4];
    for (int i=0; i<4; i++) {
      wild[i] = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "wildblock.png")));
      wild[i].setFitHeight(70);
      wild[i].setFitWidth(70);
    }

    ImageView coin[] = new ImageView[8];
    for (int i=0; i<8; i++) {
      coin[i] = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "con_board.png")));
      coin[i].setFitHeight(70);
      coin[i].setFitWidth(70);
    }

    ImageView boss[] = new ImageView[4];
    for (int i=0; i<4; i++) {
      boss[i] = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "bossblock.png")));
      boss[i].setFitHeight(70);
      boss[i].setFitWidth(70);
    }

    ImageView bonus[] = new ImageView[12];
    for (int i=0; i<12; i++) {
      bonus[i] = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "starblock.png")));
      bonus[i].setFitHeight(70);
      bonus[i].setFitWidth(70);
    }

    board.add(piranhablock,2,2);
    board.add(goombablock,6,2);
    board.add(chainblock,2,6);
    board.add(booblock,6,6);
    board.add(bonus[0],3,0);
    board.add(bonus[1],5,1);
    board.add(bonus[2],4,2);
    board.add(bonus[3],1,3);
    board.add(bonus[4],8,3);
    board.add(bonus[5],2,4);
    board.add(bonus[6],6,4);
    board.add(bonus[7],0,5);
    board.add(bonus[8],7,5);
    board.add(bonus[9],4,6);
    board.add(bonus[10],3,7);
    board.add(bonus[11],5,8);
    board.add(boss[0],3,2);
    board.add(boss[1],6,3);
    board.add(boss[2],2,5);
    board.add(boss[3],5,6);
    board.add(drop[0],2,3);
    board.add(drop[1],3,6);
    board.add(drop[2],6,5);
    board.add(drop[3],5,2);
    board.add(wild[0],4,0);
    board.add(wild[1],0,4);
    board.add(wild[2],8,4);
    board.add(wild[3],4,8);
    board.add(coin[0],5,0);
    board.add(coin[1],3,1);
    board.add(coin[2],0,3);
    board.add(coin[3],7,3);
    board.add(coin[4],1,5);
    board.add(coin[5],8,5);
    board.add(coin[6],5,7);
    board.add(coin[7],3,8);

    return board;
  }

}
