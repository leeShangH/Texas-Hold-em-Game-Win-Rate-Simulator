import java.util.*;

public class Main {
    private final static HashMap<Character, Integer> suitMap = new HashMap<>();
    private final static HashMap<Character, Integer> rankMap = new HashMap<>();

    public static void main(String[] args) {

        Deck game = new Deck();


        List<Rank> ranks = new ArrayList<>();
        ranks.add(new RoyalFlush());
        ranks.add(new StraightFlush());
        ranks.add(new FourOfAKind());
        ranks.add(new FullHouse());
        ranks.add(new Flush());
        ranks.add(new Straight());
        ranks.add(new ThreeOfAKind());
        ranks.add(new TwoPair());
        ranks.add(new OnePair());
        ranks.add(new HighCard());

        Scanner input = new Scanner(System.in);
        System.out.println("Enter how many players tables: ");
        int total = input.nextInt();
        while (total > 23){
            System.out.println("Players should below 23 \n");
            System.out.println("Enter how many players tables: ");
            total = input.nextInt();
        }

        System.out.println("Noted : enter card with suit + rank. ");
        System.out.println("e.g. cA c2 c3");
        System.out.println("e.g. sT sJ sK");


        do
        {
            System.out.println();
            System.out.println(">> New Game <<");
            System.out.println();

            List<Player> players = createPlayer(total);
            Set<Integer> existCards = new HashSet<>();
            int[] communityCard = {-1, -1, -1, -1, -1};

            for (int card: enterHand()){
                players.getFirst().handSet.add(card);
                existCards.add(card);
            }

            int[] myHand = {-1, -1};
            int j = 0;
            for (int i: players.getFirst().handSet){
                myHand[j] = i;
                j++;
            }

            showHand(players.getFirst(), game);
            System.out.println("You have " + winRate(getNewDeck(existCards, 52 - existCards.size()), players, communityCard, ranks, myHand) + " chance to win");
            System.out.println();
            if (ifContinue()){
                continue;
            };


            int comCount = 0;
            boolean notContinue = false;
            while(comCount < 5){
                for (int card: enterCommunity()){
                    communityCard[comCount] = card;
                    comCount++;
                }

                showHand(players.getFirst(), game);
                showCommunity(communityCard, game);

                System.out.println("You have " + winRate(getNewDeck(existCards, 52 - existCards.size()), players, communityCard, ranks, myHand) + " chance to win");
                System.out.println();
                notContinue = ifContinue();
                if (notContinue){
                    break;
                };
            }

            if (notContinue){
                continue;
            };

        }while(true);



    }



    private static int[] getNewDeck(Set<Integer> existCards, int newLength){
        int[] newDeck = new int[newLength];
        int count = 0;

        for (int i = 0; i<52 ;i++){
            if (!existCards.contains(i)){
                newDeck[count] = i;
                count++;
            }
        }
        return  newDeck;
    }

    private static float winRate(int[] newDeck, List<Player> players, int[] newCommunityCard, List<Rank> ranks, int[] myHand){
        int winCounts = 0;
        int totalPlayers = players.size();
        int[] winCountList = new int[10];

        for (int j = 0; j<50000; j++){ //test 50000 times
            Deck game = new Deck();
            game.setDeck(newDeck);
            List<Player> newPlayers = new ArrayList<>();
            newPlayers.add(new CpuPlayer(24));  //add test player
            for (int i : myHand) { //save my hand card to test player
                newPlayers.getFirst().handSet.add(i);
            }
            int[] myCommunityCard = new int[5];
            System.arraycopy(newCommunityCard, 0, myCommunityCard, 0, 5);


            for (int i = 0; i< totalPlayers-1; i++){  //add other players
                newPlayers.add(new CpuPlayer(i));
            }

            game.shuffle();

            for (int i = 0; i<5; i++){                      // deal community cards
                if (myCommunityCard[i] == -1){             // means haven't dealt
                    myCommunityCard[i] = game.deal();
                }
            }

            for(int i =0; i<2; i++){
                for (Player p : newPlayers){                   //deal hand cards to other players
                    if (Objects.equals(p.name, "CPU 24")){
                        p.handSet.addAll(players.getFirst().handSet);  //add existed hand card
                    }else{
                        p.handSet.add(game.deal());
                    }
                }
            }

            for (Player p: newPlayers){                        //deal community cards
                //add community card to each player
                for (int card: myCommunityCard){
                    p.handSet.add(card);
                }
            }

            // showdown
            showdown(newPlayers, ranks);

            List<Player> winners = new ArrayList<>();      //might have more than one winner
            Player temp = new CpuPlayer(-1);
            winners.add(temp);
            winners = getWinner(newPlayers,winners);


            for (Player winner: winners){
                if (Objects.equals(winner.name, "CPU 24")){
                    winCounts++;
                    winCountList[winner.bestRank]++;
                }
            }

        }

        System.out.println();
        System.out.println("Out of 50000 games :");
        for (Rank rank : ranks){
            System.out.println("win by " + rank.getName() + "\t" + winCountList[rank.setRanking()] + " times.");
        }
        System.out.println();

        return (float)winCounts/50000;
    }

