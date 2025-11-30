package models;

public class KeyStroke {
    private final char character;
    private final double keyPressed;
    private final double keyReleased;

    public KeyStroke(char character, double key_pressed, double key_released) {
        this.character = character;
        this.keyPressed = key_pressed;
        this.keyReleased = key_released;
    }

    public double getDwellTime() {
        return keyReleased - keyPressed;
    }

    @Override
    public String toString() {
        return "KeyStroke{" + character + ", " + keyPressed + ", " + keyReleased + "}";
    }
}
