package io.reactivestax;

import io.reactivestax.service.BNYMFileReader;

public class MainRunner {
    public static void main(String[] args) {
        BNYMFileReader bnymFileReader = new BNYMFileReader();
        bnymFileReader.readFile();
    }
}