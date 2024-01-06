// 버튼을 눌렀을 때 숫자 6자리가 랜덤으로 나와야한다.
// 버튼을 눌렀을 때 3분 타이머가 실행되어야 한다. 0이 되면 종료.

// let a = () => {}

let counting = () => {
    const random = String( Math.floor( Math.random() * 1000000)).padStart(6,"0")
    document.getElementById("number").innerText = random
    let time = 180
    setInterval(function() {
        if (time >= 0) {
            let min = Math.floor( time/60 )
            let sec = String(time % 60).padStart(2,"0")
            document.getElementById("timer").innerText = min + ":" + sec
            time = time - 1
        } else {
            document.getElementById("finish").disabled = true
        }
    }, 1000)
}
