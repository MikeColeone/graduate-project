package org.example.graduatemanage.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.exception.XException;
import org.example.graduatemanage.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//统一处理异常信息 返给客户端
@Slf4j
@RestControllerAdvice  //集中式的方式处理异常
public class ExceptionController {

    //    @ExceptionHandler 注解是 Spring MVC 中用来处理特定异常的注解。当控制器抛出指定的异常时，自动捕获并调用相应的函数
    //捕获XException类型的异常
   @ExceptionHandler(XException.class)
    public ResultVO exception(XException e){
       log.error(e.getMessage());
       if(e.getCode()!=null){
           return ResultVO.error(e.getCode());
       }
       return ResultVO.error(e.getNumber(),e.getMessage());
   }

   //统一处理所有异常 返回异常码以及异常信息
   @ExceptionHandler(Exception.class)
    public ResultVO exception(Exception e){
       log.error(e.getMessage());
       return ResultVO.error(Code.ERROR, e.getMessage());
   }
}
