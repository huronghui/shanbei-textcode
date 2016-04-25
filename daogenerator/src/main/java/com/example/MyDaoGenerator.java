package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
/**
 * Created by wangqiang on 2016/3/8.
 */
public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(1, "com.shanbei.greendao");
        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addWordInfo(schema);
        addLessonInfo(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
    }

    private static void addWordInfo(Schema schema){
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity wordInfo = schema.addEntity("ScallopWord");
        // 你也可以重新给表命名
        // note.setTableName("NODE");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        wordInfo.setTableName("ScallopWordCache");

        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.

        wordInfo.addIdProperty().primaryKey().autoincrement();
        wordInfo.addStringProperty("lessonBelong").notNull();//课程归属
        wordInfo.addStringProperty("content").notNull();//单词内容
        wordInfo.addStringProperty("level").notNull();//单词等级
//        wordInfo.addStringProperty("meaning").notNull();//单词意思
        wordInfo.addStringProperty("info").notNull();//单词意思

    }


    private static void addLessonInfo(Schema schema){
        Entity lessonInfo = schema.addEntity("ScallopLessonInfo");
        lessonInfo.setTableName("ScallopLessonCache");
        lessonInfo.addIdProperty().primaryKey().autoincrement();
        lessonInfo.addStringProperty("UnitBelong").notNull();
        lessonInfo.addStringProperty("LessonList").notNull();
        lessonInfo.addStringProperty("content").notNull();
        lessonInfo.addStringProperty("title").notNull();
//        lessonInfo.addStringProperty("meaning").notNull();
        lessonInfo.addStringProperty("info").notNull();
    }
}
