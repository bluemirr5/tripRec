/**
 * Created by rang on 2015-09-12.
 *
 */

//회원 클래스
var Member = (function () {
    return function () {
        this.id='';
        this.name='';
        this.password='';
        this.sex='';
        this.nickName='';
        this.birth=null;
        this.joinTime=null;
    };
})();

function sendPost(url, arg){
    // Form객체를 만들고 속성값들을 추가함
    var oForm = document.createElement('form');
    oForm.method = "post";
    oForm.action = url;

    var equIndex = 0;
    var argArr = arg.split("&");
    for(var i=0 ; i<argArr.length; i++){
        var oInputHidden = document.createElement("input");
        oInputHidden.type		= "hidden";
        oInputHidden.name		= argArr[i].split("=")[0];
        equIndex = argArr[i].indexOf("=") + 1;
        oInputHidden.value	= argArr[i].substr(equIndex);
        oForm.appendChild(oInputHidden);
    }
    oForm.appendChild(oInputHidden);
    document.body.appendChild(oForm);
    oForm.submit();
}