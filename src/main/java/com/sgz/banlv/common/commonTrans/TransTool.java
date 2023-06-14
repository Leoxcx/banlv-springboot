package com.sgz.banlv.common.commonTrans;

public class TransTool {
    // 单例
    // 空参构造
    private TransTool() {
        // 禁止该类被实例化
    }
    private static TransTool transTool = null;
    //静态工厂方法
    public static TransTool getTransTool() {
        if (transTool == null) {
            transTool = new TransTool();

            // init();
        }
        return transTool;
    }




}
