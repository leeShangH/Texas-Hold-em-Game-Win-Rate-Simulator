import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flush extends Rank {

    // A flush is a hand which has all cards of the same suit.
    @Override
    public void determine(Set<Integer> handSet){
        List<Set<Integer>> suitArrayList = new ArrayList<>(); //corresponding to suit c d h s
        for (int i=0; i<4; i++) {
            suitArrayList.add(new HashSet<>());
        }
        int[] suitCount = new int[4];

        for (int current : handSet){   //place each hand to corresponding suit list
            int currentSuit = getSuit(current);
            suitArrayList.get(currentSuit).add(current);
            suitCount[currentSuit]++;
        }

        for (int i = 0; i < 4; i++){
            if (suitCount[i] >= 5){
                isFound = true;
                int positionLeft = 5;
                int index = 4;


                for (int hand: suitArrayList.get(i)){
                    if (positionLeft == 0) { break; }

                    if (getRank(hand) == 0){ //place Ace at first
                        foundList[0] = hand;
                        positionLeft--;
                    }else {
                        foundList[index] = hand;
                        index--;
                        positionLeft--;
                    }

                }

                break;

            }
        }
    }




    @Override
    public String setName() {
        return "Flush";
    }

    @Override
    public int setRanking() {
        return 4;
    }

}
