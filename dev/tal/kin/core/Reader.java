package dev.tal.kin.core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/16/2025
 */
public class Reader {

    public static List<String> readAndReturnAsList(File file) throws IOException {
        final List<String> toReturn = new ArrayList<>();

        BufferedReader reader = new BufferedReader(
                new FileReader(file.getAbsolutePath()));

        String st;

        while((st = reader.readLine()) != null) {
            toReturn.add(st);
        }

        return toReturn;
    }
}
