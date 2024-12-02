import java.util.Scanner;

public class CpuPlayer extends Player{
    protected int number;


    public CpuPlayer(int number) {
        this.number = number;
        this.name = setName();
    }

    @Override
    public String setName() {
        return "CPU " + number;
    }

}