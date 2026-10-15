class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int position = s.charAt(i) - 'a' + 1;
            int reversePosition = 26 - position + 1;

            sum += reversePosition * (i + 1);
        }

        return sum;
    }
}
