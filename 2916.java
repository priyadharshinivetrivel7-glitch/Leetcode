class Solution {
    int n;
    long[] sum;
    long[] square;
    long[] lazy;
    int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long result = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]] - 1;
            update(1, 0, n - 1, prev + 1, i);
            result = (result + square[1]) % MOD;
            last[nums[i]] = i + 1;
        }

        return (int) result;
    }

    void update(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return;

        if (left <= start && end <= right) {
            square[node] = (square[node] + 2 * sum[node] + (end - start + 1)) % MOD;
            sum[node] = (sum[node] + (end - start + 1)) % MOD;
            lazy[node]++;
            return;
        }

        push(node, start, end);
        int mid = (start + end) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        long value = lazy[node];

        apply(node * 2, start, mid, value);
        apply(node * 2 + 1, mid + 1, end, value);

        lazy[node] = 0;
    }

    void apply(int node, int start, int end, long value) {
        long length = end - start + 1;

        square[node] = (
            square[node]
            + 2 * value % MOD * sum[node] % MOD
            + value * value % MOD * length % MOD
        ) % MOD;

        sum[node] = (sum[node] + value * length) % MOD;
        lazy[node] = (lazy[node] + value) % MOD;
    }
}
