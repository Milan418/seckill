 var seckill ={
		 /*秒杀相关地址*/
		 URL:{
			 now:function(){
				 return "/seckill/time/now";
			 },
			 exposer:function(seckillId){
				 return "/seckill/"+seckillId+"/exposer";
			 },
			 killUrl:function(seckillId,md5){
				 return "/seckill/"+seckillId+"/"+md5+"/execution";
			 }
		 },
		 //验证手机号
		 validatePhone:function(phone){
			 if(phone && phone.length == 11 && !isNaN(phone)){
				 return true;
			 }else{
				 return false;
			 }
		 },
		 //获取秒杀地址，控制显示，执行秒杀
		 handlerKill:function(seckillId,node){
			 //显示秒杀按钮
			 node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>').show();
			 //获取秒杀地址
			 $.post(seckill.URL.exposer(seckillId),{},function(result){
				 if(result && result['errcode']==1){
					 var exposer = result['data'];
					 if(exposer['exposed']){
						 //开启秒杀
						 var md5 = exposer['md5'];
						 //绑定一次点击事件
						 $("#killBtn").one('click',function(){//执行秒杀
							//1.禁用按钮
							 $(this).addClass('disabled');
							 //2.发送秒杀请求
							 $.post(seckill.URL.killUrl(seckillId,md5),{},function(result){
								 if(result && result['errcode']==1){
									 //显示秒杀结果
									 var killResult =  result['data'];
									 var state = killResult['state'];
									 var stateInfo = killResult['stateInfo'];
									 node.html('<span class="label label-success">'+stateInfo+'</label>');
								 }
							 });
						 });
					 }else{
						 //未开启秒杀,重新进入计时逻辑
						 var now = exposer['now'];
						 var start = exposer['start'];
						 var end = expsoer['end'];
						 seckill.countdown(seckillId,now,start,end);
					 }
				 }else{
					 console.log(result);
				 }
			 });
		 },
		 countdown:function(seckillId,nowTime,startTime,endTime){
			 var seckillBox = $("#seckill-box");
			 if(nowTime > endTime){
				 seckillBox.html("秒杀结束");
			 }else if(nowTime < startTime){//秒杀未开始
				 //计时操作
				 var killTime = new Date(startTime + 1000);//加上1000防止用户系统时间偏移
				 seckillBox.countdown(killTime,function(event){
					 var format = event.strftime("秒杀倒计时: %D天 %H时 %M分 %S秒");
					 seckillBox.html(format);
				 }).on("finish.countdown",function(){
					 seckill.handlerKill(seckillId,seckillBox);
				 });
			 }else{//秒杀开始
				 seckill.handlerKill(seckillId,seckillBox);
			 }
		 },
		 //秒杀相关逻辑
		 detail:{
			 //详情页初始化
			 init:function(params){
				 //用户手机验证和登录，计时交互
				 //cookie中获取手机号
				 var userPhone = $.cookie("userPhone");
				 var startTime = params["startTime"];
				 var endTime = params["endTime"];
				 var seckillId = params["seckillId"];
				 if(!seckill.validatePhone(userPhone)){
					 //绑定phone
					 var killPhoneModal = $("#killPhoneModal");
					 killPhoneModal.modal({
						 show:true,
						 backdrop:'static',//禁止位置关闭
						 keyboard:false //关闭键盘事件
					 });
					 $("#killPhoneBtn").click(function(){
						 var inputPhone = $("#userPhone").val();
						 if(seckill.validatePhone(inputPhone)){
							 //写数据到cookie
							 $.cookie('userPhone',inputPhone,{expires:7,path:'/seckill'});
							 //刷新页面
							 window.location.reload();
						 }else{
							 $("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误</label>').show(300);
						 }
					 });
				 }
				 
				 //计时
				 $.get(seckill.URL.now(),{},function(result){
					if(result && result['errcode']==1){
						//时间判断
						seckill.countdown(seckillId,result['data'],startTime,endTime)
					} else{
						console.log(result);
					}
				 });
			 }
		 }
 }