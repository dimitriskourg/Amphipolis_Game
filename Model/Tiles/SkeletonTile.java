package Model.Tiles;

/**
 * This class creates a new Skeleton Tile
 * @author csd4351
 */
public class SkeletonTile extends FindingTile{

    private String bodyPart; //upper or lower
    private String bodyType; //small or big

    /**
     * <b>Constructor:</b> Constructs a new instance of SkeletonTile and via the
     * parent FindingTile sets with the command super, id='id' and sets the private bodyPart and bodyType
     * @param id
     * @param bodyPart
     * @param bodyType
     */
    public SkeletonTile(int id , String bodyPart , String bodyType){
        super(id);
        this.bodyPart = bodyPart;
        this.bodyType = bodyType;
    }

    /**
     * <p><b>Accessor:</b> returns the BodyPart's type, upper or lower </p>
     * <b>Postcondition:</b> BodyPart's type has been returned
     * @return the BodyPart
     */
    public String getBodyPart() {
        return bodyPart;
    }

    /**
     * <b>Transformer:</b> set the SkeletonTile's bodyPart
     * <b>Postcondition</b> the SkeletonTile's bodyPart has been set
     * @param bodyPart
     */
    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    /**
     * <b>Accessor:</b> returns the BodyPart's type, upper or lower
     * <b>Postcondition:</b> BodyPart's type has been returned
     * @return the BodyPart
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * <b>Transformer:</b> set the SkeletonTile's bodyType
     * <b>Postcondition</b> the SkeletonTile's bodyType has been set
     * @param bodyType
     */
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
