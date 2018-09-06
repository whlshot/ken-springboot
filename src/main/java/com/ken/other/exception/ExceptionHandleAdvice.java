package com.ken.other.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 通过@ControllerAdvice，我们可以将对于控制器的全局配置放置在同一个位置，
 * 注解了@Controller的类的方法可以使用@ExceptionHandler、@InitBinder、@ModelAttribute
 * 注解到方法上，这对所有注解了@RequestMapping的控制器内的方法都有效。
 *
 * @ExceptionHandler: 用于全局处理控制器里的异常。
 * @InitBinder: 用来设置WebDataBinder, WebDataBinder用来自动绑定前台请求参数到Model中。
 * @ModelAttribute: @ModelAttribute 本来的作用是绑定键值对到Model里，此处是让全局的@RequestMapping都能获得在此处设置的键值对。
 */
@ControllerAdvice
public class ExceptionHandleAdvice {

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e) {
        return e.getMessage();
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }


}
