package com.aquaq.pokerface.model;

import com.aquaq.pokerface.PokerFaceException;

public class Card {

    private final Suit suit;
    private final Name name;

    public Card(final char suit, final char name){
        this.suit = determineSuit(suit);
        this.name = determineName(name);
    }

    public Suit getSuit() {
        return suit;
    }

    public Name getName() {
        return name;
    }

    private Suit determineSuit(char suit) {
        for(final Suit s : Suit.values()){
            if(s.value == suit){
                return s;
            }
        }

        final String message = String.format("Unable to match Suit of card to supplied value: %s", suit);
        System.out.println(message);
        throw new PokerFaceException(message);
    }

    private Name determineName(char name) {
        for(final Name n : Name.values()){
            if(n.value == name){
                return n;
            }
        }

        final String message = String.format("Unable to match Name of card to supplied value: %s", name);
        System.out.println(message);
        throw new PokerFaceException(message);
    }
}
