package Model;

import Model.Characters.*;
import Model.Characters.Character;
import Model.Tiles.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class created a Player of the game
 */
public class Player {

    private String name;
    private ArrayList<Character> characters;
    private ArrayList<Tile> tiles;
    private boolean hasPlayed = false;
    private int points;
    private int[] currentTiles;

    /**
     * <b>Constructor:</b> constructs a player with a given name and adds the characters to the player
     * <b>PostCondition:</b> the player has been constructed
     * @param color
     * @param name
     */
    public Player(String name , CharacterColor color){
        points = 0;
        characters = new ArrayList<>();
        tiles = new ArrayList<>();
        this.name = name;
        characters.add(new Assistant(this , color ) );
        characters.add(new Archaeologist(this , color));
        characters.add(new Digger(this, color));
        characters.add(new Professor(this, color));
        currentTiles = new int[16];
    }

    /**
     * <b>Accessor:</b> Returns the name of the Player
     * <b>PostCondition:</b> the name of the player has been returned
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * <b>Observer:</b> Checks and return true if the player has played or false elsewhere
     * <b>PostCondition:</b> returns true if the player has played or false elsewhere
     * @return true or false if the player has played or not
     */
    public boolean HasPlayed() {
        return hasPlayed;
    }

    /**
     * <b>Transformer</b> Sets the boolean hasPlayed with the given boolean variable
     * <b>PostCondition:</b> the hasPlayed variable has been set
     * @param hasPlayed
     */
    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    /**
     * <b>Accessor:</b> Returns the points of the Player
     * <b>PostCondition:</b> the points of the player has been returned
     * @return the points of the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * <b>Transformer:</b> Sets the points of the Player
     * <b>PostCondition:</b> the points of the player has been set
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * <b>Transformer:</b> add a tile to the Arraylist of the tiles of the player
     * <b>PostCondition:</b> the tile has been added to the Arraylist
     * @param tile
     */
    public void addTile(Tile tile){
        tiles.add(tile);
    }


