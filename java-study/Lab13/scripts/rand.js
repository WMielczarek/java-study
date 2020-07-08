function nextMove(myArr, enemyArr) {
    var myPlay = Math.floor(Math.random() * myArr.size());
    return myArr.remove(myPlay);
}