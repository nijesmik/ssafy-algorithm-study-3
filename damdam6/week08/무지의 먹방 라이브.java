import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        
        // Food의 amount로 정렬하는 pquAmount
        
        PriorityQueue<Food> pquAmount 
            = new PriorityQueue<>(
        (a,b) -> {
            if(a.amount<b.amount)return -1;
            if(a.amount>b.amount)return 1;
            return 0;
        });
        
        // 모든 데이터를 pquAmount에 넣음
        // 1부터 시작하는 번호를 가졌으므로 i-1이 인덱스
        for(int i=1; i <= food_times.length; i++){
            pquAmount.add(new Food(i, food_times[i-1]));
        }
        
        // 이미 기존에 돈 바퀴수
        int turnCnt = 0;
        
        // 가장 작은 Amount를 다 빼는게 불가능할때
        boolean minus = false;
        
        Food tmp = null;
        int turnTest = 0;
        while(!pquAmount.isEmpty() && k > pquAmount.size()){
            
            
                tmp = pquAmount.peek();
                turnTest = tmp.amount;
            
            turnTest -= turnCnt;
            
            
            if((long) turnTest*(pquAmount.size()) < k){
                
                // System.out.println("if - k: "+k);
                
                turnCnt = tmp.amount;
                 k-= (long) turnTest*(pquAmount.size());
                while(!pquAmount.isEmpty() &&pquAmount.peek().amount-turnCnt == 0){
                    pquAmount.poll();
                }

            }else{
                break;
            }
            
        }
        
        if(pquAmount.isEmpty())return -1;
        
        List<Food> TmpList = new ArrayList<>(pquAmount);
        
        Collections.sort(TmpList,(a,b) -> {
            if(a.idx<b.idx)return -1;
            if(a.idx>b.idx)return 1;
            return 0;
        });
        
        ArrayDeque<Food> list = new ArrayDeque<>(TmpList);
        
        int lastCnt = pquAmount.poll().amount;
        
        while((long)lastCnt * list.size() > k){
            lastCnt--;
        }
        k -= (long) lastCnt * list.size();
        
        
        int now = list.size();
        for(int j=0; j<now; j++){
            Food v = list.poll();
            v.amount -= (turnCnt+lastCnt);
            if(v.amount > 0 ){
                list.add(v);
            }
        }
        
        if(list.isEmpty())return -1;
        
        // for(Food v: list){
        //     v.amount -= (turnCnt+lastCnt);
        // }
        
        
        // System.out.println(list.toString());
        
        while(k>0 && !list.isEmpty()){
            Food f = list.poll();
            
            k--;
            f.amount-= 1;
            
            if(f.amount!=0){
                list.add(f);
            }
        }
        
        // System.out.println(list.toString());
        
        if(list.isEmpty() && k>0)return -1;
        return list.poll().idx;
        
       
    }
    
    class Food{
        int idx;
        int amount;
        
        Food(int idx, int amount){
            this.idx = idx;
            this.amount = amount;
        }
        
      int  getAmount(){

            return this.amount;
        }
        
        int getIdx(){
            return this.idx;
        }
        
    public String toString(){
        return "idx: "+idx+" amount: "+amount;
    }
    }
}