package service;

import models.FeatureVector;
import models.KeyStroke;
import utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class FeatureExtractionService {
    public static FeatureVector extract(List<KeyStroke> keyStrokes) {
        List<Double> dwellTimes  = new ArrayList<>();
        List<Double> flightTimes = new ArrayList<>();

        KeyStroke prev_ks = null;
        for(KeyStroke ks : keyStrokes) {
            dwellTimes.add(TimeUtil.getDwellTime(ks));
            if(prev_ks != null) {
                flightTimes.add(TimeUtil.getFlightTime(prev_ks, ks));
            }
            prev_ks = ks;
        }

        return new FeatureVector(dwellTimes, flightTimes);
    }
}
