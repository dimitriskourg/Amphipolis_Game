package View;

import Controller.Controller;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import sun.audio.*;
import java.net.MalformedURLException;
import java.net.URL;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * This class creates the main Window of the game
 * @author csd4351
 */
public class View extends javax.swing.JFrame implements ActionListener {
    private Controller controller;
    private JFrame win;
    private JLayeredPane mainPane;
    private JButton drawTiles;
    private JButton endTurn;
    private JLabel playerNumber;
    private JLabel useCharacter;
    private ArrayList<JButton> characters; //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
    private JLabel board;
    private URL imageURL;
    private ClassLoader cldr;
    private ArrayList<JButton> tiles_buttons;
    private ArrayList<JLabel> tiles_labels;
    private ArrayList<JLabel> players_tiles;
    private ArrayList<JLabel> players_labels;
    private int musicCalc;
    /* THE ORDER FOR tiles_buttons and tiles_labels and players_tiles and players_labels is:
        0) amphora_blue     9) mosaic_red
        1) amphora_brown   10) mosaic_yellow
        2) amphora_green   11) skeleton_big_bottom
        3) amphora_purple  12) skeleton_big_top
        4) amphora_red     13) skeleton_small_bottom
        5) amphora_yellow  14) skeleton_small_top
        6) caryatid        15) sphinx
        7) landslide
        8) mosaic_green
     */
    private BasicPlayer player;
    private JButton playMusic;


