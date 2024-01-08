// 버튼을 눌렀을 때 숫자 6자리가 랜덤으로 나와야한다.
// 버튼을 눌렀을 때 3분 타이머가 실행되어야 한다. 0이 되면 종료.

// let a = () => {}

let isStarted = false;

let counting = () => {
    if(isStarted === false) {
        // 타이머가 작동 중이 아닐 때
        isStarted = true
        document.getElementById("finish").disabled = false

        const random = String( Math.floor( Math.random() * 1000000)).padStart(6,"0")
        document.getElementById("number").innerText = random

        let time = 180
        let timer

        timer = setInterval(function() {
            if (time >= 0) {
                let min = Math.floor( time/60 )
                let sec = String(time % 60).padStart(2,"0")
                document.getElementById("timer").innerText = min + ":" + sec
                time = time - 1
            } else {
                document.getElementById("finish").disabled = true
                isStarted = false
                console.log("타이머 작동 후 0이 됨") // 이걸 찍어보면 else의 코드 위의 두줄이 계속 매초마다 실행되는 것을 볼 수 있다. -> 잘못됨
                clearInterval(timer) // 타이머에 할당을 풀어주는 역할 -> 이러면 제대로 종료 됨. 매초마다 실행되지 않음.
            }
        }, 1000)
    } else {
        // 타이머가 작동 중일 때
        document.getElementById("finish").disabled = true
        
    }
}
