import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ThreeOfAKind extends Rank {

    //three of a kind is 3 cards of the same value and 2 side cards of different values. AAA B C
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        final int SameOfThree = 3;
        List<int[]> sameOfaKindArrayList = new ArrayList<>(); // save 3-same
        Set<Integer> hasRank = new HashSet<>();               // save existed rank in arraylist

        getAllSize(handSet, hasRank, SameOfThree, sameOfaKindArrayList);

        // we have at least one 3-same AAA
        if (hasRank.isEmpty()){return;}

        isFound = true;
        int side = 1;
        int j = 0;

        for (int i : sameOfaKindArrayList.getFirst()){
            foundList[j] = i;
            j++;
        }


        foundList[3] = getOneBiggestSide(handSet, hasRank);
        foundList[4] = getOneBiggestSide(handSet, hasRank);

    }

    private void getAllSize (Set<Integer> handSet, Set<Integer> hasRank, int SameOfN, List<int[]> sameOfaKindArrayList){
        for (int current : handSet){
            // find all possible of same n set
            int currentRank = getRank(current);
            if (hasRank.contains(currentRank)){
                continue;
            }

            int[] sameRank = {currentRank, currentRank + 13, currentRank + 26, currentRank + 39};
            int[] SameOfNList = new int[SameOfN];

            findSameOfN(SameOfN, handSet, sameRank, SameOfNList);
            if (SameOfNList[SameOfN-1] == 0){
                continue;
            } else {
                hasRank.add(currentRank);
                sameOfaKindArrayList.add(SameOfNList);
            }

        }
    }

    private void findSameOfN(int SameOfN, Set<Integer> handSet, int[] sameRank, int[] SameOfNList){
        int missCount = 0;
        int position = 0;

        for (int i: sameRank){
            if (missCount > 4 - SameOfN) {
                return;
            }

            if (handSet.contains(i)){
                SameOfNList[position] = i;
                position++;
            } else {
                missCount++;
            }
        }
    }


    private int getOneBiggestSide(Set<Integer> handSet, Set<Integer> hasRank){
        int temp = -1;
        int tempRank = -1;
        for (int hand: handSet){
            int handRank = getRank(hand);
            if (hasRank.contains(handRank)){
                continue;
            } else {
                if (biggerRank(handRank, tempRank)){
                    temp = hand;
                    tempRank = handRank;
                }
            }
        }

        hasRank.add(tempRank); // record the biggest side
        return temp;
    }

    @Override
    public String setName() {
        return "Three of a Kind";
    }

    @Override
    public int setRanking() {
        return 6;
    }



}

