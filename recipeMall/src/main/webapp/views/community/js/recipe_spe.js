//로그인 안한 사람의 좋아요 수행시 
 $('.btn_like').click(function() {
    let logId = document.getElementsByName("id")[0].value;
    if (logId === null || logId === "") {
      event.preventDefault();
      alert("로그인 후 눌러주세요");
    }
  });
  
 //동영상 iframe
let youtubeLink =  $('.video_area iframe').attr('src');
$('.video_area iframe').attr('src', fn_youtube(youtubeLink)); 
 
function extractVideoId(youtubeLink) {
  var videoId = '';
  // 'v=' 다음에 오는 값 추출
  var startIndex = youtubeLink.indexOf('v=') + 2;
  var endIndex = youtubeLink.indexOf('&', startIndex);
  
  if (endIndex === -1) {
    // '&' 문자가 없을 경우
    videoId = youtubeLink.substring(startIndex);
  } else {
    videoId = youtubeLink.substring(startIndex, endIndex);
  }
  return videoId;
}

function fn_youtube(youtubeLink){
  let videoId = extractVideoId(youtubeLink);	
  let embedLink = 'https://www.youtube.com/embed/' + videoId;
  return embedLink;
}