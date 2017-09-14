package com.aquaq.pokerface;

import lombok.Data;

@Data
public class PokerFaceProperties {
    private final String delimiter;
    private final int positionOfSuit;
    private final int positionOfName;
    private final int expectedCardDataLength;
    private final String inputLocation;
}
