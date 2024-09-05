To solve the problem of finding duplicate elements in an array, you can indeed use nested loops as hinted, but a more efficient approach can be implemented using a hash set. Below, I'll provide two different implementations: one using nested loops as per the hint and another using a more efficient approach with hash sets.

### 1. Using Nested Loops

Here's a simple approach using nested loops:

```java
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    public static int[] findDuplicates(int[] array) {
        List<Integer> duplicates = new ArrayList<>();
        
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j] && !duplicates.contains(array[i])) {
                    duplicates.add(array[i]);
                }
            }
        }
        
        // Convert List<Integer> to int[]
        int[] result = new int[duplicates.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = duplicates.get(i);
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 2, 4, 3};
        int[] array2 = {5, 5, 5, 5};
        
        int[] duplicates1 = findDuplicates(array1);
        int[] duplicates2 = findDuplicates(array2);
        
        System.out.println(java.util.Arrays.toString(duplicates1)); // Output: [2, 3]
        System.out.println(java.util.Arrays.toString(duplicates2)); // Output: [5]
    }
}
```

### 2. Using Hash Sets (More Efficient)

A more efficient way to find duplicates is by using hash sets. This method has a better time complexity compared to nested loops.

```java
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    public static int[] findDuplicates(int[] array) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        
        for (int num : array) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }
        
        // Convert Set<Integer> to int[]
        int[] result = new int[duplicates.size()];
        int index = 0;
        for (int num : duplicates) {
            result[index++] = num;
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 2, 4, 3};
        int[] array2 = {5, 5, 5, 5};
        
        int[] duplicates1 = findDuplicates(array1);
        int[] duplicates2 = findDuplicates(array2);
        
        System.out.println(java.util.Arrays.toString(duplicates1)); // Output: [2, 3]
        System.out.println(java.util.Arrays.toString(duplicates2)); // Output: [5]
    }
}
```

### Explanation:

1. **Nested Loops Approach:**
    - This approach uses two loops to compare each element with every other element.
    - It adds an element to the duplicates list if it's found more than once and hasn't been added before.

2. **Hash Set Approach:**
    - It uses a `HashSet` to track elements we've seen so far.
    - If an element is already in the `seen` set, it’s added to the `duplicates` set.
    - This method is more efficient with a time complexity of \(O(n)\), where \(n\) is the number of elements in the array, compared to \(O(n^2)\) for the nested loops method.

