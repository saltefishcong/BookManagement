package com.example.bookmanagement.Adapter;

import com.example.bookmanagement.Filter.AdministratorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AdministratorFilterAdapter {

    @Bean
    public FilterRegistrationBean administrator(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setName("administrator");
        AdministratorFilter filter=new AdministratorFilter();
        bean.setFilter(filter);
        bean.setOrder(1);
        List<String> list=new ArrayList<>();
       // list.add("/addShelfObtained");
        list.add("/findUserList");
        list.add("/deleteUser");
        bean.setUrlPatterns(list);
        return bean;
    }
}
