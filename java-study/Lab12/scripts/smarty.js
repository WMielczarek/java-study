function goodEnoughMove(myArr, enemyBest) {
    var goodEnoughIndex = 0;
    var goodEnoughValue = 9999;
    for (var i = 0; i < myArr.size(); i++) {
        if (myArr.get(i) >= enemyBest && myArr.get(i) <= goodEnoughValue) {
            goodEnoughValue = myArr.get(i);
            goodEnoughIndex = i;
        }
    }
    return goodEnoughIndex;
}

function worstMove(myArr) {
    var worstIndex = 0;
    var worstValue = 9999;
    for (var i = 0; i < myArr.size(); i++) {
        if (myArr.get(i) < worstValue) {
            worstValue = myArr.get(i);
            worstIndex = i;
        }
    }
    return worstIndex;
}

function nextMove(myArr, enemyArr) {

    var removeIndex = 0;

    var myBest = 0;
    var enemyBest = 0;
    for (var i = 0; i < myArr.size(); i++) {
        if (myArr.get(i) > myBest) {
            myBest = myArr.get(i);
        }
        if (enemyArr.get(i) > enemyBest) {
            enemyBest = enemyArr.get(i);
        }
    }
    if (myBest > enemyBest) {
        removeIndex = goodEnoughMove(myArr, enemyBest);
    }
    else {
        removeIndex = worstMove(myArr);
    }
    return myArr.remove(removeIndex);
}
