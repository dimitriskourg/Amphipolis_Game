package Model.Characters;

/**
 * This enum contains the different types of where a character can take Tiles
 */
public enum AreaToTakeTiles {
    ALL_AREAS,
    ALL_AREAS_EXCEPT_LAST_CHOSEN_LOCATION,
    AREA_LAST_CHOSEN_LOCATION,
    ONE_AREA_OF_ALL_AREAS_EXCEPT_LAST_CHOSEN_LOCATION
}
