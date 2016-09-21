<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>秒杀商品列表</title>
  	<%@include file="common/header.jsp" %>
  </head>
  <body>
    <div class="container">
    	<div class="panel panel-default">
    		<div class="panel-heading text-center">
    			<h2>秒杀列表</h2>
    		</div>
    		<div class="panel-body">
    			<table class="table table-hover">
    				<thead>
    					<tr>
    						<th>名称</th>
    						<th>库存</th>
    						<th>开始时间</th>
    						<th>结束时间</th>
    						<th>创建时间</th>
    						<th>详情页</th>
    					</tr>
    				</thead>
    				<tbody>
    					<c:forEach var="sk" items="${data }">
    						<tr>
    							<td>${sk.goodName }</td>
    							<td>${sk.number }</td>
    							<td><fmt:formatDate value="${sk.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    							<td><fmt:formatDate value="${sk.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    							<td><fmt:formatDate value="${sk.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    							<td><a class="btn btn-info" href="/seckill/${sk.id}/detail" target="_blank"/>详情</td>
    						</tr>
    					</c:forEach>
    				</tbody>
    			</table>
    		</div>
    	</div>
    </div>
  </body>
  <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
  <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>