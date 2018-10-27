function doFirst(){
	$("#uploadImg").change(function(){
	  readURL(this);
	});
	function readURL(input){
	  if(input.files && input.files[0]){
	    var reader = new FileReader();
	    reader.onload = function (e) {
	       $("#previewImg").attr('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
	}
	}
// -------------------------------------
// 購物車數量加減
$(function(){
var t = $("#text_box");
$("#add").click(function(){
t.val(parseInt(t.val())+1)
setTotal();
})
$("#min").click(function(){
t.val(parseInt(t.val())-1)
setTotal();
})
function setTotal(){
var tt = $("#text_box").val();
var pbinfoid=$("#pbinfoid").val();
if(tt<=0){
alert('輸入的值錯誤');
t.val(parseInt(t.val())+1)
}else{
// window.location.href =
// "shopping!updateMyCart.action?pbinfoid="+pbinfoid+"&number="+tt;
}
}
})
// -------------------------------------
window.addEventListener('load',doFirst);