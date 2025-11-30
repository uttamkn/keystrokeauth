package utils;

import models.KeyStroke;

public class TimeUtil {
    public static double getDwellTime(KeyStroke ks) {
        return (ks.keyReleasedNano() - ks.keyPressedNano()) / 1_000_000.0;
    }

    public static double getFlightTime(KeyStroke prev, KeyStroke curr) {
        return (curr.keyPressedNano() - prev.keyReleasedNano()) / 1_000_000.0;
    }
}
