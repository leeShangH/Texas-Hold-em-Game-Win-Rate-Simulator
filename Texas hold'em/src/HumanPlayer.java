import java.util.Scanner;

public class HumanPlayer extends Player{

    @Override
    public String setName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        return input.nextLine();
    }



}