package com.aquaq.pokerface.input;

import com.aquaq.pokerface.PokerFaceException;
import com.aquaq.pokerface.PokerFaceProperties;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileInput implements Input {

    private PokerFaceProperties pokerFaceProperties;

    public FileInput(final PokerFaceProperties pokerFaceProperties){
        this.pokerFaceProperties = pokerFaceProperties;
    }

    public Stream<String> fetchInput(){
        Stream<String> input = null;
        try {
            input = Files.lines(Paths.get(getClass().getResource(pokerFaceProperties.getInputLocation()).toURI()));
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
