public class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int timeToSecond1 = toSecond(h1, m1, s1);
        int timeToSecond2 = toSecond(h2, m2, s2);
        int difCnt = alarm(timeToSecond2) - alarm(timeToSecond1);

        if (isSame(h1, m1, s1)) {
            return difCnt + 1;
        }
        return difCnt;
    }

    private int toSecond(int hour, int minute, int second) {
        return hour * 3600 + minute * 60 + second;
    }

    private int alarm(int seconds) {
        int hAlarm = (int) (seconds * 719 / 43200.0);
        int mAlarm = (int) (seconds * 59 / 3600.0);
        int twelve = seconds >= 12 * 3600 ? 1 : 0;

        return 1 + hAlarm + mAlarm - twelve;
    }

    private boolean isSame(int hour, int minute, int second) {
        double hourAngle = (hour * 30 + minute * 0.5 + second * 0.5 / 60) % 360;
        double minuteAngle = minute * 6 + second * 0.1;
        double secondAngle = second * 6;

        return hourAngle == secondAngle || minuteAngle == secondAngle || hourAngle == minuteAngle;
    }
}
