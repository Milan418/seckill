<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>秒杀商品详情</title>
  	<%@include file="common/header.jsp" %>
  </head>
  <body>
    <div class="container">
    	<div class="panel panel-default text-center">
    		<div class="panel-heading">
    		   <h1>${data.goodName }</h1>
    		</div>
    		<div class="panel-body">
    			<h2 class="text-danger">
    				<span class="glyphicon glyphicon-time"></span>
    				<span class="glyphicon" id="seckill-box"></span>
    			</h2>
    		</div>
    	</div>
    </div>
    <div id="killPhoneModal" class="modal fade">
    	<div class="modal-dialog">
    		<div class="modal-content">
    			<div class="modal-header">
    				<h3 class="modal-title text-center">
    					<span class="glyphicon glyphicon-phone"></span>秒杀手机:
    				</h3>
    			</div>
    			<div class="modal-body">
    				<div class="row">
    					<div class="col-xs-8 col-xs-offset-2">
    						<input type="text" name="userPhone" id="userPhone" 
    							placeholder="手机号" class="form-controll">
    					</div>
    				</div>
    			</div>
    			<div class="modal-footer">
    				<span id="killPhoneMessage" class="glyphicon"></span>
    				<button type="button" id="killPhoneBtn" class="btn btn-success">
    					<span class="glyphicon glyphicon-phone"></span>submit
    				</button>
    			</div>
    		</div>
    	</div>
    </div>
  </body>
  <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <!-- 倒计时插件 -->
  <script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.js"></script>
  <!-- JQuery cookie操作插件 -->
  <script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
  <script src="/resource/js/seckill.js" type="text/javascript"></script>
  <script type="text/javascript">
  $(function(){
		seckill.detail.init({
			'seckillId':${data.id},
			'startTime':${data.startTime.time},
			'endTime':${data.endTime.time}
		});
	});
  </script>
</html>