// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

import gameplay.Map;
import gameplay.Player;
import gameplay.PlayerFactory;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        // System.out.println(gameInput.getRows());
        // System.out.println(gameInput.getColumns());
        // System.out.println(gameInput.getMap());
        // System.out.println(gameInput.getNumPlayers());
        // System.out.println(gameInput.getPlayersType());
        // System.out.println(gameInput.getXCoordinate());
        // System.out.println(gameInput.getYCoordinate());
        // System.out.println(gameInput.getRounds());
        // System.out.println(gameInput.getMoves());

        Map.buildMap(gameInput.getRows(), gameInput.getColumns(), gameInput.getMap());
        Map map = Map.getInstance();

        // for (int i = 0; i < gameInput.getRows(); ++i) {
        //     for (int j = 0; j < gameInput.getColumns(); ++j) {
        //         System.out.print(map.getLocation(i, j));
        //     }
        //     System.out.printf("%n");
        // }

        // for (int i = 0; i < gameInput.getRounds(); ++i) {
        //     System.out.println(gameInput.getNextMoves());
        // }

        // for (int i = 0; i < gameInput.getNumPlayers(); ++i) {
        //     PlayerFactory.getInstance().getPlayerByName(gameInput.getPlayersType().get(i)).getPrice();
        // }
        Player pl1, pl2;
        pl1 = PlayerFactory.getInstance().getPlayerByName("P");
        pl2 = PlayerFactory.getInstance().getPlayerByName("K");
        pl1.acceptsAbilityFrom(pl2);
        pl2.acceptsAbilityFrom(pl1);

        /*
        if (gameInput.isValidInput()) {
            GoodsFactory goodsFactory = GoodsFactory.getInstance();
            GameLogic gameLogic = new GameLogic(gameInput);
            gameLogic.gameStart();
        } else {
            System.out.println("error: Bad Input!");
        }
        */
    }
}
