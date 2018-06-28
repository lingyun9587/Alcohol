package com.alcohol.util;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler implements ErrorController  {

    private static final String Error_PATH="/error";


    @RequestMapping(value = Error_PATH)
    public String handelError(HttpServletRequest request){
        request.setAttribute("error","404");
        request.setAttribute("errors","页面找不见了");
        return "error/error";
    }


    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request, Exception exception
                                      ) throws Exception{
           exception.printStackTrace();
           System.out.println("我报错了："+exception.getLocalizedMessage());
           System.out.println("我报错了："+exception.getCause());
           System.out.println("我报错了："+exception.getSuppressed());
           System.out.println("我报错了："+exception.getMessage());
           System.out.println("我报错了："+exception.getStackTrace());
           request.setAttribute("error","服务器出问题了，请联系管理员");
           request.setAttribute("errors",exception.getMessage());
           return "error/error";
    }

    @Override
    public String getErrorPath() {
        return Error_PATH;
    }
}
