package com.aquaq.pokerface.model;

public enum Name {
    TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');

    public char value;

    Name(final char value) {
        this.value = value;
    }
}
