package models;

import java.util.List;

public record UserProfile(List<Double> meanVector, List<Double> stdVector, double threshold) {
    @Override
    public String toString() {
        return "UserProfile{" +
                "meanVector=" + meanVector +
                ", stdVector=" + stdVector +
                ", threshold=" + threshold +
                '}';
    }
}

