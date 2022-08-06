package com.example.demo.util;

import javafx.beans.binding.ObjectExpression;
import lombok.Data;

@Data
// 定义的统一的返回对象
public class ResultData {

    private int succ;
    private Object data;
    private String msg;

    //正常的数据
    public static ResultData ok(Object data, String msg) {
        ResultData resultData = new ResultData();
        resultData.setSucc(200);
        resultData.setData(data);
        resultData.setMsg(msg);
        return resultData;
    }

    //错误的数据
    public static ResultData fail(int succ, Object data, String msg) {
        ResultData resultData = new ResultData();
        resultData.setSucc(succ);
        resultData.setData(data);
        resultData.setMsg(msg);
        return resultData;
    }
}
