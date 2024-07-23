import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        String[] messages = new String[record.length];
        int[] messagesTypes = new int[record.length];
        
        ChatRoom room = new ChatRoom();
        
        int chatSize = 0;
        for(int i=0;i<record.length;i++){
            String[] str = record[i].split(" ");
            //System.out.println(Arrays.toString(str));
            switch(str[0]){
                case "Enter":{
                    messagesTypes[i] = 1;
                    messages[i] = str[1];
                    room.nickname.put(str[1], str[2]);
                    chatSize++;
                    break;
                }
                case "Leave":{
                     messagesTypes[i] = 2;
                    messages[i] = str[1];
                    chatSize++;
                    break;
                }
                case "Change":{
                    room.nickname.put(str[1], str[2]);
                    break;
                }
            }
        }
        
        
        String[] answer = new String[chatSize];
        
        int idx = 0;
        for(int i=0; i < record.length; i++){
            if(messagesTypes[i] == 0)continue;
            answer[idx++] = 
                room.nickname.get(messages[i])
                +
                room.message[messagesTypes[i]];
        }
        
        return answer;
    }
    
    class ChatRoom{
        Map<String,String> nickname;
        String[] message;
        
        ChatRoom(){
            this.nickname = new HashMap<>();
            this.message = new String[]{
                "-",
                "님이 들어왔습니다.",
                "님이 나갔습니다."
            };
            
        }
    }
}