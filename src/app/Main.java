package app;

import models.FeatureVector;
import models.KeyStroke;
import models.UserProfile;
import service.EnrollmentService;
import service.FeatureExtractionService;
import service.KeystrokeCaptureService;
import service.VerificationService;
import utils.KeyStrokeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static double THRESHOLD = 1.0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Keystroke Dynamics Enrollment ===");

        // ===== Enrollment Phase =====
        System.out.print("Enter the password you want to profile: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty. Exiting.");
            return;
        }

        System.out.print("How many featureVectors for enrollment? ");
        int n = scanner.nextInt();
        scanner.nextLine();

        if (n <= 0) {
            System.out.println("Sample count must be > 0. Exiting.");
            return;
        }

        List<FeatureVector> featureVectors = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nSample " + i + " of " + n);
            System.out.println("A typing window will appear. Type the password exactly.");

            List<KeyStroke> ks = KeystrokeCaptureService.capture(
                    password.length(),
                    "Type your password (" + i + "/" + n + ")"
            );
            System.out.println("Captured keystrokes: " + ks.size());

            String typed = KeyStrokeUtil.getTypedText(ks);

            if(typed.equals(password)) {
                featureVectors.add(FeatureExtractionService.extract(ks));
            } else {
                System.out.println("Wrong password. Try again.");
                i--;
            }
        }

        System.out.println("\n=== Enrollment Complete ===");
        System.out.println("Collected " + featureVectors.size() + " featureVectors.");

        UserProfile userProfile = EnrollmentService.enroll(featureVectors, THRESHOLD);

        System.out.println("User profile created successfully.");
        System.out.println(userProfile);

        // ===== Verification Phase =====
        System.out.println("\n=== Verification Mode ===");
        String choice;

        do {
            System.out.println("\nA typing window will appear. Enter the password to verify.");

            List<KeyStroke> ks = KeystrokeCaptureService.capture(
                    password.length(),
                    "Enter password to verify"
            );

            String typed = KeyStrokeUtil.getTypedText(ks);

            if (!typed.equals(password)) {
                System.out.println("Incorrect password typed! Verification FAILED.");
            } else {
                FeatureVector inputFV = FeatureExtractionService.extract(ks);

                boolean isGenuine = VerificationService.verify(userProfile, inputFV);

                if (isGenuine) {
                    System.out.println("Verification SUCCESS: User accepted.");
                } else {
                    System.out.println("Verification FAILED: User rejected.");
                }
            }

            System.out.print("\nTry again? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();

        } while (choice.equals("yes") || choice.equals("y"));

        System.out.println("\nStopping verification. Goodbye!");
    }
}
