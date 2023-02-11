function getDeleteZeroCnt(array) {
    return array.reduce((pre, arr) => {
        return arr === '0' ? [pre[0] + 1, pre[1]] : [pre[0], pre[1] + 1]
    }, [0, 0])

}

function getBinaryNumber(num) {
    let string = "";
    
    while(num > 0) {
        string = num % 2 + string;
        num = parseInt(num / 2);
    }
    
    return string.split('');
}

function solution(s) {
    
    let countDeleteZero = 0;
    let countCycle = 0;
    let numArray = s.split('');
    
    while(numArray.length > 1) {
        let cntZero = 0;
        let cntOne = 0;
        
        [cntZero, cntOne] = getDeleteZeroCnt(numArray);
        countDeleteZero += cntZero;
      
        numArray = getBinaryNumber(cntOne);
        countCycle++;
    }
    
    return [countCycle, countDeleteZero];
}
