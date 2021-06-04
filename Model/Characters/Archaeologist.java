package Model.Characters;

import Model.Player;

/**
 * This class creates a new Archaeologist Character
 */
public class Archaeologist extends Character {

    /**
     * <b>Constructor:</b> Constructs a new instance of Archaeologist using super to initialize some ints and player
     * enum
     * <b>PostCondition:</b> Constructs a new Archaeologist
     * @param player
     * @param color
     */
    public Archaeologist(Player player,  CharacterColor color){
        super(player, color, AreaToTakeTiles.ONE_AREA_OF_ALL_AREAS_EXCEPT_LAST_CHOSEN_LOCATION, 2, 2);
    }

}
