package models;

import java.util.List;

public class FeatureVector {
    private final List<Double> dwellTimes;
    private final List<Double> flightTimes;

    public FeatureVector(List<Double> dwellTimes, List<Double> flightTimes) {
        this.dwellTimes = dwellTimes;
        this.flightTimes = flightTimes;
    }

    public List<Double> getDwellTimes() {
        return dwellTimes;
    }

    public List<Double> getFlightTimes() {
        return flightTimes;
    }

    @Override
    public String toString() {
        return "FeatureVector{ dwellTimes=" + dwellTimes + ", flightTimes=" + flightTimes + " }";
    }
}

