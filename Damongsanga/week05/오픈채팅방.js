function solution(record) {
  let events = [];
  const userMap = {};

  for (const info of record) {
    const [action, userId, username] = info.split(" ");
    if (action === "Enter") {
      userMap[userId] = username;
      events.push([userId, "들어왔습니다."]);
    } else if (action === "Leave") {
      events.push([userId, "나갔습니다."]);
    } else if (action === "Change") {
      userMap[userId] = username;
    }
  }

  return events.map(([userId, message]) => `${userMap[userId]}님이 ${message}`);
}
