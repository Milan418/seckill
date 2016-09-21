-- 数据库初始化脚本
-- CREATE DATABASE seckill
-- 创建秒杀库存表
create table t_seckill(
	id INT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
	good_name VARCHAR(120)  NOT NULL COMMENT '商品名称',
	number int NOT NULL COMMENT '库存数量',
	start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
	end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
	create_time TIMESTAMP NOT NULL COMMENT '创建时间',
	PRIMARY KEY(id),
	KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=INNODB  AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表'

-- 插入秒杀数据
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("2500元秒杀iphone 6",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("2000元秒杀iphone 6s",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("2500元秒杀ipad mini",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("1500元秒杀iphone 5",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("100元秒杀iphone 耳机",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("500元秒杀红米",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');
INSERT INTO t_seckill(good_name,number,start_time,end_time,create_time) values("100元秒杀大衣",100,'2016-07-14 00:00:00','2016-07-15 00:00:00','2016-07-13 23:34:00');

-- 创建秒杀成功明细表
create table t_success_killed(
	seckill_id INT NOT NULL COMMENT '秒杀商品ID',
  user_phone INT NOT NULL COMMENT '用户手机号',
	state TINYINT NOT NULL DEFAULT -1 COMMENT '状态码：-1(无效),0(成功),1(已付款),2(已发货)',
	create_time TIMESTAMP NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckillid,user_phone),/*联合主键*/
	KEY idx_create_time(create_time)
)ENGINE=INNODB CHARSET=utf8 COMMENT='秒杀成功明细表'