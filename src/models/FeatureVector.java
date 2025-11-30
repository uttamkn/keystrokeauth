package models;

import java.util.List;

public record FeatureVector(List<Double> dwellTimes, List<Double> flightTimes) {
    @Override
    public String toString() {
        return "FeatureVector{ dwellTimes=" + dwellTimes + ", flightTimes=" + flightTimes + " }";
    }
}

