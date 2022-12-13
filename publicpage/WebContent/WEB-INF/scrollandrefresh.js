function doRefreshAndScroll(speed) {
  $('#contain').load('file:///D:/AOT/New%20folder/Fight/test.html', function(){
    $('html, body').animate({ scrollTop: $(document).height() }, speed, function() {
      $(this).animate({ scrollTop: 0 }, speed);
    });
  }).fadeIn("slow")
}

var speed = 30000
setInterval(doRefreshAndScroll(speed), speed * 2)