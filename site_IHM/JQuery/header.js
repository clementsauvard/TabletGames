$( document ).ready(function() {
    
    if ($(this).scrollTop() > 340) { 
          $('header').css("visibility","visible");
      } else {
            $('header').css("visibility","hidden");
      }
});
$(function(){
   $(window).scroll(function () {//Au scroll dans la fenetre on déclenche la fonction
      if ($(this).scrollTop() > 340) { 
          $('header').css("visibility","visible");
      } else {
            $('header').css("visibility","hidden");
      }
   });
 }); 