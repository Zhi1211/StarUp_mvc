<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Footer -->
		<div style="padding:50px"></div>  
        <footer class="page-footer font-small"  style="z-index:10;background-color:rgba(51, 51, 51, 0.9);">
       <!-- Copyright -->
           <div class="footer-copyright py-3 text-info" style="cursor:pointer">
           		<a>使用規範 ｜</a><a id="opinion" class="text-info" style="text-decoration:none;">意見回饋 ｜</a><a>關於我們 ｜</a>
           		© 2018 Copyright :  NTUT JAVA009 Team2   
           </div>
       <!-- Copyright -->
        </footer>
       <!-- Footer -->   
         
       <script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>          
       <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
   	   <script src="${pageContext.request.contextPath}/js/action.js" type="text/javascript"></script>
   	   <script>
			$('input[name="prodType"]').bind('change',function(){
			var showOrHide = ($(this).val() == '文創周邊') ? true : false;
			$('#type1').toggle(showOrHide);
			});
			$('input[name="prodType"]').bind('change',function(){
				var showOrHide = ($(this).val() == '原創桌遊') ? true : false;
				$('#type2').toggle(showOrHide);
				});
			$(document).ready(function(){
			    $(function(){ $('#opinion').click(function(){ 
			        $('html,body').animate({scrollTop:$('#op').offset().top}, 500);});  
			    }); 
			}); 
		</script> 
   </body>
</html>