package com.example.lenovo.mytestcode.network.bean;

import com.example.lenovo.mytestcode.utils.Constant;

/**
 * Created by lenovo on 2017/6/16.
 */

public class HttpResult<T> {
  private int code;
  private String resultMessage;
  private T data;

  private int count;
  private int start;
  private String title;
  private int total;
  private T subjects;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public T getSubjects() {
    return subjects;
  }

  public void setSubjects(T subjects) {
    this.subjects = subjects;
  }

  @Override
  public String toString() {
    return "HttpResult{" +
            "code=" + code +
            ", resultMessage='" + resultMessage + '\'' +
            ", data=" + data +
            ", count=" + count +
            ", start=" + start +
            ", title='" + title + '\'' +
            ", total=" + total +
            ", subjects=" + subjects +
            '}';
  }

  public boolean isSuccess() {
    return data != null && code == Constant.SERVER_RESPONSE_CODE_OK;
  }

  public boolean isDefaultSuccess() {
    return code == Constant.SERVER_RESPONSE_CODE_OK;
  }
}
