package com.hou.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public  class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许来自 http://localhost:8080 的请求访问，你也可以根据需要替换为其他URL或设置为 "*" 表示允许所有源
        registry.addMapping("/**") // 表示对所有的请求都进行CORS配置
                .allowedOrigins("http://localhost:8080") // 允许哪些源的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // 允许的请求方法
                .allowedHeaders("*") // 允许的头信息
                .allowCredentials(true) // 是否允许发送cookie，默认不允许
                .maxAge(3600); // 跨域有效时长（秒），在有效期内，浏览器无须就相同的跨域请求再次进行预检
        // 你可以通过.exposedHeaders()来指定响应头中的哪些头部信息可以暴露给客户端
        // .exposedHeaders("header1", "header2");
    }
}