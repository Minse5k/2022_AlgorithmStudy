const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const stringToNum = new Map();
const numToString = new Map();
const setMap = () => {
  stringToNum.set("ZERO", 0);
  stringToNum.set("ONE", 1);
  stringToNum.set("TWO", 2);
  stringToNum.set("THREE", 3);
  stringToNum.set("FOUR", 4);
  stringToNum.set("FIVE", 5);
  stringToNum.set("SIX", 6);
  stringToNum.set("SEVEN", 7);
  stringToNum.set("EIGHT", 8);
  stringToNum.set("NINE", 9);

  numToString.set(0, "ZERO");
  numToString.set(1, "ONE");
  numToString.set(2, "TWO");
  numToString.set(3, "THREE");
  numToString.set(4, "FOUR");
  numToString.set(5, "FIVE");
  numToString.set(6, "SIX");
  numToString.set(7, "SEVEN");
  numToString.set(8, "EIGHT");
  numToString.set(9, "NINE");
};

const isOperator = (char) => {
  return ["-", "=", "x", "/", "+"].includes(char);
};

const getAns = (resultNum, expressionArr) => {
  console.log(expressionArr.join(""));
  let resultStr = "";
  if (resultNum < 0) {
    resultStr += "-";
  }
  const resultNumStr = Math.abs(resultNum).toString();

  for (let i = 0; i < resultNumStr.length; i++) {
    resultStr += numToString.get(parseInt(resultNumStr[i]));
  }
  console.log(resultStr);
};

const solution = () => {
  setMap();

  let str = "";
  let expressionStr = "";
  let equalOperatorCnt = 0;

  for (const char of input[0]) {
    str += char;
    if (stringToNum.has(str)) {
      expressionStr += stringToNum.get(str);
      str = "";
    } else if (isOperator(str)) {
      if (expressionStr[expressionStr.length - 1] === ",") {
        console.log("Madness!");
        return;
      }

      expressionStr += ",";
      expressionStr += str;
      if (str !== "=") {
        expressionStr += ",";
      } else {
        equalOperatorCnt++;
      }

      str = "";
    }
  }
  if (expressionStr === "" || equalOperatorCnt !== 1) {
    console.log("Madness!");
    return;
  }

  const expressionArr = expressionStr.split(",");
  let char = "";
  let resultNum = 0;
  for (let i = 0; i < expressionArr.length; i++) {
    if (i % 2 !== 0) {
      char = expressionArr[i];
    } else {
      if (i === 0) {
        resultNum = parseInt(expressionArr[i]);
      } else {
        switch (char) {
          case "+":
            resultNum += parseInt(expressionArr[i]);
            break;
          case "-":
            resultNum -= parseInt(expressionArr[i]);
            break;
          case "x":
            resultNum *= parseInt(expressionArr[i]);
            break;
          case "/":
            resultNum = parseInt(resultNum / parseInt(expressionArr[i]));
            break;
        }
      }
    }
  }

  getAns(resultNum, expressionArr);
};

solution();
