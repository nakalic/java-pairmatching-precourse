package pairmatching.domain;

import java.util.List;

public class MissionPair {
    private final Mission mission;
    private final List<String> result;

    public MissionPair(Mission mission, List<String> result) {
        this.mission = mission;
        this.result = result;
    }

    public Mission getMission() {
        return mission;
    }

    public List<String> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "MissionPair{" +
                "mission=" + mission +
                ", result=" + result +
                '}';
    }
}
