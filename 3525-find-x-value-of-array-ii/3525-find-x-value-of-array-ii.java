import java.util.*;

class Solution {
    int k;
    Node[] tree;

    class Node {
        int p;
        int[] c;

        Node() {
            c = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        tree = new Node[4 * nums.length];
        build(1, 0, nums.length - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            update(1, 0, nums.length - 1, queries[i][0], queries[i][1]);
            Node x = query(1, 0, nums.length - 1, queries[i][2], nums.length - 1);
            ans[i] = x.c[queries[i][3]];
        }
        return ans;
    }

    void build(int n, int l, int r, int[] a) {
        if (l == r) {
            tree[n] = new Node();
            tree[n].p = a[l] % k;
            tree[n].c[tree[n].p] = 1;
            return;
        }

        int m = (l + r) / 2;
        build(n * 2, l, m, a);
        build(n * 2 + 1, m + 1, r, a);
        tree[n] = merge(tree[n * 2], tree[n * 2 + 1]);
    }

    Node merge(Node a, Node b) {
        Node res = new Node();
        res.p = (a.p * b.p) % k;

        for (int i = 0; i < k; i++) {
            res.c[i] += a.c[i];
            res.c[(a.p * i) % k] += b.c[i];
        }
        return res;
    }

    void update(int n, int l, int r, int idx, int val) {
        if (l == r) {
            tree[n] = new Node();
            val %= k;
            tree[n].p = val;
            tree[n].c[val] = 1;
            return;
        }

        int m = (l + r) / 2;

        if (idx <= m)
            update(n * 2, l, m, idx, val);
        else
            update(n * 2 + 1, m + 1, r, idx, val);

        tree[n] = merge(tree[n * 2], tree[n * 2 + 1]);
    }

    Node query(int n, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return tree[n];

        int m = (l + r) / 2;

        if (qr <= m)
            return query(n * 2, l, m, ql, qr);

        if (ql > m)
            return query(n * 2 + 1, m + 1, r, ql, qr);

        return merge(
            query(n * 2, l, m, ql, qr),
            query(n * 2 + 1, m + 1, r, ql, qr)
        );
    }
}