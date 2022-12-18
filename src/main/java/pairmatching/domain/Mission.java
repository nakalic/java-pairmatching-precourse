package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    CAR_RACING("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    BASEBALL("숫자야구게임", Level.LEVEL1),
    SHOPPING_CART("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAY("지하철노선도", Level.LEVEL2),
    PERFORMANCE_IMPROVEMENT("지하철노선도", Level.LEVEL3),
    DEPLOYMENT("배포", Level.LEVEL3);

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
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

    public Level getLevel(){
        return this.level;
    }

    @Override
    public String toString() {
        return name;
    }
}
