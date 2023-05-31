package TicTacToe;
import java.util.*;
public class Game {
    public static void main(String[] args) {
        Board board = new Board() ;
        Player p1 = new Player() ;
        Player p2 = new Player() ;
        try (Scanner sc = new Scanner(System.in)) {
            board.printBoard(); 
            do {
                System.out.println("Enter X at position: ");
                
                p1.setChoice(sc.nextInt());
                while ( !board.getBoard()[board.getCell(p1.getChoice())][p1.getPosition(p1.getChoice())].isEmpty() ) {
                    p1.setChoice(sc.nextInt());
                }
                board.setChoicePlayer(p1.getChoice());

                board.getOptions()[p1.getChoice()] = "X" ;
            

                if ( board.checkEmpty() ) {
                    p2.setChoice((int)(Math.random() * 9));
                    while ( !board.getBoard()[board.getCell(p2.getChoice())][p2.getPosition(p2.getChoice())].isEmpty() ) {
                        p2.setChoice((int)(Math.random() * 9));
                    }
                    board.setChoicep2(p2.getChoice());
                    board.getOptions()[p2.getChoice()] = "O";
                }
                
                
                board.printBoard(); 

                if ( !board.checkConditions() ) 
                    continue ;
                else 
                    break ;


            } while ( board.checkEmpty() );
        }
    }
}
class Board {
    int[][] winConditions = {
        { 0 , 1 , 2 },
        { 3 , 4 , 5 },
        { 6 , 7 , 8 },
        { 0 , 3 , 6 },
        { 1 , 4 , 7 },
        { 2 , 5 , 8 },
        { 0 , 4 , 8 },
        { 2 , 4 , 6 },
    };
    int cell ;
    String[][] board = {
        // 0  | 0 1 2
        // 1  | 3 4 5
        // 2  | 6 7 8
        { "" , "" , "" },
        { "" , "" , "" },
        { "" , "" , "" }
    };  

    public String[][] getBoard () {
        return board ;
    }

    public String[] getOptions() {
        return options ;
    }

    String []options = {
        "" , "" , "" , "" , "" , "" , "" , "" , ""
    };
    public boolean checkOptionsEmpty() {
        for ( int i = 0 ; i < options.length ; i ++ ) {
            if ( options[i].equals(" ") )
                return true ;
                
        }
        return false ;
    }
    public int getCell ( int x ) {
        if ( x >= 0 && x <= 2 ) 
            return 0 ;
        if ( x >= 3 && x <= 5 ) 
            return 1 ;
        if ( x >= 6 & x <= 8)
            return 2;

        return 0 ;
    }
    public void setChoicePlayer ( int x ) {
        if ( x >= 0 && x <= 2 ) {
            cell = 0 ; 
            if ( board[cell][x].isEmpty() ) 
                board[cell][x] = "X";
            
        }
        else if ( x >= 3 && x <= 5 ) {
            cell = 1 ;
            if ( x == 3 ) 
                if ( board[cell][0].isEmpty() ) 
                    board[cell][0] = "X" ;
            if ( x == 4 )
                if ( board[cell][1].isEmpty() )
                    board[cell][1] = "X" ;
            if ( x == 5 )
                if ( board[cell][2].isEmpty() )
                    board[cell][2] = "X" ;
        }
        else if ( x >= 6 && x <= 8 ) {
            cell = 2 ; 
            if ( x == 6 ) 
                if ( board[cell][0].isEmpty() )
                    board[cell][0] = "X" ;
            if ( x == 7 )
                if ( board[cell][1].isEmpty() ) 
                    board[cell][1] = "X" ;
            if ( x == 8 )
                if ( board[cell][2].isEmpty() )
                    board[cell][2] = "X" ;
                
        }
        else {
            System.out.println("Error position entered!");
            
        }
    }
    public void setChoicep2 ( int x ) {
        cell = 0 ;
        if ( x >= 0 && x <= 2 ) {
            if ( board[cell][x].isEmpty() ) 
                board[cell][x] = "O";
        }
        else if ( x >= 3 && x <= 5 ) {
            cell = 1 ;
            if ( x == 3 ) 
                if ( board[cell][0].isEmpty() ) 
                    board[cell][0] = "O" ;
            if ( x == 4 )
                if ( board[cell][1].isEmpty() ) 
                    board[cell][1] = "O" ;
            if ( x == 5 )
                if ( board[cell][2].isEmpty() ) 
                    board[cell][2] = "O" ;
        }
        else if ( x >= 6 && x <= 8 ) {
            cell = 2 ; 
            if ( x == 6 ) 
                if ( board[cell][0].isEmpty() ) 
                    board[cell][0] = "O" ;
            if ( x == 7 )
                if ( board[cell][1].isEmpty() ) 
                    board[cell][1] = "O" ;
            if ( x == 8 )
                if ( board[cell][2].isEmpty() ) 
                    board[cell][2] = "O" ;
                
        }
        else {
            System.out.println("Error position entered!");
        }
    }

    public void printBoard() {
        for ( int i = 0 ; i < board.length ; i ++ ) {
            for ( int j = 0 ; j < board[i].length ; j ++ ) {
                System.out.print(" |\t" + board[i][j] + "\t|");
            }
            System.out.println();
        }
    }

    public boolean checkEmpty() {
        for ( int i = 0 ; i < board.length ; i ++ ) {
            for ( int j = 0 ; j < board[i].length ; j ++ ) {
                if ( board[i][j].isEmpty() ) 
                    return true ;
            }

        }
        return false ;
    }

    public boolean checkConditions() {
        boolean roundWon = false ;
        for ( int i = 0 ; i < winConditions.length ; i ++ ) {
            int []condition = winConditions[i];
            String cellA = options[condition[0]];
            String cellB = options[condition[1]];
            String cellC = options[condition[2]];

            if ( cellA == "" || cellB == "" || cellC == "" ) {
                continue ;
            }
            if ( cellA == cellB && cellB == cellC ) {
                roundWon = true ;
                break ;
            }
        }
        return roundWon ;
    }
}
class Player {
    private int choice ; 
    
    public Player(int choice) {
        this.choice = choice;
    }
    public Player() {
        this.choice = 0 ;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }

    public int getPosition ( int x ) {
        if ( x == 0 )
            return 0 ;
        if ( x == 1 )
            return 1 ;
        if ( x == 2 )
            return 2 ;
        if ( x == 3 )
            return 0 ;
        if ( x == 4 )
            return 1 ;
        if ( x == 5 )
            return 2 ;
        if ( x == 6 )
            return 0 ;
        if ( x == 7 )
            return 1 ;
        if ( x == 8 )
            return 2 ;
        
        return 0 ;
    }

}