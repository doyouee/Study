const startWord = () => {
    let myword = document.getElementById("myword").value
    let word = document.getElementById("word").innerText
    let lastword = word[word.length - 1]
    let firstword = myword[0]
    if(lastword === firstword) {
        document.getElementById("result").innerText = "성공!"
        document.getElementById("word").innerText = myword
        document.getElementById("myword").value = ""
    } else {
        document.getElementById("result").innerText = "실패!"
        document.getElementById("myword").value = ""
    }
}