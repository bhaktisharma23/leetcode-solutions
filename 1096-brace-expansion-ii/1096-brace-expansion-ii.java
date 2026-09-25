class Solution {
    public List<String> braceExpansionII(String expression) {
        return new ArrayList<>(solve(expression, 0, expression.length()));
    }

    private Set<String> solve(String s, int l, int r) {
        Set<String> result = new TreeSet<>();
        Set<String> current = new TreeSet<>();
        current.add("");

        int i = l;

        while (i < r) {
            char c = s.charAt(i);

            if (c == '{') {
                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') count++;
                    else if (s.charAt(j) == '}') count--;
                    j++;
                }

                Set<String> part = solve(s, i + 1, j - 1);
                current = multiply(current, part);
                i = j;
            } 
            else if (c == ',') {
                result.addAll(current);
                current = new TreeSet<>();
                current.add("");
                i++;
            } 
            else {
                Set<String> part = new TreeSet<>();
                part.add(String.valueOf(c));
                current = multiply(current, part);
                i++;
            }
        }

        result.addAll(current);
        return result;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}