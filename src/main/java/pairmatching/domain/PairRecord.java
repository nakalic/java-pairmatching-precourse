package pairmatching.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PairRecord {
    private final Map<Level, List<MissionPair>> pairRecord;

    public PairRecord() {
        pairRecord = new EnumMap<>(Level.class);
        pairRecord.put(Level.LEVEL1, new ArrayList<>());
        pairRecord.put(Level.LEVEL2, new ArrayList<>());
        pairRecord.put(Level.LEVEL3, new ArrayList<>());
        pairRecord.put(Level.LEVEL4, new ArrayList<>());
        pairRecord.put(Level.LEVEL5, new ArrayList<>());
    }

    public void resetPairRecord() {
        pairRecord.keySet()
                .forEach(Level -> pairRecord.get(Level).clear());
    }

    public MissionPair getMissionPair(Level level, String name) {
        return pairRecord.get(level)
                .stream()
                .filter(missionPair -> missionPair.getMission().getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 페어 이력이 없습니다"));
    }

    public void put(Level level, MissionPair missionPair) {
        pairRecord.get(level).add(missionPair);
    }

}
