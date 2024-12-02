import java.util.Random;

public class Deck {
    private int length = 52;
    private int count = 0;

    public int[] getDeck() {
        return deck;
    }

    private int[] deck = new int[length];
    private static final String[] SUITS = { "♣", "♦", "♥", "♠" };
    private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7",
                                            "8", "9", "10", "J", "Q", "K", };


    public void newDeck(){
        for (int i = 0; i < 52; i++){  //typical poker cards
            deck[i] = i;
        }
        count = 0;
        shuffle();
    }

    public void setDeck(int[] setDeck){
        count = 0;
        length = setDeck.length;
        deck = new int[length];
        System.arraycopy(setDeck, 0, deck, 0, length);
    }

    public void shuffle(){  //do swap to all the position
        for (int j=0; j < 2; j++){
            for (int i = 0; i < length; i++){
                int temp = deck[i];
                int k = new Random().nextInt(length);
                deck[i] = deck[k];
                deck[k] = temp;
            }
        }
    }

    public int deal(){
        if (count == length){
            System.out.println("----- shuffle again -----");
            newDeck();
        }
        count++;
        return deck[count-1];
    }


    public String getSuit(int index){
        return SUITS[index/13];
    }

    public String getRank(int index){
        return RANKS[index % 13];
    }



}
