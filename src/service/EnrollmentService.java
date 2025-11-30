package service;

import models.FeatureVector;
import models.UserProfile;
import utils.Statistics;

import java.util.List;

public class EnrollmentService {
    public static UserProfile enroll(List<FeatureVector> featureVectors, double threshold) {
        List<Double>  meanVector, stdVector;

        meanVector = Statistics.computeMeans(featureVectors);
        stdVector = Statistics.computeStdDevs(featureVectors,  meanVector);

        return new UserProfile(meanVector, stdVector, threshold);
    }
}
