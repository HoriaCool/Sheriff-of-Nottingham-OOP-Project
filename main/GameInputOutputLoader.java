// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package main;

import java.util.ArrayList;
import java.util.List;

import fileio.FileSystem;

public final class GameInputOutputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    public GameInputOutputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    /*
     *  Load data from InputFile.
     */
    public GameInput load() {
        int rows = 0;
        int columns = 0;
        List<String> map = new ArrayList<>();
        int numPlayers = 0;
        List<String> playersType = new ArrayList<>();
        List<Integer> xCoordinate = new ArrayList<>();
        List<Integer> yCoordinate = new ArrayList<>();
        int rounds = 0;
        List<String> moves = new ArrayList<>();

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            rows = fs.nextInt();
            columns = fs.nextInt();

            for (int i = 0; i < rows; ++i) {
                map.add(fs.nextWord());
            }

            numPlayers = fs.nextInt();

            for (int i = 0; i < numPlayers; ++i) {
                playersType.add(fs.nextWord());
                xCoordinate.add(fs.nextInt());
                yCoordinate.add(fs.nextInt());
            }

            rounds = fs.nextInt();

            for (int i = 0; i < rounds; ++i) {
                moves.add(fs.nextWord());
            }

            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(rows, columns, map, playersType,
            xCoordinate, yCoordinate, moves);
    }

    /*
     *  Write data to OutputFile.
     */
    public void write(final String output) {
        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            fs.writeWord(output);

            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
