package com.aquaq.pokerface.model;

import com.aquaq.pokerface.PokerFaceException;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

        final Map<Name,Integer> countOfNamesInHand = countOfNamesInHand(cards);

        if(straightFlush(cards, countOfNamesInHand)){
            description = "Straight flush";
        } else if(fourOfAKind(countOfNamesInHand)){
            description = "Four of a kind";
        } else if(fullHouse(countOfNamesInHand)){
            description = "Full house";
        } else if(flush(cards)){
            description = "Flush";
        } else if(straight(countOfNamesInHand)){
            description = "Straight";
        } else if(threeOfAKind(countOfNamesInHand)){
            description = "Three of a kind";
        } else if(twoPair(countOfNamesInHand)){
            description = "Two pair";
        } else if(onePair(countOfNamesInHand)){
            description = "One pair";
        } else {
            description = "High card";
        }

        return description;
    }

    private boolean straightFlush(final Set<Card> hand, Map<Name, Integer> countOfNamesInHand) {
        return flush(hand) && straight(countOfNamesInHand);
    }

    private boolean fourOfAKind(final Map<Name, Integer> countOfNamesInHand) {
        for(final Integer i : countOfNamesInHand.values()){
            if(i==4){
                return true;
            }
        }
        return false;
    }

    private boolean fullHouse(final Map<Name, Integer> countOfNamesInHand) {
        return threeOfAKind(countOfNamesInHand) && onePair(countOfNamesInHand);
    }

    private boolean flush(final Set<Card> hand) {
        final Map<Suit, Integer> countOfSuitsInHand = countOfSuitsInHand(hand);
        for(final Integer i : countOfSuitsInHand.values()){
            if(i==5){
                return true;
            }
        }
        return false;
    }

    private boolean straight(final Map<Name, Integer> countOfNamesInHand) {
        if(countOfNamesInHand.size() != 5){
            return false;
        }
        final List<Integer> nameIntegers = new ArrayList<>();
        for (final Name name: countOfNamesInHand.keySet()) {
            nameIntegers.add(Character.getNumericValue(name.value));
        }
        Collections.sort(nameIntegers);
        for(int i=0; i<nameIntegers.size()-1; i++){
            if(nameIntegers.get(i)+1 != nameIntegers.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private boolean threeOfAKind(Map<Name, Integer> countOfNamesInHand) {
        for(final Integer i : countOfNamesInHand.values()){
            if(i==3){
                return true;
            }
        }
        return false;
    }

    private boolean twoPair(final Map<Name, Integer> countOfNamesInHand) {
        int count = 0;
        for(final Integer i : countOfNamesInHand.values()){
            if(i==2){
                count++;
            }
        }
        return count == 2;
    }

    private boolean onePair(final Map<Name, Integer> countOfNamesInHand) {
        boolean pair = false;
        for(final Integer i : countOfNamesInHand.values()){
            if(i==2){
                pair = true;
            }
        }
        return pair;
    }

    private Map<Name,Integer> countOfNamesInHand(final Set<Card> hand){
        final Map<Name, Integer> nameCount = new HashMap<>();
        for(final Card card : hand){
            final Name name = card.getName();
            if(nameCount.containsKey(name)){
                final int count = nameCount.get(name) + 1;
                nameCount.put(name, count);
            } else {
                nameCount.put(name, 1);
            }
        }

        return nameCount;
    }

    private Map<Suit,Integer> countOfSuitsInHand(final Set<Card> hand){
        final Map<Suit, Integer> suitCount = new HashMap<>();
        for(final Card card : hand){
            final Suit suit = card.getSuit();
            if(suitCount.containsKey(suit)){
                final int count = suitCount.get(suit) + 1;
                suitCount.put(suit, count);
            } else {
                suitCount.put(suit, 1);
            }
        }

        return suitCount;
    }
}
