package service;

import models.FeatureVector;
import models.UserProfile;
import utils.Statistics;

public class VerificationService {
    public static boolean verify(UserProfile userProfile, FeatureVector inputFV) {
        double z_score = Statistics.computeScore(userProfile.meanVector(), userProfile.stdVector(), inputFV);
        System.out.println("z_score: " + z_score);
        return z_score <= userProfile.threshold();
    }
}
