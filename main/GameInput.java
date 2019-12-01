// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

import java.util.List;

public class GameInput {
    private final int mRows;
    private final int mColumns;
    private final List<String> mMap;
    private final int mNumPlayers;
    private final List<String> mPlayersType;
    private final List<Integer> mXCoordinate;
    private final List<Integer> mYCoordinate;
    private final int mRounds;
    private final List<String> mMoves;

    public GameInput() {
        mRows = -1;
        mColumns = -1;
        mMap = null;
        mNumPlayers = -1;
        mPlayersType = null;
        mXCoordinate = null;
        mYCoordinate = null;
        mRounds = -1;
        mMoves = null;
    }

    public GameInput(final int rows, final int columns, final List<String> map,
        final int numPlayers, final List<String> playersType, final List<Integer> xCoordinate,
        final List<Integer> yCoordinate, final int rounds, final List<String> moves) {
        mRows = rows;
        mColumns = columns;
        mMap = map;
        mNumPlayers = numPlayers;
        mPlayersType = playersType;
        mXCoordinate = xCoordinate;
        mYCoordinate = yCoordinate;
        mRounds = rounds;
        mMoves = moves;
    }

    public final int getRows() {
        return mRows;
    }

    public final int getColumns() {
        return mColumns;
    }

    public final List<String> getMap() {
        return mMap;
    }

    public final int getNumPlayers() {
        return mNumPlayers;
    }

    public final List<String> getPlayersType() {
        return mPlayersType;
    }

    public final List<Integer> getXCoordinate() {
        return mXCoordinate;
    }

    public final List<Integer> getYCoordinate() {
        return mYCoordinate;
    }

    public final int getRounds() {
        return mRounds;
    }

    public final List<String> getMoves() {
        return mMoves;
    }

    /*
    public final boolean isValidInput() {
        boolean membersInstantiated = mAssetOrder != null && mPlayersOrder != null;
        boolean membersNotEmpty = mAssetOrder.size() > 0 && mPlayersOrder.size() > 0 && mRounds > 0;

        return membersInstantiated && membersNotEmpty;
    }
    */
}
