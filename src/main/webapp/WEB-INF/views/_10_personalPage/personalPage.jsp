
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
        <div class="container-fluid" style="display: flex ; color:white"> 
            <div class="col-lg-3" >                  
                <div class="personalImg">     
                    <img class="img-circle pImg" src="getUserPicture/${userBean.user_id }">
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
				  <li class="active"><a data-toggle="tab" href="#works" v-on:click="getWorks(${userBean.user_id})"> <button type="button"  class="btn btn-info btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">作品</button></a></li>
				  <li><a data-toggle="tab" href="#mail" v-on:click="getMyMessages(${userBean.user_id})"> <button type="button" class="btn btn-primary btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">信件</button></a></li>
				  <li><a data-toggle="tab" href="#post"><button type="button" class="btn btn-warning btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">發表</button></a></li>
				  <li><a data-toggle="tab" href="#orders"><button type="button" v-on:click="showShoppingOrderList()" class="btn btn-danger btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">訂單查詢</button></a></li>
				  <li><a data-toggle="tab" href="#maintain"> <button type="button" class="btn btn-success btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">維護</button></a></li>
			</ul>				
				<div class="tab-content" style="margin:20px 0px 70px 0px;">  
				  <div id="works" class="tab-pane fade in active">
				    <!-- <h3>作品</h3> -->
				     <div class="works">                 
	                    <div class="pieceOfWork" v-for="work in works">
	                            <h6 style="display:block">{{work.worksName}}</h6>    
	                            <div style="display:flex">
		                            <div class="workImg">
		                                <img v-bind:src="'mainWorksPicture/'+ work.works_id" style="max-width:500px;">
		                            </div>
		                            <div class="workText">  
		                                    {{work.worksIntro}}
		                            </div>	
<!-- 		                             <div class="workComment"> -->
<!--                                     	<a v-bind:href="'testComment?worksId='+work.works_id" >留言測試</a> -->
<!--                                 	</div>                             -->
	                            </div>
	                    </div>
                </div>
				  </div>
				  <div id="mail" class="tab-pane fade">
				   <!--  <h3>信件</h3> -->
				   <!--  <p>Some content.</p> -->
					<button class="sendMail" onclick="sendMail()">我要發信</button>
				    <table class="table table-striped table-dark" border="1" style="width:960px;">
				    	<tr>       
              				<th style="width:80px">編號</th>
              				<th style="width:80px">留言者</th>
              				<th style="width:180px">主旨</th>
              				<th style="width:250px">留言時間</th>
              				<th style="width:250px">本文</th>
          				</tr>
          				<tr>
          					<td style="width:80px">1</td>
          					<td style="width:80px">fromId</td>
          					<td style="width:180px">title</td>
          					<td style="width:250px; text-align:left">&nbsp;timestamp</td>
          					<td style="width:250px; text-align:left">content</td>
          				</tr>
				    </table>
				  </div>
				  <div id="post" class="tab-pane fade">
				    <!-- <h3>發表</h3> -->
				 		<!-- 上傳作品頁面 -->
                <div class="u-box box" style="width: 100%; height: 100%; margin: 0 auto;">
                    <form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='works.do' />" id="works.do">
                        <div class="u-container container">
                            <div class="col-xs-0" style="width: 100%;">                               
                                <div class="form-group">
                                <label for="worksName">作品名稱</label>
                                <input type="text" class="form-control col-lg-6" name="worksName"  id="worksName" placeholder="請輸入作品名">
                                 <div style="border:2px dashed grey; height:304px;">
                                        <img alt="" src="" id="previewImg_1" style="max-height: 300px;">
                                 </div>                               
                                <!--  <span style="float: left; margin-left: 50px;"> --> 
                                   <input type="file" class="form-control-file" name="worksImg" id="worksImg" id="exampleInputFile"  aria-describedby="fileHelp">
                                 <!-- </span> -->
                                </div>
                                
                                <label for="worksIntro">作品介紹</label>
                                <textarea class="form-control" name="worksIntro" id="worksIntro" rows="9" style="width: 100%; height: 20%;"></textarea>
                            </div>
                        </div>
                        <div style="margin:20px;">
                            上傳更多照片和敘述<input type="checkbox" value="true"
                                name="moreWorksInfo" id="pemp_yes"
                                style="margin-left: 30px;"> <label for="yes"
                                style="margin-left: 5px;">Yes</label> 
                        </div>
                        <div class="u-Advanced container template hidden" id="advancedForm">
                            <!-- first-box -->
                            <div id="firstBox">
		                        <label for="detail_1">照片｜敘述（一）</label>
	                            <div style="display:flex;">
		                            <textarea class="form-control" name="detail_1" id="detail_1" rows="9" style="width: 450px; height: 150px;"></textarea>
		                                <div>
			                                <img alt="" src="" id="previewImg_2" style="max-width:300px; margin-left:5px;">                
			                          		<input type="file" class="form-control-file" name="captionImg_1" id="captionImg_1" aria-describedby="fileHelp" style="margin-left:5px">
		                                </div>
	                            </div>
                            </div>
                            <hr>  
                            <!-- second-box -->
                            <div id="secondBox">
		                        <label for="detail_2">照片｜敘述（二）</label>
	                            <div style="display:flex;">
		                            <textarea class="form-control" name="detail_2" id="detail_2" rows="9" style="width: 450px; height: 150px;"></textarea>
		                                <div>
			                                <img alt="" src="" id="previewImg_3" style="max-width:300px; margin-left:5px;">                
			                          		<input type="file" class="form-control-file" name="captionImg_2" id="captionImg_2" aria-describedby="fileHelp" style="margin-left:5px">
		                                </div>
	                            </div>
                            </div>      
                        </div>

                        <div style="width: 85%; margin: 0 auto; margin-top: 30px;">
                            <input type="submit" class="btn btn-danger" name="submit"
                                id="submit" value="發布作品" style="width: 85%;">
                        </div>
                    </form>
                </div>
				  </div>               
