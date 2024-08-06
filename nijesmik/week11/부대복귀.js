function solution(n, roads, sources, destination) {
  const graph = Array.from(Array(n + 1), () => []);
  roads.forEach(([a, b]) => {
    graph[a].push(b);
    graph[b].push(a);
  });
  const distance = bfs(graph, destination);
  return sources.map((start) => distance[start]);
}

const bfs = (graph, start) => {
  const q = [];
  const distance = Array(graph.length).fill(-1);

  q.push(start);
  distance[start] = 0;
  while (q.length) {
    const cur = q.shift();
    graph[cur].forEach((next) => {
      if (distance[next] === -1) {
        q.push(next);
        distance[next] = distance[cur] + 1;
      }
    });
  }
  return distance;
};
