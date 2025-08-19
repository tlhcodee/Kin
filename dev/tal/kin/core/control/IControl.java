package dev.tal.kin.core.control;

import java.util.List;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/17/2025
 */
public interface IControl {
    int execute(List<String> lines, String line, int index);
}
