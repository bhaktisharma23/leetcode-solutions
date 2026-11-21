class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        int[] head = new int[n], nxt = new int[n];
        Arrays.fill(head, -1);
        for (int i = 1; i < n; i++) {
            nxt[i] = head[parent[i]];
            head[parent[i]] = i;
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] prev = new int[n];    
        int[] newPar = new int[n];
        int[] order = new int[n];   
        int cnt = 0;

        int[] stack = new int[n];
        int[] it = head.clone();      
        int sp = 0;

        int c0 = s.charAt(0) - 'a';
        newPar[0] = -1;
        prev[0] = last[c0];
        last[c0] = 0;
        order[cnt++] = 0;
        stack[sp++] = 0;

        while (sp > 0) {
            int x = stack[sp - 1];
            if (it[x] != -1) {
                int child = it[x];
                it[x] = nxt[child];

                int c = s.charAt(child) - 'a';
                newPar[child] = last[c] != -1 ? last[c] : parent[child];
                prev[child] = last[c];
                last[c] = child;
                order[cnt++] = child;
                stack[sp++] = child;
            } else {
                sp--;
                last[s.charAt(x) - 'a'] = prev[x];
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);
        for (int i = n - 1; i > 0; i--) {
            int x = order[i];
            size[newPar[x]] += size[x];
        }
        return size;
    }
}