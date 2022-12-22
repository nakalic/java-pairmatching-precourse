package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairHistory {
    public Map<Info, List<String>> pairHistory;

    public PairHistory() {
        pairHistory = new HashMap<>();
    }

    public boolean isExist(Info info) {
        return pairHistory.get(info) != null;
    }

    public void addPair(Info info, List<String> crews) {
        for (Info key : pairHistory.keySet()) {
            if (key.compareCourse(info) && key.compareMissionLevel(info)) {
                checkDuplicateMatch(pairHistory.get(key), crews);
            }
        }
        pairHistory.put(info, crews);
    }

    private void checkDuplicateMatch(List<String> existCrews, List<String> newCrews) {
        for (int i = 0; i < existCrews.size() - 2; i += 2) {
            if (existCrews.get(i).equals(newCrews.get(i)) && existCrews.get(i + 1)
                    .equals(newCrews.get(i + 1))) {
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
        if (existCrews.get(existCrews.size() - 1).equals(newCrews.get(existCrews.size() - 1)) && existCrews.get(
                        existCrews.size() - 2)
                .equals(newCrews.get(existCrews.size() - 2)) && existCrews.get(existCrews.size() - 3)
                .equals(newCrews.get(existCrews.size() - 3))) {
            throw new IllegalArgumentException("매칭 중복이 발생하였습니다.");
        }
    }

    public List<String> getPair(Info info) {
        if (pairHistory.get(info) == null) {
            throw new IllegalArgumentException("해당 조건에 맞는 페어를 찾을 수 없습니다.");
        }
        return pairHistory.get(info);
    }

    public void resetPair() {
        pairHistory.clear();
    }
}
