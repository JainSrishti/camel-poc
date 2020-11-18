function inject(request,state){
const responseDirPath = require('path').dirname(__dirname) + '/../../../idm/';
const v = Math.floor((Math.random() * 10) + 1);

if (v % 3 == 0){
    return require(responseDirPath + "idmFailure.json");
}

    return require(responseDirPath + "idmResponse.json");
}