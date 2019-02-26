package ai;

public interface AI {
    String getGuess();
    void addShootResults(String hitAI, boolean resultBot);
    void removeAllDecisions();
}
