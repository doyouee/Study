const checkValidation = () => {
    let email = document.getElementById("email").value
    let pw1 = document.getElementById("pw1").value
    let pw2 = document.getElementById("pw2").value

    if(email&& pw1&& pw2) { // (email !== "" && pw1 !== "" && pw2 !== "") 와 동일한 값
        // 모든 input이 비어있지 않을 때
        document.getElementById("submit").disabled = false
    } else {
        // input 중 하나라도 비어있을 때
        document.getElementById("submit").disabled = true
    }
}