package app;

import models.KeyStroke;
import service.KeystrokeCaptureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Keystroke Dynamics Enrollment ===");

        System.out.print("Enter the password you want to profile: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty. Exiting.");
            return;
        }

        System.out.print("How many samples for enrollment? ");
        int n = scanner.nextInt();
        scanner.nextLine();

        if (n <= 0) {
            System.out.println("Sample count must be > 0. Exiting.");
            return;
        }

        List<List<KeyStroke>> samples = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nSample " + i + " of " + n);
            System.out.println("A typing window will appear. Type the password exactly.");

            List<KeyStroke> ks = KeystrokeCaptureService.capture(
                    password.length(),
                    "Type your password (" + i + "/" + n + ")"
            );

            samples.add(ks);

            System.out.println("Captured keystrokes: " + ks.size());
        }

        System.out.println("\n=== Enrollment Complete ===");
        System.out.println("Collected " + samples.size() + " samples.");

        // TODO:
        // 1. Convert Keystrokes to FeatureVectors
        // 2. Build UserProfile from FeatureVectors
        // 3. Save UserProfile in memory
        // 4. Add verification step
    }
}
