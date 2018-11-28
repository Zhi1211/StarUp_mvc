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
			  <a class="nav-link" data-toggle="tab" href="#addProduct" v-on:click="getForm">商品上架</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#user" v-on:click="getUsers">會員管理</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#order" v-on:click="getAllOrders">訂單資料</a>
			  </li>
			 <!--  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#mail">系統信件</a>
			  </li> -->
			  <li class="nav-item">
			    <a class="nav-link" data-toggle="tab" href="#opinion" v-on:click="getAllOpinions">意見回饋</a>
			  </li>
			</ul>		
			<div id="myTabContent" class="tab-content">
			  <div class="tab-pane fade active show" id="product">
			    <ul style="list-style:none;">    
  					<li v-for="(product, index) in products">  
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
	   								<button class="btn"><a  v-bind:href="'modifyProduct?id='+ product.prod_id">編輯</a></button>	 
								 	<button class="btn" v-bind:id="'delProdBtn'+product.prod_id" v-on:click="confirmDelete(index,$event)" style="text-decoration:underline;">刪除</button>
	   						 </div>	  							 					   						
						</div>  					  
  					<hr>
  					</li>  					
				</ul> 
			  </div>
			<div class="tab-pane fade" id="addProduct">
			    		  <form id="addProdForm" v-on:submit="addProduct($event)" action="addProduct" method="POST" enctype="multipart/form-data">
					            <div class="container col-lg-10" style=" display: flex; ">  
					          		 <div class="prodImg col-lg-7" style="z-index:0;margin: 10px 0px 30px 20px; background-color: rgba(255, 255, 255, 0.699); box-shadow: 1px 1px 3px black; border-radius: 5px;">
					                    <div id="dropZone" style="margin-top:10px;border:grey solid 1px;width:500;height:500px;overflow:hidden">  
					                        <img  src="" id="previewImg" style="width:100%">
					                    </div>    
					                    <div>                    
					                    	  <label for="photo">請選擇要上傳的圖片</label>
					            			  <input id="productImage" type="file" class="form-control-file" name="productImage"/>
					                    </div>
					                </div>  
					         <div class="prodInfo col-lg-6" >     
					                <div class="upper" style="margin: 10px 0px; padding: 20px; background-color: rgba(255, 255, 255, 0.767); border-radius: 5px; box-shadow: 1px 1px 3px black">
					                        <label>商品名稱：</label>  
					                        <input type="text" name="prodName" id="prodName" style="margin-bottom:15px"/> 
					                        <br> 
					                        <label>申請人/團隊：</label>       
					                        <input type="text" name="prodCompany" id="prodCompany"/>    
					                        <br>                					                        					                          
					                        <br>                					                        					                          
					                        <div style="display:flex;">
						                        <label>商品分類：</label>  
						                        <div>
							                        <input id="tog" type="radio" name="prodType" value="文創周邊" style="margin-left:50px">文創周邊<br>
							                        <input type="radio" name="prodType" value="原創桌遊" style="margin-left:50px">原創桌遊<br>						                        
						                        </div>        
					                        </div>  					                   
					                        <div style="display:flex;">
						                        <label>分類目錄：</label><br>      
						                        <div>
							                        <div id="type1" style="display:none;">
								                        <select name="prodCategory">
														    <option value="暖心小物">暖心小物</option>
														    <option value="質感選物">質感選物</option>											    
														    <option value="城市紋理">城市紋理</option>											    
														</select>					                        
							                        </div>       
													<div id="type2" style="display:none;">
														<select name="prodCategory">
														    <option value="1~3人">1~3人</option>
														    <option value="3~5人">3~5</option>											    
														    <option value="5~10人">5~10人</option>											    
														    <option value="10人以上">10人以上</option>											    
														</select>    											
													</div>
						                        </div>
					                        </div>             					                       
					                        <label>售價：NT$ </label>    
					                        <input type="number" name="prodPrice" id="prodPrice" style="margin-bottom:15px"/>   									
											<br>  
					                        <label>庫存：</label>    
					                        <input type="number" name="prodStock"/>   
					                    </div> 
						                <div class="lower" style=" margin: 10px 0px; padding: 20px 10px; border-radius: 5px; box-shadow: 1px 1px 3px black">
							                    <p style="font-size:20px; color:#123d82; text-align:center; margin:0px;">商品說明</p>   
							                    <textarea name="prodIntro" id="prodIntro" rows="5" cols="55"></textarea>   
						                        <hr>                                          
						                 </div> 
					                    <input id="btnAdd" type='submit' class='btn btn-outline-primary' value="送出"  style="margin-bottom:10px;"/>              
					                </div>     
					            </div>					 
					        </form>
					                 <hr>
			
			<!-- 申請上架表單列表 -->
			                <div class="container" style="margin-top: 50px;" id="getForm">
			
			                    <ul style="list-style: none;">
			                        <li v-for="(form, index) in forms"  v-bind:id="'form'+form.form_id" style="padding:5px">			                        
			                            <div style="display: flex; margin: 5px 0px;">
			                                <div style="width: 200px; height: 200px; overflow: hidden;">
			                                    <img style="width: 100%;"
			                                        v-bind:src="'getFormImg/'+form.form_id" />
			                                </div>
			                                <div style="margin-left: 5px;" >
			                                    <div>商品名稱：<span v-bind:id="'prodName'+form.form_id" >{{ form.formProdName }}</span></div>
			                                    <div>申請人 / 團隊名稱：<span v-bind:id="'applyName'+form.form_id" >{{ form.realName }}</span></div>
			                                    <div>商品分類：<span v-bind:id="'type'+form.form_id" >{{ form.question_1}}</span></div>
			                                    <div>售價：<span v-bind:id="'price'+form.form_id" >{{ form.formPrice}}</span></div>
			                                    <div>商品說明：<span v-bind:id="'intro'+form.form_id" >{{ form.formIntro}}</span></div>
			                                </div>
			
			                                <hr>
			                                <div style="flex-grow: 2; text-align: right;">
			                                    <button class="btn btn-outline-primary" v-bind:id="'status'+form.form_id" style="cursor:none">{{form.status}}</button>			
			                                    <button class="btn" v-on:click="approved($event)" v-bind:id="'approved'+form.form_id">核准</button>
			                                    <button class="btn" v-on:click="notApproved($event)" v-bind:id="'notApproved'+form.form_id">不核准</button>
			                                    <!--     <button class="btn"><a  v-bind:href="'modifyProduct?id='+ form.form_id">核准</a></button>      -->
			                                    <!--     <button class="btn" v-bind:id="'delProdBtn'+form.form_id" v-on:click="confirmDelete(index,$event)" style="text-decoration:underline;">拒絕</button> -->
			                                </div>
			                            </div>
			                            <hr>
			                        </li>
			                    </ul>
			
			                </div>					        
					</div>
			  
			  <div class="tab-pane fade" id="user">
			   		<ul style="list-style:none;">    
	  					<li v-for="user in users">  
							<div style="display:flex; margin: 5px 0px;">  
								 <div style="width:150px; height:150px; overflow:hidden;">    
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
			   						  <div style="flex-grow:2; text-align:right;">
			   						  <a href="#">停權</a>
	   						 </div>	  	 	
							</div>  					  
	  					<hr>
	  					</li>  					
				</ul> 
			  </div>
			  <div class="tab-pane fade" id="order">
			    <div class="orderItem" v-for="order in orders">
			    	<ul>
			    		<li>訂單編號：{{order.orderNo}}</li>
			    		<li>會員帳號：{{order.account}}</li>
			    		<li>訂單金額：$ {{order.totalAmount}}</li>
			    		<li>出貨地址：{{order.shippingAddress}}</li>
			    		<li>發票抬頭：{{order.bno}}</li>
			    		<li>統一編號：{{order.invoiceTitle}}</li>
			    		<li>訂貨日期：{{order.orderDateStr}}</li>   
			    	</ul>
			    	<hr>
			    </div>			    
			  </div>
			  <div class="tab-pane fade" id="mail">
			    <p> denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater.</p>
			  </div>
			  <div class="tab-pane fade" id="opinion">
			    <div class="orderItem" v-for="opinion in opinions">
			    	<ul>
			    		<li>意見編號：{{opinion.opinionId}}</li>
			    		<li>姓名：{{opinion.opinionName}}</li>
			    		<li>連絡信箱：$ {{opinion.opinionMail}}</li>
			    		<li>意見：{{opinion.opinionField}}</li>
			    		<li>發送時間：{{opinion.opinionUpTime}}</li>
			    	</ul>
			    	<hr>
			    </div>			    
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
        			orders:[],
        			opinions:[],
        			forms:[],
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
        			addProduct: function(e){        				
        				$.ajax({
        					  type:"POST", 
        					  cache: false,
        					  url: $(this).attr('action'),
        					  data: $('#addProdForm').serialize(),
        					  datatype: 'json',
        					  success: function() {   
        						  window.location.href= "backstage";
        					  }
        					});
        			},
        			/*  */  
        			confirmDelete: function(index,e){
        				var _self = this;
        				e.preventDefault();
        				var tagId = e.target.id;   
        				var prodId = tagId.substr(10); 		
        			    if(confirm("確定要刪除此項商品？")){
        			    	_self.products.splice(index,1);
        					  $.ajax({
    					        type: "POST",  
    					        data : {_method:"DELETE"},  
    					        url: "deleteProduct/"+ prodId,    					    
    					        success: function() {    					        	
    					        	alert('刪除成功');     					        	
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
        			/*  */
        			getAllOrders: function(){
        				var _self = this;
        				$.ajax({
        					type:"GET",
        					url:"listAllOrders",
        					success:function(data){
        						_self.orders = data;
        					}
        				})
        			},
        			/*  */
        			getAllOpinions: function(){
        				var _self = this;
        				$.ajax({
        					type:"GET",
        					url:"opinionForm",
        					success:function(data){  
        						_self.opinions = data;
        					}
        				})
        			},
        			/*  */
        			getForm: function(){      
        				var _self = this;
        				$.ajax({
        					type:"GET",
        					url:"getAllForms",
        					success:function(data){  
        						_self.forms = data;
        					}
        				})
        			},
        			/* */
        			approved: function(e){
        				var id = e.target.id;
        				var form_id = id.substring(8);
        				
        				var prodName = $('#prodName'+form_id).text();
        				var applyName = $('#applyName'+form_id).text();
        				var price = $('#price'+form_id).text();
        				var type = $('#type'+form_id).text();
        				var intro = $('#intro'+form_id).text();
        				var imgUrl = "getFormImg/"+form_id";
        				alert(prodName+"|"+applyName+"|"+price+"|"+type+"|"+intro)
        				//console.log(form_id)
        				var _self = this;
        				var review = "approved";
            			$.ajax({
            				type:"GET",
            				url:"reviewMail/" + form_id + "/" + review,
            				success:function(){
            					$('#status'+form_id).text('通過審核');
            					$('#prodName').val(prodName);
            					$('#prodCompany').val(applyName); 
            					if(type === '文創周邊'){
            						$('input[name="prodType"]')[0].checked = true; 
            					}else{
            						$('input[name="prodType"]')[1].checked = true; 
            					}            				
            					$('#prodType').val(type).attr('checked',true); 
            					$('#prodPrice').val(price);
            					$('#prodIntro').val(intro);
            				}
            			})
        			},
        			/* */
        			notApproved: function(e){
        				var id = e.target.id;
        				var form_id = id.substring(11);
        				//console.log(form_id)
        				var _self = this;
            			var review = "notApproved";
            			$.ajax({
            				type:"GET",
            				url:"reviewMail/" + form_id + "/" + review,
            				success:function(){					
            					$('#status'+form_id).text('未通過審核');
            				}
            			})
        			},
        			},
        		}) 
        </script>     
      
        </div>
     <jsp:include page="/fragment/footer.jsp" /> 