package com.alcohol.config.quartz;

import javax.annotation.Resource;

import com.alcohol.service.OrderSellDailyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class QuartzConfiguration {

	@Resource
	private OrderSellDailyService orderSellDailyService;
	@Resource
	private MethodInvokingJobDetailFactoryBean jobDetailFactory;
	@Resource
	private CronTriggerFactoryBean cronTriggerFactoryBean;
	
	/**
	 * 创建一个工厂
	 * @return
	 */
	@Bean
	public MethodInvokingJobDetailFactoryBean createJobDetail() {
		//new出jobDetailFactory对象，此工厂主要用来制作一个jobDetail，即制作一个任务
		//由于我们所做的定时任务根本上讲起始就是一个执行方法，所以用这个工厂比较方柏霓
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		//设置jobDetail的名字
		jobDetailFactoryBean.setName("product_sell_daily_job");
		//设置jobDetail的组名
		jobDetailFactoryBean.setGroup("job_product_sell_daily_group");
		//对于相同的jobDetail，当指定多个trigger是，很可能第一个job完成之前，第二个job就开始了
		//指定concurrent设为false，多个job不会并发运行，第二个job将不会再第一个完成之前开始
		jobDetailFactoryBean.setConcurrent(false);
		//指定运行任务的类
		jobDetailFactoryBean.setTargetObject(orderSellDailyService);
		//指定运行任务的方法
		jobDetailFactoryBean.setTargetMethod("dailyOrderSupermarketCheck");
		
		return jobDetailFactoryBean;
	}
	
	@Bean
	public CronTriggerFactoryBean createProductSellDailyTrigger() {
		//创建triggerfactory实例，用来创建trigger
		CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
		//设置triggerFactory的名字
		triggerFactoryBean.setName("product_sell_daily_trigger");
		//设置triggerFactory的组名
		triggerFactoryBean.setGroup("job_product_sell_daily_group");
		//绑定jobDetail
		triggerFactoryBean.setJobDetail(jobDetailFactory.getObject());
		//设定cron表达式   每一秒执行一次    0/1 * * * * ?
		triggerFactoryBean.setCronExpression("23 * * * * ?");
		return triggerFactoryBean;
	}
	
	@Bean
	public SchedulerFactoryBean createSchedulerFactory() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
		return schedulerFactoryBean;
	}
	

}
