package webserver.common;

import lombok.Data;

@Data
public class Ship {

    private final String name;
    private final ShipType shipType;

    public Ship(String name, ShipType shipType) {
        this.name = name;
        this.shipType = shipType;
    }

    public enum ShipType {
        /** 軽巡洋艦 */
        LIGHT_CRUISER,
        /** 重巡洋艦 */
        HEAVY_CRUISER;
    }
}
