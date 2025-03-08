
import java.util.*;

class UserSolution {

    ArrayList<Integer> abilList;
    ArrayList<TreeSet<Integer>> leagues = new ArrayList<>();



    int allLgSize;
    int lgSize;
    int midIdx;
    void init(int N, int L, int mAbility[]) {
        leagues.clear();


        allLgSize = L;
        lgSize = N/L;
        midIdx =(lgSize+1)/2-1;

        abilList = new ArrayList<>();
//        for(int y: mAbility){
//            abilList.add(y);
//        }

        Comparator<Integer> customComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(abilList.get(o1) > abilList.get(o2)){
                    return -1;
                };
                if(abilList.get(o1) < abilList.get(o2)){
                    return 1;
                };
                if(o1 < o2){
                    return -1;
                }
                if(o1 > o2){
                    return 1;
                }

                return 0;
            }
        };

        int playerIdx = 0;
        for (int i = 0; i < L; i++) {
            TreeSet<Integer> players = new TreeSet<>(customComparator);

            for (int j = 0; j < lgSize; j++) {

                abilList.add(mAbility[playerIdx]);
                players.add(playerIdx++);
            }

            leagues.add(players);

//            renew(i, 0);
//            renew(i, 1);
//            renew(i, 2);
        }

//        System.out.println(abilList);
//        System.out.println(leagues);
//        System.out.println(midIdx);
//        System.out.println(topMap);
//        System.out.println(midMap);
//        System.out.println(bottomMap);
    }

//    void renew(int leagueN, int topMidBottom){
//
//        switch (topMidBottom){
//            case 0:
//                topMap.put(leagueN, leagues.get(leagueN).first());
//                return;
//            case 1:
//                ArrayList<Integer> playerlist = new ArrayList<>(leagues.get(leagueN));
//                midMap.put(leagueN, playerlist.get(midIdx));
//                return;
//            case 2:
//                bottomMap.put(leagueN, leagues.get(leagueN).last());
//                return;
//        }
//
//    }



    void remove(howToMove[] moves){
        for(howToMove mv : moves){
            leagues.get(mv.fromLgN).remove(mv.idx);
            leagues.get(mv.toLgN).add(mv.idx);
        }
        return;
    }

    int move() {
        int count = 0;

        int arrIdx = 0;
        howToMove[] arr = new howToMove[(allLgSize-1)*2];
        for (int i = 0; i < allLgSize-1; i++) {
            int bottom = leagues.get(i).last();
            count += bottom;
            arr[arrIdx++] = new howToMove(bottom, i, i+1);

            int top = leagues.get(i+1).first();
            count += top;
            arr[arrIdx++] = new howToMove(top, i+1, i);
        }

        remove(arr);
//        System.out.println(count);
        return count;
    }



    int trade() {
        int count = 0;
        int arrIdx = 0;
        howToMove[] arr = new howToMove[(allLgSize-1)*2];
        for (int i = 0; i < allLgSize-1; i++) {

            Iterator<Integer> iterator = leagues.get(i).iterator();
            for (int j = 0; j < midIdx; j++) {
                iterator.next();
            }

            int mid = iterator.next();

            count += mid;
            arr[arrIdx++] = new howToMove(mid, i, i+1);

            int top = leagues.get(i+1).first();
            count += top;
            arr[arrIdx++] = new howToMove(top, i+1, i);

        }

        remove(arr);
//        System.out.println(count);
        return count;
    }

    class howToMove{
        int idx;
        int fromLgN;
        int toLgN;

        public howToMove(int idx, int fromLgN, int toLgN) {
            this.idx = idx;
            this.fromLgN = fromLgN;
            this.toLgN = toLgN;
        }
    }

}
