package com.example.lenovo.mytestcode.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2017/4/21.
 */
@Entity(nameInDb = "play_progress")
public class PlayProgress {
  private String program_number;
  private String event_id;
  private String time;
  @Generated(hash = 1069726584)
  public PlayProgress(String program_number, String event_id, String time) {
      this.program_number = program_number;
      this.event_id = event_id;
      this.time = time;
  }
  @Generated(hash = 1597896239)
  public PlayProgress() {
  }
  public String getProgram_number() {
      return this.program_number;
  }
  public void setProgram_number(String program_number) {
      this.program_number = program_number;
  }
  public String getEvent_id() {
      return this.event_id;
  }
  public void setEvent_id(String event_id) {
      this.event_id = event_id;
  }
  public String getTime() {
      return this.time;
  }
  public void setTime(String time) {
      this.time = time;
  }
}