    private static List<Player> getWinner(List<Player> players, List<Player> winners){

        for (Player p : players){
            Player winner = winners.getFirst();
            if (Objects.equals(p.name, winner.name)){ continue; }

            if (p.bestRank < winner.bestRank){
                winners = new ArrayList<>();
                winners.add(p);
            } else if (p.bestRank == winner.bestRank){ //same rank
                if (p.compare(winner)){  //if we are bigger than winner
                    winners = new ArrayList<>();
                    winners.add(p);
                } else if (!winner.compare(p)) { //if we are equal
                    winners.add(p);
                }
            }
        }
        return winners;
    }

    private static void showdown(List<Player> players, List<Rank> ranks){
        // Showdown
        for (Player p: players){
            for (Rank rank: ranks){
                rank.reset();
                rank.determine(p.handSet);
                if(rank.isFound){
                    System.arraycopy(rank.foundList, 0, p.bestHand, 0, 5);
                    p.bestRank = rank.ranking;
                    p.bestHandName = rank.getName();
                    break;
                }
            }
        }
    }

    private static boolean ifContinue(){
        Scanner input = new Scanner(System.in);
        System.out.print("Press enter to continue, Press r to reset the game ..... ");
        String line = input.nextLine();
        return line.equals("r");
    }

    private static int[] enterHand(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your hands: ");
        String line = input.nextLine();
        String[] cards = line.split(" "); // Split input into individual cards
        int length = cards.length;
        if (length != 2){enterHand();}
        return transfer(cards, length);
    }

    private static int[] enterCommunity(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your Community cards: ");
        String line = input.nextLine();

        String[] cards = line.split(" "); // Split input into individual cards
        int length = cards.length;
        if (length > 5 || length == 0){enterHand();}
        return transfer(cards, cards.length);
    }

    private static int[] transfer(String[] cards, int length){
        init();
        int[] cardValues = new int[length];
        int i = 0;

        for (String card: cards){
            int suitValue = suitMap.get(card.charAt(0));
            int rankValue = rankMap.get(card.charAt(1));
            cardValues[i] = 13*suitValue + rankValue;
            i++;
        }

        return cardValues;
    }

    private static List<Player> createPlayer(int playerNumber){
        List<Player> players = new ArrayList<>();

        players.add(new CpuPlayer(25));

        for (int i = 0; i< playerNumber-1; i++){
            players.add(new CpuPlayer(i));
        }

        return players;
    }

    private static void showHand(Player player, Deck game){
        System.out.println("You have : ");
        for (int i: player.handSet){
            if (i == -1){continue;}
            System.out.print(game.getSuit(i) + " " +game.getRank(i) + " ");
        }
        System.out.println();
    }

    private static void showCommunity(int[] community, Deck game){
        System.out.println("community cards : ");
        for (int i: community){
            if (i == -1){continue;}
            System.out.print(game.getSuit(i) + " " +game.getRank(i) + " ");
        }
        System.out.println();
    }


    private static void init(){
        suitMap.put('c', 0); // clubs
        suitMap.put('d', 1); // diamonds
        suitMap.put('h', 2); // hearts
        suitMap.put('s', 3); // spades

        rankMap.put('A', 0);
        rankMap.put('2', 1);
        rankMap.put('3', 2);
        rankMap.put('4', 3);
        rankMap.put('5', 4);
        rankMap.put('6', 5);
        rankMap.put('7', 6);
        rankMap.put('8', 7);
        rankMap.put('9', 8);
        rankMap.put('T', 9);  // Ten
        rankMap.put('J', 10); // Jack
        rankMap.put('Q', 11); // Queen
        rankMap.put('K', 12); // King
    }
}


