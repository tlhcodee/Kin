package dev.tal.kin.core.control.impl;

import dev.tal.kin.core.Parser;
import dev.tal.kin.core.control.IControl;
import dev.tal.kin.core.variables.IVariable;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/17/2025
 */
@Getter @Setter
public class IFControl implements IControl {

    private final Parser parser;
    private LoopState state;

    private LoopState preState;

    public IFControl(Parser parser) {
        this.parser = parser;
        this.state = LoopState.NONE;
        this.preState = this.state;
    }

    @Override
    public int execute(List<String> lines, String line, int index) {
        if (!line.trim().startsWith("eger")) return index;

        int open = line.indexOf("(");
        int close = line.lastIndexOf(")");
        if (open < 0 || close < 0 || close <= open) return index;

        String rule = line.substring(open + 1, close).trim();

        List<String> body = new ArrayList<>();
        int endIndex = index;

        for (int i = index + 1; i < lines.size(); i++) {
            String l = lines.get(i).trim();

            if (l.startsWith("}")) {
                endIndex = i;
                break;
            }
            body.add(lines.get(i));
        }

        boolean result = false;
        if (rule.contains("==")) result = handleEquals(rule);
        else if (rule.contains("<")) result = handleLess(rule);
        else if (rule.contains(">")) result = handleGreater(rule);

        if (result) {
            for (String l : body) {
                String trimmed = l.trim();
                if (trimmed.startsWith("atla")) { // TODO: bunlari gercekten calısır hale getirmek lazim
                    this.state = LoopState.CONTINUE;
                    return endIndex;
                }

                if (trimmed.startsWith("bitir")) {
                    this.state = LoopState.BREAK;
                    return endIndex;
                }
            }

            parseBody(body);
        }

        return endIndex;
    }


    private boolean handleLess(String rule) {
        String[] split = rule.split("<");
        if (split.length < 2) return false;

        boolean anyDigit = rule.chars().anyMatch(Character::isDigit);
        boolean digitOn1 = split[0].trim().chars().anyMatch(Character::isDigit);
        boolean digitOn2 = split[1].trim().chars().anyMatch(Character::isDigit);

        IVariable v1 = parser.getVariables().get(split[0].trim());
        IVariable v2 = parser.getVariables().get(split[1].trim());
        if (!anyDigit && (v1 == null || v2 == null)) return false;

        int val1 = anyDigit ? digitOn1 ? Integer.parseInt(split[0].trim()) : ((Number) v1.getValue()).intValue() : ((Number) v1.getValue()).intValue();
        int val2 = anyDigit ? digitOn2 ? Integer.parseInt(split[1].trim()) : ((Number) v2.getValue()).intValue() : ((Number) v2.getValue()).intValue();
        return val1 < val2;
    }

    private boolean handleGreater(String rule) {
        String[] split = rule.split(">");
        if (split.length < 2) return false;

        boolean anyDigit = rule.chars().anyMatch(Character::isDigit);
        boolean digitOn1 = split[0].trim().chars().anyMatch(Character::isDigit);
        boolean digitOn2 = split[1].trim().chars().anyMatch(Character::isDigit);

        IVariable v1 = parser.getVariables().get(split[0].trim());
        IVariable v2 = parser.getVariables().get(split[1].trim());
        if (!anyDigit && (v1 == null || v2 == null)) return false;

        int val1 = anyDigit ? digitOn1 ? Integer.parseInt(split[0].trim()) : ((Number) v1.getValue()).intValue() : ((Number) v1.getValue()).intValue();
        int val2 = anyDigit ? digitOn2 ? Integer.parseInt(split[1].trim()) : ((Number) v2.getValue()).intValue() : ((Number) v2.getValue()).intValue();
        return val1 > val2;
    }

    private boolean handleEquals(String rule) {
        String[] split = rule.split("==");
        if (split.length < 2) return false;

        boolean anyDigit = rule.chars().anyMatch(Character::isDigit);
        boolean digitOn1 = split[0].trim().chars().anyMatch(Character::isDigit);
        boolean digitOn2 = split[1].trim().chars().anyMatch(Character::isDigit);

        IVariable v1 = parser.getVariables().get(split[0].trim());
        IVariable v2 = parser.getVariables().get(split[1].trim());

        if(anyDigit) {
            int val1 = digitOn1 ? Integer.parseInt(split[0].trim()) : ((Number) v1.getValue()).intValue();
            int val2 = digitOn2 ? Integer.parseInt(split[1].trim()) : ((Number) v2.getValue()).intValue();

            return val1 == val2;
        }

        return String.valueOf(v1.getValue()).equals(String.valueOf(v2.getValue()));
    }

    private void parseBody(List<String> body) {
        try {
            parser.parse(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum LoopState {
        NONE,
        BREAK,
        CONTINUE;
    }
}
