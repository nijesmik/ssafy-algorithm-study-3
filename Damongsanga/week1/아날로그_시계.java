/** 아날로그 시계
 * 단순 계산 문제이지만 고려해야 할 부분이 많다.
 * 풀이 과정
 * 1. 분침/초침, 시침/초침 간의 만나는 횟수를 따로 더한다
 * 1.1 초기 각도를 구한다.
 * 1.2 시작 시간, 종료 시간을 모두 초 단위로 환산하여 분침, 시침 별로 초당 움직이는 각도를 계산하여 360으로 나누어준다. 이 때 초기 각도를 더한 채로 시작한다.
 * 2. 0시 0분 0초, 12시 0분 0초일 경우 분침/초침, 시침/초침 카운트가 1씩 각각 증가하는데, 이는 1번으로 계산해야하기 때문에 마지막에 보정해준다.
 * 주의점
 * 1. 12시가 넘는 시각에 대해서는 각도 계산시 12를 빼주어야 한다 (13시 -> 1시, 23시 -> 11시)
 * 2. 초기 각도를 구할 때 분침 or 시침이 앞에 있는지, 초침이 앞에 있는지 고려해야한다.
* */
class 아날로그_시계 {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int minuteCount = minuteCount(timeToSec(h1, m1, s1), timeToSec(h2, m2, s2), initMinuteAngle(m1, s1));
        int hourCount = hourCount(timeToSec(h1, m1, s1), timeToSec(h2, m2, s2), initHourAngle(h1, s1));
        System.out.println("minuteCount = " + minuteCount);
        System.out.println("hourCount = " + hourCount);

        int count = minuteCount + hourCount;
        if (h1 == 0 && m1 == 0 && s1 == 0) count--;
        if (timeToSec(h1, m1, s1) <= 12 * 3600 && 12 * 3600 <= timeToSec(h2, m2, s2)) count--;

        return count;
    }

    private int timeToSec(int h, int m, int s){
        return 3600 * h + 60 * m + s;
    }

    private int minuteCount(int srt, int end, double initAngle){
        return (int) ((6 - 0.1) * (end-srt) + initAngle)/ 360;
    }

    private int hourCount(int srt, int end, double initAngle){
        return (int) ((6 - (double) 1 / 120) * (end-srt) + initAngle)/ 360;
    }

    private double initMinuteAngle(int m, int s){
        if (6 * m + 0.1 * s >= 6 * s){
           return 360 - 6 * (m - s) + 0.1 * s;
        }
        return 6 * (s - m);
    }

    private double initHourAngle(int h, int s){
        if (h >= 12) h -= 12;
        if (h * 30 + (double) 1 / 120 * s >= s * 5){
            return 360 - (30 * h - 6 * s) + (double) 1 / 120 * s;
        }
        return 6 * s - 30 * h;
    }

}