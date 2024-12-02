import java.util.HashSet;
import java.util.Set;

public class RankTest {

    public static void main(String[] args) {
        mainRunTests();
    }

    private static void mainRunTests() {
        System.out.println("Testing Royal Flush:");
        testRoyalFlush(new int[]{8, 9, 10, 11, 12}, true);    // Clubs (10, J, Q, K, A)
        testRoyalFlush(new int[]{21, 22, 23, 24, 25}, true);   // Diamonds (10, J, Q, K, A)
        testRoyalFlush(new int[]{34, 35, 36, 37, 38}, true);   // Hearts (10, J, Q, K, A)
        testRoyalFlush(new int[]{47, 48, 49, 50, 51}, true);   // Spades (10, J, Q, K, A)
        testRoyalFlush(new int[]{0, 1, 2, 3, 4}, false);       // Clubs (A, 2, 3, 4, 5)

        System.out.println("\nTesting Straight Flush:");
        testStraightFlush(new int[]{4, 5, 6, 7, 8}, true);      // Clubs (5, 6, 7, 8, 9)
        testStraightFlush(new int[]{17, 18, 19, 20, 21}, true); // Diamonds (7, 8, 9, 10, J)
        testStraightFlush(new int[]{39, 40, 41, 42, 43}, true); // Spades (2, 3, 4, 5, 6)
        testStraightFlush(new int[]{44, 45, 46, 47, 48}, true); // Spades (6, 7, 8, 9, 10)
        testStraightFlush(new int[]{9, 10, 11, 12, 0}, false);  // Clubs (J, Q, K, A, Ace)

        System.out.println("\nTesting Four of a Kind:");
        testFourOfAKind(new int[]{1, 14, 27, 40, 11}, true);   // All Twos
        testFourOfAKind(new int[]{12, 25, 38, 51, 9}, true);   // All Kings
        testFourOfAKind(new int[]{0, 13, 26, 1, 14}, false);   // Full House

        System.out.println("\nTesting Full House:");
        testFullHouse(new int[]{0, 13, 26, 1, 14}, true);      // Three Aces, Two Twos
        testFullHouse(new int[]{10, 23, 36, 11, 24}, true);    // Three Jacks, Two Queens
        testFullHouse(new int[]{2, 15, 28, 4, 18}, false);    // Three of a Kind

        System.out.println("\nTesting Flush:");
        testFlush(new int[]{0, 3, 6, 9, 12}, true);            // Clubs (A, 4, 7, 10, K)
        testFlush(new int[]{30, 33, 36, 39, 42}, true);        // Hearts (4, 7, 10, K, 2)
        testFlush(new int[]{1, 14, 27, 40, 3}, false);         // Mixed Suit

        System.out.println("\nTesting Straight:");
        testStraight(new int[]{1, 2, 3, 4, 5}, true);          // A, 2, 3, 4, 5 (Wheel)
        testStraight(new int[]{9, 10, 11, 12, 8}, true);       // 10, J, Q, K, A
        testStraight(new int[]{12, 0, 1, 2, 3}, false);        // A, 2, 3, 4, K

        System.out.println("\nTesting Three of a Kind:");
        testThreeOfAKind(new int[]{2, 15, 28, 4, 18}, true);   // Three Threes, plus two other cards
        testThreeOfAKind(new int[]{9, 22, 35, 5, 8}, true);    // Three Tens, plus two other cards
        testThreeOfAKind(new int[]{6, 19, 3, 29, 7}, false);  // Two pairs

        System.out.println("\nTesting Two Pair:");
        testTwoPair(new int[]{0, 13, 1, 14, 9}, true);         // Aces and Twos
        testTwoPair(new int[]{3, 16, 6, 19, 8}, true);         // Fours and Sevens
        testTwoPair(new int[]{3, 16, 1, 2, 5}, false);       // Only one pair (Fours)

        System.out.println("\nTesting One Pair:");
        testOnePair(new int[]{2, 15, 4, 18, 11}, true);        // Pair of Threes
        testOnePair(new int[]{6, 19, 8, 22, 35}, true);        // Pair of Tens
        testOnePair(new int[]{0, 3, 6, 9, 11}, false);         // No pair

        System.out.println("\nTesting High Card:");
        testHighCard(new int[]{5, 8, 11, 24, 47}, true);       // High Card
        testHighCard(new int[]{1, 17, 30, 38, 45}, true);      // High Card

        System.out.println("\nFinished tests");
    }

    private static void testRoyalFlush(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        RoyalFlush hand = new RoyalFlush();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testStraightFlush(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        StraightFlush hand = new StraightFlush();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testFourOfAKind(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        FourOfAKind hand = new FourOfAKind();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testFullHouse(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        FullHouse hand = new FullHouse();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testFlush(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        Flush hand = new Flush();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testStraight(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        Straight hand = new Straight();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testThreeOfAKind(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        ThreeOfAKind hand = new ThreeOfAKind();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testTwoPair(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        TwoPair hand = new TwoPair();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testOnePair(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        OnePair hand = new OnePair();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void testHighCard(int[] testArray, boolean expected) {
        Set<Integer> testSet = new HashSet<>();
        addSet(testSet, testArray);
        HighCard hand = new HighCard();
        hand.determine(testSet);
        System.out.println("Expected: " + expected + ", Actual: " + hand.isFound);
    }

    private static void addSet(Set<Integer> set, int[] array) {
        for (int i : array) {
            set.add(i);
        }
    }
}
