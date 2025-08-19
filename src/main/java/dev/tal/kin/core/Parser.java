package dev.tal.kin.core;

import dev.tal.kin.core.cmd.impl.PrintCommand;
import dev.tal.kin.core.control.impl.IFControl;
import dev.tal.kin.core.control.impl.WhileControl;
import dev.tal.kin.core.variables.IVariable;
import dev.tal.kin.core.variables.operations.types.DecrementOp;
import dev.tal.kin.core.variables.operations.types.IncrementOp;
import dev.tal.kin.core.variables.operations.types.MultiplyOp;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/16/2025
 */
public class Parser {

    @Getter
    private final HashMap<String, IVariable> variables = new HashMap<>();

    @Getter @Setter
    private final IFControl ifControl = new IFControl(this);

    public Parser parse(List<String> fileAsList) throws IOException {
        for (int i = 0; i < fileAsList.size(); i++) {
            String rawLine = fileAsList.get(i);
            String line = rawLine.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            int commentIndex = line.indexOf("#");
            if (commentIndex != -1) {
                line = line.substring(0, commentIndex).trim();
            }

            boolean startsWithInt = line.startsWith("tam ");
            boolean startsWithCommand = line.startsWith("yazdir");
            boolean isControl = line.startsWith("eger") || line.startsWith("dongu");

            boolean isIncrement = !isControl && !startsWithCommand && (line.contains("+=") || line.endsWith("++"));
            boolean isDecrement = !isControl && !startsWithCommand && (line.contains("-=") || line.endsWith("--"));
            boolean isMultiply = !isControl && line.contains("*=");

            boolean donguBefore = line.startsWith("dongu") && !isControl;

            if (startsWithInt) {
                doParseVariable(line);
            } else if (startsWithCommand) {
                doParseCommand(line);
            } else if (isControl) {
                if (line.startsWith("eger")) {
                    i = ifControl.execute(fileAsList, line, i);
                } else if(line.startsWith("dongu")) {
                    i = new WhileControl(this).execute(fileAsList, line, i);
                }
            } else if (isIncrement) {
                new IncrementOp(this).check(fileAsList, line);
            } else if (isDecrement) {
                new DecrementOp(this).check(fileAsList, line);
            } else if (isMultiply) {
                new MultiplyOp(this).check(fileAsList, line);
            }

            donguBefore = false;
        }
        return this;
    }

    private void doParseCommand(String line) {
        if (line.startsWith("yazdir")) {
            new PrintCommand(this).execute(line);
        }
    }

    private void doParseVariable(String line) {
        String[] split = line.split("=");
        if (split.length < 2) return;

        String name = split[0].replace("tam", "").trim();
        int value = Integer.parseInt(split[1].trim());

        variables.put(name, new IVariable(IVariable.Type.INTEGER, name, value));
    }
}
