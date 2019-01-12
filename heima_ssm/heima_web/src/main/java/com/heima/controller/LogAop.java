package com.heima.controller;

import com.heima.service.SysLogService;
import com.heima.ssm.domain.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法
    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法

    @Before("execution(* com.heima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数
        //获取具体执行的方法的Method对象
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        method = methodSignature.getMethod();
    }


    //后置通知
    @After("execution(* com.heima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    //获取url
                    String url = classValue[0] + methodValue;
                    //获取访问的ip
                    String ip = request.getRemoteAddr();
                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    //从上下文中获了当前登录的用户
                    String username = context.getAuthentication().getName();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);//执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    //调用Service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
