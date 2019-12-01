// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

public final class GameLogic {
    private int mRounds;
    private List<Player> mPlayers;

    public GameLogic() {
    }

    public GameLogic(final GameInput gameInput) {
        mRounds = gameInput.getRounds();
        mPlayers = new ArrayList<>();
        mDeckId = new LinkedList<>();

        int i = 0;
        for (String playerStrategyName: gameInput.getPlayerNames()) {
            mPlayers.add(new Player(i++, playerStrategyName));
        }

        for (Integer cardId: gameInput.getAssetIds()) {
            mDeckId.add(cardId);
        }
    }
}
