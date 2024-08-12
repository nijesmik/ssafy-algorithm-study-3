function solution(files) {
  const heads = {};
  const numbers = {};
  files.forEach((file) => {
    const [_, head, number, tail] = file.match(/([^\d]+)(\d+)(.*)/);
    heads[file] = head.toLowerCase();
    numbers[file] = Number(number);
  });
  return files.sort((a, b) => {
    if (heads[a] < heads[b]) {
      return -1;
    }
    if (heads[a] === heads[b]) {
      return numbers[a] - numbers[b];
    }
    return 1;
  });
}
