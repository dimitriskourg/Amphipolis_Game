package Model;

import Model.Tiles.*;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the bag of tiles of the game
 * @author csd4351
 */
public class Bag{

    private ArrayList<Tile> bag;
    private Random rand;


    /**
     * <b>Constructor</b>
     * <b>PostCondition:</b> Creates a new Bag with an Arraylist of tiles.
     */
    public Bag(){
        bag = new ArrayList<Tile>();
    }

    /**
     * <b>Transformer:</b> fill the Arraylist with the Tiles at the start of the game.
     * <b>PostCondition:</b> the Arraylist has been filled
     */
    public void initTiles(){
        for(int i=0; i<9 ; i++){
            bag.add(new MosaicTile(i, MosaicColor.GREEN));
            bag.add(new MosaicTile(i+9, MosaicColor.RED));
            bag.add(new MosaicTile(i+18 , MosaicColor.YELLOW));
        }
        for(int i=27; i<39; i++){
            bag.add(new CaryatidTile(i));
            bag.add(new SphinxTile(i+12));
        }
        for(int i=51; i<75; i++){
            bag.add(new LandslideTile(i));
        }
        for(int i=75; i<80; i++){
            bag.add(new SkeletonTile(i , "upper" , "small"));
            bag.add(new SkeletonTile(i+5, "lower", "small"));
        }
        for(int i=85; i<95; i++){
            bag.add(new SkeletonTile(i, "upper" , "big"));
            bag.add(new SkeletonTile(i+10 , "lower" , "big"));
        }
        for(int i=105; i<110; i++){
            bag.add(new AmphoraTile(i , AmphoraColor.BLUE));
            bag.add(new AmphoraTile(i+5 , AmphoraColor.BROWN));
            bag.add(new AmphoraTile(i+10, AmphoraColor.RED));
            bag.add(new AmphoraTile(i+15, AmphoraColor.GREEN));
            bag.add(new AmphoraTile(i+20, AmphoraColor.YELLOW));
            bag.add(new AmphoraTile(i+25, AmphoraColor.PURPLE));
        }
    }

    /**
     * <b>Observer</b> : determines whether the Arraylist of Tiles is currently empty
     * <b>PostCondition:</b> Returns true id this list contains no elements
     * @return true if the Arraylist of Tiles is empty or false if is not
     */
    public boolean isEmpty(){
        return bag.isEmpty();
    }


    /**
     * <b>Transformer:</b> remove a Tile from the Arraylist
     * <b>Precondition:</b> index number should be a number between 0 and the size of the Arraylist bag -1
     *  <b>Postcondition:</b> the Tile has been removed from the Arraylist
     * @param index
     */
    public Tile removeSpecificTile(int index){
        Tile removedIndex = bag.get(index);
        bag.remove(index);
        return removedIndex;
    }

    /**
     * <b>Observer:</b> Returns the size of the Arraylist
     *  <b>Postcondition:</b> The size of the Arraylist has been returned
     * @return the size of an Arraylist
     */
    public int size(){
        return bag.size();
    }


    /**
     * <b>Accessor:</b> returns one random tile from the Arraylist of the bag
     *  <b>Postcondition:</b> a tile has been returned
     * @return an random Tile
     */
    public Tile getTile(){
       rand = new Random();
       int index = rand.nextInt(bag.size());
       Tile tile = bag.get(index);
       bag.remove(index);
       return tile;
    }


}
