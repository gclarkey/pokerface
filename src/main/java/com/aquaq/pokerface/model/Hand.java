package com.aquaq.pokerface.model;

import com.aquaq.pokerface.PokerFaceException;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
public class Hand {

    private static final int MAX_HAND_SIZE = 5;
    private Set<Card> cards = new HashSet<>();

    public void addCard(final Card card){
        if(cards.size() == MAX_HAND_SIZE){
            final String message = String.format("Number of cards: %s in hand: %s exceeds limit: %s. Cannot add card: %s", cards.size(), cards, MAX_HAND_SIZE, card);
            System.out.println(message);
            throw new PokerFaceException(message);
        }
        if(!cards.add(card)){
            final String message = String.format("Attempt to add duplicate card: %s to hand: %s. Input data invalid.", card, cards);
            System.out.println(message);
            throw new PokerFaceException(message);
        }
    }

    public String getHandRank(){
        final String description;

        if(straightFlush(cards)){
            description = "Straight flush";
        } else if(fourOfAKind(cards)){
            description = "Four of a kind";
        } else if(fullHouse(cards)){
            description = "Full house";
        } else if(twoPair(cards)){
            description = "Two pair";
        } else if(onePair(cards)){
            description = "One pair";
        } else {
            final String message = String.format("Unable to determine description for cards: %s", cards);
            System.out.println(message);
            throw new PokerFaceException(message);
        }

        return description;
    }

    private boolean straightFlush(Set<Card> hand) {
        return false;
    }

    private boolean fourOfAKind(Set<Card> hand) {
        return false;
    }

    private boolean fullHouse(Set<Card> hand) {
        return false;
    }

    private boolean twoPair(Set<Card> hand) {
        return false;
    }

    private boolean onePair(Set<Card> hand) {
        return true;
    }
}
