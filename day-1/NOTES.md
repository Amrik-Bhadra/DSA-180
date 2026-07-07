# Arrays & Hashing Foundations

## Why Hashing Exists?

Think about the most basic question you can ask about data:

- `"Have I seen this before?"`
- `"Where is this thing?"`

With a plain array, answering that means scanning — O(n) every single time. If you ask that question n times (e.g., for every element), you're at O(n²).

> Hashing's entire promise is: **trade some memory for near-instant lookup.**

A HashMap/HashSet gives you O(1) average-case insert, lookup, and delete. That's the whole trick — you're building a "cheat sheet" as you go, so instead of re-scanning, you just ask the cheat sheet.

---

## How it actually works ?

- A hash function converts your key (int, String, object) into an index in an underlying array (the "buckets").
- `map.put(key, value)` → computes `hash(key)` → drops it in that bucket.
- `map.get(key)` → computes `hash(key)` again → jumps straight to that bucket. No scanning.
- Collisions (two keys landing in the same bucket) are handled internally by chaining — you don't need to worry about this, just know it's why worst-case is technically O(n) but average-case is O(1).

```java
HashMap<Integer, Integer> map = new HashMap<>();   // key -> value (need to store *what* + *where/count*)
HashSet<Integer> set = new HashSet<>();            // just "have I seen this?" — no value needed
```

---

## Recognition Triggers

When you read a problem, your brain should flag hashing when you see:

| Phrase in problem                    | What it means                                                            |
| ------------------------------------ | ------------------------------------------------------------------------ |
| "find two numbers that add up to..." | You need to check existence of a complement fast → HashMap               |
| "duplicate", "seen before"           | You need existence check → HashSet                                       |
| "anagram", "same characters"         | You need a frequency signature to compare → HashMap<Char,Int> or int[26] |
| "most frequent", "top K"             | You need to count occurrences → HashMap<Value,Count>                     |
| "consecutive sequence" (unsorted)    | You need O(1) existence check to avoid sorting → HashSet                 |

---

## The Universal Logic-Building Framework

For almost every hashing problem, ask yourself these 3 questions in order:

1. What am I `storing as the key`? (a number? a character? a sorted string? a tuple?)
2. What am I `storing as the value`? (just presence? a count? an index? a list?)
3. What am I `checking on each pass` — before or after inserting? (This order matters a LOT — e.g., in Two Sum you check before inserting the current number, or you'd match a number with itself.)

---

## Question 1: Two Sum

> Arrays, HashMap | Easy

### Asnwers for logic building questions

- I am storing `array elements as key`
- I am storing `element's index number as value`
- on each pass will check `if the (sum - element) exists` in the hashmap or not, `before inserting`
  - if exists then we have found two numbers the add up to given sum
  - if does not exist the add the element in the HashMap, with hope later we will get an element whose complement is in hashmap

### Why not checking after inserting ?

Say target = 6 and you're at an element 3 (i.e., 3 + 3 = 6). If you inserted first and then checked, you'd search for target - 3 = 3 in the map... and find the 3 you just inserted — matching an element with itself. That's wrong, because the problem needs two distinct indices.
By checking before inserting, at the moment you're looking for 3's complement, only previously seen elements are in the map — so you can never accidentally pair an element with itself.

### Time Complexity and Space Complexity

- Time Complexity: `O(n)`

  > single pass, and each HashMap get/put is O(1) average case, so n iterations × O(1) work = O(n) overall.

- Space Complexity: `O(n)`
  > Worst case (e.g., if the answer pair is the last two elements checked, or doesn't exist at all), you'd insert almost every element into the map before finding a match or finishing the loop. So the map can grow up to size n.

### Sample I/O

- Input: nums = [2,7,11,15], target = 9; Output: [0,1]
- Input: nums = [3,2,4], target = 6; Output: [1,2]
- Input: nums = [3,3], target = 6; Output: [0,1]

### SOLUTION 1 -- Brute Force

> Time Complexity: O(n²) | Space Complexity: O(1) | using nested loop

```java
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
```

### Solution 2 -- Optimal Solution

> Time Complexity: O(n) | Space Complexity: O(n) | using HashMap

```java
public static int[] twoSumOptimised(int[] arr, int target) {
    HashMap<Integer, Integer> mp = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
        if(mp.containsKey(target - arr[i])){
            return new int[]{mp.get(target - arr[i]), i};
        }else{
            mp.put(arr[i], i);
        }
    }

    return new int[]{};
}
```

---

## Question 2: Contains Duplicate

> Arrays, HashSet | Easy

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

### Asnwers for logic building questions

- I am `storing array elements` in hashset
- Nothing stored as value as not using hashmap
- on each will check if `current array element already present` in hashset or not before inserting
    - if true, means that element appears twice
    - if no, then insert that value in hashset

### Why checking before inserting ?

Unlike Two Sum, the order genuinely doesn't create a correctness bug either way for this problem. Think about it — if you inserted first and then checked, you'd always find "yes, it exists" (since you just put it there), which would incorrectly flag every single element as a duplicate. So actually — order matters just as much here, just for a different reason: in Two Sum it was about avoiding self-pairing, here it's about avoiding "everything looks like a duplicate of itself."

### Time Complexity and Space Complexity

- Time Complexity: `O(n)`

  > single pass through the array, and each contains()/add() call on a HashSet is O(1) average case — so n iterations × O(1) = O(n).

- Space Complexity: `O(n)`
  > worst case (no duplicates at all, or the duplicate is the very last element) means you end up storing nearly all n elements in the set before finishing.

### Sample I/O

- Input: nums = [1,2,3,1] ; Output: true
- Input: nums = [1,2,3,4] ; Output: false
- Input: nums = [1,1,1,3,3,4,3,2,4,2] ; Output: true

### SOLUTION 1 -- Brute Force

> Time Complexity: O(n²) | Space Complexity: O(1) | using nested loop

```java
public static boolean containsDuplicateBruteForce(int[] arr) {
    for(int i=0; i<arr.length; i++) {
        for(int j=i+1; j<arr.length; j++){
            if(arr[i] == arr[j]) return true;
        }
    }

    return false;
}
```

### Solution 2 -- Optimal Solution

> Time Complexity: O(n) | Space Complexity: O(n) | using HashMap

```java
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
```

---

