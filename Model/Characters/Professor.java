package Model.Characters;

import Model.Player;

/**
 * This class creates a new Digger Character
 */
public class Professor extends Character{

    /**
     * <b>Constructor:</b> Constructs a new instance of Professor using super to initialize some ints and player and color
     * enum
     * <b>PostCondition:</b> Constructs a new Professor
     * @param player
     * @param color
     */
    public Professor(Player player, CharacterColor color){
        super(player, color, AreaToTakeTiles.ALL_AREAS_EXCEPT_LAST_CHOSEN_LOCATION, 1, 4);
    }
}
