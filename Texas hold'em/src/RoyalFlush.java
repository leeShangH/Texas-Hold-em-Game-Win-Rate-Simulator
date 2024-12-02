import java.util.Set;

public class RoyalFlush extends Rank {


    //a royal flush consists of 10 J Q K A ,and all the same suit.
    @Override
    public void determine(Set<Integer> handSet){
        reset();
        for (int current: handSet){

            if (getRank(current) == 0){                 // is A
                if (    handSet.contains(current+9) &&  // is 10
                        handSet.contains(current+10) && // is J
                        handSet.contains(current+11) && // is Q
                        handSet.contains(current+12)){  // is K
                    isFound = true;
                    int[] tempList = {current, current+12, current+11, current+10, current+9};
                    System.arraycopy(tempList, 0, foundList, 0, 5);
                    break;
                }
            }
        }

    }



    @Override
    public String setName() {
        return "Royal Flush";
    }

    @Override
    public int setRanking() {
        return 0;
    }

}
