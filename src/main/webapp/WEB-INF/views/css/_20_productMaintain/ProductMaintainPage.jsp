<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="css/index_main.css" rel="stylesheet">  
<jsp:include page="/fragment/header.jsp" />

        <!-- -------------------------navgation end----------------------------- -->
      
      <form:form modelAttribute="productBean" method="POST"  enctype="multipart/form-data">
            
               
            <div class="container col-lg-10" style=" display: flex; ">        
          		 <div class="prodImg col-lg-7" style="max-width:100%;max-height:100%;z-index:0;margin: 10px 0px 30px 20px; background-color: rgba(255, 255, 255, 0.699); box-shadow: 1px 1px 3px black; border-radius: 5px;">
                    <div id="dropZone" style="margin:10px auto;max-width:100%;max-height:100%;overflow:hidden">  
                        <img alt="" src="<c:url value='/getProductPicture/${product.prod_id}'/>" id="previewImg" style="width:100%">
                    	  <label for="photo">請選擇要上傳的圖片</label>
            			  <form:input id="productImage" type="file" class="form-control-file" name="productImage" path="productImage" aria-describedby="fileHelp"/>
                    </div>    
                  
                   
              
                
                </div>  
                <div class="prodInfo col-lg-5" >     
                    <div class="upper" style="margin: 10px 0px; padding: 20px; background-color: rgba(255, 255, 255, 0.767); border-radius: 5px; box-shadow: 1px 1px 3px black">
                        <label>商品名稱：</label>  
                        <form:input type="text" id="prodName" path="prodName" style="margin-bottom:15px" class="form:input-large" value="${product.prodName}"/> 
                        <br> 
                        <label>廠商名稱：</label>       
                        <form:input type="text" name="prodCompany" path="prodCompany" style="margin-bottom:15px" value="${product.prodCompany}"/>    
                        <br>                
                        <label>商品分類：</label>     
                        <form:input type="text" name="prodType" path="prodType" style="margin-bottom:15px" value="${product.prodCategory}"/>  
                        <br>                
                        <label>分類目錄：</label>     
                        <form:input type="text" name="prodCategory" path="prodCategory" style="margin-bottom:15px" value="${product.prodType}"/>  
                        <br> 
                        <label>售價：NT$ </label>    
                        <form:input type="number" name="prodPrice" path="prodPrice" style="margin-bottom:15px" value="${product.prodPrice}"/>   
						<br>
						<br>
                        <label>庫存：</label>    
                        <form:input type="number" name="prodStock" path="prodStock" value="${product.prodStock}"/>   
                    </div>
                    <div class="lower" style=" margin: 10px 0px; padding: 20px 10px; background-color: rgba(255, 255, 255, 0.733); border-radius: 5px; box-shadow: 1px 1px 3px black">
	                    <p style="font-size:20px; color:#123d82; text-align:center; margin:0px;">商品說明</p>   
	                    <form:textarea rows="5" cols="55" name="prodIntro" path="prodIntro" placeholder="${product.prodIntro}"/>
                        <hr>                                  
                    </div>
                    
                    <input id="btnAdd" type='submit' class='btn btn-primary' value="送出" />              
                </div>         
            </div>
        </div>  
        </form:form>
         
     <jsp:include page="/fragment/footer.jsp" />