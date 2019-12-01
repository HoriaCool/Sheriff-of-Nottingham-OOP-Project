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

        Player pl1, pl2, pl3, pl4;
        pl1 = PlayerFactory.getInstance().getPlayerByName("P");
        pl2 = PlayerFactory.getInstance().getPlayerByName("K");
        pl3 = PlayerFactory.getInstance().getPlayerByName("W");
        pl4 = PlayerFactory.getInstance().getPlayerByName("R");
        pl1.getPrice();
        pl2.getPrice();
        pl3.getPrice();
        pl4.getPrice();

        //pl1.getDamage();
        //pl2.getDamage();
        //pl3.getDamage();
        //pl4.getDamage();

        pl1.acceptAbilityFrom(pl1);
        pl2.acceptAbilityFrom(pl1);
        pl3.acceptAbilityFrom(pl1);
        pl4.acceptAbilityFrom(pl1);

        pl1.acceptAbilityFrom(pl2);
        pl2.acceptAbilityFrom(pl2);
        pl3.acceptAbilityFrom(pl2);
        pl4.acceptAbilityFrom(pl2);

        pl1.acceptAbilityFrom(pl3);
        pl2.acceptAbilityFrom(pl3);
        pl3.acceptAbilityFrom(pl3);
        pl4.acceptAbilityFrom(pl3);

        pl1.acceptAbilityFrom(pl4);
        pl2.acceptAbilityFrom(pl4);
        pl3.acceptAbilityFrom(pl4);
        pl4.acceptAbilityFrom(pl4);

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
