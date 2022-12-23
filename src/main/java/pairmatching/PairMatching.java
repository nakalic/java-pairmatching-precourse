package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.MissionPair;
import pairmatching.domain.PairRecord;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatching {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        PairRecord backEndPairRecord = new PairRecord();
        PairRecord frontEndPairRecord = new PairRecord();

        while (true) {
            String selectFeature = inputView.getFeature();
            if (selectFeature.equals("1")) {
                String[] userInput = inputView.getPairBackgroundInfo();
                if (userInput[0].equals("백엔드")) {
                    makeBackMissionPair(backEndPairRecord,userInput[2],0);
                }
                if (userInput[0].equals("프론트엔드")) {
                    makeFrontMissionPair(frontEndPairRecord,userInput[2],0);
                }
            }
            if (selectFeature.equals("2")) {
                inquireRecord(backEndPairRecord, frontEndPairRecord);
            }
            if (selectFeature.equals("3")) {
                backEndPairRecord.resetPairRecord();
                frontEndPairRecord.resetPairRecord();
            }
            if (selectFeature.equals("Q")) {
                break;
            }
        }
    }


    // 메소드 2개?
    private void makeBackMissionPair(PairRecord pairRecord, String mission, int count) {
        if (count == 3)
            throw new IllegalArgumentException("3회 이상 매칭 중복이 발생하였습니다.");
        try {
            MissionPair missionpair = new MissionPair(makeMission(mission), getRandomCrewNames("백엔드"));
            System.out.println("missionPair : " +missionpair);
            pairRecord.addPair(missionpair);
        } catch (IllegalArgumentException e) {
            makeBackMissionPair(pairRecord, mission, count);
        }
    }

    private void makeFrontMissionPair(PairRecord pairRecord, String mission, int count) {
        if (count == 3)
            throw new IllegalArgumentException("3회 이상 매칭 중복이 발생하였습니다.");
        try {
            MissionPair missionpair = new MissionPair(makeMission(mission), getRandomCrewNames("프론트엔드"));
            pairRecord.addPair(missionpair);
        } catch (IllegalArgumentException e) {
            makeFrontMissionPair(pairRecord, mission, count);
        }
    }

    private void inquireRecord(PairRecord backEndPairRecord, PairRecord frontEndPairRecord) {
        String[] userInput = inputView.getPairBackgroundInfo();
        if (userInput[0].equals("백엔드")) {
            outputView.printPairResult(backEndPairRecord.getMissionPair(makeLevel(userInput[1]), userInput[2]));
        }
        if (userInput[0].equals("프론트엔드")) {
            outputView.printPairResult(frontEndPairRecord.getMissionPair(makeLevel(userInput[1]), userInput[2]));
        }
    }

    private List<String> getRandomCrewNames(String userInput) {
        if (userInput.equals("백엔드")) {
            return new ArrayList<>(Randoms.shuffle(readCrewName("src/main/resources/backend-crew.md")));
        }

        //구조 이상.
        return new ArrayList<>(Randoms.shuffle(readCrewName("src/main/resources/frontend-crew.md")));
    }

    private List<String> readCrewName(String fileName) {
        List<String> names = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String name;
            while ((name = reader.readLine()) != null) {
                names.add(name);
            }
            return names;
        } catch (Exception exception) {
            throw new IllegalArgumentException("파일을 읽는 중 에러가 발생했습니다.");
        }
    }

    private Level makeLevel(String level) {
        if (level.equals("레벨1")) {
            return Level.LEVEL1;
        }
        if (level.equals("레벨2")) {
            return Level.LEVEL2;
        }
        if (level.equals("레벨3")) {
            return Level.LEVEL3;
        }
        if (level.equals("레벨4")) {
            return Level.LEVEL4;
        }
        return Level.LEVEL5;
    }

    private Mission makeMission(String mission) {
        if (mission.equals("자동차경주")) {
            return Mission.CAR_RACING;
        }
        if (mission.equals("로또")) {
            return Mission.LOTTO;
        }
        if (mission.equals("숫자야구게임")) {
            return Mission.BASEBALL;
        }
        if (mission.equals("장바구니")) {
            return Mission.SHOPPING_CART;
        }
        if (mission.equals("결제")) {
            return Mission.PAYMENT;
        }
        if (mission.equals("지하철노선도")) {
            return Mission.SUBWAY;
        }
        if (mission.equals("성능개선")) {
            return Mission.PERFORMANCE_IMPROVEMENT;
        }
        return Mission.RELEASE;
    }
}
