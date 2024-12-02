import java.util.Set;

public class FourOfAKind extends Rank {

    //consists of four cards of equal value along with another card known as a side card. 4 + 1
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        for (int current : handSet){
            int currentRank = getRank(current);
            if (    handSet.contains(currentRank)      &&
                    handSet.contains(currentRank + 13) &&
                    handSet.contains(currentRank + 26) &&
                    handSet.contains(currentRank + 39)    ){

                isFound = true;
                for (int i = 0; i < 4; i++) {
                    foundList[i] = currentRank + 13*i; //put the most significant at the start
                }
            }

            if (isFound){
                // add the biggest one as a side card
                int temp = -1;
                for (int side : handSet){
                    if (getRank(foundList[0]) != getRank(side)){
                        if (getRank(side) == 0 || getRank(side) > temp){
                            temp = side;
                        }
                    }
                }
                foundList[4] = temp;
                return;
            }

        }
    }



    @Override
    public String setName() {
        return "Four of a Kind";
    }

    @Override
    public int setRanking() {
        return 2;
    }



}
