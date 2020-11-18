function inject(request,state){
const responseDirPath = require('path').dirname(__dirname) + '/../../../blaze/';
const v = Math.floor((Math.random() * 10) + 1);
console.log(JSON.parse(request.body)[0]["type"])
const c = JSON.parse(request.body)[0]["type"]
if (v % 11 == 0){
    return require(responseDirPath + "blazeFailure.json");
}
if (c == "decision"){
    return require(responseDirPath + "blazeDecisionCalculationResponse.json");
}
return require(responseDirPath + "blazeVariableCalculationResponse.json");
}