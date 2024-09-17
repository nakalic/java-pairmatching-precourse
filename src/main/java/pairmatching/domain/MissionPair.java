package pairmatching.domain;

import java.util.List;

public class MissionPair {
    private final Mission mission;
    private final List<String> result;

    public MissionPair(Mission mission, List<String> result) {
        this.mission = mission;
        this.result = result;
    }

    public Level getMissionLevel() {
        return mission.getLevel();
    }

    public String getMissionName() {
        return mission.getName();
    }

    public List<String> getResult() {
        return result;
    }
}
