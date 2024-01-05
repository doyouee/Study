setTimeout(function () {
    console.log("펑")
}, 3000)
1
// 펑


setInterval(function() {
    console.log("1초가 지남")
}, 1000)
2
// 1초가 지남



let timer = 10
setInterval(function() {
    if(time >= 0) {
        console.log(timer)
        timer = timer -1
    }
}, 1000)
// 10
// 9
// 8
// 7
// 6
// 5
// 4
// 3
// 2
// 1
// 0




let time = 180
setInterval(function() {
    if (time >= 0) {
        let min = Math.floor( time/60 )
        let sec = String(time % 60).padStart(2,"0")
        console.log( min + ":" + sec)
        time = time - 1
    }
}, 1000)
// 3:00
// 2:59
// 2:58
// 2:57
// 2:56
// 2:55
// 2:54
// 2:53
// 2:52