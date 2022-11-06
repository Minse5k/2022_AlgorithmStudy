const getTimeToMin = (time) => {
  const [hour, min] = time.split(":").map((v) => parseInt(v));
  return hour * 60 + min;
};

const LAST_TIME = getTimeToMin("23:59");

function solution(fees, records) {
  const car = {};
  records.forEach((v) => {
    const [time, carNum, type] = v.split(" ");
    const min = getTimeToMin(time);
    if (!car[carNum]) {
      car[carNum] = { sumTime: 0, time: min, carNum, type };
    } else {
      if (type === "OUT") {
        car[carNum] = {
          sumTime: car[carNum].sumTime + min - car[carNum].time,
          time: 0,
          carNum,
          type,
        };
      } else {
        car[carNum] = { ...car[carNum], time: min, carNum, type };
      }
    }
  });

  return Object.values(car)
    .sort((a, b) => parseInt(a.carNum) - parseInt(b.carNum))
    .map((v) => {
      if (v.type === "IN") {
        v.sumTime += LAST_TIME - v.time;
      }

      if (fees[0] >= v.sumTime) {
        return fees[1];
      }
      return fees[1] + Math.ceil((v.sumTime - fees[0]) / fees[2]) * fees[3];
    });
}
