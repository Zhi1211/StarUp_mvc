<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/fragment/header.jsp" />
     
        <!-- -------------------------navgation end----------------------------- -->
        <div class="content container-fluid" style="display: flex; padding: 0px;">
            <!-- 側邊欄 --> 
              
      		 <div id="content" class="container" style="border:black solid 1px;">  
      		 	<div class="section col-lg-2" style="width:100px; padding: 20px 20px;margin-left:20px;">
	          <!--   設定管理者帳號starup@gamil.com才能進入商城的 維護-商品管理ProductMaintainList.jsp -->
	                <nav class="nav flex-column nav-tabs">
	                	<button id="btnProduct" class="nav-link active" v-on:click="getProducts">商品維護</button>  
	                	<button id="btnUser" class="nav-link active">會員資料</button>
	                	<button id="btnOrder" class="nav-link active">訂單資料</button>
	                	<button id="btnSysMail" class="nav-link active">系統信件</button>
	                	<button id="btnOpinion" class="nav-link active">意見回饋</button>  
	               	</nav>
      		 </div>         
      		 	<ul>    
  					<li v-for="product in products">  
						<div style="border:black solid 1px; display:flex; margin: 5px 0px;">  
							 <div style="width:200px; height:200px; overflow:hidden;">    
	   						 	<img style="width:100%;" v-bind:src="'getPicture/' + product.prod_id" />
	   						 </div>
	   						 <div style="margin-left:5px;"> 	   						 	
	   						 	 <div>商品編號：{{ product.prod_id }}</div>
		   						 <div>商品名稱：{{ product.prodName }}</div>
		   						 <div>廠商名稱：{{ product.prodCompany}}</div>
		   						 <div>商品介紹：{{ product.prodIntro}}</div>
		   						 <div>商品價格：{{ product.prodPrice}}</div>
		   						 <div>商品庫存：{{ product.prodStock}}</div>
		   						 <div>上架日期：{{ product.prodUpDate}}</div>   	
	   						 </div>   
							 <div style="flex-grow:2; text-align:right;"> 
							 	 <button class="btn btn-info"><a v-bind:href="'modifyProduct?id='+ product.prod_id">編輯</a></button>	 
							 	 <form action="#"  method="POST" >
										  <input type="hidden" name="_method" value="DELETE" />
							 	 		  <button class="btn btn-warning"><a v-on:click="confirmDelete">刪除</a></button>	
								 </form>
							 	
							 </div>					   						
						</div>  					  
  					</li>
				</ul> 
      		 </div>
      		  
      	<script src="js/jquery-3.3.1.min.js"></script>   
        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
        <script>
        		 var app = new Vue({
        		el:'#content',
        		data: {
        			products: [],
        		},
        		 methods: {
        			getProducts: function(){        			
        				var _self = this;
        				$.ajax({
            			    type: "GET",  
            			    url: 'productMaintain',
            			    dataType: 'json',
            	            success : function(data) { 
            	            	_self.products = data;
            	            	console.log(_self.products)
            	            }  
            	        });
        			},
        			confirmDelete: function(){
        				if(confirm('確定要刪除此筆商品？')){
        					$.ajax({
        						type:"POST",
        						url:'deleteProduct',
        						success:function(){
        							alert('商品刪除成功')
        						}
        					})
        				}
        			}
        			},
        		}) 
  	 
        	
        	
        </script>     
        <!----------------------------------------- ---------------------------------------->
        <!--  <div class="section col-lg-2" style="width:100px; padding: 20px 20px;margin-left:20px;">
	            設定管理者帳號starup@gamil.com才能進入商城的 維護-商品管理ProductMaintainList.jsp
	                <nav class="nav flex-column nav-tabs">
	                	<button id="btnProduct" class="nav-link active">商品維護</button>  
	                	<button id="btnWorks" class="nav-link active" >作品維護</button>
	                	<button id="btnUserInfo" class="nav-link active">會員資料</button>
	                	<button id="btnOrder" class="nav-link active">訂單資料</button>
	                	<button id="btnSysMail" class="nav-link active">系統信件</button>
	                	<button id="btnOpinion" class="nav-link active">意見回饋</button>  
	               	</nav>
      		 </div>    
      		 <div id="content" class="container col-lg-9 hidden" style="border:black solid 1px; background-color:white;">  
      		 	<ul>
  					<li v-for="item in items" v-if="items.length > 0">  
						<div style="border:black solid 1px; display:flex; margin: 5px 0px;">  
							 <div style="width:200px; height:200px; overflow:hidden;">  
	   						 	<img style="width:100%;" v-bind:src="'getPicture/' + item.prod_id" />
	   						 </div>
	   						 <div style="margin-left:5px;"> 
	   						 	 <div>商品編號：{{ item.prod_id }}</div>
		   						 <div>商品名稱：{{ item.prodName }}</div>
		   						 <div>廠商名稱：{{ item.prodCompany}}</div>
		   						 <div>商品介紹：{{ item.prodIntro}}</div>
		   						 <div>商品價格：{{ item.prodPrice}}</div>
		   						 <div>商品庫存：{{ item.prodStock}}</div>
		   						 <div>上架日期：{{ item.prodUpDate}}</div>   	
	   						 </div> 
							 <div style="flex-grow:2; text-align:right;">  
							 	 <button id="modifyBtn" class="btn btn-info"><a v-bind:href="'modifyProduct?id='+ item.prod_id">編輯</a></button>	
							 	 <button id="deleteBtn" class="btn btn-danger">刪除</button>	
							 </div>					
	   						   						
						</div>  					
   						  
  					</li>
				</ul>
      		 	 
      		 </div>
      	 <script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>  
        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
        <script>
            	$('#btnProduct').click(function(){            		
            		 $.ajax({
            			    type: "GET",  
            			    url: 'productMaintain',
            			    dataType: 'json',
            	            success : function(data) {
            	            	var app = new Vue({
            	  	        	  el: '#content',
            	  	        	  data: {
            	  	        	    items:data,
            	  	        	  }
            	  	        	})     
            	            	$('#content').toggleClass('hidden');
            	            }
            	        });	
            	}	) ;

        </script>     -->
        </div>
     <jsp:include page="/fragment/footer.jsp" /> 