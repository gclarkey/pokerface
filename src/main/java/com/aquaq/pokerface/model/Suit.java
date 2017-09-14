package com.aquaq.pokerface.model;

public enum Suit {
    CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S');

    public char value;

    Suit(final char value){
        this.value = value;
    }
}
