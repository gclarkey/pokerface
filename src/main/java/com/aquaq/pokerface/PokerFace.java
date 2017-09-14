package com.aquaq.pokerface;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerFace {

    private static final String DELIMITER = " ";
    private static final int POSITION_SUIT = 1;
    private static final int POSITION_NAME = 0;
    private static final int EXPECTED_CARD_DATA_LENGTH = 2;
    private static final int EXPECTED_CARD_LIST_SIZE = 5;

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
        List<String> record = input.collect(Collectors.toList());
        for(String s : record){
            System.out.println(s);
            System.out.println(determineHand(s));
        }
    }

    public String determineHand(final String cards){
        final List<Card> hand = new ArrayList<>();
        final List<String> cardList = Arrays.asList(cards.split(DELIMITER));
        validateCardList(cardList);
        for(final String card : cardList){
            validateCard(card);
            char suit = card.charAt(POSITION_SUIT);
            //validateSuit(suit);
            char name = card.charAt(POSITION_NAME);
            //validateName(name);
            hand.add(new Card(suit, name));
        }
        final String handDescription = determineHandDescription(hand);

        return handDescription;
    }

    private String determineHandDescription(final List<Card> hand) {
        final String description;

        if(straightFlush(hand)){
            description = "Straight flush";
        } else if(fourOfAKind(hand)){
            description = "Four of a kind";
        } else if(fullHouse(hand)){
            description = "Full house";
        } else if(twoPair(hand)){
            description = "Two pair";
        } else if(onePair(hand)){
            description = "One pair";
        } else {
            final String message = String.format("Unable to determine description for hand: %s", hand);
            System.out.println(message);
            throw new PokerFaceException(message);
        }

        return description;
    }

    private boolean straightFlush(List<Card> hand) {
        return false;
    }

    private boolean fourOfAKind(List<Card> hand) {
        return false;
    }

    private boolean fullHouse(List<Card> hand) {
        return false;
    }

    private boolean twoPair(List<Card> hand) {
        return false;
    }

    private boolean onePair(List<Card> hand) {
        return false;
    }

    private void validateCardList(List<String> cardList) {
        if(cardList.size() != EXPECTED_CARD_LIST_SIZE){
            final String message = String.format("Number of elements in cardList: %s does not match expected size: %s", cardList.size(), EXPECTED_CARD_LIST_SIZE);
            System.out.println(message);
            throw new PokerFaceException(message);
        }
        //TODO Ensure no two values are the same
    }

    private void validateCard(final String card) {
        if(card.length() != EXPECTED_CARD_DATA_LENGTH){
            final String message = String.format("card length: %s does not match expected length: %s", card.length(), EXPECTED_CARD_DATA_LENGTH);
            System.out.println(message);
            throw new PokerFaceException(message);
        }
    }
}
