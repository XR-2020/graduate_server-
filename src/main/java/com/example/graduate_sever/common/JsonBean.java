package com.example.graduate_sever.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonBean {
    private int code;
    private String msg;
    private Object data;
}
