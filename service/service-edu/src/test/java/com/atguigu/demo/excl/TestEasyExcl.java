package com.atguigu.demo.excl;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcl {
    public static void main(String[] args) {
        //实现写操作
        //设置写操作的存放路径

        //String filename="G:\\write.xlsx";

        //调用easyexcl 实现写操作
        //.doWrite()  list;
        //EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(TestEasyExcl.getDate());

        //实现excl读取
        String filename="G:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExclListener()).sheet().doRead();
    }
    //创建返回list集合
    private static List<DemoData> getDate(){
        List<DemoData> list=new ArrayList<>();
        for (int i = 0; i <10; i++) {
            DemoData demoData=new DemoData();
            demoData.setSname("luck"+i);
            demoData.setSno(i);
            list.add(demoData);
        }
        return list;
    }
}
