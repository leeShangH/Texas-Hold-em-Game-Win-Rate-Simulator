import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullHouse extends Rank {

    //consists of three cards of one value and two cards of another 3 + 2
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        final int SameOfThree = 3;
        final int SameOfTwo = 2;
        List<int[]> sameOfaKindArrayList = new ArrayList<>(); // save both 3-same and 2-same
        Set<Integer> hasRank = new HashSet<>();               // save existed rank in arraylist


        getAllSize(handSet, hasRank, SameOfThree, sameOfaKindArrayList);

        // we have at least one 3-same AAA
        if (hasRank.isEmpty()){return;}

        // if we have two of 3-same AAA BBB
        if (hasRank.size() == 2){
            isFound = true;
            int lead = (biggerRank(getRank(sameOfaKindArrayList.get(0)[0]), getRank(sameOfaKindArrayList.get(1)[0]))) ? 0:1;
            int side = (lead == 0) ? 1:0;

            int j = 0;
            for (int i : sameOfaKindArrayList.get(lead)){
                foundList[j] = i;
                j++;
            }
            foundList[3] = sameOfaKindArrayList.get(side)[0];
            foundList[4] = sameOfaKindArrayList.get(side)[1];
            return;
        }

        getAllSize(handSet, hasRank, SameOfTwo, sameOfaKindArrayList);

        // if we have at least one 3-same and one 2-same AAA BB
        if (hasRank.size() >= 2){
            isFound = true;
            int side = 1;
            int j = 0;

            for (int i : sameOfaKindArrayList.get(0)){
                foundList[j] = i;
                j++;
            }

            if (hasRank.size() == 3){ //one 3-same and two 2-same AAA BB CC
                side = (biggerRank(getRank(sameOfaKindArrayList.get(1)[0]), getRank(sameOfaKindArrayList.get(2)[0]))) ? 1:2;
            }

            foundList[3] = sameOfaKindArrayList.get(side)[0];
            foundList[4] = sameOfaKindArrayList.get(side)[1];
        }

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

    @Override
    public String setName() {
        return "Full House";
    }

    @Override
    public int setRanking() {
        return 3;
    }



}


