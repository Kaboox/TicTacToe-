import java.util.Scanner;
 

public class App {
    static String[] board;
    
    // displays board
    static void print_board() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|------------");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|------------");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");

    }

    // checks for the winner or draw
    public static String win_check() {
        String line = null;
        for (int a = 0; a < 8; a++) {
            switch(a) {
            case 0:
                line = board[0] + board[1] + board[2];
                break;
            case 1:
                line = board[3] + board[4] + board[5];
                break;
            case 2:
                line = board[6] + board[7] + board[8];
                break;
            case 3:
                line = board[0] + board[3] + board[6];
                break;
            case 4:
                line = board[1] + board[4] + board[7];
                break;
            case 5:
                line = board[2] + board[5] + board[8];
                break;
            case 6:
                line = board[0] + board[4] + board[8];
                break;
            case 7:
                line = board[6] + board[4] + board[2];
                break;
                
            }
            if (line.equals("xxx")) {
                return "X HAS WON!";
            } else if (line.equals("ooo")) {
                return "O HAS WON!";
            }

        }
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] != null) {
                counter++;
            }
        }
        if (counter == 9) {
            return "DRAW";
        }
    return null;
    }

    // asks player for the symbol x or o
    public static String getPlayerSymbol() {
        Scanner sc= new Scanner(System.in);
        char player1 = ' ';
        String base_player;
        while (player1 != 'o' && player1 != 'x') {
            System.out.println("Player1 chooses symbol: (x/o)");
            String toplayer = sc.next();
            player1 = toplayer.toLowerCase().charAt(0);
        }
        if (player1 == 'x') {
            System.out.println("Player1 chose " + player1 + ", player2 is o");
            base_player = "x";
        } else {
            System.out.println("Player1 chose " + player1 + ", player 2 is x");
            base_player = "o";
        }
        return base_player;
    }

    // checks if the spot on the board is free, if so returns true
    public static boolean check_for_spot(int choice) {
        if (board[choice] != null) {
            System.out.println("Spot taken");
        } else {
            return true;
        }
        return false;
    }

    public static void place_marker(int choice, String player) {
        board[choice] = player;
    }


    // function returns index of the board, that player wants to move to,
    // after checking if the spot is free
    public static int players_move() {
        Scanner sc= new Scanner(System.in);
        String str_choice;
        char first_element;
        int choice = -1;
        boolean valid_spot = false;
        while (!valid_spot) {
            do {
                System.out.println("Select a spot: (0-8)");
                str_choice = sc.next();
                if (str_choice.length() == 1) {
                    first_element = str_choice.charAt(0);
                    if (Character.isDigit(first_element)) {
                        choice = Character.getNumericValue(first_element);
                        System.out.println(choice);
                    }   
                } 
            } while (choice < 0 || choice > 8);
            valid_spot = check_for_spot(choice);
        }  
        return choice;
     }

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Tic-Tac-Toe Game! :D:D::D::D:D");
        String player1 = getPlayerSymbol();
        String player2 = " ";
        if (player1 == "x") {
            player2 = "o";
        } else {
            player2 = "x";
        }
        
        board = new String[9];
        print_board();
        String winner;
        int turn = 1;
        String move;
        int pos_to_move;
        do {
            if (turn % 2 == 0) {
                move = player2;
            } else {
                move = player1;
            }
            pos_to_move = players_move();
            place_marker(pos_to_move, move);
            print_board();
            turn++;
            winner = win_check();
            if (winner != null) {
                System.out.println(winner);
            }
        } while (winner == null);
    }
}