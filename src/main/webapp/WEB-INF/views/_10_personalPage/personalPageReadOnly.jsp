
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
        <div class="container-fluid" style="display: flex ; color:white"> 
            <div class="col-lg-3" >                  
                <div class="personalImg">     
                    <img id="userImg${userBean.user_id }" class="img-circle pImg" src="getUserPicture/${userBean.user_id }">
                </div> 
                <div>
                <p></p>
                    <div><h4>${userBean.nickname}</h4></div>  
                    <div>
                        <ul>
                            <li>連絡信箱：${userBean.account}</li>                                   
                            <li>性別：${userBean.gender}</li>
                            <li>生日：${userBean.birthday}</li>  
                            <li>
                                <div class="introduction">
                                  ${introduction}
                                </div>  
                            </li>                                                                                 
                        </ul>
                    </div>
                </div>
            </div>     
            <div class="mainContent col-lg-9" id="personalMainContent" style="padding:15px; border-radius:10px;">       
            <ul class="nav nav-tabs">
				  <li class="active nav-item"><a class="active show" data-toggle="tab" href="#works" v-on:click="getWorks(${userBean.user_id})"> <button type="button"  class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">作品</button></a></li>

			</ul>							
				<div class="tab-content" style="margin:20px 0px 70px 0px;">  
				  <div id="works" class="tab-pane fade active show">
				    <!-- <h3>作品</h3> -->
				    
				     <div class="works col-lg-12" style="display:flex; flex-wrap:wrap">                 
	                    <div class="pieceOfWork" v-for="(work,index) in works" style="width:320px; margin-right:20px;" v-bind:id="'works'+work.works_id">
	                            <div style="display:flex">
		                            <h6 style="display:block; flex-grow:1">{{work.worksName}}</h6>    
		                            <div style="textt-align:right">
			                            <i class="far fa-times-circle" style="margin-right:5px; pointer:cursor;" v-on:click="deleteWorks(work.works_id,index,$event)"></i> 
			                            <i class="far fa-edit" style="pointer:cursor;" v-on:click="updateWorks(work.works_id,index)" data-toggle="modal" data-target="#exampleModalCenter"></i>		                           		                           
		                            </div>    
	                            </div>
	                            <div>
	                            	<form v-bind:id="'updateForm'+work.works_id">
		                            <div class="workImg">
		                                <img v-bind:src="'mainWorksPicture/'+ work.works_id" style="max-width:300px; margin-bottom:5px">
		                            </div>
		                            <div class="workText" v-bind:id="'worksIntro'+work.works_id">  
		                                    {{work.worksIntro}}
		                            </div>	
									
										<input type="hidden" name="worksIntro" >
									</form> 
	                            </div>
	                    </div>
                </div>
				  </div>
			
				          
	
				 
            </div>
        </div> 
        </div>
        <script src="js/jquery-3.3.1.min.js"></script>   
        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
        <script>
        	var app = new Vue({
        		el:'#personalMainContent',
        		data:{
        			works:[],
        			orders:[],
        			orderDateStr:'',
        			orderDetail:[],
        			orderItems:[],
        		},        	 	
        		created: function(){	
        				var _self = this;        				
        				var id = $('.personalImg img').attr('id').substring(7)        				
        				$.ajax({
            			    type: "POST",    
            			    url: 'userWorks?userId='+id,
            			    contentType:'application/json',		
            			    dataType: 'json',
            	            success : function(data) { 
            	            	_self.works = data;
            	            	console.log(_self.works)
            	            }  
            	        });
        		
        		},
        		methods:{
        			getWorks:function(userId){        			
        				var _self = this;
        				$.ajax({
            			    type: "POST",    
            			    url: 'userWorks?userId='+userId,
            			    contentType:'application/json',		
            			    dataType: 'json',
            	            success : function(data) { 
            	            	_self.works = data;
            	            	console.log(_self.works)
            	            }  
            	        });
        			}, 
        			/*  */
        			confirmUpdate:function(userId){
        				var nickname = $('#editNickname').text();
        				var phone = $('#editPhone').text();
        				var address = $('#editAddress').text();
        				var intro = $('#editIntro').text();
        				$("input[name='nickname']").val(nickname);
        				$("input[name='phone']").val(phone);
        				$("input[name='address']").val(address);
        				$("input[name='userIntro']").val(intro);
        				$.ajax({
            			    type: "POST",    
            			    cache:false,
            			    url: $(this).attr('action'),            			    
            			    data: $('#editUser').serialize(),            			    
            			    dataType: 'json',            			  
            	            success : function() {        
            	            }  
            	        });				
        			},
        			/*  */
        			showShoppingOrderList:function(){        				
        				var _self = this;
        				$.ajax({
            			    type: "GET",      
            			    url:'orderListAjax',            			      			    
            			    dataType: 'json',            			  
            	            success : function(data) { 
            	          		_self.orders = data;
            	          		
            	            }  
            	        });	    
        			},
        			/*  */
        			showOrderDetail:function(orderId,e,index){          			
        				console.log(orderId)  
        				console.log(e.target)        				
        				var _self = this;
        				$.ajax({   
            			    type: "GET",      
            			    url:'showOneOrderDetail/'+ orderId+'/anOrderShow',            			      			    
            			    dataType: 'json',            			  
            	            success : function(data) {
            	            	_self.orderDetail = data;
            	            	_self.orderItems = data.items;	
            	            }  
            	        });	        				        				        		
        				$('#order'+index).toggleClass('hidden');
        			},
        			/*  */        			
        			deleteWorks: function(worksId,index,e){
        				var _self = this;
        				e.preventDefault();
        			    if(confirm("確定要刪除作品？")){
        			    	_self.works.splice(index,1);
        					  $.ajax({
    					        type: "POST",  
    					        data : {_method:"DELETE"},  
    					        dataType: 'json', 
    					        url:'deleteWorks?id='+worksId,            					    
    					        success: function() {    					        	    					        		        
    					        }
    					    });
        				}
        			},		
        			/*  */
        			updateWorks:function(worksId){        				
        				$('#worksIntro'+worksId).prop('contenteditable',true).focus().css('background-color','rgba(32, 31, 58,0.8)');
        				$('#worksIntro'+worksId).blur(function(e){
        					var worksIntro = $('#worksIntro'+worksId).text();
        					$("input[name='worksIntro']").val(worksIntro);        				        			
        					$.ajax({   
                			    type: "POST",	
                			    cache:false,
                			    url:'updateWorks/'+worksId,            			      			      
                			    data: $('#updateForm'+worksId).serialize(),  
                			    dataType: 'json',            			  
                	            success : function(data) {
                	            
                	            }  
                	        });
        	        		$(this).prop('contenteditable',false)
        	        		$(this).css('background-color','');  
        	        	})
        				
        			},
        		},        		
        	})
        	$("#userImage").change(function(){
        		  readURL(this);
        		});
        		function readURL(input){
        		  if(input.files && input.files[0]){
        		    var reader = new FileReader();
        		    reader.onload = function (e) {
        		       $("#prviewUserImg").attr('src', e.target.result);
        		    }
        		    reader.readAsDataURL(input.files[0]);
        		  }
        		}
        	
        	$('#editUser .edit').dblclick(function(e){
        		console.log("click")
        		$(this).prop('contenteditable',true).focus();
        		$(this).css('background-color','rgba(32, 31, 58,0.8)');   
        	})
        	.blur(function(e){
        		$(this).prop('contenteditable',false)
        		$(this).css('background-color','');  
        	})
        	$('#uploadWorksForm').submit(function(){        	
        		var a = document.getElementById('worksName').value;
				var b = document.getElementById('worksPhoto').value;
				var c = document.getElementById('worksIntro').value;
				var warn = "";
				if(a ===""){
					warn += '作品名不得為空 | '
				}
				if(b ===""){
					warn += '請上傳作品圖片 | '
				}
				if(c ===""){
					warn += '作品介紹不得為空'
				}
				if(warn != ""){
					alert(warn);
					return false;
				}
        		
				  $.ajax({
				  type:"POST", 
				  cache: false,
				  url: $(this).attr('action'),
				  data: $('#uploadWorksForm').serialize(),
				  datatype: 'json',

				});
        	})
        </script>
<jsp:include page="/fragment/footer.jsp" />

