package com.example.lenovo.mytestcode.bean;

import java.io.Serializable;

/**
 * Created by sunannan on 2016/11/14.
 */

public class SceneInfo implements Serializable {
  public static final int SCENE_TYPE_CUSTOM = 200;
  public static final int SCENE_TYPE_RECOMMEND = 201;
  private int sceneId;
  private String name;
  private int duration;
  private String iconUrl;
  private int sceneType;
  private int programNumber;//车机首页轮播频编号
  private int streamType;//车机首页轮播频道类型
  private String streamUrl;//车机首页轮播频道播放地址
  private int eventId;//车机首页轮播节目id
  /**
   * 正在播放状态，true为正在播放，false为其他状态
   */
  private boolean playingStatus;

  public boolean isPlayingStatus() {
    return playingStatus;
  }

  public void setPlayingStatus(boolean playingStatus) {
    this.playingStatus = playingStatus;
  }

  public int getSceneId() {
    return sceneId;
  }

  public void setSceneId(int sceneId) {
    this.sceneId = sceneId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  public int getSceneType() {
    return sceneType;
  }

  public void setSceneType(int sceneType) {
    this.sceneType = sceneType;
  }

  public int getProgramNumber() {
    return programNumber;
  }

  public int getStreamType() {
    return streamType;
  }

  public void setStreamType(int streamType) {
    this.streamType = streamType;
  }

  public String getStreamUrl() {
    return streamUrl;
  }

  public void setStreamUrl(String streamUrl) {
    this.streamUrl = streamUrl;
  }

  public void setProgramNumber(int programNumber) {
    this.programNumber = programNumber;
  }

  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {
    this.eventId = eventId;
  }

  @Override
  public String toString() {
    return "SceneInfo{" +
            "sceneId=" + sceneId +
            ", name='" + name + '\'' +
            ", duration=" + duration +
            ", iconUrl='" + iconUrl + '\'' +
            ", sceneType=" + sceneType +
            ", playingStatus=" + playingStatus +
            '}';
  }
}
