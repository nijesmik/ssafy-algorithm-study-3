function solution(s){
    var answer = true;

    // 스택구현
    class Stack{
        
        constructor(){
            this._stk = [];
        }
        
        push(i){
            this._stk.push(i);
        }
        pop(){
            return this._stk.pop();
        }
        peek(){
           return this._stk[this._stk.length - 1]; 
        }
        
        isEmpty(){
            if(this._stk.length==0){
                return true;
            }else{
                return false;
            }
        }
        
    }
    // 뭐임 근데.. 구현할 필요가 없었네...
    var stack = new Stack();
    
    for(var i = 0; i < s.length; i++){
        
        if(s[i] == "("){
            stack.push(0);
        }else if(s[i] == ")"){
            if(stack.isEmpty()){
                answer = false;
                break;
            }
            stack.pop();
        }
    }
    
    if(!stack.isEmpty()){
        answer = false;
    }

    return answer;
}