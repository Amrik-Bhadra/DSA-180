import java.util.HashMap;

public class p1_TwoSum {
    public static int[] twoSumBruteForce(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(i == j) continue;
                
                if(arr[j] + arr[i] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
    }

    public static int[] twoSumOptimised(int[] arr, int target) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (mp.containsKey(target - arr[i])) {
                return new int[] { mp.get(target - arr[i]), i };
            } else {
                mp.put(arr[i], i);
            }
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] arr1 = { 3, 2, 4 };
        int target1 = 6; // output: [1,2]

        int[] arr2 = { 3, 3 };
        int target2 = 6; // output: [0,1]

        int[] arr3 = { 2, 7, 11, 15 };
        int target3 = 9; // output: [0,1]

        int ans1[] = twoSumOptimised(arr1, target1);
        System.out.println("Answer 1: " + "[" + ans1[0] + ", " + ans1[1] + "]");

        int ans2[] = twoSumOptimised(arr2, target2);
        System.out.println("Answer 2: " + "[" + ans2[0] + ", " + ans2[1] + "]");

        int ans3[] = twoSumOptimised(arr3, target3);
        System.out.println("Answer 3: " + "[" + ans3[0] + ", " + ans3[1] + "]");
    }
}