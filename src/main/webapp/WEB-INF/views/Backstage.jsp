<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/fragment/header.jsp" />
     
        <!-- -------------------------navgation end----------------------------- -->
        <div class="content container-fluid" style="display: flex; padding: 0px; ">
            <!-- 側邊欄 -->
  
      		 <div id="display" class="container" style="background-color:white;border-radius:5px;">
      		 
      	<ul class="nav nav-tabs" style="padding:10px;">         
			  <li class="nav-item">
			    <a class="nav-link active show" data-toggle="tab" href="#product" v-on:click="getProducts">商品維護</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#user" v-on:click="getUsers">會員管理</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#order">訂單資料</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#mail">系統信件</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#opinion">意見回饋</a>
			  </li>
			</ul>
			<div id="myTabContent" class="tab-content">
			  <div class="tab-pane fade active show" id="product">
			    <ul style="list-style:none;">    
  					<li v-for="product in products">  
						<div style="display:flex; margin: 5px 0px;">  
							 <div style="width:200px; height:200px; overflow:hidden;">    
	   						 	<img style="width:100%;" v-bind:src="'getProductPicture/' + product.prod_id" />
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
	   								<a class="btn" v-bind:href="'modifyProduct?id='+ product.prod_id">編輯</a>	 
								 	<a class="btn" href="" v-bind:id="'delProdBtn'+product.prod_id" v-on:click="confirmDelete($event)">刪除</a>
	   						 </div>	  							 					   						
						</div>  					  
  					<hr>
  					</li>  					
				</ul> 
			  </div>
			  <div class="tab-pane fade" id="user">
			   		<ul style="list-style:none;">    
	  					<li v-for="user in users">  
							<div style="display:flex; margin: 5px 0px;">  
								 <div style="width:200px; height:200px; overflow:hidden;">    
		   						 	<img style="width:100%;" v-bind:src="'getUserPicture/' + user.user_id" />
		   						 </div>
		   						 <div style="margin-left:5px;"> 	   						 	
		   						 	 <div>會員編號：{{ user.user_id }}</div>
			   						 <div>會員帳號：{{ user.account }}</div>
			   						 <div>姓名：{{ user.name}}</div>
			   						 <div>暱稱：{{ user.nickname}}</div>
			   						 <div>性別：{{ user.gender}}</div>  
			   						 <div>電話：{{ user.phone}}</div>
			   						 <div>收件地址：{{ user.address}}</div>   	
		   						 </div>   											   					
							</div>  					  
	  					<hr>
	  					</li>  					
				</ul> 
			  </div>
			  <div class="tab-pane fade" id="order">
			    <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork.</p>
			  </div>
			  <div class="tab-pane fade" id="mail">
			    <p> denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater.</p>
			  </div>
			  <div class="tab-pane fade" id="opinion">
			    <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater.</p>
			  </div>
			</div>
      		 

      		 	
      		 </div>
      		  
      	<script src="js/jquery-3.3.1.min.js"></script>   
        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
        <script>
        		var app = new Vue({
        		el:'#display',  
        		data: {
        			products: [],
        			users:[],        			
        		},
        		created: function(){    
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
        			/*  */  
        			confirmDelete: function(e){
        				var tagId = e.target.id;
        				var prodId = tagId.substr(10)
        				console.log(e.target.id);    
        				console.log(prodId);    
        			    if(confirm("確定要刪除此項商品？")){	
        					  $.ajax({
    					        type: "POST",  
    					        data : {_method:"DELETE"},  
    					        url: "deleteProduct/"+ prodId,    					    
    					        success: function(data) {
    					           location.reload();
    					        }
    					    });   
        				}
        			},
        			/*  */
        			getUsers: function(){
        				var _self = this;
        				$.ajax({
        					type:"GET",
        					url:"listAllUsers",
        					success:function(data){
        						_self.users = data;
        					}
        				})
        			},
        			},
        			
        		}) 
        </script>     
      
        </div>
     <jsp:include page="/fragment/footer.jsp" /> 