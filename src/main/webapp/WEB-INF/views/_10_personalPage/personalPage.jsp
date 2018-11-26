
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
            <ul class="nav nav-tabs" style="border-style:none">
				  <li class="active nav-item"><a class="active show" data-toggle="tab" href="#works" v-on:click="getWorks(${userBean.user_id})"> <button type="button"  class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">作品</button></a></li>
				  <li class="nav-item"><a data-toggle="tab" href="#mail"> <button type="button" class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">信件</button></a></li>  
				  <li class="nav-item"><a data-toggle="tab" href="#post"><button type="button" class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">發表</button></a></li>
				   <li class="nav-item"><a data-toggle="tab" href="#orders"><button type="button" v-on:click="showShoppingOrderList()" class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46); font-size: 20px;"> 訂單<br>查詢</button></a></li>
					<li class="nav-item"><a data-toggle="tab" href="#maintain">
							<button type="button" class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">維護</button></a></li>
					<li class="nav-item"><a data-toggle="tab" href="#form"> <button type="button" class="btn btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46); font-size: 20px;">申請<br>上架 </button></a></li>
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
				  <div id="mail" class="tab-pane fade">				  
				      <div class="container-fluid">
                            <div class="col-xs-0" style="width: 100%;">
                            <form method="POST" action="sendMail" name="mailContent" id="mailContent">
                                <div class="form-group" style="display:flex">
                                	<div style="margin-right:20px;">
		                                <label for="">收件人：</label>
		                                <input type="text" class="form-control" name="receiverNickname" style="width:200px;">                                                   
                                	</div>
									<div>
		                                <label for="">主旨：</label>
		                                <input type="text" class="form-control" name="messageTitle" style="width:300px;">                                                                                  									
									</div>
                                </div>                                                                                      
		                                <label for="">內文：</label>
									<div style="display:flex;align-items:flex-end">
		                                <textarea class="form-control" name="messageContent"  rows="9" style="width: 50%;"></textarea>
		                                <button class="btn btn-info btn-lg" type="submit" style=" margin-left:10px; ">Send</button>
									</div>
                             </form>    
                            </div>
                            <div>                            	    
                            <hr style="margin-top:20px">                               
                            	<h4 style="text-align:center;margin-top:40px">收件表</h4>
	                            <table class="table table-striped table-dark" border="1" style="margin-top:20px;">			   			
									<tr>
										<td width="150" style="text-align:center;">信件編號</td>
										<td width="225" style="text-align:center;">收件日期</td>
										<td width="150" style="text-align:center;">寄件人</td>
										<td style="text-align:center;">標題</td>
										<td width="70" style="text-align:center;"><i class="far fa-times-circle" style="margin-right:5px; cursor:pointer;"></i></td>     
									</tr>    
									<c:forEach varStatus='vsRM' var="receivedMail" items="${receivedMessages}">
									<tr>
										<td width="150" style="text-align:center;">${vsRM.count}</td>
										<td width="225" style="text-align:center;">${receivedMail.posttime}</td>
										<td width="150" style="text-align:center;">${receivedMail.fromNickname}</td>
										<td style="text-align:center;">${receivedMail.messageTitle}</td>
										<td rowspan="2"></td>
									</tr>
									<tr>
										<td width="70" colspan="4" style="text-align:center;">${receivedMail.messageContent}</td>
									</tr>
									<tr>
										<td colspan="5"></td>
									</tr>
									</c:forEach>
	 							</table>
                            </div>
                            <div>
                            	<h4 style="text-align:center;">寄件表</h4>
                            	<table class="table table-striped table-dark" border="1" style="margin-top:20px;">
                            		<tr>
                            			<td width="150" style="text-align:center;">信件編號</td>
										<td width="225" style="text-align:center;">寄送日期</td>
										<td width="150" style="text-align:center;">收件人</td>
										<td style="text-align:center;">標題</td>
										<td width="70" style="text-align:center;"><i class="far fa-times-circle" style="margin-right:5px; cursor:pointer;"></i></td>
                            		</tr>
                            		<c:forEach varStatus='vsDM' var="deliveredMail" items="${deliveredMessage}">
									<tr>
										<td width="150" style="text-align:center;">${vsDM.count}</td>
										<td width="225" style="text-align:center;">${deliveredMail.posttime}</td>
										<td width="150" style="text-align:center;">${deliveredMail.fromNickname}</td>
										<td style="text-align:center;">${deliveredMail.messageTitle}</td>
										<td rowspan="2"></td>
									</tr>
									<tr>
										<td width="70" colspan="4" style="text-align:center;">${deliveredMail.messageContent}</td>
									</tr>
									<tr>
										<td colspan="5"></td>
									</tr>
									</c:forEach>
                            	</table>
                            </div>
                        </div>
				  </div>
				  <div id="post" class="tab-pane fade">
				    <!-- <h3>發表</h3> -->
				 		<!-- 上傳作品頁面 -->
                <div class="u-box box" style="width: 100%; height: 100%; margin: 0 auto;">
                    <form ENCTYPE="multipart/form-data" method="POST" action="saveWorks" id="uploadWorksForm">
                        <div class="u-container container">
                            <div class="col-xs-0" style="width: 100%;">                               
                                <div class="form-group">
                                <label for="worksName">作品名稱</label>
                                <input type="text" class="form-control col-lg-6" name="worksName"  id="worksName" placeholder="請輸入作品名">
                                 <div style="border:2px dashed grey; height:304px;">
                                        <img alt="" src="" id="previewImg_1" style="max-height: 300px;">
                                 </div>                               
                                <!--  <span style="float: left; margin-left: 50px;"> --> 
                                   <input type="file" class="form-control-file" name="worksPhoto" id="worksImg" id="exampleInputFile"  aria-describedby="fileHelp">
                                 <!-- </span> -->
                                </div>
                                
                                <label for="worksIntro">作品介紹</label>
                                <textarea class="form-control" name="worksIntro" id="worksIntro" rows="9" style="width: 100%; height: 20%;"></textarea>
                            </div>
                        </div>
                        <div style="margin:20px 0px; text-align:center">
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
			                          		<input type="file" class="form-control-file" name="captionPhoto_1" id="captionImg_1" aria-describedby="fileHelp" style="margin-left:5px">
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
			                          		<input type="file" class="form-control-file" name="captionPhoto_2" id="captionImg_2" aria-describedby="fileHelp" style="margin-left:5px">
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
				  <div id="orders" class="tab-pane fade">
				    <h3>訂單查詢</h3>
				      <div class="content container-fluid" style="padding: 0px;">
						<table class="table table-striped table-dark" style="margin-bottom:2px;">			   			
							<tr>
								<td width="150">訂單編號</td>
								<td width="225">訂購日期</td>
								<td width="150">總金額</td>
								<td>送貨地址</td>     
								<td width="70"></td>     
							</tr>    
 						</table>
						<table class="table table-striped table-dark" v-for="(order, index) in orders" style="margin-bottom:2px;">							
								<tr height="30" style="margin-bottom:2px;">   
									<td width="150">
										<p class="text-warning">
											{{order.orderNo}}    
										</p>    
									</td>       
									<td width="225">{{order.orderDateStr}}</td>      
									<td width="150">{{order.totalAmount}}</td>
									<td>&nbsp;{{order.shippingAddress}}</td>     
									<td style=""><button class="btn btn-outline-light btn-sm" v-on:click="showOrderDetail(order.orderNo,$event,index)">查看明細</button></td>   
									<!-- <td>
										<table>
										<tr v-bind:id="'order'+index">
											<td></td>
										</tr>
									</table>
									</td> -->
								</tr>
								<tr class="hidden itemList" v-bind:id="'order'+index">
									<td colspan="5">
										<table>
										<tr>   
											<td width="600">
												商品資訊
											</td>  
											<td width="150">
												購買數量
											</td>
											<td width="150">
												單價
											</td>											
										</tr>
										<tr v-for="item in orderItems">
											<td>{{item.description}}</td>
											<td>{{item.quantity}}</td>
											<td>{{item.unitPrice}}</td>
										</tr>
									</table>
									</td>
								</tr>								
						</table>
					</div>
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
										<span class="text-warning" style="text-align:right">* 點兩下來修改 *</span>
									
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
					    		<button type="submit" v-on:click="confirmUpdate(${userBean.user_id },$event)" class="btn btn-info" style="margin-right:10px;">保存</button>
				    		</div>     				    	
				    		<input type="hidden" name="nickname" id="nickname">
				    		<input type="hidden" name="phone" id="phone">
				    		<input type="hidden" name="address" id="address">
				    		<input type="hidden" name="userIntro" id="userIntro">				    						    				    		
				    	</form>
				  </div>
				  	  			<!-- 申請上架 -->
			<div id="form" class="tab-pane fade">

				<!-- 				<h3>表單填寫</h3> -->
				<div
					style="display: flex; background-color: white; padding: 10px; border-radius: 5px; width: 85%; margin: 0 auto;">
					<div class="col-lg-10"
						style="line-height: 28px; font-weight: 400; color: rgb(0, 0, 0);padding:10px">
						<form ENCTYPE="multipart/form-data" id="applyForm" method="POST" action="addForm" v-on:submit="submitForm()">

							<p style="font-size:28px; font-weight:200">
								我要申請商品上架</p>								
								<p class="text-info">以下提出幾個問題，幫助你檢視商品是否已經準備好開始於網路販售
							</p>
								<p>填寫上架申請表前，你是否已經閱讀各項上架資訊？</p>

							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="checkform"> <label class="custom-control-label"
									for="checkform">我已經詳細閱讀<a href="#" data-toggle="modal" data-target="#exampleModalLong">這個頁面</a>，並同意這樣的上架方式。</label>
							</div>
							<hr>
							<div class="custom-control custom-radio">
								<input type="radio" id="product1" name="question_1" value="原創桌遊"
									class="custom-control-input" > <label
									class="custom-control-label" for="product1">原創桌遊</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" id="product2" name="question_1" value="文創周邊"
									class="custom-control-input"> <label
									class="custom-control-label" for="product2">文創周邊</label>
							</div>
							<hr>
							<p>你的商品照片有花時間好好用心拍嗎？</p>

							<div class="custom-control custom-radio">
								<input type="radio" id="photo" name="question_2" value="yes"
									class="custom-control-input"> <label
									class="custom-control-label" for="photo">有，我有好好拍，我的商品照都很好看！</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" id="photoNG" name="question_2" value="no"
									class="custom-control-input"> <label
									class="custom-control-label" for="photoNG">我還沒有時間用心拍，現在手邊的商品照都是隨手拍。</label>
							</div>
							<hr>
							<p>商品販售政策，你看過也讀懂了嗎？</p>

							<div class="custom-control custom-radio">
								<input type="radio" id="understand" name="question_3" value="yes"
									class="custom-control-input"> <label
									class="custom-control-label" for="understand">我了解，非原創商品和有侵權疑慮商品不可以賣。</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" id="notyet" name="question_3" value="no"
									class="custom-control-input"> <label
									class="custom-control-label" for="notyet">我會先自己瞭解過侵權的可能性後，再來申請。</label>
							</div>
							<hr>
							<p>你知道你要從多少申請者中脫穎而出，才能成功通過審核嗎？</p>

							<div class="custom-control custom-radio">
								<input type="radio" id="great" name="question_4" value="yes"
									class="custom-control-input"> <label
									class="custom-control-label" for="great">沒問題，無論商品品質和商品照，我都有信心可以從眾多申請者中脫穎而出！</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" id="notgood" name="question_4" value="no"
									class="custom-control-input"> <label
									class="custom-control-label" for="notgood">那請等我準備得更完整以後再回來申請。</label>
							</div>

							<hr>
						
							<p>填寫上架申請表：</p>
								<label for="InputName">申請人/團隊</label> 
								<input type="text" name="realName" class="form-control" id="InputName" placeholder="請輸入申請人或團隊名稱"style="width: 250px;"> 
								<label for="ProductName">商品名稱</label> 
								<input type="text" class="form-control" id="ProductName" name="formProdName" placeholder="請輸入商品名稱" style="width: 250px;"> 
								<label for="InputPrice">價格</label> 
								<input type="text" name="formPrice" class="form-control" id="InputPrice" placeholder="請輸入商品價格" style="width: 250px;"> 
								<label for="InputEmail">Email信箱</label>
							    <input type="email" class="form-control" id="InputEmail" name="formMail" aria-describedby="emailHelp" value="${userBean.account}" style="width: 450px;"> 
								<label for="productPhoto">照片｜敘述</label>
								<input type="hidden" name="user_id" id="user_id" value="${userBean.user_id }">
							    <div style="display: flex;">
								<textarea class="form-control" name="formIntro"
									id="productPhoto" rows="9" style="width: 450px; height: 150px;"></textarea>
								<div>
									<img alt="" src="" id="previewImg_4"
										style="max-width: 300px; margin-left: 5px;"> <input
										type="file" class="form-control-file" name="formImage"
										id="captionImg_3" aria-describedby="fileHelp"
										style="margin-left: 5px">
								</div>
	
							</div>
							<div style="margin: 0 auto; margin-top: 30px;">
								<input type="submit" class="btn btn-secondary" name="submit"
									id="submitForm" value="提交表單" style="width: 85%;">
								<!-- 									 disabled -->
							</div>
						</form>



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
        			confirmUpdate:function(userIde,e){
        	/* 			var nickname = $('#editNickname').text();
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
            			/*     dataType: 'json',   	   */
            	        /*     success : function(data,XMLHttpRequest, textStatus, errorThrown) {
            	            	console.log(data+' success');  
            	            	console.log(XMLHttpRequest.status +' success');  
            	            	console.log(XMLHttpRequest.readyState +' success');  
            	            	console.log(textStatus +' success');  
			   	            	e.preventDefault()
            	            },
            	            error:function(data, XMLHttpRequest, textStatus, errorThrown){  
            	            	alert(data);  
            	            	         alert(XMLHttpRequest.status);  
            	            	alert(XMLHttpRequest.readyState);  
            	            	alert(textStatus);  
            	            	}  
            	        });	 */
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
        			submitForm:function(e){        				
        				$.ajax({   
            			    type: "POST",	
            			    cache:false,
            			    url: $(this).attr('action'),            			    
            			    data: $('#applyForm').serialize(),  
            			    dataType: 'json',            			  
            	            success : function(data) {
            	            	e.preventDefault();
            	            }  
            	        });
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

