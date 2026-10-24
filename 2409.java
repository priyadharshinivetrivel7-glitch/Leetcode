class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        int start = Math.max(getDays(arriveAlice), getDays(arriveBob));
        int end = Math.min(getDays(leaveAlice), getDays(leaveBob));

        return Math.max(0, end - start + 1);
    }

    public int getDays(String date) {
        int[] months = {31, 28, 31, 30, 31, 30,
                        31, 31, 30, 31, 30, 31};

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));

        int total = day;

        for (int i = 0; i < month - 1; i++) {
            total += months[i];
        }

        return total;
    }
}