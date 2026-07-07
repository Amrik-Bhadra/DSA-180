import java.util.HashSet;

public class p2_ContainsDuplicate {
    public static boolean containsDuplicateBruteForce(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] == arr[j]) return true;
            }
        }

        return false;
    }
    
    public static boolean containsDuplicateOptimised(int[] arr) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if(hs.contains(arr[i])){
                return true;
            }else{
                hs.add(arr[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 3, 1 };
        int[] arr2 = { 1, 2, 3, 4 };
        int[] arr3 = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };

        System.out.println("Ans1: " + containsDuplicateOptimised(arr1));
        System.out.println("Ans2: " + containsDuplicateOptimised(arr2));
        System.out.println("Ans3: " + containsDuplicateOptimised(arr3));
    }
}
