package Model.Characters;

import Model.Player;

/**
 * Contains the methods signatures needed for creating a Character
 * @author csd4351
 */
public abstract class  Character {

    private Player player;
    private boolean hasUsed = false;
    private CharacterColor color;
    private AreaToTakeTiles abilityArea;
    private int maxTilesToTake;
    private int id; // 1 = Assistant || 2 = Archaeologist || 3 = Digger || 4 = Professor

    /**
     * <b>Constructor:</b> Creates a new instance of Character
     * <b>PostCondition:</b> A new Character has been created
     * @param player
     * @param color
     * @param area
     * @param maxTilesToTake
     * @param id
     */
    public Character(Player player , CharacterColor color, AreaToTakeTiles area, int maxTilesToTake, int id){
        this.player = player;
        this.color = color;
        this.abilityArea = area;
        this.maxTilesToTake = maxTilesToTake;
        this.id = id;
    }

    /**
     * <b>Accessor:</b> Returns the player who uses this Character
     * <b>PostCondition:</b> The player has been returned
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * <b>Transformer:</b> Sets the player who uses this Character
     * <b>PostCondition:</b> The player has been set
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * <b>Transformer:</b> Sets the hasUsed true or false
     * <b>PostCondition:</b> The hasUsed has been set
     * @param hasUsed
     */
    public void setHasUsed(boolean hasUsed) {
        this.hasUsed = hasUsed;
    }

    /**
     * <b>Accessor:</b> Returns if the Character has been used
     * <b>PostCondition:</b> The hasUsed boolean has been returned
     * @return boolean hasUsed
     */
    public boolean hasUsed(){
        return hasUsed;
    }

    /**
     * <b>Transformer:</b> Sets the color of a Character
     * <b>PostCondition:</b> The color has been set
     * @param color
     */
    public void setColor(CharacterColor color) {
        this.color = color;
    }

    /**
     * <b>Accessor:</b> Returns the color of a Character
     * <b>PostCondition:</b> The color has been returned
     * @return the color of a character
     */
    public CharacterColor getColor() {
        return color;
    }

    /**
     * <b>Accessor:</b> Returns the Area of the Ability of the Character
     * <b>PostCondition:</b> The abilityArea has been returned
     * @return the abilityArea of a Character
     */
    public AreaToTakeTiles getAbilityArea() {
        return abilityArea;
    }

    /**
     * <b>Transformer:</b> Sets the Area of the ability of a Character
     * <b>PostCondition:</b> The abilityArea has been set
     * @param abilityArea
     */
    public void setAbilityArea(AreaToTakeTiles abilityArea) {
        this.abilityArea = abilityArea;
    }

    /**
     * <b>Accessor:</b> Returns the max tiles a Character can take
     * <b>PostCondition</b> the maxTilesToTake has been returned
     * @return maxTilesToTake of the Character
     */
    public int getMaxTilesToTake() {
        return maxTilesToTake;
    }

    /**
     * <b>Transformer:</b> Sets the max tiles a Character can take
     * <b>PostCondition</b> the max Tiles a character can take has been set
     * @param maxTilesToTake
     */
    public void setMaxTilesToTake(int maxTilesToTake) {
        this.maxTilesToTake = maxTilesToTake;
    }

    /**
     * <b>Accessor</b> Returns the id of the Player who owns this Character
     *<b>PostCondition</b> the id  of the Player who owns this Character has been returned
     * @return the id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * <b>Transformer</b> Sets the id of the Player who owns this Character
     * <b>PostCondition</b> the id  of the Player who owns this Character has been returned
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


}
