import java.util.HashSet;
import java.util.Set;


public class HighCard extends Rank {

    //High card is when you have five cards that do not interact with each other to make any of the above hands. 2 4 6 8 13
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        Set<Integer> hasRank = new HashSet<>();               // save existed rank in arraylist
        foundList[0] = getOneBiggestSide(handSet, hasRank);
        foundList[1] = getOneBiggestSide(handSet, hasRank);
        foundList[2] = getOneBiggestSide(handSet, hasRank);
        foundList[3] = getOneBiggestSide(handSet, hasRank);
        foundList[4] = getOneBiggestSide(handSet, hasRank);
        isFound = true;

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
        return "High Card";
    }

    @Override
    public int setRanking() {
        return 9;
    }



}


