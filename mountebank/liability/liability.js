function inject(request,state){
const responseDirPath = require('path').dirname(__dirname) + '/../../../liability/';
    return require(responseDirPath + "liabilityResponse.json");
}