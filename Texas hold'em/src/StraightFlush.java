import java.util.Set;

public class StraightFlush extends Rank {

    //a straight flush consists of any straight that is all the same suit. 2s 3s 4s 5s 6s
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        for (int current : handSet){

            if (hasSameSuitRight(handSet, current)) {           // assume to be the last one, does not have right index
                continue;
            }
            if (    hasSameSuitLeft(handSet, current)     &&
                    hasSameSuitLeft(handSet, current-1) &&
                    hasSameSuitLeft(handSet, current-2) &&
                    hasSameSuitLeft(handSet, current-3)) {
                isFound = true;
                for (int i = 0; i < 5; i++) {
                    foundList[i] = current -  i; //put the most significant at the start
                }
                break;
            }

        }
    }

    private boolean hasSameSuitLeft(Set<Integer> handSet, int i){
        return handSet.contains(i - 1) && (getSuit(i) == getSuit(i-1));
    }

    private boolean hasSameSuitRight(Set<Integer> handSet, int i){
        return handSet.contains(i + 1) && (getSuit(i) == getSuit(i+1));
    }


    @Override
    public String setName() {
        return "Straight Flush";
    }

    @Override
    public int setRanking() {
        return 1;
    }



}
