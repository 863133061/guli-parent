package com.atguigu.demo.excl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExclListener extends AnalysisEventListener<DemoData> {

    List<DemoData> list=new ArrayList<>();

    //一行一行去读取excle内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("----"+data);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
