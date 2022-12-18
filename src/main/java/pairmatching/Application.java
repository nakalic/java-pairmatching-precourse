package pairmatching;

public class Application {
    public static void main(String[] args) {
        try {
            PairMatching pairMatching = new PairMatching();
            pairMatching.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