<!-- <<<<<<< HEAD -->
				  <div id="orders" class="tab-pane fade">
				    <h3>訂單查詢</h3>
				      <div class="content container-fluid" style="display: flex; padding: 0px;">
						<table class="table table-striped table-dark" v-for="(order, index) in orders">
<!-- 							<tr height="50"> -->				
<%-- 								<th colspan="4" align="center">${LoginOK.nickname}的訂購紀錄</th> --%>
<!-- 							</tr> -->
<!-- 							<tr height="36"> -->
<!-- 								<th>訂單編號</th> -->
<!-- 								<th>訂購日期</th> -->
<!-- 								<th>總金額</th> -->
<!-- 								<th>送貨地址</th>   -->
<!-- 							</tr> -->
							
								<tr height="30">
									<td width="86">
										<a class="text-warning" v-bind:href="'/showOneOrderDetail/'+ order.orderNo + '/anOrderShow'" >
											{{order.orderNo}}
										</a>
									</td>    
									<td width="100">{{order.orderDate}}</td>
									<td width="80">{{order.totalAmount}}</td>
									<td width="400">&nbsp;{{order.shippingAddress}}</td>
								</tr>
							
						</table>
					</div>
<!-- ======= -->
<!-- 				  <div id="collection" class="tab-pane fade"> -->
<!-- 				    <h3>收藏</h3> -->
<!-- 				    <p>Some content in menu 2.</p> -->
<!-- >>>>>>> f4b72a366aeb19fc66a0a00934244f08f45abc3a -->
				  </div>
				  <div id="maintain" class="tab-pane fade">
				    <h3>修改個人資訊</h3>
				    	<form id="editUser" enctype="multipart/form-data" action="updateUser/${userBean.user_id}" method="POST">   				    		
				    		<div style="display:flex;background-color: rgba(0, 0, 0, 0.397); padding:10px;border-radius:5px">
				    		<div class="col-lg-8" style="line-height:28px;font-weight:400">
				    		  	<ul style="list-style:none;">
									<li>
					    		  		帳號：<span>${userBean.account}</span>
									</li>
									<li>
					    		  		姓名：<span>${userBean.name}</span>
									</li>
									<li>
					    		  		性別：<span>${userBean.gender}</span>
									</li>
									<li>
					    		  		生日：<span>${userBean.birthday}</span>
									</li>
								  		<hr>
										<span class="text-warning" style="text-align:right"><small>點兩下來修改</small></span>
									
									<li style="display:flex">
					    		  		<div>暱稱：</div>
					    		  		<div class="edit" id="editNickname" contenteditable="false" style="flex-grow:1">${userBean.nickname}</div>
									</li>
									<li style="display:flex">
				    		  			<div>聯絡電話：</div>
				    		  			<div class="edit" id="editPhone" contenteditable="false" style="flex-grow:1">${userBean.phone}</div>  		
				    		  		</li>
				 					<li style="display:flex">
				    		  			<div>送貨地址：</div>
				    		  			<div class="edit" id="editAddress" contenteditable="false" style="flex-grow:1">${userBean.address}</div>  		
				    		  		</li>
				    		  		<li style="display:flex">				    		 
				    		  			<div>關於我：</div>
				    		  			<div class="edit" id="editIntro" contenteditable="false" style="flex-grow:1">${introduction}</div> 
				    		  		</li> 		      							    		  					    	 				    		  	
				    		  	</ul>	
				    		</div>       
				    		<div style="margin: 0px auto">
				    		    <div style="border:1px solid grey; width:150px;height:150px;background-color:rgba(0, 0, 0, 0.397);">
					    			<img id="prviewUserImg" style="border:1px solid black; max-width:150px;">
				    		    </div>   				         		
				    			<label for="userImage">請選擇要上傳的圖片</label>  
					            <input id="userImage" type="file" class="form-control-file" name="userImage"/>
				    		</div>
				    		</div> 
				    		<div style="margin: 10px auto;display:flex; justify-content:center;">
					    		<button v-on:click="confirmUpdate(${userBean.user_id })" class="btn btn-info" style="margin-right:10px;">保存</button>
					    		<button class="btn btn-warning">取消</button>
				    		</div>     				    	
				    		<input type="hidden" name="nickname" id="nickname">
				    		<input type="hidden" name="phone" id="phone">
				    		<input type="hidden" name="address" id="address">
				    		<input type="hidden" name="userIntro" id="userIntro">				    						    				    		
				    	</form>
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
        			myMessages:[],
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
        			showShoppingOrderList:function(){
        				var _self = this;
        				$.ajax({
            			    type: "GET",    
            			    url:'orderListA',            			      			    
            			    dataType: 'json',            			  
            	            success : function(data) { 
            	          		_self.orders = data;
            	            }  
            	        });	
        			},
        			getMyMessages:function(userId){
        				var _self = this;
        				$.ajax({
        					type: "POST",
        					url:'getMyMails?userId='+userId,
        					contentType:'application/json',		
            			    dataType: 'json',
            			    success : function(data) {
            			    	_self.myMessages = data;
            			    }
        				})
        			}
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
        	})
        	.blur(function(e){
        		$(this).prop('contenteditable',false)
        	})
        </script>
<jsp:include page="/fragment/footer.jsp" />

