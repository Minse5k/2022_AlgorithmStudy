const d = [1, -1];
let N, A, B;
const visitedA = Array.from({ length: 500001 }, () =>
  Array.from({ length: 20 }, () => -1),
);
const visitedB = Array.from({ length: 500001 }, () =>
  Array.from({ length: 20 }, () => -1),
);
let ans = -1;

class Node {
  constructor(data) {
    this.data = data;
    this.next = null;
  }
}

// 큐 클래스
class Queue {
  constructor() {
    this.head = null; // 제일 앞 노드
    this.rear = null; // 제일 뒤 노드
    this.length = 0; // 노드의 길이
  }

  enqueue(data) {
    // 노드 추가.
    const node = new Node(data); // data를 가진 node를 만들어준다.
    if (!this.head) {
      // 헤드가 없을 경우 head를 해당 노드로
      this.head = node;
    } else {
      this.rear.next = node; // 아닐 경우 마지막의 다음 노드로
    }
    this.rear = node; // 마지막을 해당 노드로 한다.
    this.length++;
  }

  dequeue() {
    // 노드 삭제.
    if (!this.head) {
      // 헤드가 없으면 한 개도 없는 것이므로 false를 반환.
      return false;
    }
    const data = this.head.data; // head를 head의 다음 것으로 바꿔주고 뺀 data를 return
    this.head = this.head.next;
    this.length--;

    return data;
  }
  // head를 반환하는 함수
  front() {
    // head가 있을 경우 head의 data를 반환.
    return this.head && this.head.data;
  }
  //큐의 모든 원소를 반환하는 함수
  getQueue() {
    if (!this.head) return false;
    let node = this.head;
    const array = [];
    while (node) {
      // node가 없을 때까지 array에 추가한 후 반환해준다.
      array.push(node.data);
      node = node.next;
    }
    return array;
  }
}
const solution = () => {
  goA();
  goB();
};

const goA = () => {
  const queue = new Queue();
  queue.enqueue({ now: A, time: 0 });

  visitedA[A][0] = 0;
  while (queue.length !== 0) {
    const cur = queue.dequeue();
    const dist = 1 << cur.time;

    for (let i = 0; i < 2; i++) {
      let next = cur.now + dist * d[i];
      if (1 <= next && next <= N && visitedA[next][cur.time + 1] === -1) {
        visitedA[next][cur.time + 1] = cur.time + 1;
        queue.enqueue({ now: next, time: cur.time + 1 });
      }
    }
  }
};

const goB = () => {
  const queue = new Queue();
  queue.enqueue({ now: B, time: 0 });
  visitedB[B][0] = 1;
  while (queue.length !== 0) {
    const cur = queue.dequeue();
    const dist = 1 << cur.time;
    if (visitedB[cur.now][cur.time] === visitedA[cur.now][cur.time]) {
      ans = cur.time;
      return;
    }
    for (let i = 0; i < 2; i++) {
      let next = cur.now + dist * d[i];
      if (1 <= next && next <= N && visitedB[next][cur.time + 1] === -1) {
        visitedB[next][cur.time + 1] = cur.time + 1;
        queue.enqueue({ now: next, time: cur.time + 1 });
      }
    }
  }
};
require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    [N, A, B] = line.split(' ').map(v => parseInt(v));
  })
  .on('close', () => {
    solution();
    console.log(ans);
    process.exit();
  });
