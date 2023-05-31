package TicTacToe;
import java.util.*;
public class TicTacToe_2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            byte choice ; 
            do {
                System.out.println("1. Player Vs Player");
                System.out.println("2. Player Vs Computer");
                System.out.println("0. Exit");
                choice = sc.nextByte() ;
                switch ( choice ) {
                    case 1 -> {
                        Board board = new Board();
                        board.display();
                        Player p1 = new Player("X") ;
                        do {
                            do {
                                System.out.println("Enter position " + p1.getIcon() + ": ");
                                p1.setChoice(sc.nextInt());
                            } while (p1.getChoice() < 0 || p1.getChoice() > 9 || board.checkEmpty(p1.getChoice()));
                            board.updateBoard(p1,p1.getChoice());
                            if ( board.checkWinner() ) break ;

                            p1.changePlayer();
                            board.display();
                        } while ( board.checkAllEmpty() && !board.checkWinner() ) ;
                        if ( !board.checkAllEmpty() && !board.checkWinner())
                            System.out.println("Draw!") ;
                    }
                    case 2 -> {
                        Board board = new Board();
                        board.display();
                        Player p1 = new Player("X") ;
                        Player p2 = new Player("O") ;
                        do {
                            do {
                                System.out.println("Enter position X:");
                                p1.setChoice(sc.nextInt());
                            } while (p1.getChoice() < 0 || p1.getChoice() > 9 || board.checkEmpty(p1.getChoice()));
                            board.updateBoard(p1,p1.getChoice());
                            if ( board.checkWinner() ) break ;

                            if ( board.checkAllEmpty() ) {
                                do {
                                    p2.setChoice((int)(Math.random()*9+1));
                                } while (board.checkEmpty(p2.getChoice()));
                                board.updateBoard(p2, p2.getChoice());
                                if ( board.checkWinner() ) break ;
                            }
                            board.display();
                        } while ( board.checkAllEmpty() && !board.checkWinner() ) ;
                        if ( !board.checkAllEmpty() && !board.checkWinner())
                            System.out.println("Draw!") ;
                    }
                }
            } while ( choice != 0 );
            
        }
    }
}
class Board {
    final int [][] winConditions= {
        { 0 , 1 , 2 },
        { 3 , 4 , 5 },
        { 6 , 7 , 8 },
        { 0 , 3 , 6 },
        { 1 , 4 , 7 },
        { 2 , 5 , 8 },
        { 0 , 4 , 8 },
        { 2 , 4 , 6 },
    };
    public String[] board = {
        "-","-","-","-","-","-","-","-","-",
    };
    public String[] options = {
        "" , "" , "" , "" , "" , "" , "" , "" , ""
    };
    public boolean checkAllEmpty() {
        for ( String i : options ) {
            if ( i.isEmpty() )
                return true ;
        }
        return false ;
    }
    public boolean checkEmpty( int x ) {
        return !options[x - 1].isEmpty();
    }
    public void display() {
        for ( int i = 1 ; i <= 9 ; i ++ ) {
            System.out.print("|");
            if ( i % 3 == 0 ) 
                System.out.println("  " + board[i-1] + "  |" + "\n");
            else 
                System.out.print("  " + board[i-1] + "  ");    
        }
    }
    public void updateBoard (Player p ,  int x ) {
        options[x-1] = p.getIcon() ;
        board[x-1] = options[x-1] ;
    }
    public boolean checkWinner() {
        boolean roundWon = false ;
        for (int[] condition : winConditions) {
            String cellA = options[condition[0]];
            String cellB = options[condition[1]];
            String cellC = options[condition[2]];
            if (cellA.equals("") || cellB.equals("") || cellC.equals("")) {
                continue;
            }
            if (cellA.equals(cellB) && cellB.equals(cellC)) {
                roundWon = true;
                display();
                System.out.println(cellA + " win!");
                break;
            }
        }
        return roundWon ;
    }
}
class Player {
    private int choice ;
    private String icon ;
    public Player( String icon ) {
        this.icon = icon ;
    }
    public void setIcon( String x ) {
        this.icon = x ;
    }
    public String getIcon() {
        return icon ;
    }
    public int getChoice() {
        return choice;
    }
    public void setChoice(int choice) {
        this.choice = choice;
    }
    public void changePlayer() {
        if ( this.getIcon() == "X" ) this.setIcon("O") ;
        else if ( this.getIcon() == "O" ) this.setIcon("X") ;
    }
}