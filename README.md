# Homework 1: Valley Traveler (25 points)

## :sparkles: Lore: The First Valley Explorers

In the land of Numerica, travelers seek the legendary First Valley—the initial dip in the terrain that holds untold treasures :moneybag::moneybag::moneybag:. As a master cartographer, you've been tasked with creating a magical map called `ValleyTraveler` that identifies these prized valleys in the ever-changing landscape and modifies them when sudden changes occur. The Data Lords of Numerica have left treasure in valleys. Upon excavating a valley, you, the cartographer collect these mysterious rewards. 

## :scroll: Problem Description

Implement a Java class called `ValleyTraveler` that operates on an array of distinct integers (representing the landscape of Numerica). The class should perform the following operations:

1. **Constructor:**  
   `ValleyTraveler(int[] landscape)` Initializes the magical map with the given landscape of Numerica.

2. **`getFirst()`:**  Locates the first valley point in the current landscape of Numerica and returns the associated treasure.  

3. **`remove()`:**  Excavates (removes) the first valley point from the current landscape and returns the associated treasure. This operation updates the landscape and its hidden treasures accordingly. 

4. **`insert(int height)`:**  Creates a new landform with the given height *at the position where the first valley was just removed*. This operation updates the landscape and its hidden treasures accordingly.

5. **`isEmpty()`:**  Returns `true` if the entire landscape is excavated (i.e., there are no landforms left), and `false` otherwise.

6. **`getTotalTreasure()`:**  Returns the current total treasure that has been "collected" through successive `remove()` operations.

### Valley Point Definition

A valley point is defined as a landform that is lower than its neighboring landforms. For example, in the landscape `[5, 2, 8, 6, 3, 9, 4]`, the valley points are `[2, 3, 4]`.

More formally, let $A$ be the 0-indexed array of $n$ distinct integers, and $A_{i}$ be the element at index $i$. An element $A_{i}$ is a valley point if and only if:

- For $n = 1$: $A_{0}$ is a valley point  
- For $i = 0$: $A_{i} < A_{i+1}$  
- For $i = n-1$: $A_{i} < A_{i-1}$  
- For $0 < i < n-1$: $A_{i} < A_{i-1}$ and $A_{i} < A_{i+1}$

### Treasure Definition

Let the landscape be represented by a 0-indexed array $A$ of $n$ distinct integers, and suppose a valley point is identified at index $i$ (where $0 \leq i < n$). The treasure $T_{i}$ associated with this valley point is given by:

$$
T_{i} = \frac{1}{i+1} \sum_{j=0}^{i} A_j
$$

## :briefcase: Requirements

1. **Implementation File:**  
   Implement the `ValleyTraveler` class in `ValleyTraveler.java`.

2. **Constructor:**  
   The class should have a constructor that takes an array of distinct integers (the initial landscape).

3. **Methods:**  
   Implement the following methods:
   - `getFirst()`
   - `remove()`
   - `insert(int height)`
   - `isEmpty()`
   - `getTotalTreasure()`
   Handle edge cases and maintain the landscape's integrity after operations.

4. **State Management:**  
   The provided array should not be modified directly. Instead, the class must maintain its internal state to reflect any changes made by `remove()` and `insert()` operations.

5. **Restrictions:**  
   You are **not** allowed to use Java standard collections (e.g., `ArrayList`, `HashMap`, `HashSet`, etc.). Only primitive data types and arrays (e.g., `int[]`) are allowed. You may implement your own data structure(s) if necessary.

## :footprints: Example Expedition

Consider the following initial landscape: `[3, 2, 1, 4, 5]`.

1. `getFirst()` returns `2.0` (First treasure located)

   **Terrain**: `[3, 2, 1, 4, 5]`

2. `remove()` returns `2.0` (First valley excavated, treasure collected)

   **Terrain**: `[3, 2, 4, 5]`

3. `insert(6)` (New landform created at current first valley)

   **Terrain**: `[3, 6, 2, 4, 5]`

4. `remove()` returns `3.0` (Valley excavated, treasure collected)

   **Terrain**: `[6, 2, 4, 5]`

5. `remove()` returns `4.0` (Valley excavated, treasure collected)

   **Terrain**: `[6, 4, 5]`

6. `remove()` returns `5.0` (Valley excavated, treasure collected)

   **Terrain**: `[6, 5]`

7. `getTotalTreasure()` returns `14.0` 

## :envelope: Submission Details

Submit your `ValleyTraveler.java` file and any additional Java files (if created) through Gradescope.

- **Submission link:** 
- **Deadline:** Feb 16th, 2025 at 11:59 PM EST.
- **Late Submission Policy:** Late submissions will not be accepted.

## :bar_chart: Evaluation

### Criteria

- __Correctness:__ Does the implementation correctly identify and modify valley points in the landscape?
- __Efficiency:__ Does the implementation have a reasonable time and space complexity?

### Procedure

- __Initialization:__ The `ValleyTraveler` object is initialized with an arbitrary array of distinct integers, representing the initial landscape.
- __Operations:__ The three methods `getFirst()`, `remove()`, `insert(int height)`, and `getTotalTreasure()` can be invoked in any arbitrary order.
- __Assumptions:__ It is guaranteed that invalid invocations will not occur. Specifically, `remove()` or `getFirst()` will not be called on an empty landscape. Also, at any points, the heights of all the landforms in the landscape are guaranteed to be distinct.   
- __Time Complexity:__ Given an initial landscape with $N$ landforms and $Q$ queries (operations), the entire procedure should be completed in $O(N+Q)$ time complexity.
- __Testing:__ We will test your implementation with various test cases, each containing different landscapes and operations, and based on how many and which test cases your implementation passes, we will assign you a score between 0 and 25 points. 

## :rocket: Starter Code

Begin your expedition with the provided [`ValleyTraveler.java`](ValleyTraveler.java) file.

> **Note:** The starter code is provided as a guideline. You may modify or completely replace it as long as your final implementation meets all the requirements.

### Evaluation Script

A script similar to [`Evaluator.java`](Evaluator.java) will be used to evaluate your implementation. To run the evaluation script locally:

```bash
# Ensure Java is installed on your computer
java -version

# Navigate to the problems/hw1 directory
cd problems/hw1

# Compile the Evaluator.java file along with ValleyTraveler.java
javac Evaluator.java ValleyTraveler.java

# Run the Evaluator with a provided sample test case
java Evaluator sample_tc.txt

# Feel free to create and run your own test cases
