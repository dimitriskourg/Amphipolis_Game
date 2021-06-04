package Model.Characters;

import Model.Player;

/**
 * This class creates a new Assistant Character
 */
public class Assistant extends Character {

    /**
     * <b>Constructor:</b> Constructs a new instance of Assistant using super to initialize some ints and player and color
     * enum
     * <b>PostCondition:</b> Constructs a new Assistant
     * @param player
     * @param color
     */
    public Assistant(Player player, CharacterColor color){
        super(player, color, AreaToTakeTiles.ALL_AREAS , 1 , 1);
    }
}
