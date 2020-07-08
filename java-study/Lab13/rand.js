function nextMove(myArr) {
    var myPlay = Math.floor(Math.random() * myArr.size());
    return myArr.remove(myPlay);
}