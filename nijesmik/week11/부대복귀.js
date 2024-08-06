function solution(n, roads, sources, destination) {
  const graph = Array.from(Array(n + 1), () => []);
  roads.forEach(([a, b]) => {
    graph[a].push(b);
    graph[b].push(a);
  });
  return sources.map((start) => bfs(graph, start, destination));
}

const bfs = (graph, start, destination) => {
  const q = [];
  const visited = Array(graph.length).fill(false);

  q.push([start, 0]);
  visited[start] = true;
  while (q.length) {
    const [cur, distance] = q.shift();
    if (cur === destination) {
      return distance;
    }
    graph[cur].forEach((next) => {
      if (!visited[next]) {
        q.push([next, distance + 1]);
        visited[next] = true;
      }
    });
  }
  return -1;
};
