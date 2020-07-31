package tictactoe;
import java.util.Scanner;
public class Main {
    public static int n = 5;
    public static int m = 9;
    public static String[][] arr = new String[n][m];
    final public static Scanner in = new Scanner(System.in);
    public static int numberOfX = 0;
    public static int numberOfY = 0;
    public static String Status = "NF";
    public static String queue = "Cross";
    public static void main(String[] args) {
        prepare();
       // insert();
        print();
        do {
            add();
            print();
            winner_check();
        } while(!Status.equals("X wins") && !Status.equals("O wins") && !Status.equals("Draw"));
        in.close();
    }
    public static void prepare() {
        for(int i = 0; i < m; i++) {
            arr[0][i] = "-";
            arr[4][i] = "-";
        }
        for(int i = 1; i < n - 1; i++) {
            arr[i][0] = "|";
            arr[i][8] = "|";
        }
        for(int i = 1; i < n - 1; i++) {
            for(int j = 1; j < m - 1; j++) {
                arr[i][j] = " ";
            }
        }

    }
    public static void print() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    public static void insert() {
        System.out.println("Enter cells: ");
        String input = in.nextLine();
        int next = 0;
        for(int i = 1; i < n - 1; i ++) {
            for(int j = 1; j < m - 1; j++) {
                if(j % 2 == 0) {
                    if(String.valueOf(input.charAt(next)).equals("_")) {
                        arr[i][j] = " ";
                    }
                    else {
                        arr[i][j] = String.valueOf(input.charAt(next));
                    }
                    if(String.valueOf(input.charAt(next)).equals("X")) {
                        numberOfX++;
                    }
                    if(String.valueOf(input.charAt(next)).equals("O")) {
                        numberOfY++;
                    }
                    next++;
                }
            }
        }
    }
    public static void winner_check() {
        if(checker("X") > checker("O")) {
            System.out.println("X wins");
            Status = "X wins";
        }
        if(checker("O") > checker("X")) {
            System.out.println("O wins");
            Status = "O wins";
        }
        //Impossible case
        if(Math.abs(numberOfX - numberOfY) > 1 || checker("X") == checker("O") && checker("X") == 1) {
            System.out.println("Impossible");
            Status = "Impossible";
        }
        if(numberOfY + numberOfX == 9 && checker("X") == 0 && checker("O") == 0) {
            System.out.println("Draw");
            Status = "Draw";
        }
    }
    public static int checker(String var) {
        int result = 0;
        for(int i = 1; i < n - 1; i++) {
            for(int j = 2; j < m - 2; j++) {
                if(arr[i][j].equals(var) && arr[i][j + 2].equals(var) && arr[i][j + 4].equals(var)) {
                    result = 1;
                    break;
                }
                if(arr[i][j].equals(var) && arr[i][j + 2].equals(var) && arr[i][j - 2].equals(var)) {
                    result = 1;
                    break;
                }
                if(arr[i][j].equals(var) && arr[i - 1][j].equals(var) && arr[i - 2][j].equals(var)) {
                    result = 1;
                    break;
                }
                if(arr[i][j].equals(var) && arr[i - 1][j].equals(var) && arr[i + 1][j].equals(var)) {
                    result = 1;
                    break;
                }
                if(arr[i][j].equals(var) && arr[i - 1][j + 2].equals(var) && arr[i - 2][j + 4].equals(var)) {
                    result = 1;
                    break;
                }
                if(arr[i][j].equals(var) && arr[i - 1][j - 2].equals(var) && arr[i - 2][j - 4].equals(var)) {
                    result = 1;
                    break;
                }
            }
        }
        return result;
    }
    public static void add() {
        System.out.println("Enter the coordinates: ");
        int x;
        int y;
        do {
            while (!in.hasNextInt() && !in.hasNextInt()) {
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates: ");
                in.next();
                in.next();
            }
            x = in.nextInt();
            y = in.nextInt();
            while (x > 3 || y > 3 || x < 1 || y < 1) {
                System.out.println("Coordinates should be from 1 to 3");
                x = in.nextInt();
                y = in.nextInt();
            }
           if(!arr[n - 1 - y][x * 2].equals(" ")){
                System.out.println("This cell is occupied! Choose another one");
            }
        } while (!arr[n - 1 - y][x * 2].equals(" "));
        switch (queue) {
            case "Cross" :
                arr[n - 1 - y][x * 2] = "X";
                numberOfX++;
                queue = "Zero";
                break;
            case "Zero" :
                arr[n - 1 - y][x * 2] = "O";
                numberOfY++;
                queue = "Cross";
                break;
        }
    }
}
