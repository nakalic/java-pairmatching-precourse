package pairmatching.view;

import java.util.List;

public class OutputView {
    public void printPairResult(List<String> pair) {
        System.out.println("페어매칭 결과입니다");
        for (int i = 0; i < pair.size() - 3; i += 2) {
            System.out.println(pair.get(i) + " : " + pair.get(i + 1));
        }
        for (int i = 0; i < pair.size() - 1; i++) {
            System.out.print(pair.get(i) + " : ");
        }
        System.out.println(pair.get(pair.size() - 1));
        System.out.println();
    }
}
