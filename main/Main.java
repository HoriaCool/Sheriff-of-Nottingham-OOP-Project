// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

import gameplay.Map;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) {
        GameInputOutputLoader gameInputOutputLoader = new GameInputOutputLoader(args[0], args[1]);
        GameInput gameInput = gameInputOutputLoader.load();
        Map.buildMap(gameInput.getRows(), gameInput.getColumns(), gameInput.getMap());
        GameLogic gameLogic = new GameLogic(gameInput);
        gameInputOutputLoader.write(gameLogic.gameStart(gameInput));
    }
}
