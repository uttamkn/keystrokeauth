package models;

import java.util.ArrayList;
import java.util.List;

public record FeatureVector(List<Double> dwellTimes, List<Double> flightTimes) {

    public List<Double> allFeatures() {
        List<Double> combinedFeatures = new ArrayList<>();
        combinedFeatures.addAll(dwellTimes);
        combinedFeatures.addAll(flightTimes);
        return combinedFeatures;
    }

    public int size() {
        return dwellTimes.size() +  flightTimes.size();
    }

    @Override
    public String toString() {
        return "FeatureVector{ dwellTimes=" + dwellTimes + ", flightTimes=" + flightTimes + " }";
    }
}

