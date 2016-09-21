package com.milan.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.milan.bean.SecKill;
import com.milan.dto.Exposer;
import com.milan.dto.SecKillExecution;
import com.milan.dto.SecKillResult;
import com.milan.enums.SecKillStateEnum;
import com.milan.exception.RepeatKillException;
import com.milan.exception.SecKillCloseException;
import com.milan.service.SecKillService;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SecKillService seckillService;

	/**
	 * 秒杀商品清单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		List<SecKill> list = seckillService.getSecKillList();
		model.addAttribute("data", list);
		return "list";
	}
	
	/**
	 * 秒杀商品详情
	 * @param seckillId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model){
		if(seckillId == null){
			return "redirect:/seckill/list";
		}
		SecKill seckill = seckillService.getById(seckillId);
		if(seckill == null){
			return "redirect:/seckill/list";
		}
		model.addAttribute("data", seckill);
		return "detail";
	}
	
	/**
	 * 曝光秒杀URL
	 * @param seckillId
	 * @return
	 */
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SecKillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		SecKillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSecKillUrl(seckillId);
			result = new SecKillResult<Exposer>(1, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result = new SecKillResult<Exposer>(0, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SecKillResult<SecKillExecution> excute(@PathVariable("seckillId")Long seckillId,
			@PathVariable("md5")String md5,@CookieValue("userPhone")Long userPhone){
		SecKillResult<SecKillExecution> result;
		if(userPhone == null){
			return new SecKillResult<SecKillExecution>(0, "未注册!");
		}
		try {
			SecKillExecution excution = seckillService.execuetSeckill(seckillId, userPhone, md5);
			result = new SecKillResult<SecKillExecution>(1, excution);
		}catch (SecKillCloseException e1) {
			SecKillExecution excution = new SecKillExecution(seckillId, SecKillStateEnum.END, null);
			result = new SecKillResult<SecKillExecution>(1,excution);
		}catch (RepeatKillException e2) {
			SecKillExecution excution = new SecKillExecution(seckillId, SecKillStateEnum.REPEAT_KILL, null);
			result = new SecKillResult<SecKillExecution>(1,excution);
		} catch (Exception e) {
			SecKillExecution excution = new SecKillExecution(seckillId, SecKillStateEnum.INNER_ERROR, null);
			result = new SecKillResult<SecKillExecution>(1,excution);
		}
		return result;
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SecKillResult<Long> time(){
		Date now = new Date();
		return new SecKillResult<Long>(1, now.getTime());
	}
}
