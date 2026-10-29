
import java.util.*;

class Solution {
    private int[] head, to, next;
    private int[] first, last, depth;
    private int[][] lift;
    private int time, maxPower;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        maxPower = 31 - Integer.numberOfLeadingZeros(n);
        head = new int[n];
        to = new int[2 * edges.length];
        next = new int[2 * edges.length];
        first = new int[n];
        last = new int[n];
        depth = new int[n];
        lift = new int[maxPower + 1][n];

        Arrays.fill(head, -1);

        int idx = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1];

            to[idx] = v;
            next[idx] = head[u];
            head[u] = idx++;

            to[idx] = u;
            next[idx] = head[v];
            head[v] = idx++;
        }

        time = 0;
        dfs(0, -1);

        BIT bit = new BIT(n + 1);
        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (arr[i] - 'a');
            bit.update(first[i], mask);
            bit.update(last[i] + 1, mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int change = (1 << (arr[u] - 'a'))
                           ^ (1 << (c - 'a'));

                if (change != 0) {
                    bit.update(first[u], change);
                    bit.update(last[u] + 1, change);
                    arr[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int ancestor = lca(u, v);

                int mask = bit.query(first[u])
                         ^ bit.query(first[v])
                         ^ (1 << (arr[ancestor] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int parent) {
        first[u] = ++time;
        lift[0][u] = (parent == -1) ? u : parent;

        for (int k = 1; k <= maxPower; k++) {
            lift[k][u] = lift[k - 1][lift[k - 1][u]];
        }

        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];

            if (v == parent) continue;

            depth[v] = depth[u] + 1;
            dfs(v, u);
        }

        last[u] = time;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = maxPower; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                u = lift[k][u];
            }
        }

        if (u == v) return u;

        for (int k = maxPower; k >= 0; k--) {
            if (lift[k][u] != lift[k][v]) {
                u = lift[k][u];
                v = lift[k][v];
            }
        }

        return lift[0][u];
    }

    private static class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}
