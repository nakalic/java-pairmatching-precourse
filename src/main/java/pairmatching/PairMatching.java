package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import pairmatching.domain.Course;
import pairmatching.domain.Info;
import pairmatching.domain.Mission;
import pairmatching.domain.PairHistory;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatching {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        PairHistory pairHistory = new PairHistory();
        while (true) {
            String selectedFeature = inputView.getFeature();
            if (selectedFeature.equals("Q")) {
                break;
            }
            if (selectedFeature.equals("1")) {
                matchPair(pairHistory);
            }
            if (selectedFeature.equals("2")) {
                inquirePair(pairHistory);
            }
            if (selectedFeature.equals("3")) {
                resetPair(pairHistory);
            }
        }
    }

    private void matchPair(PairHistory pairHistory) {
        Info info = getPairInfo();
        if (pairHistory.isExist(info) && inputView.getTryMore().equals("아니오")) {
            return;
        }
        try {
            addNewPair(pairHistory, info, 0);
            outputView.printPairResult(pairHistory.getPair(info));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            matchPair(pairHistory);
        }
    }

    private void addNewPair(PairHistory pairHistory, Info info, int count) {
        if (count == 3) {
            throw new IllegalArgumentException("3회 이상 매칭 중복이 발생하였습니다.");
        }
        try {
            if (info.compareCourse(Course.BACKEND)) {
                pairHistory.addPair(info, getRandomCrewNames(Course.BACKEND.getEnglishName()));
            }
            if (info.compareCourse(Course.FRONTEND)) {
                pairHistory.addPair(info, getRandomCrewNames(Course.FRONTEND.getEnglishName()));
            }
        } catch (IllegalArgumentException e) {
            addNewPair(pairHistory, info, count + 1);
        }
    }

    private Info getPairInfo() {
        String[] pairBackgroundInfo = inputView.getPairBackgroundInfo();
        Course course = Course.findByName(pairBackgroundInfo[0]);
        Mission mission = Mission.findByName(pairBackgroundInfo[2]);
        return new Info(course, mission);
    }

    private void inquirePair(PairHistory pairHistory) {
        Info info = getPairInfo();
        try {
            outputView.printPairResult(pairHistory.getPair(info));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            inquirePair(pairHistory);
        }
    }

    private void resetPair(PairHistory pairHistory) {
        pairHistory.resetPair();
        System.out.println("초기화 되었습니다.");
    }

    private List<String> getRandomCrewNames(String crewCourse) {
        String filePath = "src/main/resources/" + crewCourse + "-crew.md";
        return new ArrayList<>(Randoms.shuffle(readCrewName(filePath)));
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
}
