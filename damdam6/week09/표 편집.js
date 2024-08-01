function solution(n, k, cmd) {
    class Node {
        constructor(preNode, nextNode, num) {
            this.preNode = preNode;
            this.nextNode = nextNode;
            this.num = num;
            this.OX = 'O';
        }
    }

    let nodes = [];
    for (let i = 0; i < n; i++) {
        nodes.push(new Node(i - 1, i + 1, i));
    }
    nodes[n - 1].nextNode = -1;

    let selec = k;
    let bin = [];

    function UX(X) {
        for (let i = 0; i < X; i++) {
            selec = nodes[selec].preNode;
        }
    }

    function DX(X) {
        for (let i = 0; i < X; i++) {
            selec = nodes[selec].nextNode;
        }
    }

    function CC() {
        let nowNodes = nodes[selec];
        bin.push(nowNodes);
        nowNodes.OX = 'X';

        if (nowNodes.preNode !== -1) {
            nodes[nowNodes.preNode].nextNode = nowNodes.nextNode;
        }
        if (nowNodes.nextNode !== -1) {
            nodes[nowNodes.nextNode].preNode = nowNodes.preNode;
            selec = nowNodes.nextNode;
        } else {
            selec = nowNodes.preNode;
        }
    }

    function ZZ() {
        let getFromBin = bin.pop();
        getFromBin.OX = 'O';

        if (getFromBin.preNode !== -1) {
            nodes[getFromBin.preNode].nextNode = getFromBin.num;
        }
        if (getFromBin.nextNode !== -1) {
            nodes[getFromBin.nextNode].preNode = getFromBin.num;
        }
    }

    
    function process(c) {
        let [command, num] = c.split(' ');
        num = parseInt(num);
        switch (command) {
            case 'U':
                UX(num);
                break;
            case 'D':
                DX(num);
                break;
            case 'C':
                CC();
                break;
            case 'Z':
                ZZ();
                break;
        }
    }

    cmd.forEach((v) => {
        process(v);
    });

    let answer = nodes.map((v) => v.OX).join('');
    return answer;
}
