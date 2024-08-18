import java.util.Scanner;

class WordSearch {
    public static boolean exist(char[][] board, String target) {
        if (board == null || board.length == 0 || target == null || target.length() == 0)
            return false;

        int numRows = board.length;
        int numCols = board[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (dfs(board, i, j, target, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int row, int col, String target, int index) {
        if (index == target.length())
            return true;

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != target.charAt(index))
            return false;

        char temp = board[row][col];
        board[row][col] = '*'; // Mark the cell as visited

        // Explore in all four directions
        boolean found = dfs(board, row + 1, col, target, index + 1) ||
                        dfs(board, row - 1, col, target, index + 1) ||
                        dfs(board, row, col + 1, target, index + 1) ||
                        dfs(board, row, col - 1, target, index + 1);

        board[row][col] = temp; // Restore the cell

        return found;
    }

    
}

public class Hw5 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();//row
        int m = keyboard.nextInt();//col
        keyboard.nextLine();
        char[][] board = new char[n][m];
        for(int i = 0; i < n; i++) {
            String input = keyboard.nextLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = (input.toCharArray())[2 * j];
            }
        }
        String target = keyboard.next();
        keyboard.nextLine();

        boolean result = WordSearch.exist(board, target);
        System.out.println(result);

        keyboard.close();
    }
}