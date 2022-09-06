function solution(enroll, referral, seller, amount) {
  const answer = Array.from({ length: enroll.length }, () => 0);

  for (let i = 0; i < seller.length; i++) {
    let curName = seller[i];
    let curPrice = amount[i] * 100;
    let sellerIndex = 0;
    let hasParent = true;

    for (let j = 0; j < enroll.length; j++) {
      if (enroll[j] === curName) {
        sellerIndex = j;
        break;
      }
    }

    while (hasParent) {
      const distributionPrice = parseInt(curPrice * 0.1);
      answer[sellerIndex] += curPrice - distributionPrice;
      if (referral[sellerIndex] === '-') {
        hasParent = false;
      } else {
        curName = referral[sellerIndex];
        curPrice = distributionPrice;
        if (curPrice < 1) {
          hasParent = false;
        } else {
          for (let j = 0; j < enroll.length; j++) {
            if (enroll[j] === curName) {
              sellerIndex = j;
              break;
            }
          }
        }
      }
    }
  }

  return answer;
}

console.log(
  solution(
    ['john', 'mary', 'edward', 'sam', 'emily', 'jaimie', 'tod', 'young'],
    ['-', '-', 'mary', 'edward', 'mary', 'mary', 'jaimie', 'edward'],
    ['young', 'john', 'tod', 'emily', 'mary'],
    [12, 4, 2, 5, 10],
    [360, 958, 108, 0, 450, 18, 180, 1080],
  ),
);
