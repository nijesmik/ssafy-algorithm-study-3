import java.util.ArrayDeque;
import java.util.ArrayList;

class UserSolution
{
    int[][] map;
    int mapSize;
    int energy;

    void init(int N, int mRange, int mMap[][])
    {
        stations.clear();
        recordDis.clear();

        map = mMap;
        mapSize = N;
        energy = mRange;

        return;
    }

    ArrayList<Pos> stations = new ArrayList<>();
    ArrayList<ArrayList<Integer>> recordDis = new ArrayList<>();
    void add(int mID, int mRow, int mCol)
    {
        stations.add(new Pos(mRow, mCol));
        map[mRow][mCol] = 1;

        // 신규 충전소 행 추가
        recordDis.add(new ArrayList<>());

        // 각 행마다 열 추가
        for (int i = 0; i < mID; i++) {
            recordDis.get(i).add(Integer.MIN_VALUE);

            // 최근에 더해진 행에 하나씩 값 추가.
            recordDis.get(mID).add(Integer.MIN_VALUE);
        }

        recordDis.get(mID).add(Integer.MIN_VALUE);

        return;
    }

    int distance(int mFrom, int mTo)
    {
        return 0;
    }

    int getDis(Pos p1, Pos p2){

        if(p1.x == p2.x && p1.y == p2.y)return 0;

        ArrayDeque<CalDis> qu = new ArrayDeque<>();
        qu.add(new CalDis(p1, 0));

        while(!qu.isEmpty()){
            CalDis tmp = qu.poll();

            if(tmp.p.x == p2.x && tmp.p.y == p2.y){
                return tmp.cum;
            }


        }

        return -1;
    }

    class btwStation implements Comparable<btwStation>{
        int num;
        int fromNum;
        int dis;

        public btwStation(int num, int fromNum) {
            this.num = num;
            this.fromNum = fromNum;

            int record = recordDis.get(this.num).get(fromNum);
            if(record != Integer.MIN_VALUE){
                this.dis = record;
            }else{

            }

        }

        @Override
        public int compareTo(btwStation o){
            return Integer.compare(this.dis, o.dis);
        }

    }

    class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class CalDis{
        Pos p;
        int cum;

        public CalDis(Pos p, int cum) {
            this.p = p;
            this.cum = cum;
        }
    }
}