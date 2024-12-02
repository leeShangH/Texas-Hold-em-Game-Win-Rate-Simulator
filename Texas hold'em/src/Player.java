import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Player {
    protected String name;
    protected Set<Integer> handSet = new HashSet<>();
    protected int bestRank;
    protected int[] bestHand;
    protected String bestHandName;


    public Player() {
        this.name = setName();
        this.bestHand = new int[]{-1, -1, -1, -1, -1};
        this.bestRank = 100;
        this.bestHandName = null;

    }

    //if equal returns false
    public boolean compare(Player that){
        for (int i = 0; i < 4; i++){
            if (getRank(this.bestHand[i]) != getRank(that.bestHand[i])){
                return isBiggerRank(getRank(this.bestHand[i]), getRank(that.bestHand[i]));
            }
        }


        return isBiggerRank(getRank(this.bestHand[4]), getRank(that.bestHand[4]));//compare from the most significant int
    };

    public boolean isBiggerRank(int thisRank, int thatRank){
        if (thisRank == 0){
            thisRank = 14;
        }
        if (thatRank == 0){
            thatRank = 14;
        }
        return thisRank > thatRank;
    }


    public int getRank(int index){ return index % 13;}

    public abstract String setName();

    public String getName(){
        return name;
    }


}
