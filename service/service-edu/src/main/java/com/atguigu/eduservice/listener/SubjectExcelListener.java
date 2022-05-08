package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excl.ExcelSubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //读取excl内容一行一行读
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if(excelSubjectData==null){
            throw  new GuliException(20001,"文件数据为空");
        }
        //一行一行读取，第二个值为二级分类
        //每次读取的值不能为重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, excelSubjectData.getOneSubjectName());
        if(existOneSubject==null){
            //没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }
        String pid=existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, excelSubjectData.getTwoSubjectName(), pid);
         if(existTwoSubject==null){
             existTwoSubject = new EduSubject();
             existTwoSubject.setParentId(pid);
             existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
             eduSubjectService.save(existTwoSubject);
        }

    }
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> query=new QueryWrapper<>();
        query.eq("title",name);
        query.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(query);
        return eduSubject;
    }
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String  pid){
        QueryWrapper<EduSubject> query=new QueryWrapper<>();
        query.eq("title",name);
        query.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(query);
        return eduSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
