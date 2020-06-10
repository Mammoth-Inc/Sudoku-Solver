package com.company;

/**
 * @author Peyton Scott
 * @version 2020-10-6
 *
 * This program solves sudoku puzzles using a back tracking algorithm.
 */

public class sudokuSolver {
    //define simple sudoku grid
    public static int[][] puzzle_board = {
            {6, 0, 0, 0, 0, 0, 9, 0, 4},
            {0, 3, 0, 5, 0, 0, 0, 6, 0},
            {0, 0, 9, 7, 0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 3, 0, 4, 0, 2, 0},
            {0, 2, 0, 0, 0, 0, 5, 4, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 9, 0, 1, 7, 0, 0},
            {0, 0, 8, 0, 7, 2, 3, 1, 0},
    };

    private int[][] board;
    public static final int EMPTY = 0; // empty cell
    public static final int SIZE = 9; // size of Sudoku grids

    public sudokuSolver(int[][] board) {
        this.board = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }


    //checks each column to determine if number is already in column
    private boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == number)
                return true;

        return false;
    }

    // checks each row to determine if number is already in row
    private boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number)
                return true;

        return false;

    }

    // checks each box to determine if number is already in box
    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;

        return false;
    }

    // checks to make sure all three rules are followed
    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }


    //Solve method by using back tracking algorithm.

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // we search and empty cell
                if (board[row][col] == EMPTY) {
                    // try possible numbers
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOk(row, col, number)) {

                            board[row][col] = number;

                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }

                    }
                    return false;
                }
            }
        }

                return true;
            }


    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        sudokuSolver sudoku = new sudokuSolver(puzzle_board);
        System.out.println("Puzzle Board");
        sudoku.display();

        if (sudoku.solve()) {
            System.out.println("Sudoku Grid solved!");
            sudoku.display();
        } else {
            System.out.println("Unsolvable!");
        }


    }

}
