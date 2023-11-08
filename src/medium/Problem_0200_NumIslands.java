package medium;

/**
 * 200. 岛屿数量
 */
public class Problem_0200_NumIslands {

    // 使用 感染 方法
    public int numIslands(char[][] grid) {
        int numIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    infect(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    // 从(i,j)这个位置出发，把所有连成一片的'1'字符，变成0
    public void infect(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != '1') return;

        board[i][j] = '0';
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }
}
