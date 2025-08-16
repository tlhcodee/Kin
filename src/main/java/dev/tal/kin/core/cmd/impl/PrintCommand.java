package dev.tal.kin.core.cmd.impl;

import dev.tal.kin.core.Parser;
import dev.tal.kin.core.cmd.ICommand;
import dev.tal.kin.core.variables.IVariable;
import lombok.Getter;

import java.util.HashMap;

public class PrintCommand implements ICommand {

    @Getter
    private final Parser parent;

    public PrintCommand(Parser parent) {
        this.parent = parent;
    }

    @Override
    public void execute(String line) {
        HashMap<String, IVariable> variables = parent.getVariables();

        String cleaned = line
                .replace("yazdir", "")
                .replace("(", "")
                .replace(")", "")
                .replace(";", "")
                .trim();

        boolean insideQuotes = false;
        StringBuilder builder = new StringBuilder();
        StringBuilder varBuffer = new StringBuilder();

        for (int i = 0; i < cleaned.length(); i++) {
            char c = cleaned.charAt(i);

            switch (c) {
                case '"':
                    insideQuotes = !insideQuotes;
                    if (!insideQuotes && varBuffer.length() > 0) {
                        builder.append(varBuffer.toString());
                        varBuffer.setLength(0);
                    }
                    break;

                case '+':
                    if (varBuffer.length() > 0) {
                        String varName = varBuffer.toString().trim();
                        if (variables.containsKey(varName)) {
                            Object val = variables.get(varName).getValue(); // <-- BURASI
                            builder.append(val != null ? val.toString() : "null");
                        } else {
                            builder.append(varName);
                        }
                        varBuffer.setLength(0);
                    }
                    break;

                default:
                    if (insideQuotes) {
                        builder.append(c);
                    } else {
                        varBuffer.append(c);
                    }
                    break;
            }
        }

        if (varBuffer.length() > 0) {
            String varName = varBuffer.toString().trim();
            if (variables.containsKey(varName)) {
                Object val = variables.get(varName).getValue();
                builder.append(val != null ? val.toString() : "null");
            } else {
                builder.append(varName);
            }
        }

        System.out.println(builder.toString());
    }
}
