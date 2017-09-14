package com.aquaq.pokerface;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerFace {

    private static final String DELIMITER = " ";
    private static final int POSITION_SUIT = 1;
    private static final int POSITION_NAME = 0;
    private static final int EXPECTED_CARD_DATA_LENGTH = 2;

    public void readInput(){
        Stream<String> input = null;
        try {
            input = Files.lines(Paths.get(getClass().getResource("/pokerHands.txt").toURI()));
        } catch (final IOException e) {
            final String message = "Error reading in file.";
            System.out.println(message);
            throw new PokerFaceException(message);
        } catch (final URISyntaxException e) {
            final String message = "Invalid URI.";
            System.out.println(message);
            throw new PokerFaceException(message);
        }
        //input.forEach(System.out::println);
        final List<String> record = input.collect(Collectors.toList());
        for(final String hand : record){
            final String description = determineHand(hand);
            System.out.println(String.format("Hand: %s, Description: %s.", hand, description));
        }
    }

    private String determineHand(final String cards){
        final Hand hand = new Hand();
        final List<String> cardList = Arrays.asList(cards.split(DELIMITER));
        for(final String card : cardList){
            validateCard(card);
            char suit = card.charAt(POSITION_SUIT);
            char name = card.charAt(POSITION_NAME);
            hand.addCard(new Card(suit, name));
        }

        return hand.getHandRank();
    }

    private void validateCard(final String card) {
        if(card.length() != EXPECTED_CARD_DATA_LENGTH){
            final String message = String.format("card length: %s does not match expected length: %s", card.length(), EXPECTED_CARD_DATA_LENGTH);
            System.out.println(message);
            throw new PokerFaceException(message);
        }
    }
}
