import java.util.Set;

public abstract class Rank {
    private final String name;
    protected final int ranking;
    protected boolean isFound = false;
    protected int[] foundList;


    public Rank() {
        this.name = setName();
        this.ranking = setRanking();
        this.foundList = new int[]{-1, -1, -1, -1, -1};
    }

    public abstract void determine(Set<Integer> handSet);



    public void reset(){
        if (isFound){
            foundList = new int[]{-1, -1, -1, -1, -1};
            isFound = false;
        }
    }


    public int getSuit(int index){
        return index/13;
    }

    public int getRank(int index){
        return index % 13;              //Ace = 0, '2' = 1
    }


    public boolean biggerRank(int thisRank, int thatRank){
        if (thisRank == 0){
            thisRank = 14;
        }
        if (thatRank == 0){
            thatRank = 14;
        }
        return thisRank > thatRank;
    }


    public abstract String setName();

    public abstract int setRanking();

    public String getName(){
        return name;
    }

    public boolean getIsFound() { return isFound; }


}
