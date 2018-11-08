<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<jsp:include page="/fragment/header.jsp" />
    <!-- 商品輪播 -->
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style="height:250px; margin: 0px">
                <div class="carousel-inner" style="overflow:hidden; height:250px; margin:0px;">
                  <div class="carousel-item active" >
                    <img class="d-block w-100" src="image/sew.jpg" alt="First slide" style="width:100%;">
                  </div>
                  <div class="carousel-item">   
                    <img class="d-block w-100" src="image/wps.jpg" alt="Second slide">
                  </div>
                  <div class="carousel-item">
                    <img class="d-block w-100" src="image/gos.jpg" alt="Third slide">  
                  </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>
        <!-- -------------------------navgation end----------------------------- -->
        <div class="content container-fluid" style="display: flex; padding: 0px;">
            <!-- 側邊欄 -->
            <div class="section col-lg-2" style="width:100px; padding: 20px 0px;">
            <!-- 設定管理者帳號starup@gamil.com才能進入商城的 維護-商品管理ProductMaintainList.jsp-->
                <nav class="nav flex-column nav-tabs">
                    <a class="nav-link active" href="<c:url value='/_03_listProducts/DisplayPageProducts'/>">分類商品</a>
                    <a class="nav-link" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&prod_type=文創周邊'/>" style="color:#dfc2ef; font-weight:400;">
                    <i class="fas fa-gift"></i>  文創周邊</a>
                    		
                    			<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=暖心'/>">暖心小物</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=城市'/>">城市紋理</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=質感'/>">質感選物</a>
    						
                    <a class="nav-link" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&prod_type=原創桌遊'/>" style="color:#dfc2ef; font-weight:400;">
                    <i class="fas fa-user-astronaut"></i>  原創桌遊</a>
                    		
                    			<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=1~3人'/>">建議遊玩：1 ~ 3人</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=3~5人'/>">建議遊玩：3 ~ 5人</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=5~10人'/>">建議遊玩：5 ~ 10人</a>
    						
                </nav>
           
                <nav class="nav flex-column nav-tabs">
                        <a class="nav-link active">購物專區</a>
                        <a class="nav-link" href="<c:url value='/showShoppingCart' />" style="color:#dfc2ef; font-weight:400;">結帳</a>
                        <a class="nav-link" href="#" style="color:#dfc2ef; font-weight:400;">訂單查詢</a>
                </nav>
            </div>
            <!-- 商品列表 -->
            <div class="container col-lg-10" style=" display: flex; justify-content: space-between; flex-wrap: wrap;">
            <!-- 1.顯示所有商品-------------------------------------------------------------- -->
  			
  			 <c:forEach var="product"  items="${products}" >
               
                <div class="productbox" onclick="location.href='<c:url value='/productDetail?id=${product.prod_id}'/>';">
                        <div class="imgFrame">
                            <img class="prodImg" src="<c:url value='/getProductPicture/${product.prod_id}'/>">
                        </div> 										
                        <div class="textFrame">    
<%--                            <FORM  action="<c:url value='Shopping.do' />" method="POST">   --%>
                           <FORM  action="<c:url value='/addProductToCart' />" method="POST">  
                            <p class="prodName">${product.prodName}</p>
                            <p class="prodCompany">By ${product.prodCompany} </p>
                            <div class="prodIntro">${product.prodIntro}</div>   
						   <hr>		
			               <!-- 這些隱藏欄位都會送到後端 -->      
<%-- 			               <Input type='hidden' name='prodId' value='${product.prod_id}'> --%>
<%-- 			               <Input type='hidden' name='prodName' value='${product.prodName}'> --%>
<%-- 			               <Input type='hidden' name='prodCompany' value='${product.prodCompany}'> --%>
<%-- 			               <Input type='hidden' name='prodIntro' value='${product.prodIntro}'><P/> --%>
<%-- 			               <Input type='hidden' name='discount' value='${product.prodPrice}'><P/> --%>
<%-- 			               <Input type='hidden' name='pageNo' value='${param.pageNo}'><P/> --%>
			               
			               <Input type='hidden' name='prod_id' value='${product.prod_id}'><P/>
			               <Input type='hidden' name='prodName' value='${product.prodName}'><P/>
			               <Input type='hidden' name='prodPrice' value='${product.prodPrice}'><P/>
			               <Input type='hidden' name='prodCompany' value='${product.prodCompany}'><P/>
			               <Input type='hidden' name='prodIntro' value='${product.prodIntro}'><P/>
			               <Input type='hidden' name='prodType' value='${product.prodType}'><P/>
			               <Input type='hidden' name='discount' value='${product.prodPrice}'><P/>
			               <Input type='hidden' name='pageNo' value='${param.pageNo}'><P/> 
			               <Input type='hidden' name='qty' value='1'><P/>
			                   
			               <div class="priceBlock">
				               <h4 class="prodPrice">NT$ ${product.prodPrice}</h4>			                    
				               <button class="addCartBtn" type="submit">
				               			<a  class="action-button shadow animate yellow"><i class="fa fa-cart-plus fa-lg"  aria-hidden="true" style="line-height:35px; color:#fff;"></i></a>			               		
				               	</button>			  			           				               
			               </div>
			           </FORM>
			         </div>
                </div>   
               </c:forEach>
          
          
                <!-- 頁數 -->
                <div id="product" style="display: flex; width: 100%; justify-content: center; ">
                        <ul class="pagination">
                       <c:set var="page" value="${pageNo}"/>
                        <c:set var="limitPage" value="${totalPages}"/>
                        <!--  -->
                        <c:choose>
                        	<c:when test="${page > 1}">
	                      		<li class="page-item">
	                           			 <a class="page-link" href="<c:url value='DisplayPageProducts?pageNo=${page - 1}' />">&laquo;</a>
	                       		 </li>                        	
                        	</c:when>
                        	<c:otherwise>
                        		<li class="page-item disabled">
	                           			 <a class="page-link disabled" href="#">&laquo;</a>
	                       		 </li>
                        	</c:otherwise>
                        </c:choose>
                        <!--  -->
	                      		<c:forEach var="i" begin="1" end="${totalPages}" step="1">
                        <c:choose>
		                   <c:when test="${page == i}">
			                         <li class="page-item active">
			                          		  <a class="page-link" href="<c:url value='DisplayPageProducts?pageNo=${i}' />">${i}</a>
			                         </li>                        	
	                        </c:when>
	                        <c:otherwise>
	                        		  <li class="page-item">
			                          		  <a class="page-link" href="<c:url value='DisplayPageProducts?pageNo=${i}' />">${i}</a>
			                         </li>                        		
	                        </c:otherwise>
                        </c:choose>
		                      	</c:forEach>                        	
                      	<!--  -->
                      	<c:choose>
                        	<c:when test="${page < limitPage}">
	                      		<li class="page-item">
	                           			 <a class="page-link" href="<c:url value='DisplayPageProducts?pageNo=${page + 1}' />">&raquo;</a>
	                       		 </li>                        	
                        	</c:when>
                        	<c:otherwise>
                        		<li class="page-item disabled">
	                           			 <a class="page-link" href="#">&raquo;</a>
	                       		 </li>
                        	</c:otherwise>
                        </c:choose>
                        </ul>
                </div>
            </div>
        </div>
     <jsp:include page="/fragment/footer.jsp" />