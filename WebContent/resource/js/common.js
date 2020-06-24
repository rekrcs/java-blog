console.clear();

function MobileSideBar__init() {
  $('.mobile-top-bar .btn-toggle-mobile-side-bar').click(function() {
    var $this = $(this);
   if($this.hasClass('active')) {
    $this.removeClass('active')
    $('.mobile-side-bar').removeClass('active');
   }
   else {
     $this.addClass('active')
     $('.mobile-side-bar').addClass('active');
   }
  });
}

$(function() {
  MobileSideBar__init();
});

/*
function a() {
  $('div.abc').css('color', 'red');
}

$(function() {
  // 프로그램 시작시에 실행되어야 하는 함수실행 코드 넣기
  a();
});
*/

/*
// 특정 스크롤바 위치로 이동
setTimeout(function() {
  $(window).scrollTop(0);
}, 500);
*/