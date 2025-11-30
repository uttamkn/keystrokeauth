package models;

public record KeyStroke(char character, long keyPressedNano, long keyReleasedNano) {
    @Override
    public String toString() {
        return "KeyStroke{" + character + ", " + keyPressedNano + ", " + keyReleasedNano + "}";
    }
}
