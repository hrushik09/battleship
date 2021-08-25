package battleship;

import java.util.ArrayList;

public class Ship {
    String[] shipNameArray;
    int[] shipSizeArray;
    boolean[] isSankArray;
    ArrayList<int[]>[] coordinatesArray;

    public Ship() {
        this.shipNameArray = new String[] {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        this.shipSizeArray = new int[] {5, 4, 3, 3, 2};
        this.isSankArray = new boolean[] {false, false, false, false, false};
        this.coordinatesArray = new ArrayList[] {new ArrayList<int[]>(), new ArrayList<int[]>(), new ArrayList<int[]>(), new ArrayList<int[]>(), new ArrayList<int[]>()};
    }
}