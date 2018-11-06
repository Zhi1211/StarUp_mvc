
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
        <div class="container-fluid" style="display: flex ; color:white"> 
            <div class="col-lg-3" >                
                <div class="personalImg">   
                    <img class="img-circle pImg" src="getUserPhoto/${userBean.user_id}">
                </div> 
                <div>
                <p></p>
                    <div><h4>${userBean.nickname}</h4></div>  
                    <div>
                        <ul>
                            <li>聯絡：${account}</li>     
                            <li>  <i class="fas fa-female fa-lg"></i></li>    
                            <li>
                                <div class="introduction">
                                   	${intro}
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mainContent">
                <div class="featureIcon">
                    <button type="button" class="btn btn-primary btn-circle btn-xl btnHidden" style="cursor: default"></button> 
                    <button type="button" class="btn btn-info btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">信件</button>
                    <button type="button" class="btn btn-primary btn-circle btn-xl btnHidden" style="cursor: default"></button>
                    <button type="button" class="btn btn-warning btn-circle btn-xl btnFeature" onclick="location.href = 'upload' " style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">發表</button>
                                
                   <button type="button" class="btn btn-primary btn-circle btn-xl btnHidden" style="cursor: default"></button>
                    <button type="button" class="btn btn-danger btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">收藏</button>
                    <button type="button" class="btn btn-primary btn-circle btn-xl btnHidden" style="cursor: default"></button>
                    <button type="button" class="btn btn-success btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">維護</button>
                    <button type="button" class="btn btn-primary btn-circle btn-xl btnHidden" style="cursor: default"></button>  
                </div>
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
        </div> 
</body>
<jsp:include page="/fragment/footer.jsp" />

