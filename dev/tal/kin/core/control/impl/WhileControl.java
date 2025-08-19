package dev.tal.kin.core.control.impl;

import dev.tal.kin.core.Parser;
import dev.tal.kin.core.control.IControl;
import dev.tal.kin.core.variables.IVariable;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/17/2025
 */
@Getter
public class WhileControl implements IControl {

    private final Parser parser;

    private boolean inside;

    public WhileControl(Parser parser) {
        this.parser = parser;
    }

    @Override
    public int execute(List<String> lines, String line, int index) {
        if (!line.trim().startsWith("dongu")) return index;

        int open = line.indexOf("(");
        int close = line.lastIndexOf(")");
        if (open < 0 || close < 0 || close <= open) return index;

        String rule = line.substring(open + 1, close).trim();

        List<String> body = new ArrayList<>();
        int endIndex = index;

        for (int i = index + 1; i < lines.size(); i++) {
            String l = lines.get(i).trim();

            if (l.startsWith("son")) {
                endIndex = i;
                break;
            }
            body.add(lines.get(i));
        }

        int parsedRule = Integer.parseInt(rule);

        for(int i = 0; i < parsedRule; i++) {
            IFControl.LoopState state = getParser().getIfControl().getState();

            if (state == IFControl.LoopState.CONTINUE) {
                getParser().getIfControl().setState(IFControl.LoopState.NONE);
                continue;
            }

            if (state == IFControl.LoopState.BREAK) {
                getParser().getIfControl().setState(IFControl.LoopState.NONE);
                break;
            }

            parseBody(body);
        }

        return endIndex;
    }

    private void parseBody(List<String> body) {
        try {
            parser.parse(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
