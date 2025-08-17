package dev.tal.kin.core.variables.operations;

import dev.tal.kin.core.Parser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/17/2025
 */
@AllArgsConstructor
@Getter
public abstract class VarOperation {

    private Parser parser;
    private String symbol;

    public abstract void check(List<String> lines, String line);
}
