
function xssFilte(inputStr) {
	inputStr = inputStr.replace(/\'/g, "");// 替换半角单引号为全角单引号
	inputStr = inputStr.replace(/\"/g, "");// 替换半角双引号为全角双引号
	inputStr = inputStr.replace(/\</g, "[");// 替换左尖括号
	inputStr = inputStr.replace(/\>/g, "]");// 替换右尖括号
	inputStr = inputStr.replace(/\//g, "");// 替换结束符
	return inputStr;
}

function validateUrl(inputStr) {
	var urlreg = /^((https|http|ftp|rtsp|mms)?:\/\/)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
	if (!inputStr.test(url)) {
		return true;
	} else {
		return false;
	}

}