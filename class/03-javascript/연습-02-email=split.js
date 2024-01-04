const id = ["a","b","c","1"]
undefined
console.l
undefined
console.log(id)
VM555:1 (4) ['a', 'b', 'c', '1']
undefined
let set
undefined
const email = "abcd@naver.com"
undefined
email.includes("@")
true
email.split("@")
(2) ['abcd', 'naver.com']
userMail
'abcd'
let comp = email.split("@")[1]
undefined
comp
'naver.com'
let maskingMail = []
undefined
maskingMail.push(userMail[0])
1
maskingMail.push(userMail[1])
2
maskingMail.push(userMail[2])
3
maskingMail.push(userMail[3])
4
maskingMail
(4) ['a', 'b', 'c', 'd']
maskingMail.push("*")
5
maskingMail.push("*")
6
maskingMail.push("*")
7
maskingMail
(7) ['a', 'b', 'c', 'd', '*', '*', '*']
maskingMail.joi
undefined
maskingMail.join("")
'abcd***'
result
let result = maskingMail.join("")+"@"+comp
undefined
result
'abcd***@naver.com'