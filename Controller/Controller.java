package Controller;

import Model.Board;
import Model.Characters.*;
import Model.Characters.Character;
import Model.Player;
import Model.Bag;
import Model.Tiles.*;
import Model.Tiles.MosaicColor;
import Model.Tiles.MosaicTile;
import Model.Tiles.Tile;
import View.View;
import java.util.ArrayList;

/**
 * Controller is the master of the game and controls all
 * of the operations executed
 * @author csd4351
 */
public class Controller {
    private Player P1, P2, P3, P4;
    private ArrayList<Player> players;
    private int currentPlayerId; //einai ola +1 ap ton pinaka
    private boolean currentPlayerHasDrawnTiles;
    private int currentPlayerChosenArea; //areas: 1 = MosaicRegion || 2 = AmphoraRegion || 3 = SkeletonRegion || 4 = StatueRegion
    private int currentPlayerCollectedTiles;
    private Board board;
    private Bag bag;
    private View view;
    private int currentPlayerMaxTilesFromRegion[]; //how many tiles can a player take from a region because he has been used a character
    private int currentPlayerUsedCharacter; //1 = Assistant || 2 = Archaeologist || 3 = Digger || 4 = Professor

    /**
     * <b>Constructor</b> Creates a new Controller
     * <b>PostCondition:</b> a new Controller has been created
     */
    public Controller(){

        P1 = new Player("Player 1" , CharacterColor.BLACK);
        P2 = new Player("Player 2" , CharacterColor.BLUE);
        P3 = new Player("Player 3" , CharacterColor.RED);
        P4 = new Player("Player 4" , CharacterColor.YELLOW);
        players = new ArrayList<>();
        players.add(P1);
        players.add(P2);
        players.add(P3);
        players.add(P4);

        bag = new Bag();
        board = new Board();
        currentPlayerId = 1;
        currentPlayerHasDrawnTiles =false;
        currentPlayerChosenArea = 0;
        currentPlayerCollectedTiles = 0;
        currentPlayerMaxTilesFromRegion = new int[4];
        currentPlayerUsedCharacter = 0;
        view = new View(this);

    }

    /**
     * <b>Transformer:</b> set the currentPlayerId
     * <b>PostConditions</b> sets the currentPlayerId
     * @param id
     */
    public void setCurrentPlayerId(int id){
        this.currentPlayerId = id;
    }

    /**
     * <b>Accessor:</b> Returns the currentPlayerId
     * <b>PostCondition:</b> the currentPlayerId has been returned
     * @return the currentPlayerId
     */
    public int getCurrentPlayerId(){
        return this.currentPlayerId;
    }


    /**
     * <b>Observer:</b> returns true if the game has finished or false if it hasn't
     * <b>PostConditions:</b> returns true if the game has finished or false if it hasn't.
     * @return true or false if the game has finished
     */
    public boolean isGameFinished(){
        return board.hasGameFinished();
    }

    /**
     * <b>Transformer:</b> set which one should play next or if the next player should not play yet
     * <b>PostConditions</b> sets which Player should play next
     */
    public void nextTurn(){
        if(currentPlayerHasDrawnTiles) {
            if (getCurrentPlayerId() == 4) {
                setCurrentPlayerId(1);
            } else {
                setCurrentPlayerId(this.getCurrentPlayerId() + 1);
            }
            this.setCurrentPlayersTilesInView();
            currentPlayerHasDrawnTiles = false;
            currentPlayerChosenArea = 0;
            currentPlayerCollectedTiles = 0;
            setCurrentPlayersCharactersInView();
            currentPlayerUsedCharacter = 0;
            for(int i=0; i<4; i++){
                currentPlayerMaxTilesFromRegion[i]=0;
            }
        }else {
            view.errorMessage("Please press draw tiles button");
        }
    }

    /**
     * <b>Transformer:</b> initialize the game
     * <b>PostConditions</b> the game has been initialized
     */
    public void initGame(){
        bag.initTiles();
        board.addTileToRegion(bag.removeSpecificTile(0));
        view.addTiles_labels(8);
        view.enableTiles(8);
        board.addTileToRegion(bag.removeSpecificTile(31));
        view.addTiles_labels(15);
        view.enableTiles(15);
        board.addTileToRegion(bag.removeSpecificTile(80));
        view.addTiles_labels(13);
        view.enableTiles(13);
        board.addTileToRegion(bag.removeSpecificTile(114));
        view.addTiles_labels(2);
        view.enableTiles(2);
    }

