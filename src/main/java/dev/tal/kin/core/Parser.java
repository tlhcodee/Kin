package dev.tal.kin.core;

import dev.tal.kin.core.cmd.impl.PrintCommand;
import dev.tal.kin.core.variables.IVariable;
import dev.tal.kin.core.variables.operations.types.IncrementOp;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/16/2025
 */
public class Parser {

    @Getter
    private final HashMap<String, IVariable> variables = new HashMap<>();

    /*
     * parse(list<string>: arg)
     * Constructor gorevi gorur
     * dongu icerisinde parse komut ve islevlerini calistirir.
     * dosyayi okur ve islemleri tamamlar
     */
    public Parser parse(List<String> fileAsList) throws IOException {
        for (String line : fileAsList) {
            boolean startsWithInt = line.startsWith("tam");
            boolean startsWithCommand = line.startsWith("yazdir");
            boolean isIncrement = line.contains("+="); // <-- BURASI

            if (startsWithInt) {
                doParseVariable(fileAsList, line);
            } else if (startsWithCommand) {
                doParseCommand(line);
            } else if (isIncrement) {
                new IncrementOp(this).check(fileAsList, line);
            }
        }

        return this;
    }

    /*
    * doParseCommand(string: arg)
    * Basitce, syntax komutlari icin parse islemi
    * degiskenler ve bir suru seyi hafizadan ceker
    */
    private void doParseCommand(String line) {
        String cmd = "";
        if (line.startsWith("yazdir"))
            cmd = "yazdir";

        switch (cmd) {
            case "yazdir":
                new PrintCommand(this).execute(line);
                break;
        }
    }

    /*
     * doParseInt(string: arg)
     * tam, adi altinda variablelara yer verir
     * algiladigi variable syntaxini isim ve degeri ile kaydeder.
     */
    private void doParseVariable(List<String> lines, String line) {
        IVariable.Type type = IVariable.Type.INTEGER;

        String[] split = line.split("=");
        if (split.length < 2) return;

        String name = split[0].replace("tam", "").trim();
        int value = Integer.parseInt(split[1].trim());

        variables.put(name, new IVariable(IVariable.Type.INTEGER, name, value));

    }
}
