package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드", "backend"),
    FRONTEND("프론트엔드", "frontend");

    private final String name;
    private final String englishName;

    Course(String name, String englishName) {
        this.name = name;
        this.englishName = englishName;
    }

    public static Course findByName(String name) {
        return Arrays.stream(Course.values())
                .filter(course -> course.checkName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 코스를 찾을 수 없습니다."));
    }

    private boolean checkName(String name) {
        return this.name.equals(name);
    }

    public String getEnglishName() {
        return englishName;
    }

    @Override
    public String toString() {
        return name;
    }
}
