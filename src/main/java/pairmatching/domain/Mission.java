package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    CAR_RACING("자동차경주"),
    LOTTO("로또"),
    BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"),
    PAYMENT("결제"),
    SUBWAY("지하철노선도"),
    PERFORMANCE_IMPROVEMENT("성능개선"),
    RELEASE("배포");

    private final String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission findByName(String name) {
        return Arrays.stream(Mission.values())
                .filter(mission -> mission.checkName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 미션을 찾을 수 없습니다."));
    }

    private boolean checkName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
