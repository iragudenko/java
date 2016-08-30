package com.springjpamvc.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.springjpamvc.controllers"})
//«модель-представление-контроллер», «модель-вид-контроллер») —схема использования нескольких шаблонов проектирования,
// с помощью которых модель данных приложения, пользовательский интерфейс и взаимодействие с пользователем разделены на
// три отдельных компонента таким образом, чтобы модификация одного из компонентов оказывала минимальное воздействие на остальные.
//      Модель (англ. Model)предоставляет знания: данные и методы работы с этими данными, реагирует на запросы, изменяя своё состояние.
// Не содержит информации, как эти знания можно визуализировать.
//      Представление, вид (англ. View)отвечает за отображение информации (визуализацию).
//      Контроллер (англ. Controller) обеспечивает связь между пользователем и системой: контролирует ввод данных пользователем
// и использует модель и представление для реализации необходимой реакции.
public class SpringMVCContext extends WebMvcConfigurerAdapter {

//Handling Mapping
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//view resolver
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        return new CommonsMultipartResolver();
    }


}
