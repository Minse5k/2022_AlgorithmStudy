function solution(clothes) {
    let answer = 1;
    let obj = {};
    
    for(let i = 0; i < clothes.length; i++) {
        if(!obj[clothes[i][1]]) { obj[clothes[i][1]] = 1; }
        else {
            obj[clothes[i][1]] = obj[clothes[i][1]] + 1;
        }
    }
    for(let key in obj) {
        answer *= (obj[key] + 1);
    }
    
    return (answer - 1);
}
