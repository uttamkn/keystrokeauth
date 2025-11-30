package models;

import java.util.List;

public class UserProfile {
    private final List<Double> meanVector;
    private final List<Double> stdVector;
    private final double threshold;

    public UserProfile(List<Double> meanVector, List<Double> stdVector, double threshold) {
        this.meanVector = meanVector;
        this.stdVector = stdVector;
        this.threshold = threshold;
    }

    public List<Double> getMeanVector() {
        return meanVector;
    }

    public List<Double> getStdVector() {
        return stdVector;
    }

    public double getThreshold() {
        return threshold;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "meanVector=" + meanVector +
                ", stdVector=" + stdVector +
                ", threshold=" + threshold +
                '}';
    }
}

