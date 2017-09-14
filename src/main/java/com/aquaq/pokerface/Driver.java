package com.aquaq.pokerface;

import com.aquaq.pokerface.input.FileInput;
import com.aquaq.pokerface.input.Input;

public class Driver {

    private static final String DELIMITER = " ";
    private static final int POSITION_SUIT = 1;
    private static final int POSITION_NAME = 0;
    private static final int EXPECTED_CARD_DATA_LENGTH = 2;
    private static final String INPUT_LOCATION = "/pokerHands.txt";

    public static void main(String args[]) {
        final PokerFaceProperties pokerFaceProperties = createPokerFaceProperties();
        final Input input = new FileInput(pokerFaceProperties);
        final PokerFace pokerFace = new PokerFace(input, pokerFaceProperties);

        pokerFace.process();
    }

    private static PokerFaceProperties createPokerFaceProperties() {
        return new PokerFaceProperties(DELIMITER, POSITION_SUIT, POSITION_NAME, EXPECTED_CARD_DATA_LENGTH, INPUT_LOCATION);
    }
}
