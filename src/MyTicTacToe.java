import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MyTicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int GAME_SIZE = 3;

    char[][] game = new char[GAME_SIZE][GAME_SIZE];

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void showGame() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void initBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';
            }
        }
    }

    public Move readMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti mutarea: ");
        String myMove = scanner.nextLine();

        String[] mySplit = myMove.split("-");

        Move mutare = new Move(parseInt(mySplit[0]), parseInt(mySplit[1]));
        return mutare;


    }

    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;

    }

    public boolean isWinLine(int line, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[line][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinCol(int col, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][col] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag1(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag2(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][GAME_SIZE - i - 1] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }


    public boolean isWin(Move move, char symbol) {

        return isWinLine(move.line, symbol)
                ||
                isWinCol(move.col, symbol)
                ||
                isWinDiag1(symbol)
                ||
                isWinDiag2(symbol);


    }

    public void playGame() {
        boolean isWin;
        do {
            initBoard();
            System.out.println("Incepe jocul.");
            showGame();

            Player currentPlayer = player1;
            char currentSymbol = SYMBOL_X;
            int nrMoves = 0;
            isWin = false;

            while (isWin == false && nrMoves < 9) {
                System.out.println("Player " + currentPlayer.name + " face mutarea");
                //citiesc mutarea

                Move move = readMove();


                //validez mutarea
                if (move.line < GAME_SIZE && move.line >= 0 && move.col < GAME_SIZE && move.col >= 0 && game[move.line][move.col] == '.') {

                    //efectuiez mutarea
                    makeMove(move, currentSymbol);
                    showGame();

                    //numar mutare
                    nrMoves++;
                    if (nrMoves >= (2 * GAME_SIZE - 1)) {
                        //testez daca avem stare de WIN
                        isWin = isWin(move, currentSymbol);

                    }

                    //daca nu e win sau mai am mutari -- schimb jucatorul
                    if (!isWin) {//isWin ==false
                        if (currentPlayer == player1) {
                            currentPlayer = player2;
                            currentSymbol = SYMBOL_0;
                        } else {
                            currentPlayer = player1;
                            currentSymbol = SYMBOL_X;
                        }
                    }
                } else {
                    System.out.println("Mutare incorecta! Introduceti alta mutare!");
                }
            }
            //afisez mesaj corespunzator
            if (isWin) {
                System.out.print("Castigatorul este " + currentPlayer.name);
            } else {
                System.out.println("Nu a castigat nimeni!Jocul se reia");
            }
        } while (!isWin);
    }
}
