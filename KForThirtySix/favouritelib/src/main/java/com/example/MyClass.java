package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String args[]) {
        //数据库图表(数据库的框架)
        //第一个参数是数据库的版本号
        //第二个参数是我自动生成代码的包名
        Schema schema = new Schema(1, "com.hongxiang.kforthirtysix.sql");
        addNote(schema);
        //自动生成代码
        //第一个参数 图表对象 第二个参数是自动生成代码的路径
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {


        }

    }


    public static void addNote(Schema schema) {
        //添加表名
        Entity entity = schema.addEntity("FavouriteText");
        //id,并且id自增
        entity.addIdProperty().autoincrement().primaryKey();
        //添加各种类型的属性
        entity.addStringProperty("title");
        entity.addStringProperty("writer");
        entity.addStringProperty("urlid");
        entity.addStringProperty("imgurl");



    }
}
