package com.example.view_texst;


/**
 * Created by Administrator on 2016/12/23.
 */
public class DrugUseTest {

    private int id;                              //id
    private int user_id;                          //用户id
    private String serial_field;                    //序列化内容
    private int score;                           //问卷得分
    private TestType test_type;                   //测试表类型
    private String date;                         //问卷填写时间

    public DrugUseTest() {
    }

    public DrugUseTest(int id, int user_id, String serial_field, int score, TestType test_type, String date) {
        this.id = id;
        this.user_id = user_id;
        this.serial_field = serial_field;
        this.score = score;
        this.test_type = test_type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSerial_field() {
        return serial_field;
    }

    public void setSerial_field(String serial_field) {
        this.serial_field = serial_field;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TestType getTest_type() {
        return test_type;
    }

    public void setTest_type(TestType test_type) {
        this.test_type = test_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DrugUseTest{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", serial_field='" + serial_field + '\'' +
                ", score=" + score +
                ", test_type=" + test_type +
                ", date='" + date + '\'' +
                '}';
    }
}
