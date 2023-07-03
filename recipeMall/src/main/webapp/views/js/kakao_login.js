$(function () {
  //Kakao.init('5b8b7aac37491a8185479f0c8694adda'); // 사용하려는 앱의 JavaScript 키 입력
  Kakao.init('fac8fe27cd06b11a0af5701b4431195e');
  console.log(console.log(Kakao.isInitialized()));
});

function loginWithKakao() {
    Kakao.Auth.authorize({
      //redirectUri: 'https://developers.kakao.com/tool/demo/oauth',
      redirectUri: 'https://localhost:8090/recipeMall/loginProc/kakao/oauth'
    });
}
  // 아래는 데모를 위한 UI 코드입니다.
displayToken()

function displayToken() {
    var token = getCookie('authorize-access-token');

    if(token) {
      Kakao.Auth.setAccessToken(token);
      Kakao.Auth.getStatusInfo()
        .then(function(res) {
          if (res.status === 'connected') {
            /*document.getElementById('token-result').innerText
              = 'login success, token: ' + Kakao.Auth.getAccessToken();*/
              $('input[name=token]').attr('value', Kakao.Auth.getAccessToken());
          }
        })
        .catch(function(err) {
          Kakao.Auth.setAccessToken(null);
        });
    }
}

function getCookie(name) {
    var parts = document.cookie.split(name + '=');
    if (parts.length === 2) { return parts[1].split(';')[0]; }
}