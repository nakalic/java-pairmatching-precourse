package pairmatching.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                .filter(missionPair -> missionPair.getMissionName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 페어 이력이 없습니다"));
    }

    public void addPair(MissionPair newMissionPair) {
        checkDuplicateMatch(newMissionPair);
        pairRecord.get(newMissionPair.getMissionLevel()).add(newMissionPair);
    }

    public boolean isExist(Mission mission) {
        Optional<MissionPair> temp = pairRecord.get(mission.getLevel()).stream()
                .filter(missionPair -> missionPair.getMissionName().equals(mission.getName()))
                .findAny();
        return temp.isPresent();
    }

    // 검증 메소드 a,b / b,a 및 a : b: c 고려할 것
    public void checkDuplicateMatch(MissionPair newMissionPair) {
        if (pairRecord.get(newMissionPair.getMissionLevel()).isEmpty()) {
            return;
        }
        List<String> existCrews = this.getMissionPair(newMissionPair.getMissionLevel(),
                newMissionPair.getMissionName()).getResult();
        List<String> newCrews = newMissionPair.getResult();
        for (int i = 0; i < existCrews.size() - 2; i += 2) {
            if (existCrews.get(i).equals(newCrews.get(i)) && existCrews.get(i + 1)
                    .equals(newCrews.get(i + 1))) {
                throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
            }
            if (existCrews.get(i).equals(newCrews.get(i+1)) && existCrews.get(i+1)
                    .equals(newCrews.get(i))) {
                throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
            }
        }
        if (existCrews.size() % 2 == 0) {
            checkEvenNumberOfCrews(existCrews, newCrews);
        }
        if (existCrews.size() % 2 == 1) {
            checkOddNumberOfCrews(existCrews, newCrews);
        }
    }

    private void checkEvenNumberOfCrews(List<String> existCrews, List<String> newCrews) {
        if (existCrews.get(existCrews.size() - 1).equals(newCrews.get(existCrews.size() - 1)) && existCrews.get(
                        existCrews.size() - 2)
                .equals(newCrews.get(existCrews.size() - 2))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
    }

    private void checkOddNumberOfCrews(List<String> existCrews, List<String> newCrews) {
        if (existCrews.get(existCrews.size() - 3).equals(newCrews.get(existCrews.size() - 2)) && existCrews.get(
                        existCrews.size() - 2)
                .equals(newCrews.get(existCrews.size() - 3))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
        if (existCrews.get(existCrews.size() - 2).equals(newCrews.get(existCrews.size() - 3)) && existCrews.get(
                        existCrews.size() - 3)
                .equals(newCrews.get(existCrews.size() - 2))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
        if (existCrews.get(existCrews.size() - 2).equals(newCrews.get(existCrews.size() - 1)) && existCrews.get(
                        existCrews.size() - 1)
                .equals(newCrews.get(existCrews.size() - 2))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
        if (existCrews.get(existCrews.size() - 1).equals(newCrews.get(existCrews.size() - 2)) && existCrews.get(
                        existCrews.size() - 2)
                .equals(newCrews.get(existCrews.size() - 1))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
        if (existCrews.get(existCrews.size() - 3).equals(newCrews.get(existCrews.size() - 1)) && existCrews.get(
                        existCrews.size() - 1)
                .equals(newCrews.get(existCrews.size() - 3))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
        if (existCrews.get(existCrews.size() - 1).equals(newCrews.get(existCrews.size() - 3)) && existCrews.get(
                        existCrews.size() - 3)
                .equals(newCrews.get(existCrews.size() - 1))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
    }

    @Override
    public String toString() {
        return "PairRecord{" +
                "pairRecord=" + pairRecord +
                '}';
    }
}
