package com.aid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 12200
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.aid.mapper"})
public class FirstAidApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(FirstAidApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
