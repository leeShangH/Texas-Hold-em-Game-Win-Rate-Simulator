import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TwoPair extends Rank {

    //consists of two cards of equal value, another two cards of equal value, and one extra card. AA BB C
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        final int SameOfTwo = 2;
        List<int[]> sameOfaKindArrayList = new ArrayList<>(); // save 2-same
        Set<Integer> hasRank = new HashSet<>();               // save existed rank in arraylist


        getAllSize(handSet, hasRank, SameOfTwo, sameOfaKindArrayList);

        // we have at least two 2-same AA BB
        if (hasRank.size() < 2){ return; }
        isFound = true;

        // if we have more than two of 2-same AA BB CC DD
        int size = sameOfaKindArrayList.size(); //how many 2-same
        int firstSet = 0;
        int secondSet = 0;

        for (int i=1; i<size; i++){
            if (biggerRank(getRank(sameOfaKindArrayList.get(i)[0]), getRank(sameOfaKindArrayList.get(firstSet)[0]))){
                firstSet = i;
            }
        }

        if (firstSet == 0){secondSet++;}
        for (int i=1; i<size; i++){
            if (i == firstSet){continue;}

            if (biggerRank(getRank(sameOfaKindArrayList.get(i)[0]), getRank(sameOfaKindArrayList.get(secondSet)[0]))){
                    secondSet = i;
                }
        }

        int j = 0;
        for (int i : sameOfaKindArrayList.get(firstSet)){
            foundList[j] = i;
            j++;
        }
        for (int i : sameOfaKindArrayList.get(secondSet)){
            foundList[j] = i;
            j++;
        }
        foundList[4] = getOneBiggestSide(handSet, hasRank);

    }

    private void getAllSize (Set<Integer> handSet, Set<Integer> hasRank, int SameOfN, List<int[]> sameOfaKindArrayList){
        for (int current : handSet){                    // find all possible of same n set
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
        return "Two Pair";
    }

    @Override
    public int setRanking() {
        return 7;
    }



}


