package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：网易公开课分类
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class NetaseClassify {


    private DataBean data;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {

        private ClassifyConfigBean classifyConfig;


        public ClassifyConfigBean getClassifyConfig() {
            return classifyConfig;
        }

        public void setClassifyConfig(ClassifyConfigBean classifyConfig) {
            this.classifyConfig = classifyConfig;
        }


        public static class ClassifyConfigBean {
            private List<DataBean.ClassifyConfigBean.SourceBean> source;
            private List<DataBean.ClassifyConfigBean.BaseBean> base;
            private List<DataBean.ClassifyConfigBean.SpecialBean> special;

            public List<DataBean.ClassifyConfigBean.SourceBean> getSource() {
                return source;
            }

            public void setSource(List<DataBean.ClassifyConfigBean.SourceBean> source) {
                this.source = source;
            }

            public List<DataBean.ClassifyConfigBean.BaseBean> getBase() {
                return base;
            }

            public void setBase(List<DataBean.ClassifyConfigBean.BaseBean> base) {
                this.base = base;
            }

            public List<DataBean.ClassifyConfigBean.SpecialBean> getSpecial() {
                return special;
            }

            public void setSpecial(List<DataBean.ClassifyConfigBean.SpecialBean> special) {
                this.special = special;
            }

            public static class SourceBean {
                /**
                 * id : 4
                 * templateType : TED
                 * notUseName : false
                 * name : TED
                 */

                private int id;
                private String templateType;
                private boolean notUseName;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTemplateType() {
                    return templateType;
                }

                public void setTemplateType(String templateType) {
                    this.templateType = templateType;
                }

                public boolean isNotUseName() {
                    return notUseName;
                }

                public void setNotUseName(boolean notUseName) {
                    this.notUseName = notUseName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class BaseBean {
                /**
                 * id : 23
                 * templateType : 心理
                 * notUseName : false
                 * name : 心理
                 */

                private int id;
                private String templateType;
                private boolean notUseName;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTemplateType() {
                    return templateType;
                }

                public void setTemplateType(String templateType) {
                    this.templateType = templateType;
                }

                public boolean isNotUseName() {
                    return notUseName;
                }

                public void setNotUseName(boolean notUseName) {
                    this.notUseName = notUseName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class SpecialBean {
                /**
                 * id : 33
                 * templateType :
                 * notUseName : false
                 * name : 灯下尘
                 */

                private int id;
                private String templateType;
                private boolean notUseName;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTemplateType() {
                    return templateType;
                }

                public void setTemplateType(String templateType) {
                    this.templateType = templateType;
                }

                public boolean isNotUseName() {
                    return notUseName;
                }

                public void setNotUseName(boolean notUseName) {
                    this.notUseName = notUseName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

    }
}