    /**
     * <b>Accessor:</b> Returns the current players id
     * <b>PostCondition:</b> the current players id has been returned
     * @return the current player id
     */
    public Player getCurrentPlayer(){
        return players.get(currentPlayerId-1);
    }

    /**
     * <b>Transformer: </b> Check if a player should have drawn some tiles to the board and if he should,
     * it draws 4 tile and place them to the board or if he shouldn't, a message shows
     * <b>PostCondition:</b> 4 tiles has been drawn and added to the board or a error message shown
     */
    public void addTilesToBoard(){
        if(currentPlayerHasDrawnTiles){
            view.errorMessage("You have already drawn tiles");
            return;
        }
        int i=0;
        currentPlayerHasDrawnTiles = true;
        while(i<4 && !isGameFinished()){
            int region;
            Tile tile = bag.getTile();
            region = board.addTileToRegion(tile);
            if(region == 1){ //check if tile is a mosaic
                if ( ((MosaicTile)tile).getColour() == MosaicColor.GREEN){
                    view.addTiles_labels(8);
                    if(view.isTileDisabled(8)){
                        view.enableTiles(8);
                    }
                }else if( ((MosaicTile)tile).getColour() == MosaicColor.RED ){
                    view.addTiles_labels(9);
                    if(view.isTileDisabled(9)){
                        view.enableTiles(9);
                    }
                }else{
                    view.addTiles_labels(10);
                    if(view.isTileDisabled(10)){
                        view.enableTiles(10);
                    }
                }
            }else if(region == 2){ //check if region is a amphora
                if( ((AmphoraTile)tile).getColor() == AmphoraColor.BLUE ){
                    view.addTiles_labels(0);
                    if(view.isTileDisabled(0)){
                        view.enableTiles(0);
                    }
                }else if( ((AmphoraTile)tile).getColor() == AmphoraColor.BROWN ){
                    view.addTiles_labels(1);
                    if(view.isTileDisabled(1)){
                        view.enableTiles(1);
                    }
                }else if( ((AmphoraTile)tile).getColor() == AmphoraColor.RED ){
                    view.addTiles_labels(4);
                    if(view.isTileDisabled(4)){
                        view.enableTiles(4);
                    }
                }else if( ((AmphoraTile)tile).getColor() == AmphoraColor.GREEN){
                    view.addTiles_labels(2);
                    if(view.isTileDisabled(2)){
                        view.enableTiles(2);
                    }
                }else if( ((AmphoraTile)tile).getColor() == AmphoraColor.YELLOW ) {
                    view.addTiles_labels(5);
                    if(view.isTileDisabled(5)){
                        view.enableTiles(5);
                    }
                }else if( ((AmphoraTile)tile).getColor() == AmphoraColor.PURPLE ){
                    view.addTiles_labels(3);
                    if(view.isTileDisabled(3)){
                        view.enableTiles(3);
                    }
                }
            }else if(region == 3){
                if( ((SkeletonTile)tile).getBodyPart().equals("upper") && ((SkeletonTile)tile).getBodyType().equals("big") ){
                    view.addTiles_labels(12);
                    if(view.isTileDisabled(12)){
                        view.enableTiles(12);
                    }
                }else if( ((SkeletonTile)tile).getBodyPart().equals("upper") && ((SkeletonTile)tile).getBodyType().equals("small") ){
                    view.addTiles_labels(14);
                    if(view.isTileDisabled(14)){
                        view.enableTiles(14);
                    }
                }else if( ((SkeletonTile)tile).getBodyPart().equals("lower") && ((SkeletonTile)tile).getBodyType().equals("big") ){
                    view.addTiles_labels(11);
                    if(view.isTileDisabled(11)){
                        view.enableTiles(11);
                    }
                }else{
                    view.addTiles_labels(13);
                    if(view.isTileDisabled(13)){
                        view.enableTiles(13);
                    }
                }
            }else if(region == 4){
                view.addTiles_labels(7);
            }
            else{
                if( tile instanceof CaryatidTile ){
                    view.addTiles_labels(6);
                    if(view.isTileDisabled(6)){
                        view.enableTiles(6);
                    }
                }else {
                    view.addTiles_labels(15);
                    if(view.isTileDisabled(15)){
                        view.enableTiles(15);
                    }
                }
            }
            i++;
        }
       if(isGameFinished()) finishGame();
    }

