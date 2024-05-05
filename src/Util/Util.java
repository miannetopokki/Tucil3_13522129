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

    public static void timer(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static boolean isValid(String sh) {
        if (sh.trim().isEmpty()) { // Periksa apakah string kosong atau hanya berisi spasi
            return false;
        }
        for (int i = 0; i < sh.length(); i++) {
            if (sh.charAt(i) < 0 || sh.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }
}
