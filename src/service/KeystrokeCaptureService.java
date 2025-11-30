package service;

import models.KeyStroke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;



public class KeystrokeCaptureService {

    public static List<KeyStroke> capture(int passwordLength, String prompt) {

        List<KeyStroke> keystrokes = new ArrayList<>();
        Map<Integer, Long> pressTimes = new HashMap<>();

        CountDownLatch latch = new CountDownLatch(1);

        JFrame frame = new JFrame("Keystroke Capture");
        JPasswordField field = new JPasswordField(passwordLength);
        JLabel label = new JLabel(prompt);

        field.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                pressTimes.put(e.getKeyCode(), System.nanoTime());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                long releaseTime = System.nanoTime();
                long pressTime = pressTimes.getOrDefault(e.getKeyCode(), releaseTime);

                char c = e.getKeyChar();

                if (!Character.isISOControl(c)) {
                    keystrokes.add(new KeyStroke(c, pressTime, releaseTime));
                }

                if (field.getPassword().length == passwordLength) {
                    latch.countDown();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) { }
        });

        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(field);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            latch.await();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        String typed = new String(field.getPassword());
        frame.dispose();

        return keystrokes;
    }
}
