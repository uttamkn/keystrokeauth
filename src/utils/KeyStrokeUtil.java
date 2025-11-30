package utils;

import models.KeyStroke;

import java.util.List;

public class KeyStrokeUtil {
    public static String getTypedText(List<KeyStroke> ks) {
        StringBuilder text = new StringBuilder();
        for (KeyStroke k : ks) {
            text.append(k.character());
        }
        return text.toString();
    }
}
