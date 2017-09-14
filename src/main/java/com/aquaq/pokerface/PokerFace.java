package com.aquaq.pokerface;

import com.aquaq.pokerface.model.Card;
import com.aquaq.pokerface.model.Hand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PokerFace {

    private static final String DELIMITER = " ";
    private static final int POSITION_SUIT = 1;
    private static final int POSITION_NAME = 0;
    private static final int EXPECTED_CARD_DATA_LENGTH = 2;

    public static void process(){
        final FileInputStream fileInputStream = new FileInputStream();
        final Stream<String> input = fileInputStream.readInput();
        input.forEach(PokerFace::determineHand);
    }

    private static void determineHand(final String cards){
        final Hand hand = new Hand();
        final List<String> cardList = Arrays.asList(cards.split(DELIMITER));
        for(final String card : cardList){
            validateCard(card);
            char suit = card.charAt(POSITION_SUIT);
            char name = card.charAt(POSITION_NAME);
            hand.addCard(new Card(suit, name));
        }

        final String description = hand.getHandRank();
        System.out.println(String.format("Hand: %s, Description: %s.", cards, description));
    }

    private static void validateCard(final String card) {
        if(card.length() != EXPECTED_CARD_DATA_LENGTH){
            final String message = String.format("card length: %s does not match expected length: %s", card.length(), EXPECTED_CARD_DATA_LENGTH);
            System.out.println(message);
            throw new PokerFaceException(message);
        }
    }
}
