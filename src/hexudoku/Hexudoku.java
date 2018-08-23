/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexudoku;

import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class Hexudoku {

    static Scanner sc=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i=0;i<3;i++) {
            Board board=readInGrid();
            int changes=board.doIt();
            System.out.println(changes);
        }
    }

    private static Board readInGrid() {
        Board board=new Board();

        for (int i=0;i<16;i++) {
            board.addLine(sc.nextLine(),i);
        }
        return board;
    }
    
}

class Board {
    char[][] grid=new char[16][16];
    
    public int doIt() {
        int count=0;
        for (int i=0;i<16;i++) {
            for (int j=0;j<16;j++) {
                if (grid[i][j]=='-') {
                    if (tryChar(i,j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    public void addLine(String line,int row) {
        int i=0;
        for (char c:line.toCharArray()) {
            if (c>='0' && c<='9') {
                grid[row][i]=(char)(c-'0');
            } else if (c>='A' && c<='F'){
                grid[row][i]=(char)(c-'A'+10);
            } else {
                grid[row][i]='-';
            }
            i++;
        }
    }

    private boolean tryChar(int i, int j) {
        for (char c=0;c<16;c++) {
            if (!tryRow(i,c)) {
                continue;
            }
            if (!tryColumn(j,c)) {
                continue;
            }
            if (!tryQuadrant(i,j,c)) {
                continue;
            }
            grid[i][j]=c;
            return true;
        }
        return false;
    }

    private boolean tryRow(int row, char c) {
        for (int i=0;i<16;i++) {
            if (grid[row][i]==c) return false;
        }
        return true;
    }

    private boolean tryColumn(int column, char c) {
        for (int i=0;i<16;i++) {
            if (grid[i][column]==c) return false;
        }
        return true;
    }

    private boolean tryQuadrant(int row, int col, char c) {
        int startRow=row/4*4;
        int startCol=col/4*4;
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (grid[startRow+i][startCol+j]==c) return false;
            }
        }
        return true;
    }
}
