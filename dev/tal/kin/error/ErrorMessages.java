package dev.tal.kin.error;

import lombok.Getter;

/**
 * @proje KinLanguage
 * @gelistirici talyazilim
 * @tarih 8/16/2025
 */
@Getter
public enum ErrorMessages {
    FILE_NOT_FOUND("Belirtilen dosya bulunamadi."),
    PATH_NOT_FOUND("Belirtilen dosya yolu bulunamadi, tekrar dene!"),
    ARG_IS_EMPTY("Argumanlardan birisi bos gibi, ilginc, tekrar dene."),
    VAROP_NAME_NOT_FOUND("Islem yapmaya calistigin isim de bir degisken olusturulmamis.");

    private final String value;

    ErrorMessages(String value) {
        this.value = "[Kin] -> " + value;
    }
}
