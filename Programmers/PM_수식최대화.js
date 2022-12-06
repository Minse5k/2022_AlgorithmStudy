function solution(expression) {
    const operatorPrecedence = [['*', '+', '-'], ['*', '-', '+'], ['+', '*', '-'], ['+', '-', '*'], ['-', '*', '+'], ['-', '+', '*']];
    
    let stringNum = "";
    const numArray = [];
    const operatorArray = [];

    for(const value of expression)  {
        if(value === '+' || value === '-' || value === '*') {
            operatorArray.push(value);
            numArray.push(parseInt(stringNum));
            stringNum = ""
            continue;
        }
        stringNum += value;
    }
    numArray.push(parseInt(stringNum));
    let max = -1;
    
    for(let i = 0; i < operatorPrecedence.length ; i++) {
        const number = [...numArray];
        const operator = [...operatorArray];
        
        for(let j = 0; j < 3; j++) {
            if(!expression.includes(operatorPrecedence[i][j])) continue;
                calculation(number, operator, operatorPrecedence[i][j])
        }
        if(max < Math.abs(number[0])) max = Math.abs(number[0]);
    }
    
    return max;
}

function calculation(numArray, operatorArray, nowOperator) {
    for(let i = 0; i < operatorArray.length; i++) {
        if(!operatorArray.includes(nowOperator)) return;
        if(operatorArray[i] !== nowOperator) continue;
        switch(nowOperator) {
            case '+':
                numArray[i] += numArray[i + 1];
                break;
            case '*':
                numArray[i] *= numArray[i + 1];
                break;
            case '-':
                numArray[i] -= numArray[i + 1];
                break;
        }
        numArray.splice(i + 1, 1);
        operatorArray.splice(i, 1);
        i--;
    }
}
