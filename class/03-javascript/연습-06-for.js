const children = ["철수", "영희", "훈이"]

for(let i=0; i<3; i++) {
    console.log(children[i] + "입니다.")
}
// 철수입니다.
// 영희입니다.
// 훈이입니다.

for(let i=0; i<children.length; i++) {
    console.log(children[i] + "입니다.")
}
// 철수입니다.
// 영희입니다.
// 훈이입니다.



// ★★★★★★ 백틱 ! ★★★★★★★★
const fruits = [
    {number:1, title:"귤"},
    {number:2, title:"복숭아"},
    {number:3, title:"딸기"},
    {number:4, title:"체리"},
    {number:5, title:"토마토"}
]

for(let k=0; k<fruits.length; k++) {
    console.log(`과일 차트의 ${fruits[k].number}위는 ${fruits[k].title}입니다.`) 
    // 백틱(``) 사용하면 안에 변수와 문자 공백을 다 더할 수 있다.
    // 참고로 변수는 ${} 안에 담아서 사용한다.
}
// 1 귤
// 2 복숭아
// 3 딸기
// 4 체리
// 5 토마토