    /**
     * <b>Transformer: </b> check if a player should have drawn some tiles from a region and if he can he draw a specific
     * tile and add it to his region
     * <b>PostCondition: </b> a tile has been added to currents player region or an error message shown
     * @param button
     * @param area
     */
    public void addTilesToPlayer(int button , int area){
        //sto prwto if mpainei an exei epileksei enan xarakthra alliws mpainei sto deytero
        if(currentPlayerUsedCharacter != 0){

            if(currentPlayerUsedCharacter == 1){
                if(currentPlayerMaxTilesFromRegion[area-1] == 1){
                    players.get(currentPlayerId - 1).addTile(board.getTileFromRegion(button));
                    view.removeTiles_labels(button);
                    view.addPlayers_tiles(button);
                    currentPlayerMaxTilesFromRegion[area-1]=0;
                }else{
                    view.errorMessage("You can't take a tile from this region");
                }
            }
            else if(currentPlayerUsedCharacter == 2){
                if(currentPlayerMaxTilesFromRegion[area-1] == 2){
                    players.get(currentPlayerId - 1).addTile(board.getTileFromRegion(button));
                    view.removeTiles_labels(button);
                    view.addPlayers_tiles(button);
                    currentPlayerMaxTilesFromRegion[area-1]=1;
                    for(int i=0; i<4; i++){
                        if(i+1 != area){
                            currentPlayerMaxTilesFromRegion[i] = 0;
                        }
                    }
                }else if(currentPlayerMaxTilesFromRegion[area-1] == 1){
                    players.get(currentPlayerId - 1).addTile(board.getTileFromRegion(button));
                    view.removeTiles_labels(button);
                    view.addPlayers_tiles(button);
                    currentPlayerMaxTilesFromRegion[area-1]--;
                }else{
                    view.errorMessage("You can't take tiles from this region");
                }

            }
            else if(currentPlayerUsedCharacter == 3){
                if(currentPlayerMaxTilesFromRegion[area-1] > 0){
                    players.get(currentPlayerId - 1).addTile(board.getTileFromRegion(button));
                    view.removeTiles_labels(button);
                    view.addPlayers_tiles(button);
                    currentPlayerMaxTilesFromRegion[area-1]--;
                }else{
                    view.errorMessage("You can't take a tile from this region");
                }
            }
            else{
                if(currentPlayerMaxTilesFromRegion[area-1] > 0){
                    players.get(currentPlayerId - 1).addTile(board.getTileFromRegion(button));
                    view.removeTiles_labels(button);
                    view.addPlayers_tiles(button);
                    currentPlayerMaxTilesFromRegion[area-1]=0;
                }else{
                    view.errorMessage("You can't take a tile from this region");
                }
            }
        }

        else if( currentPlayerHasDrawnTiles && (currentPlayerChosenArea == 0 || (currentPlayerCollectedTiles<2 && currentPlayerChosenArea == area)) ) {
            players.get(currentPlayerId - 1).addTile(board.getTileFromRegion(button));
            view.removeTiles_labels(button);
            view.addPlayers_tiles(button);
            currentPlayerChosenArea = area;
            currentPlayerCollectedTiles++;
        }else if(!currentPlayerHasDrawnTiles){
            view.errorMessage("You have to press draw tiles first");
        }else if(currentPlayerChosenArea != area){
            view.errorMessage("You can't Choose tile from another Area");
        }else if(currentPlayerCollectedTiles == 2){
            view.errorMessage("You can't draw more than 2 tiles");
        }

    }

