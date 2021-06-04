package Model.Tiles;

/**
 * This class creates a new Amphora tile
 * @author csd4351
 */
public class AmphoraTile extends FindingTile{

    private AmphoraColor  color;

    /**
     * <b>Constructor:</b> Constructs a new instance of AmphoraTile and via the
     * parent class FindingTile sets with the command super, id='id'
     * @param id
     * @param color
     */
    public AmphoraTile(int id , AmphoraColor color){
        super(id);
        this.color = color;
    }

    /**
     * <b>Accessor:</b> returns the AmphoraTile's color
     * <b>Postcondition:</b> Tile's color has been returned
     * @return the tile's color
     */
    public AmphoraColor getColor() {
        return color;
    }

    /**
     * <b>Transformer:</b> set the AmphoraTile's color
     * <b>Postcondition</b> the AmphoraTile's color has been set
     * @param color
     */
    public void setColor(AmphoraColor color) {
        this.color = color;
    }
}
