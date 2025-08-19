package dev.tal.kin;

import dev.tal.kin.core.Parser;
import dev.tal.kin.core.Reader;
import dev.tal.kin.error.ErrorMessages;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        switch(args.length) {
            case 0:
                System.out.println(ErrorMessages.PATH_NOT_FOUND.getValue()); // Belirtilen dosya yolu bulunamadi.
                break;
            case 1:
                if(args[0].isEmpty()) {
                    System.out.println(ErrorMessages.ARG_IS_EMPTY.getValue()); // Arg (dosya yolu) belirtilmemis.
                    return;
                }

                try {
                    String path = args[0]; // pathi cek
                    File file = new File(path); // dosyayi al

                    if(file.exists()) {
                        List<String> lines = Reader.readAndReturnAsList(file);

                        if(!lines.isEmpty()) {
                            new Parser().parse(lines); // parseri dosya uzerinde calistir.
                            // Basitce dosyanin her satirini okuyup, islem yapicak
                        }
                    } else {
                        System.out.println(ErrorMessages.FILE_NOT_FOUND.getValue()); // Dosya bulunamadi
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}