package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class InputView {
    public String getFeature() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");
        String userInput = Console.readLine();
        System.out.println();
        try {
            if (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("Q")) {
                throw new IllegalArgumentException("[ERROR] 잘못된 기능 선택입니다.\n");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            getFeature();
        }
        return userInput;
    }

    public String[] getPairBackgroundInfo() {
        System.out.println("#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션:");
        System.out.println("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5:");
        System.out.println("############################################");
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
        String userInput = Console.readLine();
        System.out.println();
        try {
            if (!Pattern.matches("[가-힣]{3,}, [가-힣1-5]{3}, [가-힣]{2,}", userInput)) {
                throw new IllegalArgumentException("[ERROR] 입력 양식이 올바르지 않습니다.\n");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            getPairBackgroundInfo();
        }
        return userInput.split(", ");
    }

    public String getTryMore() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
        String userInput = Console.readLine();
        System.out.println();
        try {
            if (!userInput.equals("네") && !userInput.equals("아니오")) {
                throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.\n");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            getTryMore();
        }
        return userInput;
    }
}
