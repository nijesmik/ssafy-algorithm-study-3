function solution(words) {
  const trie = new Trie();
  words.forEach((word) => {
    trie.insert(word);
  });
  return words.reduce((answer, word) => {
    return answer + trie.getMinLength(word);
  }, 0);
}

class Trie {
  constructor() {
    this.root = new TrieNode();
  }

  insert(word) {
    let node = this.root;
    for (let char of word) {
      if (!node.children[char]) {
        node.children[char] = new TrieNode();
      }
      node = node.children[char];
      node.count++;
    }
    node.endOfWord = true;
  }

  getMinLength(word) {
    let node = this.root;
    let length = 0;
    for (let char of word) {
      length++;
      node = node.children[char];
      if (node.count === 1) {
        return length;
      }
    }
    return length;
  }
}

class TrieNode {
  constructor() {
    this.children = {};
    this.endOfWord = false;
    this.count = 0;
  }
}
