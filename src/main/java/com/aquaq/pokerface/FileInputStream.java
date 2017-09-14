package com.aquaq.pokerface;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileInputStream {

    public Stream<String> readInput(){
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

        return input;
    }
}
