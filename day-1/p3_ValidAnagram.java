import java.util.HashMap;

public class p3_ValidAnagram {

    public static boolean checkValidAnagramOptimised(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] ch = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int index_s = s.charAt(i) - 'a';
            ch[index_s]++;
        }

        for (int i = 0; i < t.length(); i++) {
            int index_t = t.charAt(i) - 'a';
            ch[index_t]--;
        }

        for (int i = 0; i < 26; i++) {
            if (ch[i] != 0) return false;
        }

        return true;
    }

    public static boolean checkValidAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> mp = new HashMap<>();

        // first string
        for (int i = 0; i < s.length(); i++) {
            if (mp.containsKey(s.charAt(i))) {
                mp.put(s.charAt(i), mp.get(s.charAt(i)) + 1);
            } else {
                mp.put(s.charAt(i), 1);
            }
        }

        // go for next string
        for (int i = 0; i < t.length(); i++) {
            if (mp.containsKey(t.charAt(i))) {
                mp.put(t.charAt(i), mp.get(t.charAt(i)) - 1);
            } else {
                return false;
            }
        }

        for (char ch : mp.keySet()) {
            if (mp.get(ch) != 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "rat", t1 = "car";
        String s2 = "anagram", t2 = "nagaram";

        System.out.println("Ans1: " + checkValidAnagram(s1, t1));
        System.out.println("Ans2: " + checkValidAnagram(s2, t2));
    }
}
