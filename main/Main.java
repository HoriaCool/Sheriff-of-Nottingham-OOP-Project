// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        System.out.println(gameInput.getRows());
        System.out.println(gameInput.getColumns());
        System.out.println(gameInput.getMap());
        System.out.println(gameInput.getNumPlayers());
        System.out.println(gameInput.getPlayersType());
        System.out.println(gameInput.getXCoordinate());
        System.out.println(gameInput.getYCoordinate());
        System.out.println(gameInput.getRounds());
        System.out.println(gameInput.getMoves());


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
