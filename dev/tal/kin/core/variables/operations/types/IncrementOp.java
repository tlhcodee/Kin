package dev.tal.kin.core.variables.operations.types;

import dev.tal.kin.core.Parser;
import dev.tal.kin.core.variables.IVariable;
import dev.tal.kin.core.variables.operations.VarOperation;
import dev.tal.kin.error.ErrorMessages;

import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/17/2025
 */
public class IncrementOp extends VarOperation {

    public IncrementOp(Parser parser) {
        super(parser, "+=");
    }

    @Override
    public void check(List<String> lines, String line) {
        String[] split = line.split("\\+=");

        String clearedName = split[0].trim();
        int amount = line.contains("++") ? 1 : Integer.parseInt(split[1].trim());

        IVariable variable = getParser().getVariables().get(clearedName);

        if (variable != null) {
            switch (variable.getType()) {
                case INTEGER:
                    int current = (Integer) variable.getValue();
                    int updated = current + amount;
                    variable.setValue(updated);
                    break;
            }
        } else {
            System.out.println(ErrorMessages.VAROP_NAME_NOT_FOUND);
        }
    }

}
