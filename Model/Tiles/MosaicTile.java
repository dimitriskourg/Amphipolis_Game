package Model.Tiles;

/**
 * This class creates a new Mosaic Tile
 * @author csd4351
 */
public class MosaicTile extends FindingTile {

    private MosaicColor color;

    /**
     * <b>Constructor:</b> Constructs a new instance of MosaicTile and via the
     * parent FindingTile sets with the command super, id='id' and sets the private
     * color = color
     * @param id
     * @param color
     */
    public MosaicTile(int id ,MosaicColor color){
        super(id);
        this.color = color;
    }

    /**
     * <b>Accessor:</b> returns the MosaicTile's color
     * <b>Postcondition:</b> Tile's color has been returned
     * @return the tile's color
     */
    public MosaicColor getColour() {
        return color;
    }

    /**
     * <b>Transformer:</b> set the AmphoraTile's color
     * <b>Postcondition</b> the AmphoraTile's color has been set
     * @param colour
     */
    public void setColour(MosaicColor colour) {
        this.color = colour;
    }
}
