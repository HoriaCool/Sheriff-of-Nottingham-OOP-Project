// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

import java.util.List;

public final class Map {
    private static Map mInstance = null;

    private int mRows;
    private int mColumns;
    private List<String> mMap;

    private Map() {
        mRows = 0;
        mColumns = 0;
        mMap = null;
    }

    private Map(int rows, int columns, List<String> map) {
        mRows = rows;
        mColumns = columns;
        mMap = map;
    }

    /*
     * Returns the location from x, y coordinates.
     */
    public char getLocation(int x, int y) {
        try {
            return mInstance.mMap.get(x).charAt(y);
        } catch (Exception e) {
            // index out of bounds
            System.err.println(e.toString());
        }

        return 0;
    }

    /*
     * Builds the map if it is not already built.
     */
    public static void buildMap(int rows, int columns, List<String> map) {
        if (mInstance == null) {
            mInstance = new Map(rows, columns, map);
        }
    }

    /*
     * Returns map instance.
     */
    public static Map getInstance() {
        if (mInstance == null) {
            mInstance = new Map();

            throw new NullPointerException("Uninitialized map");
        }

        return mInstance;      
    }
}
