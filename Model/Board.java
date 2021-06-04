package Model;

import Model.Tiles.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates the main board of the game
 * @author csd4351
 */
public class Board {

    private ArrayList<MosaicTile> mosaicRegion;
    private ArrayList<AmphoraTile> amphoraRegion;
    private ArrayList<SkeletonTile> skeletonRegion;
    private ArrayList<StatueTile> statueRegion;
    private ArrayList<LandslideTile> entranceRegion;
    private int last_players_ID;
    private int current_players_ID;

    /**
     * <b>Constructor:</b> Constructs a new Board , creating Arraylist for all different regions
     * and initialize some ints
     * <b>PostCondition:</b> The main Board has been constructed and is ready to start the game
     */
    public Board(){
        this.mosaicRegion = new ArrayList<>();
        this.amphoraRegion = new ArrayList<>();
        this.skeletonRegion = new ArrayList<>();
        this.statueRegion = new ArrayList<>();
        this.entranceRegion = new ArrayList<>();
        this.current_players_ID = 0;
        this.last_players_ID = 0;
    }


    /**
     * <b>Transformer:</b> Adds the given tile to the correct Arraylist of Tiles
     * <b>PostCondition:</b> the given tile has been added to the correct Arraylist of Tiles
     * @param tile
     */
    public int addTileToRegion(Tile tile){
        if(tile instanceof MosaicTile){
            mosaicRegion.add((MosaicTile) tile);
            return 1;
        }else if(tile instanceof AmphoraTile){
            amphoraRegion.add((AmphoraTile) tile);
            return 2;
        }else if(tile instanceof SkeletonTile){
            skeletonRegion.add((SkeletonTile) tile);
            return 3;
        }else if(tile instanceof LandslideTile){
            entranceRegion.add((LandslideTile) tile);
            return 4;
        }else{
            statueRegion.add((StatueTile) tile);
            return 5;
        }
    }

    /**
     * <b>Transformer:</b> Removes a specific tile from a region, ex. green mosaic
     * <b>PreCondition:</b> choice should be between 1 to 15 , but not 7
     * <b>PostCondition:</b> the specific tile has been returned
     * @param choice
     */
    public Tile getTileFromRegion(int choice){
        Tile tile = new AmphoraTile(12 , AmphoraColor.YELLOW);
        if(choice ==0){
            for (AmphoraTile til : amphoraRegion){
                if(til.getColor() == AmphoraColor.BLUE) {
                    amphoraRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 1){
            for (AmphoraTile til : amphoraRegion){
                if(til.getColor() == AmphoraColor.BROWN) {
                    amphoraRegion.remove(til);
                   return til;
                }
            }
        }
        else if(choice == 2){
            for (AmphoraTile til : amphoraRegion){
                if(til.getColor() == AmphoraColor.GREEN) {
                    amphoraRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 3){
            for (AmphoraTile til : amphoraRegion){
                if(til.getColor() == AmphoraColor.PURPLE) {
                    amphoraRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 4){
            for (AmphoraTile til : amphoraRegion){
                if(til.getColor() == AmphoraColor.RED) {
                    amphoraRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 5){
            for (AmphoraTile til : amphoraRegion){
                if(til.getColor() == AmphoraColor.YELLOW) {
                    amphoraRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 6){
            for( StatueTile til : statueRegion ){
                if(til instanceof CaryatidTile){
                    statueRegion.remove(til);
                    return til;
                }
            }
        }
        else if (choice == 8){
            for(MosaicTile til : mosaicRegion){
                if (til.getColour() == MosaicColor.GREEN){
                    mosaicRegion.remove(til);
                    return til;
                }
            }
        }
        else if (choice == 9){
            for(MosaicTile til : mosaicRegion){
                if (til.getColour() == MosaicColor.RED){
                    mosaicRegion.remove(til);
                    return til;
                }
            }
        }
        else if (choice == 10){
            for(MosaicTile til : mosaicRegion){
                if (til.getColour() == MosaicColor.YELLOW){
                    mosaicRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 11){
            for(SkeletonTile til : skeletonRegion){
                if(til.getBodyType().equals("big") && til.getBodyPart().equals("lower")){
                    skeletonRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 12){
            for(SkeletonTile til : skeletonRegion){
                if(til.getBodyType().equals("big") && til.getBodyPart().equals("upper")){
                    skeletonRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 13){
            for(SkeletonTile til : skeletonRegion){
                if(til.getBodyType().equals("small") && til.getBodyPart().equals("lower")){
                    skeletonRegion.remove(til);
                    return til;
                }
            }
        }
        else if(choice == 14){
            for(SkeletonTile til : skeletonRegion){
                if(til.getBodyType().equals("small") && til.getBodyPart().equals("upper")){
                    skeletonRegion.remove(til);
                    return til;
                }
            }
        }
        else {
            for (StatueTile til : statueRegion) {
                if (til instanceof SphinxTile) {
                    statueRegion.remove(til);
                    return til;
                }
            }
        }
        return tile;
    }

    /**
     * <b>Observer:</b> checks and return 0 if a given region is empty
     * or the size of the region if it isn't.
     * <b>Precondition:</b> region number should be between 1-4
     * <b>PostCondition:</b> returns 0 if if a given region is empty
     * or the size of the region if it isn't.
     * @param region
     * @return the size of a region
     */
    public int isRegionEmpty(int region){
        switch (region){
            case 1:
                return mosaicRegion.size();
            case 2:
                return amphoraRegion.size();
            case 3:
                return skeletonRegion.size();
            default:
                return statueRegion.size();
        }
    }


    /**
     * <b>Observer:</b> Checks and return true if the game has finished or false if it hasn't
     * <b>PostCondition:</b> returns true if the game has finished or false if it hasn't
     * @return true if the game has finished or false if it hasn't
     */
    public boolean hasGameFinished(){
        if (entranceRegion.size() == 16) return true;
        else return false;
    }

}
