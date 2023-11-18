package com.zy.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import Control.ControllerLayer;
import Server.PersonService;
import Dao.DataDAO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class Application implements ErrorController {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ControllerLayer homeController(PersonService personService) {
        return new ControllerLayer(personService);
    }

    @Bean
    public PersonService addressBookService(DataDAO dataDAO) {
        return new PersonService(dataDAO);
    }

    @Bean
    public DataDAO addressBookDAO() {
        return new DataDAO();
    }

    // 添加以下方法来处理/error路径
    @RequestMapping("/error")
    public String handleError() {
        // 处理错误逻辑，可以返回自定义的错误页面或其他处理方式
        return "errorPage"; // 返回自定义的错误页面
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
