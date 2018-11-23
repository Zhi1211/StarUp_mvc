//  =====================================
//  作品進階發布隱藏


$("input[name='moreWorksInfo']").on("click", function(){
//    $(".template").toggle(this.value === "true" && this.checked);
    $('#advancedForm').toggleClass("hidden");
});


//  =====================================
//  商品與作品預覽照片


function doFirst() {
	$("#productImage").change(function() {
		readURL(this);
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewImg").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$("#worksImg").change(function() {
		readURL1(this);
	});
	function readURL1(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewImg_1").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$("#captionImg_1").change(function() {
		readURL2(this);
	});
	function readURL2(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewImg_2").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$("#captionImg_2").change(function() {
		readURL3(this);
	});
	function readURL3(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewImg_3").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$("#captionImg_3").change(function() {
		readURL4(this);
	});
	function readURL4(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewImg_4").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}


}

// -------------------------------------
// 購物車數量加減
$(function() {
	var t = $("#text_box");
	$("#add").click(function() {
		t.val(parseInt(t.val()) + 1)
		setTotal();
	})
	$("#min").click(function() {
		t.val(parseInt(t.val()) - 1)
		setTotal();
	})
	function setTotal() {
		var tt = $("#text_box").val();
		var pbinfoid = $("#pbinfoid").val();
		if (tt <= 0) {
			alert('輸入的值錯誤');
			t.val(parseInt(t.val()) + 1)
		} else {
			// window.location.href =
			// "shopping!updateMyCart.action?pbinfoid="+pbinfoid+"&number="+tt;
		}
	}
})
// -------------------------------------
window.addEventListener('load', doFirst);