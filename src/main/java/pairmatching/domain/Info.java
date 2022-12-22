package pairmatching.domain;

public class Info {
    private Course course;
    private Mission mission;

    public Info(Course course, Mission mission) {
        this.course = course;
        this.mission = mission;
    }

    public boolean compareCourse(Info info) {
        return this.course == info.course;
    }

    //오버로딩?
    public boolean compareCourse(Course course) {
        return this.course == course;
    }

    public boolean compareMissionLevel(Info info) {
        return this.mission.getLevel() == info.mission.getLevel();
    }
}
