import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class p4_GroupAnagram {
    // method to create a string
    // example: "street" --> "e2r1s1t2"
    public static String createSignature(String str) {
        Map<Character, Integer> freqMap = new TreeMap<>();
        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (char key : freqMap.keySet()) {
            sb.append(key).append(freqMap.get(key));
        }

        return sb.toString();
    }

    // method to return groups of anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groupsMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String signature = createSignature(str);

            if (groupsMap.containsKey(signature)) {
                groupsMap.get(signature).add(str);
            } else {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                groupsMap.put(signature, newGroup);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (String key : groupsMap.keySet()) {
            result.add(groupsMap.get(key));
        }

        return result;
    }

    public static void display(List<List<String>> result) {
        for(int i=0; i<result.size(); i++){
            List<String> l = result.get(i);
            for(int j=0; j<l.size(); j++) {
                System.out.print(l.get(j) + " ");
            }System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};

        List<List<String>> result1 = groupAnagrams(strs1);
        List<List<String>> result2 = groupAnagrams(strs2);
        List<List<String>> result3 = groupAnagrams(strs3);

        System.out.println("Result 1:");
        display(result1);
        System.out.println("Result 2:");
        display(result2);
        System.out.println("Result 3:");
        display(result3);
    }
}
