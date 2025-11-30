package models;

public record KeyStroke(char character, long keyPressedNano, long keyReleasedNano) {

    public double getDwellTimeMs() {
        return (keyReleasedNano - keyPressedNano) / 1_000_000.0;
    }

    @Override
    public String toString() {
        return "KeyStroke{" + character + ", " + keyPressedNano + ", " + keyReleasedNano + "}";
    }
}
