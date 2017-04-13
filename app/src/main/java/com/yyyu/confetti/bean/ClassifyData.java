package com.yyyu.confetti.bean;

import java.util.List;

/**
 * 功能：分类中数据（四种来源统一封装）
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ClassifyData {

    private List<ClassifyItem> classifyItemList;

    public List<ClassifyItem> getClassifyItemList() {
        return classifyItemList;
    }

    public void setClassifyItemList(List<ClassifyItem> classifyItemList) {
        this.classifyItemList = classifyItemList;
    }
}
