function solution(record) {
  const chatting = [];
  const nicknames = new Map();

  for (let i = 0; i < record.length; i++) {
    const [command, id, nickname] = record[i].split(' ');

    if (command === 'Enter') {
      nicknames.set(id, nickname);
      chatting.push([id, '님이 들어왔습니다.']);
    } else if (command === 'Leave') {
      chatting.push([id, '님이 나갔습니다.']);
    } else if (command === 'Change') {
      nicknames.set(id, nickname);
    }
  }

  return chatting.map((message) => nicknames.get(message[0]) + message[1]);
}
