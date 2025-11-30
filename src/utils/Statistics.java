package utils;

import models.FeatureVector;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    public static List<Double> computeMeans(List<FeatureVector> samples){
        int size = samples.getFirst().size();
        List<Double> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {result.add(0.0);}

        for (FeatureVector fv : samples) {
            int idx = 0;
            for(double dwellTime:  fv.dwellTimes()) {
                result.set(idx, (result.get(idx) + dwellTime));
                idx++;
            }

            for (double flightTime :  fv.flightTimes()) {
                result.set(idx, (result.get(idx) + flightTime));
                idx++;
            }
            result.replaceAll(aDouble -> aDouble / samples.size());
        }

        return result;
    }

    public static List<Double> computeStdDevs(List<FeatureVector> samples, List<Double> means) {
        int size = means.size();
        List<Double> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) result.add(0.0);

        for (FeatureVector fv : samples) {
            int idx = 0;
            for (double dwellTime : fv.dwellTimes()) {
                double diff = dwellTime - means.get(idx);
                result.set(idx, result.get(idx) + diff * diff);
                idx++;
            }
            for (double flightTime: fv.flightTimes()) {
                double diff = flightTime - means.get(idx);
                result.set(idx, result.get(idx) + diff * diff);
                idx++;
            }
        }

        for (int i = 0; i < size; i++) {
            result.set(i, Math.sqrt(result.get(i) / samples.size()));
        }

        return result;
    }

    public static double computeScore(List<Double> means, List<Double> stds, FeatureVector inputFV) {
        double eps = 1e-6;
        List<Double> values = inputFV.allFeatures();

        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            double z = Math.abs(values.get(i) - means.get(i))
                    / (stds.get(i) + eps);
            sum += z;
        }
        return sum / values.size();
    }
}
