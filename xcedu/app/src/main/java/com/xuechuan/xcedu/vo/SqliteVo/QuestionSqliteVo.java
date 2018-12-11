package com.xuechuan.xcedu.vo.SqliteVo;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.vo.SqliteVo
 * @Description: 问题数据库
 * @author: L-BackPacker
 * @date: 2018.12.10 上午 11:34
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class QuestionSqliteVo {
    private int id;
    private byte[] question;
    private String questionimg;
    private int isreadcom;
    private int parent_id;
    private int questiontype;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private String option_e;
    private String option_f;
    private String option_g;
    private String option_h;
    private String choice_answer;
    private byte[] explained;
    private byte[] explainimg;
    private int chapter_id;
    private int question_mold;
    private int sort;
    private byte[] keywords;
    private int difficulty;
    private double wrong_rate;
    private double score;
    private int courseid;

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getQuestion() {
        return question;
    }

    public void setQuestion(byte[] question) {
        this.question = question;
    }

    public String getQuestionimg() {
        return questionimg;
    }

    public void setQuestionimg(String questionimg) {
        this.questionimg = questionimg;
    }

    public int getIsreadcom() {
        return isreadcom;
    }

    public void setIsreadcom(int isreadcom) {
        this.isreadcom = isreadcom;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(int questiontype) {
        this.questiontype = questiontype;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getOption_e() {
        return option_e;
    }

    public void setOption_e(String option_e) {
        this.option_e = option_e;
    }

    public String getOption_f() {
        return option_f;
    }

    public void setOption_f(String option_f) {
        this.option_f = option_f;
    }

    public String getOption_g() {
        return option_g;
    }

    public void setOption_g(String option_g) {
        this.option_g = option_g;
    }

    public String getOption_h() {
        return option_h;
    }

    public void setOption_h(String option_h) {
        this.option_h = option_h;
    }

    public String getChoice_answer() {
        return choice_answer;
    }

    public void setChoice_answer(String choice_answer) {
        this.choice_answer = choice_answer;
    }

    public byte[] getExplained() {
        return explained;
    }

    public void setExplained(byte[] explained) {
        this.explained = explained;
    }

    public byte[] getExplainimg() {
        return explainimg;
    }

    public void setExplainimg(byte[] explainimg) {
        this.explainimg = explainimg;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public int getQuestion_mold() {
        return question_mold;
    }

    public void setQuestion_mold(int question_mold) {
        this.question_mold = question_mold;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public byte[] getKeywords() {
        return keywords;
    }

    public void setKeywords(byte[] keywords) {
        this.keywords = keywords;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public double getWrong_rate() {
        return wrong_rate;
    }

    public void setWrong_rate(double wrong_rate) {
        this.wrong_rate = wrong_rate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
