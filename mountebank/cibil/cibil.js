function inject(request,state){
const responseDirPath = require('path').dirname(__dirname) + '/../../../cibil/';
const v = Math.floor((Math.random() * 10) + 1);
console.log(JSON.parse(request.body)[0]["FNAME"])
const c = JSON.parse(request.body)[0]["FNAME"]
if (v % 3 == 0)
{
return require(responseDirPath + "cibilresponsefailure.json");
}
if (c == "Shashanka"){
    return require(responseDirPath + "cibilResponseOne.json");
}
return require(responseDirPath + "cibilResponseTwo.json");
}