// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

import java.util.ArrayList;
import java.util.List;

import gameplay.PlayerFactory;
import gameplay.Player;

public final class GameLogic {
    private final int mNumPlayers;
    private final List<Player> mPlayers;

    public GameLogic() {
        mNumPlayers = 0;
        mPlayers = null;
    }

    public GameLogic(final GameInput gameInput) {
        mNumPlayers = gameInput.getNumPlayers();
        mPlayers = new ArrayList<>();

        for (int i = 0; i < mNumPlayers; ++i) {
            Player player = PlayerFactory.getInstance()
                    .getPlayerByName(gameInput.getPlayersType().get(i),
                                     gameInput.getXCoordinate().get(i),
                                     gameInput.getYCoordinate().get(i));
            mPlayers.add(player);
        }
    }

    public String gameStart(final GameInput gameInput) {
        String output = "";

        for (int j = 0; j < gameInput.getRounds(); ++j) {
            String nextMoves = gameInput.getNextMoves();  

            for (int i = 0; i < mNumPlayers; ++i) {
                if (mPlayers.get(i).isAlive()) {
                    mPlayers.get(i).applyPeriodicEffectAndMove(nextMoves.charAt(i));
                }
            }

            for (int i = 0; i < mNumPlayers; ++i) {
                for (int k = i + 1; k < mNumPlayers; ++k) {
                    if (mPlayers.get(i).sameLocation(mPlayers.get(k)) &&
                        mPlayers.get(i).isAlive() &&
                        mPlayers.get(k).isAlive()) {

                        mPlayers.get(i).acceptAbilityFrom(mPlayers.get(k));
                        mPlayers.get(k).acceptAbilityFrom(mPlayers.get(i));

                        mPlayers.get(i).getExperiencePoints(mPlayers.get(k));
                        mPlayers.get(k).getExperiencePoints(mPlayers.get(i));

                        mPlayers.get(i).incrementHits();
                        mPlayers.get(k).incrementHits();
                    }
                }
            }
        }

        for (int i = 0; i < mNumPlayers; ++i) {
            output += mPlayers.get(i).toString();
        }

        return output;
    }
}