    /**
     * <B>Transformer:</B> Calculate the points of the player at the end of the game.
     * <b>PreCondition:</b> an Arraylist of 4 players should be provided
     * <b>PostCondition:</b> the points of the player has been calculated
     * @param players
     */
    public void calculatePoints(ArrayList<Player> players){
        int[] regionPoints = new int[4];
        /*
        the order is: 0) mosaicPoints
                      1) amphoraPoints
                      2) skeletonPoints
                      3) statuePoints
         */

        int[] mosaicTiles = new int[3];
        int[] skeletonTiles = new int[4];
        int[] amphoraTiles = new int[6];
        /*
        the order is :
        amphora: 0) amphora_blue
                 1) amphora_brown
                 2) amphora_green
                 3) amphora_purple
                 4) amphora_red
                 5) amphora_yellow

        mosaic: 0) mosaic_green
                1) mosaic_red
                2) mosaic_yellow

       skeleton: 0) skeleton_big_lower
                 1) skeleton_big_upper
                 2) skeleton_small_lower
                 3) skeleton_small_upper
         */

        //here I count all amphora tiles , all mosaicTiles , all SkeletonTiles
        for(Tile til : tiles){
            if(til instanceof AmphoraTile){
                if(((AmphoraTile) til).getColor() == AmphoraColor.BLUE) amphoraTiles[0]++;
                else if(((AmphoraTile) til).getColor() == AmphoraColor.BROWN ) amphoraTiles[1]++;
                else if (((AmphoraTile) til).getColor() == AmphoraColor.GREEN) amphoraTiles[2]++;
                else if(((AmphoraTile) til).getColor() == AmphoraColor.PURPLE) amphoraTiles[3]++;
                else if(((AmphoraTile) til).getColor() == AmphoraColor.RED) amphoraTiles[4]++;
                else amphoraTiles[5]++;
            }
            else if(til instanceof MosaicTile){
                if(((MosaicTile) til).getColour() == MosaicColor.GREEN) mosaicTiles[0]++;
                else if(((MosaicTile) til).getColour() == MosaicColor.RED) mosaicTiles[1]++;
                else mosaicTiles[2]++;
            }
            else if(til instanceof SkeletonTile) {
                if (((SkeletonTile) til).getBodyType().equals("big") && ((SkeletonTile) til).getBodyPart().equals("lower")) skeletonTiles[0]++;
                else if(((SkeletonTile) til).getBodyType().equals("big") && ((SkeletonTile) til).getBodyPart().equals("upper") ) skeletonTiles[1]++;
                else if(((SkeletonTile) til).getBodyType().equals("small") && ((SkeletonTile) til).getBodyPart().equals("lower")) skeletonTiles[2]++;
                else skeletonTiles[3]++;
            }
        }

        //calculate amphora points
        Arrays.sort(amphoraTiles);

        int amphoraMin = amphoraTiles[0];
        regionPoints[1] = 6*amphoraMin;
        for (int i = 0; i < amphoraTiles.length; i++) {
            amphoraTiles[i] -= amphoraMin;
        }
        amphoraMin = amphoraTiles[1];
        regionPoints[1] = regionPoints[1] + amphoraMin*4;
        for (int i = 1; i < amphoraTiles.length; i++) {
            amphoraTiles[i] -= amphoraMin;
        }
        amphoraMin = amphoraTiles[2];
        regionPoints[1] = regionPoints[1] + amphoraMin*2;
        for (int i = 2; i < amphoraTiles.length; i++) {
            amphoraTiles[i] -= amphoraMin;
        }
        amphoraMin = amphoraTiles[3];
        regionPoints[1] = regionPoints[1] + amphoraMin;
        for (int i = 3; i <amphoraTiles.length ; i++) {
            amphoraTiles[i] -= amphoraMin;
        }

        //calculate mosaic points
        regionPoints[0] = (mosaicTiles[0] / 4 + mosaicTiles[1] / 4 + mosaicTiles[2] / 4)*4 + ((mosaicTiles[0]%4 + mosaicTiles[1]%4 + mosaicTiles[2]%4)/4) * 2;

        //calculate skeleton points
        int skeletonBig = Math.min(skeletonTiles[0] , skeletonTiles[1]);
        int skeletonSmall = Math.min(skeletonTiles[2] , skeletonTiles[3]);

        int skeletonPair = skeletonBig / 2;
        int skeletonOther = skeletonBig % 2;

        int skeletonFamily = Math.min(skeletonPair , skeletonSmall);

        if(skeletonFamily == 0){
            regionPoints[2] = skeletonBig + skeletonSmall;
        }else{
            regionPoints[2] = skeletonFamily*6;
            if(skeletonSmall < skeletonPair){
                regionPoints[2] += (skeletonPair - skeletonSmall) * 2 + skeletonOther;
            }else{
                regionPoints[2] += skeletonSmall - skeletonPair + skeletonOther;
            }
        }

        //calculate statues points
        ArrayList<CaryatidTile> car0 = players.get(0).getCaryatids();
        ArrayList<SphinxTile> sph0 = players.get(0).getSphinxs();
        ArrayList<CaryatidTile> car1 = players.get(1).getCaryatids();
        ArrayList<SphinxTile> sph1 = players.get(1).getSphinxs();
        ArrayList<CaryatidTile> car2 = players.get(2).getCaryatids();
        ArrayList<SphinxTile> sph2 = players.get(2).getSphinxs();
        ArrayList<CaryatidTile> car3 = players.get(3).getCaryatids();
        ArrayList<SphinxTile> sph3 = players.get(3).getSphinxs();

        int statuePoints = 0;
        int maxCaryatids , maxSphinx, minCaryatids , minSphinx;

        maxCaryatids = Math.max(car0.size() , car1.size());
        maxCaryatids = Math.max(maxCaryatids , car2.size());
        maxCaryatids = Math.max(maxCaryatids, car3.size());
        maxSphinx = Math.max(sph0.size(), sph1.size());
        maxSphinx = Math.max(maxSphinx , sph2.size());
        maxSphinx = Math.max(maxSphinx , sph3.size());

        minCaryatids = Math.min(car0.size() , car1.size());
        minCaryatids = Math.min(minCaryatids , car2.size());
        minCaryatids = Math.min(minCaryatids, car3.size());
        minSphinx = Math.min(sph0.size(), sph1.size());
        minSphinx = Math.min(minSphinx , sph2.size());
        minSphinx = Math.min(minSphinx , sph3.size());

        if(this.getCaryatids().size() == maxCaryatids){
            statuePoints += 6;
        }else if(this.getCaryatids().size() != minCaryatids){
            statuePoints += 3;
        }

        if(this.getSphinxs().size() == maxSphinx){
            statuePoints += 6;
        }else if(this.getSphinxs().size() != minSphinx){
            statuePoints += 3;
        }

        this.setPoints(statuePoints + regionPoints[0] + regionPoints[1] + regionPoints[2]);
    }

