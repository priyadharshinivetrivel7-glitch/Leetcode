class Solution {
    private int[][] grid;
    private int m, n;
    private int[] dirs = {-1, 0, 1, 0, -1};

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n
                || grid[i][j] == 0) {
            return 0;
        }

        long sum = grid[i][j];
        grid[i][j] = 0;

        for (int d = 0; d < 4; d++) {
            sum += dfs(i + dirs[d], j + dirs[d + 1]);
        }

        return sum;
    }
}
