import java.util.Scanner;
//import java.lang.Math;
public class TicTacToe {
    static final Scanner s = new Scanner(System.in);
    static char[][] GameBoard = new char[3][3];
    static int row = 3;
    static int col = 3;
    static int xc = 0;//x count
    static int oc = 0;//o count
    static int osp = 0;// _ count
    public static void initialize(){
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                GameBoard[i][j] = '_';
            }
        }

    }
    /*public static void field_input(){
        System.out.print("Enter cells: ");
        String cells = s.nextLine();
        //char[][] GameBoard = new char[3][3];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                GameBoard[i][j] = cells.charAt(i*3+j);
                if(GameBoard[i][j] == 'X') xc++;
                else if(GameBoard[i][j] == 'O') oc++;
                else osp++;
            }
        }
        //return GameBoard;
    }*/
    public static void display(){
        for(int i = 0; i < row+2; i++) {
            if(i==0||i==4)
                System.out.print("---------");
            else {
                for(int j = 0; j < col+2; j++) {
                    if(j==0)
                        System.out.print("|");
                    else if(j==4)
                        System.out.print(" |");
                    else {
                        //if(GameBoard[i-1][j-1]!='X'||GameBoard[i-1][j-1]!='O'||GameBoard[i-1][j-1]!='_')
                         //   System.out.print("  ");
                       // else
                            System.out.print(" "+ GameBoard[i-1][j-1]);
                    }
                }
            }
            System.out.println();
        }
    }
    public static int status_analyze(){
        int flag = 0;
        char midr;
        char midc;
        char mid;
        //int c = 0;
        osp = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                if(GameBoard[i][j] == 'X') xc++;
                else if(GameBoard[i][j] == 'O') oc++;
                else osp++;
            }
        }
        //check each row
        for(int i = 0; i < row; i++) {
            /*if(c>0) {//if count is more than one, we are checking for impossible
                char temp = GameBoard[i][1];
                if(temp == GameBoard[i][0] && temp == GameBoard[i][2] && temp!= '_') {
                    c++;
                }
            }
            else {*/
                midr = GameBoard[i][1];
                if (midr == GameBoard[i][0] && midr == GameBoard[i][2] && midr != '_') {
                    System.out.println(midr+" wins");
                    flag = 1;//wins horizontally
                    //c++;
                }
                //return true;
            //}
        }
        //check each col
        for(int i = 0; i < col; i++) {
            /*if(c>0){
                char temp = GameBoard[1][i];
                if(temp == GameBoard[0][i] && temp == GameBoard[2][i] && temp!= '_') {
                    c++;
                }
            }
            else {*/
                midc = GameBoard[1][i];
                if (midc == GameBoard[0][i] && midc == GameBoard[2][i] && midc != '_') {
                    System.out.println(midc+" wins");
                    flag = 2;//wins vertically
                    //c++;
                }
                //return true;
            //}
        }
        mid = GameBoard[1][1];
        if(mid == GameBoard[0][0] && mid == GameBoard[2][2] && mid!= '_') {
            System.out.println(mid+" wins");
            flag = 3;//wins diagonally
            //return true;
        }
        if(mid == GameBoard[0][2] && mid == GameBoard[2][0] && mid!= '_'){
            System.out.println(mid+" wins");
            flag = 3;
            //return true;
        }
        if(flag==0) {
            System.out.println(osp);
            if(osp == 0) {
                flag = 4;//Draw when no side has a three in a row and the grid has no empty cells.
                System.out.println("Draw");
            }
            else
                flag = 5;//Game not finished when neither side has three in a row but the grid still has empty cells.
        }
        return flag;
    }

    public static void coordinate_input(char P){
        int r;
        int c;
        int flag = 0;
        do {
            System.out.print(P+ "-> Enter the coordinates: ");
            r = s.nextInt() - 1;
            c = s.nextInt() - 1;

            if (r<0||r>2||c<0||c>2){
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else if (GameBoard[r][c] == 'X' || GameBoard[r][c] == 'O'){
                System.out.println("This cell is occupied! Choose another one!");
            }
            else {
                GameBoard[r][c] = P;
                display();
                flag = 1;
            }
        }while(flag!=1);
    }
    public static void main(String[] args) {
        initialize();
        display();
        int f;
        do{
            coordinate_input('X');
            f = status_analyze();
            if(f==1||f==2||f==3||f==4) break;
            coordinate_input('O');
            f = status_analyze();
        }while(f==5);
    }
}
