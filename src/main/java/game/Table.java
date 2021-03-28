package game;

import java.util.Random;
import java.util.Scanner;

public class Table {
    private final char X = 'X';
    private final char O = 'O';
    private final char EMPTY = '*';
    private char[][] table = {
                    {EMPTY,EMPTY,EMPTY},
                    {EMPTY,EMPTY,EMPTY},
                    {EMPTY,EMPTY,EMPTY}
            };
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public void play(){

        while (true){
            userTurn();
            if (isWin(X)){
                break;
            }
            if (isDraw()){
                break;
            }
            computerTurn();
            if (isWin(O)){
                break;
            }
        }
    }
    private void printTable(){
        System.out.println("---------");
        for (int i = 0; i < 3;i++){
            System.out.print("| ");
            for (int j = 0;j < 3; j++){
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    private boolean isPossible(int x, int y){
        if (x < 0 || y < 0 || x > 2 || y > 2){
            printTable();
            System.out.println("Impossible");
            return false;
        }
        return table[y][x] == EMPTY;
    }
    private void userTurn(){
        printTable();
        int x, y;
        do {
            System.out.print("Enter cells: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isPossible(x, y));
        table[y][x] = X;
    }
    private void computerTurn(){
        printTable();
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }while (!isPossible(x, y));
        table[y][x] = O;
    }
    private boolean isWin(char sign){
        for(int i = 0; i < 3; i++){
            if ((table[i][0] == sign && table[i][1] == sign && table[i][2] == sign)||
                    (table[0][i] == sign && table[1][i] == sign && table[2][i] == sign)){
                printTable();
                System.out.println(sign + " wins");
                return true;
            }else if((table[0][0] == sign && table[1][1] == sign && table[2][2] == sign) ||
                    (table[2][0] == sign && table[1][1] == sign && table[0][2] == sign)){
                printTable();
                System.out.println(sign + " wins");
                return true;
            }
        }
        return false;
    }
    private boolean isDraw(){
        for (int i = 0; i < 3; i++){
            for (int j = 0;j < 3; j++){
                if (table[i][j] == EMPTY)
                    return false;
            }
        }
        System.out.println("Draw!");
        return true;
    }
}
