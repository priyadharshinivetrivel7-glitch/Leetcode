class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] last = new int[26];
        int[] prev = new int[n];
        int[] newParent = new int[n];

        Arrays.fill(last, -1);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (node < 0) {
                node = ~node;
                last[s.charAt(node) - 'a'] = prev[node];
                continue;
            }

            int c = s.charAt(node) - 'a';

            prev[node] = last[c];

            newParent[node] = (last[c] != -1)
                    ? last[c] : parent[node];

            last[c] = node;

            stack.push(~node);

            for (int child : children[node]) {
                stack.push(child);
            }
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        int[] order = new int[n];
        int index = 0;

        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            order[index++] = node;

            for (int child : newChildren[node]) {
                stack.push(child);
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {
            int node = order[i];
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
