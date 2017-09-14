package com.aquaq.pokerface;

import com.aquaq.pokerface.input.Input;
import com.aquaq.pokerface.model.Card;
import com.aquaq.pokerface.model.Hand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PokerFace {

    private Input input;
    private PokerFaceProperties pokerFaceProperties;

    public PokerFace(final Input input, final PokerFaceProperties pokerFaceProperties) {
        this.input = input;
        this.pokerFaceProperties = pokerFaceProperties;
    }

    public void process(){
        final Stream<String> data = input.fetchInput();
        data.forEach(this::determineHand);
    }

    private void determineHand(final String cards){
        final Hand hand = new Hand();
        final List<String> cardList = Arrays.asList(cards.split(pokerFaceProperties.getDelimiter()));
        for(final String card : cardList){
            validateCard(card);
            char suit = card.charAt(pokerFaceProperties.getPositionOfSuit());
            char name = card.charAt(pokerFaceProperties.getPositionOfName());
            hand.addCard(new Card(suit, name));
        }

        final String description = hand.getHandRank();
        System.out.println(String.format("Hand: %s, Description: %s.", cards, description));
    }

    private void validateCard(final String card) {
        if(card.length() != pokerFaceProperties.getExpectedCardDataLength()){
            final String message = String.format("card length: %s does not match expected length: %s", card.length(), pokerFaceProperties.getExpectedCardDataLength());
            System.out.println(message);
            throw new PokerFaceException(message);
        }
    }
}