    /**
     * <b>Transformer: </b> Every round sets current players' tiles labels to the correct value in view
     * <b>PostCondition</b> Current players' tiles labels has been set to the correct value in view
     */
    public void setCurrentPlayersTilesInView(){
        players.get(currentPlayerId-1).setCurrentTiles();
        for(int i=0; i<7; i++){
            view.setPlayers_tiles(i , players.get(currentPlayerId-1).getPlayersAmountOfATile(i));
        }
        for(int i=8; i<16; i++){
            view.setPlayers_tiles(i , players.get(currentPlayerId-1).getPlayersAmountOfATile(i));
        }
    }

    /**
     * <b>Transformer: </b> Every round sets current players' characters. It enables the characters that has not played
     * yet and disabled the characters that has been played
     * <b>PostCondition:</b> Current players characters has been set
     */
    public void setCurrentPlayersCharactersInView(){
        for(int i=0; i<4; i++){
            Character chara = players.get(currentPlayerId-1).getCharacter(i);
            if(chara.hasUsed()){
                view.disableCharacters(i+1);
            }else{
                view.enableCharacters(i+1);
            }
        }
    }

    /**
     * <b>Transformer: </b> Check if the current player should use a specific character and if he can,
     * it sets some values and disables the character
     * @param id
     */
    public void useCharacter(int id) {
        if (currentPlayerCollectedTiles == 0) {
            view.errorMessage("You have to take at least one tile from the board!");
        } else {
            if (players.get(currentPlayerId - 1).getCharacter(id - 1) instanceof Archaeologist) {
                players.get(currentPlayerId - 1).getCharacter(id - 1).setHasUsed(true);
                currentPlayerUsedCharacter = 2;
                for (int i = 0; i < 4; i++) {
                    if (i + 1 != currentPlayerChosenArea) {
                        currentPlayerMaxTilesFromRegion[i] = 2;
                    }
                }
            } else if (players.get(currentPlayerId - 1).getCharacter(id - 1) instanceof Assistant) {
                players.get(currentPlayerId - 1).getCharacter(id - 1).setHasUsed(true);
                currentPlayerUsedCharacter = 1;
                for (int i = 0; i < 4; i++) {
                    currentPlayerMaxTilesFromRegion[i] = 1;
                }
            } else if (players.get(currentPlayerId - 1).getCharacter(id - 1) instanceof Digger) {
                if (board.isRegionEmpty(currentPlayerChosenArea) == 0) {
                    view.errorMessage("You can't use this character at this moment");
                    return;
                } else {
                    System.out.println("MPHKE");
                    System.out.println(board.isRegionEmpty(currentPlayerChosenArea));
                    players.get(currentPlayerId - 1).getCharacter(id - 1).setHasUsed(true);
                    currentPlayerUsedCharacter = 3;
                    currentPlayerMaxTilesFromRegion[currentPlayerChosenArea - 1] = 2;
                }
            } else {
                players.get(currentPlayerId - 1).getCharacter(id - 1).setHasUsed(true);
                currentPlayerUsedCharacter = 4;
                for (int i = 0; i < 4; i++) {
                    if (i + 1 != currentPlayerChosenArea) {
                        currentPlayerMaxTilesFromRegion[i] = 1;
                    }
                }
            }
            for (int i = 1; i < 5; i++) {
                view.disableCharacters(i);
            }
        }
    }

    /**
     * <b>Transformer:</b> Calculate the points of every player and shows a window with the winner
     * <b>PostCondition:</b> The winner has been announced
     */
    public void finishGame(){
        for(Player player : players){
            player.calculatePoints(players);
        }
        String winner ;
        int maxP = Math.max(players.get(3).getPoints() , Math.max( players.get(2).getPoints() , Math.max(players.get(0).getPoints() , players.get(1).getPoints())));
        if(maxP == players.get(0).getPoints()) winner = "Player 1";
        else if(maxP == players.get(1).getPoints()) winner = "Player 2";
        else if (maxP == players.get(2).getPoints()) winner = "Player 3";
        else winner = "Player 4";
        view.errorMessage("Winner is: " + winner);
        view.discardWindow();
    }

    /**
     * <b>Transformer: </b> The main function which starts the game
     * @param Args
     */
    public static void main (String[] Args){
        Controller mainController = new Controller();
        mainController.initGame();
    }

}
