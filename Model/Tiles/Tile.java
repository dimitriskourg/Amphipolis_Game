package Model.Tiles;

/**
 * Contains the methods signatures needed for creating a Tile
 * @author csd4351
 */
public abstract class Tile {
    private int tile_id;

    /**
     * <b>Constructor:</b> Constructs a new instance of Tile
     * @param id
     */
    public Tile(int id){
        this.tile_id = id;
    }

    /**
     * <p><b>Accessor:</b> Returns the Tile's id</p>
     * <b>Postcondition:</b> The Tile's id has been returned
     * @return Tiles id
     */
    public int getTile_id() {
        return tile_id;
    }

    /**
     * <b>Transformer:</b> set the Tile's id
     * <b>Postcondition</b> the Tile's id has been set
     * @param tile_id
     */
    public void setTile_id(int tile_id) {
        this.tile_id = tile_id;
    }
}
