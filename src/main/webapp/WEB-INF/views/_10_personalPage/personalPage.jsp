
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
        <div class="container-fluid" style="display: flex ; color:white"> 
            <div class="col-lg-3" >                
                <div class="personalImg">     
                    <img class="img-circle pImg" src="getUserPhoto/${userBean.account}">
                </div> 
                <div>
                <p></p>
                    <div><h4>${userBean.nickname}</h4></div>  
                    <div>
                        <ul>
                            <li>${userBean.account}</li>                                   
                            <li>
                                <div class="introduction">
                                  ${introduction}
                                </div>
                            </li>                                                      
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mainContent">
            <ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#works"> <button type="button" class="btn btn-info btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">作品</button></a></li>
				  <li><a data-toggle="tab" href="#mail"> <button type="button" class="btn btn-primary btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">信件</button></a></li>
				  <li><a data-toggle="tab" href="#post"><button type="button" class="btn btn-warning btn-circle btn-xl btnFeature" onclick="location.href = 'upload' " style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">發表</button></a></li>
				  <li><a data-toggle="tab" href="#collection"><button type="button" class="btn btn-danger btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">收藏</button></a></li>
				  <li><a data-toggle="tab" href="#maintain"> <button type="button" class="btn btn-success btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">維護</button></a></li>
			</ul>				
				<div class="tab-content" style="margin-top:20px;">
				  <div id="works" class="tab-pane fade in active">
				    <h3>作品</h3>
				     <div class="works">                 
	                    <div class="pieceOfWork">
	                            <div class="workImg">
	                                <img src="${pageContext.servletContext.contextPath}/Util/getImage?id=${user_id}&type=WORKS"style="width:100%; height:100%">
	                            </div>
	                            <div class="workText">
	                                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam, ab itaque! Quae vel beatae consequuntur sapiente fugiat cupiditate dolores, sed quidem corrupti quis ad saepe impedit unde architecto sint veritatis.
	                            </div>
	                    </div>
                </div>
				  </div>
				  <div id="mail" class="tab-pane fade">
				    <h3>信件</h3>
				    <p>Some content.</p>
				  </div>
				  <div id="post" class="tab-pane fade">
				    <h3>發表</h3>
				    <p>Some content in menu 1.</p>
				  </div>
				  <div id="collection" class="tab-pane fade">
				    <h3>收藏</h3>
				    <p>Some content in menu 2.</p>
				  </div>
				  <div id="maintain" class="tab-pane fade">
				    <h3>維護</h3>
				    <p>Some content in menu 2.</p>
				  </div>
				</div>
				            
            
               
            </div>
        </div> 
</body>
<jsp:include page="/fragment/footer.jsp" />

