package Model.Characters;

import Model.Player;

/**
 * This class creates a new Digger Character
 */
public class Digger extends Character {

    /**
     * <b>Constructor:</b> Constructs a new instance of Digger using super to initialize some ints and player and color
     * enum
     * <b>PostCondition:</b> Constructs a new Digger
     * @param player
     * @param color
     */
    public Digger(Player player , CharacterColor color){
        super(player , color, AreaToTakeTiles.AREA_LAST_CHOSEN_LOCATION, 2 , 3 );
    }
}
