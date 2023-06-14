package com.sgz.banlv.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{

    private boolean msg;
    private T data;
    private String error;


    public static <T> Result<T> success(){
        return new Result<>( true, null, null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>( true, data,null);
    }

    public static <T> Result<T> failure(){
        return new Result<>( false, null,null);
    }


    public static <T> Result<T> failure(String error){
        return new Result<>( false, null, error);
    }

//    private Map<String,Object> toMap(boolean msg, T data) {
//        Map map = new HashMap<String,Object>();
//        map.put("msg",msg);
//        map.put("data",data);
//        return map;
//    }
//    return new Result<>().toMap(true, data)
}
