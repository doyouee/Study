// << 데이터 타입 연산자 간단하게 실습 >>

1+1
2

1+'1'
'11'

1-1
0

1-'1'
0

'밥' + '공기'
'밥공기'

123 == "123"
true

123 === "123"
false

true && true
true

true || false
true

true && false
false

!false
true


// ---------------------------------------------------------------
// << 조건문 실습  1>>
if(1+1 === 2) {
    console.log("정답입니다.")
} else {
    console.log("틀렸습니다.")
}
// 정답입니다.

if(!true) {
    console.log("정답입니다.")
} else {
    console.log("틀렸습니다.")
}
// 틀렸습니다.

if(0) {
    console.log("정답입니다.")
} else {
    console.log("틀렸습니다.")
}
// 틀렸습니다.

// false, 0, -0, 0n, "", null, undefined, NaN 는 모두 false와 같은 값이다.



// << 조건문 실습  2>>
const profile = {
    name : "철수",
    age : 12,
    school : "다람쥐초등학교"
}

if(profile.name === "철수") {
    if(profile.age >= 20) {
        console.log("성인입니다.")
    } else if(profile.age >= 8) {
        console.log("학생입니다.")
    } else if(profile.age > 0) {
        console.log("어린이입니다.")
    } else { // 이렇게 에러처리도 해줘야함
        console.log("잘못 입력하셨습니다.")
    }
}
// 학생입니다.