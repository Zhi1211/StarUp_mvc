<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<jsp:useBean id="ProdBean" class="com.web.store.repository.impl.ProductRepositoryImpl" />

<!--  
  // 程式功能: 
  //  1. 顯示所有書籍(商品)資訊，書籍資訊以分頁的方式顯示，每頁顯示四筆書籍。
  //     使用者可以按下『第一頁』、『前一頁』、『下一頁』與『最後頁』逐頁地
  //     瀏覽商品。
  //  2. 提供『新增書籍』資料、『修改書籍資料』與『刪除某本書籍』的功能。
  //     
  //  3. 當使用者要新增某本書籍資料時，直接按下右上角『新增書籍』的超連結便可
  //     進入『新增書籍資料』的畫面(由BookInsert.php提供)，輸入新的書籍資料。
  //     當使用者要修改某本書籍資料時，可直接點選該本書籍的『書名』超連結，便
         可進入『更新書籍資料』的畫面(由BookUpdate.php提供)，可對該本書籍進行
         資料修改或刪除紀錄。
-->
  
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/header.jsp" />

<div style="display:flex; justify-content:center;">
	<nav class="nav nav-tabs ">   
		<a class="nav-link active">&nbsp;&nbsp;※ 請點選商品圖片，進入修改頁面 ※</a>
		<a class="nav-link active" href="addProduct">&nbsp;&nbsp;※ 新增商品 ※</a>   
	</nav>
</div>
<br><br> 
     <!-- 商品列表 -->   
            <div class="container-fluid col-lg-12" style=" display: flex; flex-wrap: wrap; justify-content:center;">
            <!-- 1.顯示所有商品-------------------------------------------------------------- -->
  			
  			 <c:forEach var="product"  items="${products}" >
               
                <div class="productboxMD" onclick="location.href='<c:url value='/modifyProduct?id=${product.prod_id}'/>';">
                        <div class="imgFrameMD">
                            <img class="prodImg" src="<c:url value='/getPicture/${product.prod_id}'/>"> 
                        </div> 										
                        <div class="textFrameMD">    
                           <FORM  action="<c:url value='Shopping.do' />" method="POST">  
                            <p class="prodName">${product.prodName}</p>
                            <p class="prodCompany">By ${product.prodCompany} </p>
                            <div class="prodIntro">${product.prodIntro}</div>   
											 
			               <!-- 這些隱藏欄位都會送到後端 -->      
			               <Input type='hidden' name='prodId' value='${product.prod_id}'>
			               <Input type='hidden' name='prodName' value='${product.prodName}'>
			               <Input type='hidden' name='prodCompany' value='${product.prodCompany}'>
			               <Input type='hidden' name='prodIntro' value='${product.prodIntro}'><P/>
			               <Input type='hidden' name='discount' value='${product.prodPrice}'><P/>
			               <Input type='hidden' name='pageNo' value='${param.pageNo}'><P/>    			            
				               <h4 class="prodPrice">NT$ ${product.prodPrice}</h4>				  		                        				              			            
			           </FORM>  		             	 		         
			         </div> 
			         <div>    
			           			<button class="btn btn-danger" style="margin-top:10px; margin-left:45px;"><i class="fas fa-trash-alt" ></i></button>	
			           		</div>	
                </div>   
               </c:forEach>
          
          

<jsp:include page="/fragment/footer.jsp" />