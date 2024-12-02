import java.util.Set;

public class Straight extends Rank {

    // A flush is a hand which has all cards of the same suit.
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        for (int current : handSet){

            int currentRank = getRank(current);
            if (showRight(handSet, currentRank) == -1) {  // the last one should not have right index
                continue;
            }
            int[] tempList = {current, -1, -1, -1, -1};
            int i = 1;
            int tempHand = showLeft(handSet, currentRank);

            if (currentRank == 0){  //find A K Q J 10
                tempHand = showLeft(handSet, 13 ); // find from K+1
                do {
                    if (tempHand == -1) {
                        break;
                    } else {
                        tempList[i] = tempHand;
                        tempHand = showLeft(handSet, currentRank - i);
                        i++;
                    }
                }while (i < 5);

            } else {

                do {
                    if (tempHand == -1) {
                        break;
                    } else {
                        tempList[i] = tempHand;
                        tempHand = showLeft(handSet, currentRank - i);
                        i++;
                    }
                }while (i < 5);
            }


            if (tempList[4] == -1){ //check last one exist
                continue;
            } else {
                isFound = true;
                //put the most significant at the start
                System.arraycopy(tempList, 0, foundList, 0, 5);
                break;
            }

        }
    }

    private int showLeft(Set<Integer> handSet, int rank){
        if (rank == 0) {return -1;}

        int leftRank = rank-1;
        int[] leftSet = {leftRank, leftRank+13, leftRank+26, leftRank+39};
        for (int left: leftSet){
            if (handSet.contains(left)){ return left; }
        }

        return -1;
    }

    private int showRight(Set<Integer> handSet, int rank){  //return 0 if no right
        if (rank == 12){return -1;}

        int rightRank = rank+1;
        int[] rightSet = {rightRank, rightRank+13, rightRank+26, rightRank+39};
        for (int right: rightSet){
            if (handSet.contains(right)){ return -1; }
        }

        return 0;
    }


    @Override
    public String setName() {
        return "Straight";
    }

    @Override
    public int setRanking() {
        return 5;
    }

}
