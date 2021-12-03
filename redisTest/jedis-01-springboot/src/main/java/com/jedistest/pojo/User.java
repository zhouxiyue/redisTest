package com.jedistest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
//pojo类需要序列化
public class User implements Serializable {
    private String name;
    private int age;
    
}
