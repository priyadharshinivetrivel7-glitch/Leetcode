import java.util.*;

class Solution {
    int MOD = 1000000007;
    int[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j] 
                            + comb[i - 1][j - 1]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }

        return (int) ((dfs(list) - 1L + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            int x = nums.get(i);

            if (x < root) {
                left.add(x);
            } else {
                right.add(x);
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        long ways = comb[left.size() + right.size()][left.size()];

        return (ways * leftWays % MOD) * rightWays % MOD;
    }
}
