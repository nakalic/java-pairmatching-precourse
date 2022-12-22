package pairmatching.domain;

public class Pair {
    private Course course;
    private Mission mission;

    public Pair(Course course, Mission mission) {
        this.course = course;
        this.mission = mission;
    }

    public boolean compareCourse(Pair pair) {
        return this.course == pair.course;
    }

    //오버로딩?
    public boolean compareCourse(Course course) {
        return this.course == course;
    }

    public boolean compareMissionLevel(Pair pair) {
        return this.mission.getLevel() == pair.mission.getLevel();
    }
}
