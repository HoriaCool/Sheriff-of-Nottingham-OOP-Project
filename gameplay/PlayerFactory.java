// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

import java.util.HashMap;
import java.util.Map;

public final class PlayerFactory {
    private static PlayerFactory mInstance = null;
    private static final String PYROMANCER_NAME = "P";
    private static final String KNIGHT_NAME = "K";
    private static final String WIZARD_NAME = "W";
    private static final String ROGUE_NAME = "R";

    private enum PlayerType {
        PyromancerType, KnightType, WizardType, RogueType
    }

    private final Map<String, PlayerType> mPlayerTypeByName;

    private PlayerFactory() {
        mPlayerTypeByName = new HashMap<String, PlayerType>();

        // insert player types into the hashMap
        mPlayerTypeByName.put(PYROMANCER_NAME, PlayerType.PyromancerType);
        mPlayerTypeByName.put(KNIGHT_NAME, PlayerType.KnightType);
        mPlayerTypeByName.put(WIZARD_NAME, PlayerType.WizardType);
        mPlayerTypeByName.put(ROGUE_NAME, PlayerType.RogueType);
    }

    public Player getPlayerByName(String playerName, int xCoordinate, int yCoordinate) {
    	try {
        	PlayerType playerType = mPlayerTypeByName.get(playerName);

        	switch (playerType) {
            	case PyromancerType: return new Pyromancer(xCoordinate, yCoordinate, playerName);
            	case KnightType:     return new Knight(xCoordinate, yCoordinate, playerName);
            	case WizardType:     return new Wizard(xCoordinate, yCoordinate, playerName);
            	case RogueType:      return new Rogue(xCoordinate, yCoordinate, playerName);
        	}
    	} catch (Exception e) {
    		// no matched key
            System.err.println(e.toString());
            throw new IllegalArgumentException("The player name " + playerName +
            	" is not recognized.");
    	}

    	return new Pyromancer(0, 0, null); // default in case of error
    }

    public static PlayerFactory getInstance() {
        if (mInstance == null) {
            mInstance = new PlayerFactory();
        }

        return mInstance;
    }
}
