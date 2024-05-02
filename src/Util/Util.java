package Util;

public class Util {
    private Util() {
    }

    public static int countHeuristic(String word1, String word2) {
        // prekondisi : panjang word1 == word2
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount;
    }

}