    /**
     * <B>Transformer:</B> Set the number of the current tiles of a player
     * <b>PostCondition:</b> the points of the player has been calculated
     */
    public void setCurrentTiles(){
        for (int i = 0; i < 16; i++) {
            currentTiles[i] = 0;
        }
        for(Tile til : tiles){
            if(til instanceof MosaicTile){
                if(((MosaicTile) til).getColour() == MosaicColor.GREEN){
                    currentTiles[8]++;
                } else if(((MosaicTile) til).getColour() == MosaicColor.RED){
                    currentTiles[9]++;
                }else if(((MosaicTile) til).getColour() == MosaicColor.YELLOW){
                    currentTiles[10]++;
                }
            }
            else if(til instanceof SkeletonTile){
                if(((SkeletonTile) til).getBodyPart().equals("lower") && ((SkeletonTile) til).getBodyType().equals("big")){
                    currentTiles[11]++;
                }else if(((SkeletonTile) til).getBodyPart().equals("upper") && ((SkeletonTile) til).getBodyType().equals("big")){
                    currentTiles[12]++;
                }else if(((SkeletonTile) til).getBodyPart().equals("lower") && ((SkeletonTile) til).getBodyType().equals("small")){
                    currentTiles[13]++;
                }else{
                    currentTiles[14]++;
                }
            }
            else if (til instanceof AmphoraTile){
                if(((AmphoraTile) til).getColor() == AmphoraColor.BLUE){
                    currentTiles[0]++;
                }else if (((AmphoraTile) til).getColor() == AmphoraColor.BROWN){
                    currentTiles[1]++;
                }else if (((AmphoraTile) til).getColor() == AmphoraColor.GREEN){
                    currentTiles[2]++;
                }else if (((AmphoraTile) til).getColor() == AmphoraColor.PURPLE){
                    currentTiles[3]++;
                }else if (((AmphoraTile) til).getColor() == AmphoraColor.RED){
                    currentTiles[4]++;
                }else if (((AmphoraTile) til).getColor() == AmphoraColor.YELLOW){
                    currentTiles[5]++;
                }
            }
            else if (til instanceof StatueTile){
                if (til instanceof CaryatidTile){
                    currentTiles[6]++;
                }else{
                    currentTiles[15]++;
                }
            }
        }
    }

    /**
     * <b>Accessor:</b> Returns the amount of a specific region of tiles
     * <b>PostCondition:</b> the amount of tiles has been returned
     * @return the amount of tiles
     */
    public int getPlayersAmountOfATile(int numb){
        return currentTiles[numb];
    }

    /**
     * <b>Accessor:</b> Returns a character of the Arraylist of characters of the player
     * <b>PostCondition:</b> A character of the Arraylist of characters of the player has been returned
     * @return a character
     */
    public Character getCharacter(int id){
        return characters.get(id);
    }

    /**
     * <b>Accessor:</b> Returns all the Caryatids tiles of the player
     * <b>PostCondition:</b> The Caryatids tiles of the player has been returned
     * @return Caryatid tiles
     */
    public ArrayList<CaryatidTile> getCaryatids(){
        ArrayList<CaryatidTile> caryatids = new ArrayList<>();

        for(Tile til : tiles){
            if(til instanceof CaryatidTile){
                caryatids.add( (CaryatidTile) til);
            }
        }
        return caryatids;
    }

    /**
     * <b>Accessor:</b> Returns all the Sphinxs tiles of the player
     * <b>PostCondition:</b> The Sphinxs tiles of the player has been returned
     * @return Sphinxs tiles
     */
    public ArrayList<SphinxTile> getSphinxs(){
        ArrayList<SphinxTile> sphinxs = new ArrayList<>();

        for(Tile til : tiles){
            if(til instanceof SphinxTile){
                sphinxs.add( (SphinxTile) til);
            }
        }
        return sphinxs;
    }

}