    /**
     * <b>Constructor:</b> Creates a new Window and initializes some buttons and panels <br />
     * <b>PostConditions:</b> Creates a new Window and initializes some buttons and panels <br />
     */
    public View(Controller Cntrl){
        this.controller = Cntrl;
        cldr = this.getClass().getClassLoader();

        mainPane = new JLayeredPane();
        mainPane.setBounds(0 , 0 , 1100, 970);

        //todo delete or not -- music player
        musicCalc = 0;
        player = new BasicPlayer();
        playMusic = new JButton();
        playMusic.setOpaque(true);
        imageURL = cldr.getResource("images/music.png");
        playMusic.setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        playMusic.setBounds(1000, 750 , 50 ,50);
        playMusic.setBackground(new Color(20, 117, 51));
        playMusic.addActionListener(this);
        this.add(playMusic);
        mainPane.add(playMusic, Integer.valueOf(2));



        //creating all elements
        board = new JLabel();
        board.setOpaque(true);
        imageURL = cldr.getResource("images/background.png");
        board.setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH)));
        board.setBounds(0 , 0, 800, 800);

        playerNumber = new JLabel("Player " + controller.getCurrentPlayerId());
        playerNumber.setOpaque(true);
        Font font = playerNumber.getFont();
        playerNumber.setFont(new Font(font.getName(), Font.PLAIN, 22));
        playerNumber.setBounds(900, 0, 120, 50 );

        useCharacter = new JLabel("Use Character");
        useCharacter.setOpaque(true);
        useCharacter.setFont(new Font("Verdana", Font.PLAIN, 18));
        useCharacter.setBounds(880, 50, 150, 50);

        characters = new ArrayList<JButton>();
        characters.add(new JButton());
        characters.add(new JButton());
        characters.add(new JButton());
        characters.add(new JButton());

        imageURL = cldr.getResource("images/archaeologist.png");
        characters.get(1).setOpaque(true);
        characters.get(1).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(120, 195, Image.SCALE_SMOOTH)));
        characters.get(1).setBounds(810 , 110, 120 ,195);


        imageURL = cldr.getResource("images/assistant.png");
        characters.get(0).setOpaque(true);
        characters.get(0).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(120, 195, Image.SCALE_SMOOTH)));
        characters.get(0).setBounds(945 , 110, 120 ,195);

        imageURL = cldr.getResource("images/digger.png");
        characters.get(2).setOpaque(true);
        characters.get(2).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(120, 195, Image.SCALE_SMOOTH)));
        characters.get(2).setBounds(810 , 330, 120 ,195);

        imageURL = cldr.getResource("images/professor.png");
        characters.get(3).setOpaque(true);
        characters.get(3).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(120, 195, Image.SCALE_SMOOTH)));
        characters.get(3).setBounds(945 , 330, 120 ,195);


        for(JButton charact : characters){
            charact.addActionListener(this);
            this.add(charact);
        }

        //here I create all different buttons and labels of tiles
        tiles_buttons = new ArrayList<JButton>();
        for (int i = 0; i <= 16; i++) {
            tiles_buttons.add(new JButton());
            tiles_buttons.get(i).setOpaque(true);
        }

        tiles_labels = new ArrayList<JLabel>();
        for(int i = 0; i<16; i++){
            tiles_labels.add(new JLabel("0"));
            tiles_labels.get(i).setOpaque(true);
        }

        imageURL = cldr.getResource("images/amphora_blue.png");
        tiles_buttons.get(0).setBounds(30 , 585, 50 ,50);
        tiles_buttons.get(0).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(0).setBounds(84 , 600, 18, 18);

        imageURL = cldr.getResource("images/amphora_brown.png");
        tiles_buttons.get(1).setBounds(140 , 584, 50 ,50);
        tiles_buttons.get(1).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(1).setBounds(194 , 600, 18, 18);

        imageURL = cldr.getResource("images/amphora_green.png");
        tiles_buttons.get(2).setBounds(30 , 650, 50 ,50);
        tiles_buttons.get(2).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(2).setBounds(84 , 670, 18, 18);

        imageURL = cldr.getResource("images/amphora_purple.png");
        tiles_buttons.get(3).setBounds(140 , 649, 50 ,50);
        tiles_buttons.get(3).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(3).setBounds(194 , 670, 18, 18);

        imageURL = cldr.getResource("images/amphora_red.png");
        tiles_buttons.get(4).setBounds(30 ,716 , 50 ,50);
        tiles_buttons.get(4).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(4).setBounds(84 , 728, 18, 18);

        imageURL = cldr.getResource("images/amphora_yellow.png");
        tiles_buttons.get(5).setBounds(140 , 716, 50 ,50);
        tiles_buttons.get(5).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(5).setBounds(194 , 728, 18, 18);

        imageURL = cldr.getResource("images/caryatid.png");
        tiles_buttons.get(6).setBounds(570 , 100, 50 ,50);
        tiles_buttons.get(6).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(6).setBounds(625 , 120, 18, 18);

        imageURL = cldr.getResource("images/sphinx.png");
        tiles_buttons.get(15).setBounds(690 , 100, 50 ,50);
        tiles_buttons.get(15).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(15).setBounds(745 , 120, 18, 18);

        imageURL = cldr.getResource("images/landslide.png");
        tiles_buttons.get(7).setBounds(370 , 440, 50 ,50);
        tiles_buttons.get(7).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(7).setBounds(425 , 460, 18, 18);

        imageURL = cldr.getResource("images/mosaic_green.png");
        tiles_buttons.get(8).setBounds(50 , 70, 50 ,50);
        tiles_buttons.get(8).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(8).setBounds(103 , 90, 18, 18);

        imageURL = cldr.getResource("images/mosaic_red.png");
        tiles_buttons.get(9).setBounds(160 , 70, 50 ,50);
        tiles_buttons.get(9).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(9).setBounds(213 , 90, 18, 18);

        imageURL = cldr.getResource("images/mosaic_yellow.png");
        tiles_buttons.get(10).setBounds(105 , 140, 50 ,50);
        tiles_buttons.get(10).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(10).setBounds(160 , 160, 18, 18);

        imageURL = cldr.getResource("images/skeleton_big_bottom.png");
        tiles_buttons.get(11).setBounds(570 , 680, 50 ,50);
        tiles_buttons.get(11).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(11).setBounds(623 , 700, 18, 18);

        imageURL = cldr.getResource("images/skeleton_big_top.png");
        tiles_buttons.get(12).setBounds(570 , 620, 50 ,50);
        tiles_buttons.get(12).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(12).setBounds(623 , 640, 18, 18);

        imageURL = cldr.getResource("images/skeleton_small_bottom.png");
        tiles_buttons.get(13).setBounds(680 , 680, 50 ,50);
        tiles_buttons.get(13).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(13).setBounds(733 , 700, 18, 18);

        imageURL = cldr.getResource("images/skeleton_small_top.png");
        tiles_buttons.get(14).setBounds(680 , 620, 50 ,50);
        tiles_buttons.get(14).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(50 , 50 , Image.SCALE_SMOOTH)));
        tiles_labels.get(14).setBounds(733 , 640, 18, 18);

        for(JButton til : tiles_buttons){
            til.addActionListener(this);
            this.add(til);
        }

        //add players tiles and labels
        players_labels = new ArrayList<JLabel>();
        for(int i=0; i<16 ; i++){
            players_labels.add(new JLabel("0"));
        }

        players_tiles = new ArrayList<JLabel>();
        for(int i=0; i<16; i++){
            players_tiles.add(new JLabel());
            players_tiles.get(i).setOpaque(true);
        }

        imageURL = cldr.getResource("images/amphora_blue.png");
        players_tiles.get(0).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(0).setBounds(10, 820 , 60 , 60);
        players_labels.get(0).setBounds(30 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/amphora_brown.png");
        players_tiles.get(1).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(1).setBounds(80, 820 , 60 , 60);
        players_labels.get(1).setBounds(100 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/amphora_green.png");
        players_tiles.get(2).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(2).setBounds(150, 820 , 60 , 60);
        players_labels.get(2).setBounds(170 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/amphora_purple.png");
        players_tiles.get(3).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(3).setBounds(220, 820 , 60 , 60);
        players_labels.get(3).setBounds(240 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/amphora_red.png");
        players_tiles.get(4).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(4).setBounds(290, 820 , 60 , 60);
        players_labels.get(4).setBounds(310 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/amphora_yellow.png");
        players_tiles.get(5).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(5).setBounds(360, 820 , 60 , 60);
        players_labels.get(5).setBounds(380 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/caryatid.png");
        players_tiles.get(6).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(6).setBounds(430, 820 , 60 , 60);
        players_labels.get(6).setBounds(450 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/mosaic_green.png");
        players_tiles.get(8).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(8).setBounds(500, 820 , 60 , 60);
        players_labels.get(8).setBounds(520 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/mosaic_red.png");
        players_tiles.get(9).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(9).setBounds(570, 820 , 60 , 60);
        players_labels.get(9).setBounds(590 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/mosaic_yellow.png");
        players_tiles.get(10).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(10).setBounds(640, 820 , 60 , 60);
        players_labels.get(10).setBounds(660 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/skeleton_big_bottom.png");
        players_tiles.get(11).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(11).setBounds(710, 820 , 60 , 60);
        players_labels.get(11).setBounds(730 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/skeleton_big_top.png");
        players_tiles.get(12).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(12).setBounds(780, 820 , 60 , 60);
        players_labels.get(12).setBounds(800 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/skeleton_small_bottom.png");
        players_tiles.get(13).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(13).setBounds(850, 820 , 60 , 60);
        players_labels.get(13).setBounds(870 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/skeleton_small_top.png");
        players_tiles.get(14).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(14).setBounds(920, 820 , 60 , 60);
        players_labels.get(14).setBounds(940 , 890 , 20 ,20);

        imageURL = cldr.getResource("images/sphinx.png");
        players_tiles.get(15).setIcon(new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(60 , 60 , Image.SCALE_SMOOTH)));
        players_tiles.get(15).setBounds(990, 820 , 60 , 60);
        players_labels.get(15).setBounds(1010 , 890 , 20 ,20);


        this.drawTiles = new JButton("Draw Tiles");
        drawTiles.setOpaque(true);
        drawTiles.setBounds(820, 560, 230, 80);

        this.drawTiles.addActionListener(this);
        this.add(this.drawTiles);

        this.endTurn = new JButton("End Turn");
        endTurn.setOpaque(true);
        endTurn.setBounds(820 , 660 , 230 , 80);

        this.endTurn.addActionListener(this);
        this.add(this.endTurn);

        //ADDING ALL ELEMENTS TO THE MAIN PANE
        for (JButton charact : characters) {
            mainPane.add( charact ,Integer.valueOf(1) );
        }

        for(JButton til : tiles_buttons){
            mainPane.add(til, Integer.valueOf(1));
        }

        for(JLabel til : tiles_labels){
            mainPane.add(til , Integer.valueOf(1));
        }

        for(JLabel pl : players_labels){
            mainPane.add(pl , Integer.valueOf(1));
        }

        for(JLabel pl : players_tiles){
            mainPane.add(pl , Integer.valueOf(2));
        }

        mainPane.add(board, Integer.valueOf(0));
        mainPane.add(playerNumber, Integer.valueOf(0));
        mainPane.add(useCharacter, Integer.valueOf(0));
        mainPane.add(drawTiles, Integer.valueOf(1));
        mainPane.add(endTurn , Integer.valueOf(1));

        //adding mane pane to window
        imageURL = cldr.getResource("images/tile_back.png");
        win = new JFrame("Amphipolis");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setIconImage(new ImageIcon(imageURL).getImage());
        win.setSize(1100, 970);
        win.setResizable(false);
        win.setLayout(null);

        win.add(mainPane);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        win.setLocation(dim.width/2-win.getSize().width/2, dim.height/2-win.getSize().height/2);

        win.setVisible(true);

        //set all buttons tiles to disabled
        for(JButton til : tiles_buttons){
            til.setEnabled(false);
        }
        tiles_buttons.get(7).setEnabled(true);

        //Mouse listeners to change the size of characters when mouse is over the buttons

        characters.get(0).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                characters.get(0).setBackground(Color.RED);
                characters.get(0).setSize(130,205);
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                characters.get(0).setSize(120, 195);
            }
        });
        characters.get(1).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                characters.get(1).setBackground(Color.RED);
                characters.get(1).setSize(130,205);
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                characters.get(1).setSize(120, 195);
            }
        });
        characters.get(2).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                characters.get(2).setBackground(Color.RED);
                characters.get(2).setSize(130,205);
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                characters.get(2).setSize(120, 195);
            }
        });
        characters.get(3).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                characters.get(3).setBackground(Color.RED);
                characters.get(3).setSize(130,205);
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                characters.get(3).setSize(120, 195);
            }
        });

    }

    /**
     * <b>Transformer:</b> Whenever a button is clicked this method is used for doing some action
     * <b>PostCondition:</b> Doing some action whenever a button is clicked
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source == this.drawTiles){
            System.out.println("Draw tiles key pressed!!");
            controller.addTilesToBoard();
            System.out.println("ALL GOOD!");
        }
        else if(source == this.playMusic){
            System.out.println("Play music key pressed!!");
            this.music();
        }
        else if (source == this.endTurn){
            System.out.println("End turn key pressed!");
            controller.nextTurn();
            setPlayerNumber();
        }
        else if(source == this.characters.get(0)){
            System.out.println("0");
            controller.useCharacter(1);
        }
        else if(source == this.characters.get(1)){
            System.out.println("1");
            controller.useCharacter(2);
        }
        else if(source == this.characters.get(2)){
            System.out.println("2");
            controller.useCharacter(3);
        }
        else if(source == this.characters.get(3)){
            System.out.println("3");
            controller.useCharacter(4);
        }
        else if(source == this.tiles_buttons.get(0)){
            System.out.println("amphora_blue");
            controller.addTilesToPlayer(0 , 2);
        }
        else if(source == this.tiles_buttons.get(1)){
            System.out.println("amphora_brown");
            controller.addTilesToPlayer(1 , 2);
        }
        else if(source == this.tiles_buttons.get(2)){
            System.out.println("amphora_green");
            controller.addTilesToPlayer(2 , 2);
        }
        else if(source == this.tiles_buttons.get(3)){
            System.out.println("amphora_purple");
            controller.addTilesToPlayer(3 , 2);
        }
        else if(source == this.tiles_buttons.get(4)){
            System.out.println("amphora_red");
            controller.addTilesToPlayer(4 , 2);
        }
        else if(source == this.tiles_buttons.get(5)){
            System.out.println("amphora_yellow");
            controller.addTilesToPlayer(5 , 2);
        }
        else if(source == this.tiles_buttons.get(6)){
            System.out.println("caryatid");
            controller.addTilesToPlayer(6 , 4);
        }
        else if(source == this.tiles_buttons.get(8)){
            System.out.println("mosaic_green");
            controller.addTilesToPlayer(8 , 1);
        }
        else if(source == this.tiles_buttons.get(9)){
            System.out.println("mosaic_red");
            controller.addTilesToPlayer(9 , 1);
        }
        else if(source == this.tiles_buttons.get(10)){
            System.out.println("mosaic_yellow");
            controller.addTilesToPlayer(10, 1);
        }
        else if(source == this.tiles_buttons.get(11)){
            System.out.println("skeleton_big_bottom");
            controller.addTilesToPlayer(11 ,3);
        }
        else if(source == this.tiles_buttons.get(12)){
            System.out.println("skeleton_big_top");
            controller.addTilesToPlayer(12 , 3);
        }
        else if(source == this.tiles_buttons.get(13)){
            System.out.println("skeleton_small_bottom");
            controller.addTilesToPlayer(13 , 3);
        }
        else if(source == this.tiles_buttons.get(14)){
            System.out.println("skeleton_small_top");
            controller.addTilesToPlayer(14 , 3);
        }
        else if(source == this.tiles_buttons.get(15)){
            System.out.println("sphinx");
            controller.addTilesToPlayer(15 ,4);
        }
    }

    /**
     * <b>Transformer:</b> Is used to disable a character button after it has been pressed
     * <b>PostCondition:</b> A character has been disabled
     * @param numb_of_character
     */
    public void disableCharacters(int numb_of_character){
        characters.get(numb_of_character-1).setEnabled(false);
    }

    /**
     *<b>Transformer:</b> Is used to enable a character button after it has been disabled
     *<b>PostCondition:</b> The button has been enabled again
     * @param numb_of_character
     */
    public void enableCharacters(int numb_of_character){
        characters.get(numb_of_character-1).setEnabled(true);
    }

    /**
     * <b>transformer:</b>change the label of a tile: sets its value to previousValue-1 or
     * if the value is 0, then it disables the button of the tile<br />
     * <p><b>PostCondition</b> the label of the tile has been changed</p>
     * @param tile
     */
    public void removeTiles_labels(int tile){
        int tiles = Integer.parseInt(tiles_labels.get(tile).getText());
        if(tiles == 1) {
            disableTiles(tile);
        }
        tiles_labels.get(tile).setText("" + (tiles - 1));
    }

    /**
     * <b>transformer:</b>change the label of a tile: sets its value to previousValue+1 <br />
     * <p><b>PostCondition</b> the label of the tile has been changed</p>
     * @param tile
     */
    public void addTiles_labels(int tile){
        int tiles = Integer.parseInt(tiles_labels.get(tile).getText());
        tiles_labels.get(tile).setText(""+(tiles+1));
    }

    /**
     * <b>transformer:</b>disables a button of a specific tile <br />
     * <p><b>PostCondition</b> the button of the tile has been disabled</p>
     * @param tile
     */
    public void disableTiles(int tile){
        tiles_buttons.get(tile).setEnabled(false);
    }

    /**
     * <b>transformer:</b>enables a button of a specific tile <br />
     * <p><b>PostCondition</b> the button of the tile has been enabled</p>
     * @param tile
     */
    public void enableTiles(int tile){
        tiles_buttons.get(tile).setEnabled(true);
    }

    /**
     * <b>Observer:</b> check if a button of a tile is disabled
     * @param tile
     * @return true or false if a button of a tile is disabled
     */
    public boolean isTileDisabled(int tile){
        return !tiles_buttons.get(tile).isEnabled();
    }


    /**
     * <b>transformer:</b> change the label of a tile of a player: sets its value to previousValue+1 <br />
     * <p><b>PostCondition</b> the label of the tile has been changed</p>
     * @param tile
     */
    public void addPlayers_tiles(int tile){
        int tiles = Integer.parseInt(players_labels.get(tile).getText());
        players_labels.get(tile).setText(""+(tiles+1));
    }

    /**
     * <b>transformer:</b> set players label tile to the correct value
     * <b>PostCondition:</b> the tile has been set
     * @param tile
     * @param number
     */
    public void setPlayers_tiles(int tile , int number){
        players_labels.get(tile).setText(""+number);
    }

    /**
     * <b>Transformer:</b> set the current player label
     * <b>PostCondition:</b> the currents player label has been set
     */
    public void setPlayerNumber(){
        playerNumber.setText(controller.getCurrentPlayer().getName());
    }

    /**
     * <b>Transformer: </b> creates a window that shows a message or error
     * <b>PostCondition: </b> the window has been activated
     * @param message
     */
    public void errorMessage(String message){
        final Runnable SOUND = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
        if (SOUND != null) SOUND.run();
        JOptionPane.showMessageDialog(null , message);
    }

    /**
     * <b>Transformer: </b> close a JFRAME window
     * <b>PostCondition: </b> the window has been closed
     */
    public void discardWindow(){
        win.dispatchEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSING));
    }

    public void music(){
        cldr = this.getClass().getClassLoader();
        musicCalc++;
        if(musicCalc == 1) {
            try {
                imageURL = cldr.getResource("backgroundSound.mp3");
                player.open(new URL("file:///" + "C:/CSD/hy252/phaseA_4351/src/backgroundSound.mp3"));
                player.play();
            } catch (BasicPlayerException | MalformedURLException e) {
               e.printStackTrace();
            }
        }
        else if (musicCalc %2 == 0){
            try {
                player.pause();
            }catch (BasicPlayerException e){
                e.printStackTrace();
            }

        }else {
            try {
                player.resume();
            }catch (BasicPlayerException e){
                e.printStackTrace();
            }
        }
    }

}
