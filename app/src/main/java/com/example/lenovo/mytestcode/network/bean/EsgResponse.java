package com.example.lenovo.mytestcode.network.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/6/14.
 */

public class EsgResponse {

  /**
   * protocol_version : 6
   * timestamp : 1495786764
   * pat : {"version_number":0,"pat_arr":[{"program_number":1,"stream_pid":3004,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3004/3004.m3u8","stream_type":3,"inflate_mode":1},{"program_number":2,"stream_pid":3005,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3005/3005.m3u8","stream_type":3,"inflate_mode":1},{"program_number":3,"stream_pid":3006,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3006/3006.m3u8","stream_type":3,"inflate_mode":1},{"program_number":5,"stream_pid":3008,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3008/3008.m3u8","stream_type":3,"inflate_mode":1},{"program_number":6,"stream_pid":3009,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3009/3009.m3u8","stream_type":3,"inflate_mode":1},{"program_number":7,"stream_pid":3010,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3010/3010.m3u8","stream_type":3,"inflate_mode":1},{"program_number":8,"stream_pid":3011,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3011/3011.m3u8","stream_type":3,"inflate_mode":1},{"program_number":9,"stream_pid":3012,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3012/3012.m3u8","stream_type":3,"inflate_mode":1},{"program_number":11,"stream_pid":3014,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3014/3014.m3u8","stream_type":3,"inflate_mode":1},{"program_number":12,"stream_pid":3015,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3015/3015.m3u8","stream_type":3,"inflate_mode":1},{"program_number":13,"stream_pid":3016,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3016/3016.m3u8","stream_type":3,"inflate_mode":1},{"program_number":14,"stream_pid":3017,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3017/3017.m3u8","stream_type":3,"inflate_mode":1},{"program_number":15,"stream_pid":3018,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3018/3018.m3u8","stream_type":3,"inflate_mode":1},{"program_number":16,"stream_pid":3019,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3019/3019.m3u8","stream_type":3,"inflate_mode":1},{"program_number":17,"stream_pid":3020,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3020/3020.m3u8","stream_type":3,"inflate_mode":1},{"program_number":18,"stream_pid":3021,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3021/3021.m3u8","stream_type":3,"inflate_mode":1},{"program_number":19,"stream_pid":3022,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3022/3022.m3u8","stream_type":3,"inflate_mode":1},{"program_number":20,"stream_pid":3023,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3023/3023.m3u8","stream_type":3,"inflate_mode":1},{"program_number":21,"stream_pid":3024,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3024/3024.m3u8","stream_type":3,"inflate_mode":1},{"program_number":22,"stream_pid":3025,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3025/3025.m3u8","stream_type":3,"inflate_mode":1},{"program_number":28,"stream_pid":3031,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3031/3031.m3u8","stream_type":3,"inflate_mode":1},{"program_number":29,"stream_pid":3032,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3032/3032.m3u8","stream_type":3,"inflate_mode":1},{"program_number":31,"stream_pid":3034,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3034/3034.m3u8","stream_type":1,"inflate_mode":1},{"program_number":300,"stream_pid":5000,"stream_url":"","stream_type":6,"inflate_mode":1},{"program_number":301,"stream_pid":5001,"stream_url":"","stream_type":6,"inflate_mode":1},{"program_number":302,"stream_pid":5002,"stream_url":"","stream_type":6,"inflate_mode":1}]}
   * sdt : {"version_number":0,"sdt_arr":[{"program_number":1,"service_provider_name":"中国国际广播电台","service_name":"CRI劲曲调频","icon_url":"http://fes.gvmedia.com.cn:80/resources/3004.png","free_ca_mode":0},{"program_number":2,"service_provider_name":"中央人民广播电台","service_name":"CNR音乐之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3005.png","free_ca_mode":0},{"program_number":3,"service_provider_name":"中央人民广播电台","service_name":"CNR文艺之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3006.png","free_ca_mode":0},{"program_number":5,"service_provider_name":"重庆市广播电台","service_name":"重庆音乐广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3008.jpg","free_ca_mode":0},{"program_number":6,"service_provider_name":"湖北省人民广播电台","service_name":"湖北经典音乐广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3009.png","free_ca_mode":0},{"program_number":7,"service_provider_name":"湖北省人民广播电台","service_name":"楚天音乐台","icon_url":"http://fes.gvmedia.com.cn:80/resources/3010.jpg","free_ca_mode":0},{"program_number":8,"service_provider_name":"江苏省人民广播电台","service_name":"江苏文艺广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3011.png","free_ca_mode":0},{"program_number":9,"service_provider_name":"四川省人民广播电台","service_name":"四川文艺广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3012.jpg","free_ca_mode":0},{"program_number":11,"service_provider_name":"中国国际广播电台","service_name":"CRI环球资讯","icon_url":"http://fes.gvmedia.com.cn:80/resources/3014.jpg","free_ca_mode":0},{"program_number":12,"service_provider_name":"中央人民广播电台","service_name":"CNR中国之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3015.png","free_ca_mode":0},{"program_number":13,"service_provider_name":"中央人民广播电台","service_name":"CNR经济之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3016.png","free_ca_mode":0},{"program_number":14,"service_provider_name":"四川省人民广播电台","service_name":"四川新闻频率","icon_url":"http://fes.gvmedia.com.cn:80/resources/3017.png","free_ca_mode":0},{"program_number":15,"service_provider_name":"重庆人民广播电台","service_name":"重庆交通广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3018.jpg","free_ca_mode":0},{"program_number":16,"service_provider_name":"湖北省人民广播电台","service_name":"湖北新闻广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3019.png","free_ca_mode":0},{"program_number":17,"service_provider_name":"北京人民广播电台","service_name":"北京新闻广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3020.png","free_ca_mode":0},{"program_number":18,"service_provider_name":"中国国际广播电台","service_name":"CRI南海之音","icon_url":"http://fes.gvmedia.com.cn:80/resources/3021.jpg","free_ca_mode":0},{"program_number":19,"service_provider_name":"中国国际广播电台","service_name":"CRI轻松调频","icon_url":"http://fes.gvmedia.com.cn:80/resources/3022.png","free_ca_mode":0},{"program_number":20,"service_provider_name":"中央人民广播电台","service_name":"CNR中国交通广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3023.png","free_ca_mode":0},{"program_number":21,"service_provider_name":"中央人民广播电台","service_name":"CNR藏语广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3024.png","free_ca_mode":0},{"program_number":22,"service_provider_name":"重庆市广播电台","service_name":"重庆都市广播私家车938","icon_url":"http://fes.gvmedia.com.cn:80/resources/3025.png","free_ca_mode":0},{"program_number":28,"service_provider_name":"江苏省人民广播电台","service_name":"江苏故事广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3031.png","free_ca_mode":1},{"program_number":29,"service_provider_name":"重庆人民广播电台","service_name":"重庆故事广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3032.jpg","free_ca_mode":1},{"program_number":31,"service_provider_name":"中央电视台","service_name":"CCTV-1(高清)","icon_url":"http://fes.gvmedia.com.cn:80/resources/3034.png","free_ca_mode":0},{"program_number":300,"service_provider_name":"","service_name":"怀旧经典","icon_url":"http://fes.gvmedia.com.cn:80/resources/cinema_icon_2.png","free_ca_mode":0},{"program_number":301,"service_provider_name":"","service_name":"微电影","icon_url":"http://fes.gvmedia.com.cn:80/resources/cinema_icon_3.png","free_ca_mode":0},{"program_number":302,"service_provider_name":"","service_name":"院线同步","icon_url":"http://fes.gvmedia.com.cn:80/resources/cinema_icon_3.png","free_ca_mode":0}]}
   * eit_arr : [{"program_number":1,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":21600,"running_status":4,"event_name":"The Night Mix","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"Morning Call","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 07:00:00","duration":10800,"running_status":4,"event_name":"Hit Morning Show","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 10:00:00","duration":10800,"running_status":4,"event_name":"At Work Network","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 13:00:00","duration":10800,"running_status":4,"event_name":"Lazy Afternoon","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 16:00:00","duration":10800,"running_status":4,"event_name":"Big Drive Home","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 19:00:00","duration":10800,"running_status":4,"event_name":"New Music Express","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 22:00:00","duration":7200,"running_status":0,"event_name":"Night Vibe","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":2,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":21600,"running_status":4,"event_name":"电台休息","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 06:00:00","duration":10800,"running_status":4,"event_name":"早安音乐","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 09:00:00","duration":10800,"running_status":4,"event_name":"自在音乐","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 12:00:00","duration":7200,"running_status":4,"event_name":"全球流行音乐金榜","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 14:00:00","duration":7200,"running_status":4,"event_name":"快意音乐","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 16:00:00","duration":10800,"running_status":4,"event_name":"都会音乐","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 19:00:00","duration":7200,"running_status":4,"event_name":"蒙牛绿色心情中国Top排行榜","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 21:00:00","duration":3600,"running_status":4,"event_name":"KAMA音乐VIP","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 22:00:00","duration":7200,"running_status":4,"event_name":"音乐万岁","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":3,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":3600,"running_status":4,"event_name":"拍案惊奇录","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 01:00:00","duration":3600,"running_status":4,"event_name":"名家书场","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 02:00:00","duration":300,"running_status":4,"event_name":"结束曲","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 02:05:00","duration":10200,"running_status":4,"event_name":"电台休息","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 04:55:00","duration":300,"running_status":4,"event_name":"全天节目预告","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 05:00:00","duration":3600,"running_status":4,"event_name":"空中书苑","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"早安我的亲","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 07:00:00","duration":7200,"running_status":4,"event_name":"快乐早点到","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 09:00:00","duration":1800,"running_status":4,"event_name":"天下奇谈","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 09:30:00","duration":1800,"running_status":4,"event_name":"笑动2013","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 10:00:00","duration":3600,"running_status":4,"event_name":"名家书场","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 11:00:00","duration":1800,"running_status":4,"event_name":"我买我开心","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 11:30:00","duration":1800,"running_status":4,"event_name":"长篇连播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 12:00:00","duration":3600,"running_status":4,"event_name":"快乐早点到","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 13:00:00","duration":3600,"running_status":4,"event_name":"欢乐中国","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":16,"start_time":"2017-06-14 14:00:00","duration":1800,"running_status":4,"event_name":"天天向上","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":17,"start_time":"2017-06-14 14:30:00","duration":1800,"running_status":4,"event_name":"非常营养","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":18,"start_time":"2017-06-14 15:00:00","duration":3600,"running_status":4,"event_name":"评书开讲","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":19,"start_time":"2017-06-14 16:00:00","duration":3600,"running_status":4,"event_name":"文化地产","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":20,"start_time":"2017-06-14 17:00:00","duration":3600,"running_status":4,"event_name":"海阳现场秀","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":21,"start_time":"2017-06-14 18:00:00","duration":3600,"running_status":4,"event_name":"时尚知道","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":22,"start_time":"2017-06-14 19:00:00","duration":3600,"running_status":4,"event_name":"中国相声榜","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":23,"start_time":"2017-06-14 20:00:00","duration":1800,"running_status":4,"event_name":"创意英雄谱","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":24,"start_time":"2017-06-14 20:30:00","duration":1800,"running_status":4,"event_name":"睡前故事","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":25,"start_time":"2017-06-14 21:00:00","duration":3600,"running_status":4,"event_name":"品味书香","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":26,"start_time":"2017-06-14 22:00:00","duration":3600,"running_status":4,"event_name":"一撇一捺的北京","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":27,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"快乐不限速","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":5,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"重庆音乐广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":6,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"湖北经典音乐广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":7,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"楚天音乐台","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":8,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"江苏文艺广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":9,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"四川文艺广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]},{"program_number":11,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":3600,"running_status":4,"event_name":"大话体坛(重播)","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 01:00:00","duration":3600,"running_status":4,"event_name":"环球名人坊（重播） /环球故事会（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 02:00:00","duration":3600,"running_status":4,"event_name":"边走边看（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 03:00:00","duration":3600,"running_status":4,"event_name":"环球文化圈（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 04:00:00","duration":3600,"running_status":4,"event_name":"资讯非常道（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 05:00:00","duration":3600,"running_status":4,"event_name":"新闻盘点（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"档案揭秘（重播）/直播世界","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 07:00:00","duration":7200,"running_status":4,"event_name":"早间第一资讯","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 09:00:00","duration":3600,"running_status":4,"event_name":"环球媒体浏览","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 10:00:00","duration":3600,"running_status":4,"event_name":"环球故事会","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 11:00:00","duration":3600,"running_status":4,"event_name":"环球文化圈","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 12:00:00","duration":3600,"running_status":4,"event_name":"午间第一资讯","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 13:00:00","duration":3600,"running_status":4,"event_name":"老外看点/档案揭秘","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 14:00:00","duration":3600,"running_status":4,"event_name":"边走边看","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 15:00:00","duration":3600,"running_status":4,"event_name":"环球教育/新财富时间","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":16,"start_time":"2017-06-14 16:00:00","duration":3600,"running_status":4,"event_name":"新财富时间","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":17,"start_time":"2017-06-14 17:00:00","duration":7200,"running_status":4,"event_name":"晚间第一资讯","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":18,"start_time":"2017-06-14 19:00:00","duration":3600,"running_status":4,"event_name":"资讯非常道","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":19,"start_time":"2017-06-14 20:00:00","duration":3600,"running_status":4,"event_name":"新闻盘点","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":20,"start_time":"2017-06-14 21:00:00","duration":3600,"running_status":4,"event_name":"大话体坛","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":21,"start_time":"2017-06-14 22:00:00","duration":3600,"running_status":4,"event_name":"老外看点(重播)/档案揭秘（重播)","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":22,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"新闻盘点（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":12,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":7200,"running_status":4,"event_name":"千里共良宵","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 02:00:00","duration":1800,"running_status":4,"event_name":"记录中国","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 02:30:00","duration":5400,"running_status":4,"event_name":"昨日新闻重现","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 04:00:00","duration":1800,"running_status":4,"event_name":"养生大讲堂","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 04:30:00","duration":1800,"running_status":4,"event_name":"中央农业广播学校","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 05:00:00","duration":3600,"running_status":4,"event_name":"阳光购物街","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 06:00:00","duration":1800,"running_status":4,"event_name":"国防时空","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 06:30:00","duration":1800,"running_status":4,"event_name":"新闻和报纸摘要","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 07:00:00","duration":7200,"running_status":4,"event_name":"新闻纵横","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 09:00:00","duration":10800,"running_status":4,"event_name":"央广新闻","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 12:00:00","duration":3600,"running_status":4,"event_name":"全球华语广播网","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 13:00:00","duration":12600,"running_status":4,"event_name":"央广新闻（午后版）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 16:30:00","duration":7200,"running_status":4,"event_name":"央广新闻晚高峰","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 18:30:00","duration":1800,"running_status":4,"event_name":"全国新闻联播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 19:00:00","duration":3600,"running_status":4,"event_name":"央广新闻晚高峰","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":16,"start_time":"2017-06-14 20:00:00","duration":1800,"running_status":4,"event_name":"小喇叭","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":17,"start_time":"2017-06-14 20:30:00","duration":1800,"running_status":4,"event_name":"直播中国","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":18,"start_time":"2017-06-14 21:00:00","duration":10800,"running_status":4,"event_name":"央广夜新闻","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":13,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":3600,"running_status":4,"event_name":"那些年（重播） ","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 01:00:00","duration":3600,"running_status":4,"event_name":"当夜晚来临的时候（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 02:00:00","duration":3600,"running_status":4,"event_name":"财经夜读（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 03:00:00","duration":3600,"running_status":4,"event_name":"那些年（重播） ","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 04:00:00","duration":3600,"running_status":4,"event_name":"财经夜读（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 05:00:00","duration":3600,"running_status":4,"event_name":"新鲜早世界","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"天下财经","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 07:00:00","duration":1800,"running_status":4,"event_name":"新闻和报纸摘要（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 07:30:00","duration":5400,"running_status":4,"event_name":"天下财经","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 09:00:00","duration":9000,"running_status":4,"event_name":"交易实况","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 11:30:00","duration":1800,"running_status":4,"event_name":"冬吴相对论","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 12:00:00","duration":3600,"running_status":4,"event_name":"天天3.15","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 13:00:00","duration":9000,"running_status":4,"event_name":"交易实况","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 15:30:00","duration":1800,"running_status":4,"event_name":"冬吴相对论（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 16:00:00","duration":12600,"running_status":4,"event_name":"天下公司","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":16,"start_time":"2017-06-14 19:30:00","duration":1800,"running_status":4,"event_name":"冬吴相对论（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":17,"start_time":"2017-06-14 20:00:00","duration":3600,"running_status":4,"event_name":"全球资本市场","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":18,"start_time":"2017-06-14 21:00:00","duration":3600,"running_status":4,"event_name":"那些年","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":19,"start_time":"2017-06-14 22:00:00","duration":3600,"running_status":4,"event_name":"当夜晚来临的时候","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":20,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"财经夜读","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":14,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"四川新闻频率","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":15,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"重庆交通广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":16,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"湖北新闻广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":17,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":1500,"running_status":4,"event_name":"健康有约","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 00:25:00","duration":2100,"running_status":4,"event_name":"警法在线（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 01:00:00","duration":1500,"running_status":4,"event_name":"话里话外（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 01:25:00","duration":2100,"running_status":4,"event_name":"大城小事（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 02:00:00","duration":7200,"running_status":4,"event_name":"新闻2013（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 04:00:00","duration":1800,"running_status":4,"event_name":"纪实广播小说连播（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 04:30:00","duration":1800,"running_status":4,"event_name":"健康有约","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 05:00:00","duration":3600,"running_status":4,"event_name":"健康有约","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 06:00:00","duration":1800,"running_status":4,"event_name":"新闻晨报-新闻热线-节目预告-气象服务","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 06:30:00","duration":1800,"running_status":4,"event_name":"转播中央人民广播电台《新闻和报纸摘要》","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 07:00:00","duration":3600,"running_status":4,"event_name":"北京新闻- 新闻热线（重播）-新闻大视野","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 08:00:00","duration":3600,"running_status":4,"event_name":"资讯早8点","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 09:00:00","duration":1800,"running_status":4,"event_name":"整点快报-夹叙夹议（重播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 09:30:00","duration":1800,"running_status":4,"event_name":"议政论坛","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 10:00:00","duration":3600,"running_status":4,"event_name":"整点快报-警法在线","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":16,"start_time":"2017-06-14 11:00:00","duration":3600,"running_status":4,"event_name":"整点快报-记者视线-话里话外","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":17,"start_time":"2017-06-14 12:00:00","duration":3600,"running_status":4,"event_name":"整点快报-新闻天天谈","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":18,"start_time":"2017-06-14 13:00:00","duration":900,"running_status":4,"event_name":"整点快报","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":19,"start_time":"2017-06-14 13:15:00","duration":2700,"running_status":4,"event_name":"健康乐园","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":20,"start_time":"2017-06-14 14:00:00","duration":900,"running_status":4,"event_name":"整点快报","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":21,"start_time":"2017-06-14 14:15:00","duration":2700,"running_status":4,"event_name":"健康乐园","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":22,"start_time":"2017-06-14 15:00:00","duration":3600,"running_status":4,"event_name":"整点快报- 今日财经","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":23,"start_time":"2017-06-14 16:00:00","duration":3600,"running_status":4,"event_name":"整点快报-大城小事","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":24,"start_time":"2017-06-14 17:00:00","duration":7200,"running_status":4,"event_name":"新闻2013","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":25,"start_time":"2017-06-14 19:00:00","duration":1800,"running_status":4,"event_name":"转播中央电视台《新闻联播》","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":26,"start_time":"2017-06-14 19:30:00","duration":1800,"running_status":4,"event_name":"纪实广播小说连播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":27,"start_time":"2017-06-14 20:00:00","duration":3600,"running_status":4,"event_name":"整点快报-新闻面对面","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":28,"start_time":"2017-06-14 21:00:00","duration":3600,"running_status":4,"event_name":"整点快报-世界纵览","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":29,"start_time":"2017-06-14 22:00:00","duration":3600,"running_status":4,"event_name":"整点快报- 新闻故事","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0},{"event_id":30,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"健康有约","text":"","icon_url":"","stream_url":"","content_nibble_level_1":2,"content_nibble_level_2":0}]},{"program_number":18,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"CRI南海之音","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":19,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":1800,"running_status":4,"event_name":"People In The Know","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 00:30:00","duration":1800,"running_status":4,"event_name":"Frontline","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 01:00:00","duration":7200,"running_status":4,"event_name":"The Groove Sessions","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 03:00:00","duration":10800,"running_status":4,"event_name":"Autopilot音乐自驾游","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"Daybreak","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 07:00:00","duration":3600,"running_status":4,"event_name":"Beijing Hour 新闻纵贯线","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 08:00:00","duration":10800,"running_status":4,"event_name":"EZ Morning 飞鱼秀","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 11:00:00","duration":7200,"running_status":4,"event_name":"Third Wheel 摩天轮","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 13:00:00","duration":7200,"running_status":4,"event_name":"China Drive 轻松杂志","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 15:00:00","duration":10800,"running_status":4,"event_name":"The Pulse 心声场","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 18:00:00","duration":3600,"running_status":4,"event_name":"Feel Good Inc.","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 19:00:00","duration":3600,"running_status":4,"event_name":"Beijing Hour 新闻纵贯线","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 20:00:00","duration":7200,"running_status":4,"event_name":"EZ Café 轻松咖啡馆","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 22:00:00","duration":3600,"running_status":4,"event_name":"All That Jazz 爵士春秋","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"Daily Flashback","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":20,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"CNR中国交通广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":21,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":21600,"running_status":4,"event_name":"电台休息","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 06:00:00","duration":7200,"running_status":4,"event_name":"文艺园地","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 08:00:00","duration":1800,"running_status":4,"event_name":"西藏传统文化","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 08:30:00","duration":1800,"running_status":4,"event_name":"特别关注报道","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 09:00:00","duration":3600,"running_status":4,"event_name":"来自雪域的报道","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 10:00:00","duration":7200,"running_status":4,"event_name":"来自雪域的报道","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 12:00:00","duration":1800,"running_status":4,"event_name":"新闻和报纸摘要-藏语","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 12:30:00","duration":1800,"running_status":4,"event_name":"新闻解读-藏语","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 13:00:00","duration":3600,"running_status":4,"event_name":"故乡云 圣地西藏","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 14:00:00","duration":25200,"running_status":4,"event_name":"格桑美朵","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 21:00:00","duration":1800,"running_status":4,"event_name":"全国新闻联播-藏语","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 21:30:00","duration":1800,"running_status":4,"event_name":"新闻解读21点-藏语","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 22:00:00","duration":3600,"running_status":4,"event_name":"康巴方言全国新闻联播 新闻专题","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"西藏台康巴方言节目","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":22,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"重庆都市广播私家车938","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":28,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":3600,"running_status":4,"event_name":"老歌回忆录(梦石）（复播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 01:00:00","duration":3600,"running_status":4,"event_name":"悬疑故事","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 02:00:00","duration":3600,"running_status":4,"event_name":"畅听风云榜（复播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 03:00:00","duration":3600,"running_status":4,"event_name":"城市记忆","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 04:00:00","duration":3600,"running_status":4,"event_name":"相声小品集锦（复播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 05:00:00","duration":3600,"running_status":4,"event_name":"养生课堂","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"养生课堂","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 07:00:00","duration":1800,"running_status":4,"event_name":"笑话江湖","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":9,"start_time":"2017-06-14 07:30:00","duration":1800,"running_status":4,"event_name":"养生课堂","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":10,"start_time":"2017-06-14 08:00:00","duration":3600,"running_status":4,"event_name":"畅听风云榜（首播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":11,"start_time":"2017-06-14 09:00:00","duration":3600,"running_status":4,"event_name":"城市记忆（王鹏）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":12,"start_time":"2017-06-14 10:00:00","duration":1800,"running_status":4,"event_name":"档案揭秘","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":13,"start_time":"2017-06-14 10:30:00","duration":1800,"running_status":4,"event_name":"新闻故事","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":14,"start_time":"2017-06-14 11:00:00","duration":3600,"running_status":4,"event_name":"悬疑故事","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":15,"start_time":"2017-06-14 12:00:00","duration":3600,"running_status":4,"event_name":"拍案惊奇A","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":16,"start_time":"2017-06-14 13:00:00","duration":3600,"running_status":4,"event_name":"相声小品集锦","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":17,"start_time":"2017-06-14 14:00:00","duration":3600,"running_status":4,"event_name":"故事俱乐部（复播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":18,"start_time":"2017-06-14 15:00:00","duration":3600,"running_status":4,"event_name":"老歌回忆录（梦石）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":19,"start_time":"2017-06-14 16:00:00","duration":3600,"running_status":4,"event_name":"养生课堂","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":20,"start_time":"2017-06-14 17:00:00","duration":3600,"running_status":4,"event_name":"畅听风云榜（复播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":21,"start_time":"2017-06-14 18:00:00","duration":3600,"running_status":4,"event_name":"拍案惊奇B","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":22,"start_time":"2017-06-14 19:00:00","duration":3600,"running_status":4,"event_name":"武侠剧场（首播）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":23,"start_time":"2017-06-14 20:00:00","duration":3600,"running_status":4,"event_name":"故事俱乐部（小强）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":24,"start_time":"2017-06-14 21:00:00","duration":1800,"running_status":4,"event_name":"新书快读（聂梅）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":25,"start_time":"2017-06-14 21:30:00","duration":3600,"running_status":4,"event_name":"养生课堂","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":26,"start_time":"2017-06-14 22:30:00","duration":1800,"running_status":4,"event_name":"经典阅读（聂梅）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0},{"event_id":27,"start_time":"2017-06-14 23:00:00","duration":3600,"running_status":4,"event_name":"情感故事（范舟）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0}]},{"program_number":29,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"重庆故事广播","text":"","icon_url":"","stream_url":"","content_nibble_level_1":7,"content_nibble_level_2":0}]},{"program_number":31,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":86400,"running_status":4,"event_name":"CCTV-1（高清）","text":"","icon_url":"","stream_url":"","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":300,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"放牛班的春天","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/classic/01.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/classic/01/01.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"满城尽带黄金甲","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/classic/02.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/classic/02/02.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"冷山","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/classic/03.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/classic/03/03.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":301,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"渡梦人","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/movies/01.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/movies/01/01.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"王的男妃","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/movies/02.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/movies/02/02.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"校花2","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/movies/03.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/movies/03/03.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0}]},{"program_number":302,"version_number":0,"eit_event_arr":[{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"流浪猫鲍勃","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/cinema/01.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/cinema/01/01.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"奇异博士","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/cinema/02.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/cinema/02/02.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 00:00:00","duration":500,"running_status":4,"event_name":"神奇动物在哪里","text":"","icon_url":"http://fes.gvmedia.com.cn/resources/vod/cinema/03.jpg","stream_url":"http://fes.gvmedia.com.cn/vod/cinema/03/03.m3u8","content_nibble_level_1":15,"content_nibble_level_2":0}]}]
   */

  private int protocol_version;
  private int timestamp;
  private PatBean pat;
  private SdtBean sdt;
  private List<EitArrBean> eit_arr;

  public int getProtocol_version() {
    return protocol_version;
  }

  public void setProtocol_version(int protocol_version) {
    this.protocol_version = protocol_version;
  }

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public PatBean getPat() {
    return pat;
  }

  public void setPat(PatBean pat) {
    this.pat = pat;
  }

  public SdtBean getSdt() {
    return sdt;
  }

  public void setSdt(SdtBean sdt) {
    this.sdt = sdt;
  }

  public List<EitArrBean> getEit_arr() {
    return eit_arr;
  }

  public void setEit_arr(List<EitArrBean> eit_arr) {
    this.eit_arr = eit_arr;
  }

  @Override
  public String toString() {
    return "EsgResponse{" +
            "protocol_version=" + protocol_version +
            ", timestamp=" + timestamp +
            ", pat=" + pat +
            ", sdt=" + sdt +
            ", eit_arr=" + eit_arr +
            '}';
  }

  public static class PatBean {
    /**
     * version_number : 0
     * pat_arr : [{"program_number":1,"stream_pid":3004,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3004/3004.m3u8","stream_type":3,"inflate_mode":1},{"program_number":2,"stream_pid":3005,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3005/3005.m3u8","stream_type":3,"inflate_mode":1},{"program_number":3,"stream_pid":3006,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3006/3006.m3u8","stream_type":3,"inflate_mode":1},{"program_number":5,"stream_pid":3008,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3008/3008.m3u8","stream_type":3,"inflate_mode":1},{"program_number":6,"stream_pid":3009,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3009/3009.m3u8","stream_type":3,"inflate_mode":1},{"program_number":7,"stream_pid":3010,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3010/3010.m3u8","stream_type":3,"inflate_mode":1},{"program_number":8,"stream_pid":3011,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3011/3011.m3u8","stream_type":3,"inflate_mode":1},{"program_number":9,"stream_pid":3012,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3012/3012.m3u8","stream_type":3,"inflate_mode":1},{"program_number":11,"stream_pid":3014,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3014/3014.m3u8","stream_type":3,"inflate_mode":1},{"program_number":12,"stream_pid":3015,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3015/3015.m3u8","stream_type":3,"inflate_mode":1},{"program_number":13,"stream_pid":3016,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3016/3016.m3u8","stream_type":3,"inflate_mode":1},{"program_number":14,"stream_pid":3017,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3017/3017.m3u8","stream_type":3,"inflate_mode":1},{"program_number":15,"stream_pid":3018,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3018/3018.m3u8","stream_type":3,"inflate_mode":1},{"program_number":16,"stream_pid":3019,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3019/3019.m3u8","stream_type":3,"inflate_mode":1},{"program_number":17,"stream_pid":3020,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3020/3020.m3u8","stream_type":3,"inflate_mode":1},{"program_number":18,"stream_pid":3021,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3021/3021.m3u8","stream_type":3,"inflate_mode":1},{"program_number":19,"stream_pid":3022,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3022/3022.m3u8","stream_type":3,"inflate_mode":1},{"program_number":20,"stream_pid":3023,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3023/3023.m3u8","stream_type":3,"inflate_mode":1},{"program_number":21,"stream_pid":3024,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3024/3024.m3u8","stream_type":3,"inflate_mode":1},{"program_number":22,"stream_pid":3025,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3025/3025.m3u8","stream_type":3,"inflate_mode":1},{"program_number":28,"stream_pid":3031,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3031/3031.m3u8","stream_type":3,"inflate_mode":1},{"program_number":29,"stream_pid":3032,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3032/3032.m3u8","stream_type":3,"inflate_mode":1},{"program_number":31,"stream_pid":3034,"stream_url":"http://fes.gvmedia.com.cn:80/hls/3034/3034.m3u8","stream_type":1,"inflate_mode":1},{"program_number":300,"stream_pid":5000,"stream_url":"","stream_type":6,"inflate_mode":1},{"program_number":301,"stream_pid":5001,"stream_url":"","stream_type":6,"inflate_mode":1},{"program_number":302,"stream_pid":5002,"stream_url":"","stream_type":6,"inflate_mode":1}]
     */

    private int version_number;
    private List<PatArrBean> pat_arr;

    public int getVersion_number() {
      return version_number;
    }

    public void setVersion_number(int version_number) {
      this.version_number = version_number;
    }

    public List<PatArrBean> getPat_arr() {
      return pat_arr;
    }

    public void setPat_arr(List<PatArrBean> pat_arr) {
      this.pat_arr = pat_arr;
    }

    @Override
    public String toString() {
      return "PatBean{" +
              "version_number=" + version_number +
              ", pat_arr=" + pat_arr +
              '}';
    }

    public static class PatArrBean {
      /**
       * program_number : 1
       * stream_pid : 3004
       * stream_url : http://fes.gvmedia.com.cn:80/hls/3004/3004.m3u8
       * stream_type : 3
       * inflate_mode : 1
       */

      private int program_number;
      private int stream_pid;
      private String stream_url;
      private int stream_type;
      private int inflate_mode;

      public int getProgram_number() {
        return program_number;
      }

      public void setProgram_number(int program_number) {
        this.program_number = program_number;
      }

      public int getStream_pid() {
        return stream_pid;
      }

      public void setStream_pid(int stream_pid) {
        this.stream_pid = stream_pid;
      }

      public String getStream_url() {
        return stream_url;
      }

      public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
      }

      public int getStream_type() {
        return stream_type;
      }

      public void setStream_type(int stream_type) {
        this.stream_type = stream_type;
      }

      public int getInflate_mode() {
        return inflate_mode;
      }

      public void setInflate_mode(int inflate_mode) {
        this.inflate_mode = inflate_mode;
      }

      @Override
      public String toString() {
        return "PatArrBean{" +
                "program_number=" + program_number +
                ", stream_pid=" + stream_pid +
                ", stream_url='" + stream_url + '\'' +
                ", stream_type=" + stream_type +
                ", inflate_mode=" + inflate_mode +
                '}';
      }
    }
  }


  public static class SdtBean {
    /**
     * version_number : 0
     * sdt_arr : [{"program_number":1,"service_provider_name":"中国国际广播电台","service_name":"CRI劲曲调频","icon_url":"http://fes.gvmedia.com.cn:80/resources/3004.png","free_ca_mode":0},{"program_number":2,"service_provider_name":"中央人民广播电台","service_name":"CNR音乐之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3005.png","free_ca_mode":0},{"program_number":3,"service_provider_name":"中央人民广播电台","service_name":"CNR文艺之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3006.png","free_ca_mode":0},{"program_number":5,"service_provider_name":"重庆市广播电台","service_name":"重庆音乐广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3008.jpg","free_ca_mode":0},{"program_number":6,"service_provider_name":"湖北省人民广播电台","service_name":"湖北经典音乐广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3009.png","free_ca_mode":0},{"program_number":7,"service_provider_name":"湖北省人民广播电台","service_name":"楚天音乐台","icon_url":"http://fes.gvmedia.com.cn:80/resources/3010.jpg","free_ca_mode":0},{"program_number":8,"service_provider_name":"江苏省人民广播电台","service_name":"江苏文艺广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3011.png","free_ca_mode":0},{"program_number":9,"service_provider_name":"四川省人民广播电台","service_name":"四川文艺广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3012.jpg","free_ca_mode":0},{"program_number":11,"service_provider_name":"中国国际广播电台","service_name":"CRI环球资讯","icon_url":"http://fes.gvmedia.com.cn:80/resources/3014.jpg","free_ca_mode":0},{"program_number":12,"service_provider_name":"中央人民广播电台","service_name":"CNR中国之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3015.png","free_ca_mode":0},{"program_number":13,"service_provider_name":"中央人民广播电台","service_name":"CNR经济之声","icon_url":"http://fes.gvmedia.com.cn:80/resources/3016.png","free_ca_mode":0},{"program_number":14,"service_provider_name":"四川省人民广播电台","service_name":"四川新闻频率","icon_url":"http://fes.gvmedia.com.cn:80/resources/3017.png","free_ca_mode":0},{"program_number":15,"service_provider_name":"重庆人民广播电台","service_name":"重庆交通广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3018.jpg","free_ca_mode":0},{"program_number":16,"service_provider_name":"湖北省人民广播电台","service_name":"湖北新闻广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3019.png","free_ca_mode":0},{"program_number":17,"service_provider_name":"北京人民广播电台","service_name":"北京新闻广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3020.png","free_ca_mode":0},{"program_number":18,"service_provider_name":"中国国际广播电台","service_name":"CRI南海之音","icon_url":"http://fes.gvmedia.com.cn:80/resources/3021.jpg","free_ca_mode":0},{"program_number":19,"service_provider_name":"中国国际广播电台","service_name":"CRI轻松调频","icon_url":"http://fes.gvmedia.com.cn:80/resources/3022.png","free_ca_mode":0},{"program_number":20,"service_provider_name":"中央人民广播电台","service_name":"CNR中国交通广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3023.png","free_ca_mode":0},{"program_number":21,"service_provider_name":"中央人民广播电台","service_name":"CNR藏语广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3024.png","free_ca_mode":0},{"program_number":22,"service_provider_name":"重庆市广播电台","service_name":"重庆都市广播私家车938","icon_url":"http://fes.gvmedia.com.cn:80/resources/3025.png","free_ca_mode":0},{"program_number":28,"service_provider_name":"江苏省人民广播电台","service_name":"江苏故事广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3031.png","free_ca_mode":1},{"program_number":29,"service_provider_name":"重庆人民广播电台","service_name":"重庆故事广播","icon_url":"http://fes.gvmedia.com.cn:80/resources/3032.jpg","free_ca_mode":1},{"program_number":31,"service_provider_name":"中央电视台","service_name":"CCTV-1(高清)","icon_url":"http://fes.gvmedia.com.cn:80/resources/3034.png","free_ca_mode":0},{"program_number":300,"service_provider_name":"","service_name":"怀旧经典","icon_url":"http://fes.gvmedia.com.cn:80/resources/cinema_icon_2.png","free_ca_mode":0},{"program_number":301,"service_provider_name":"","service_name":"微电影","icon_url":"http://fes.gvmedia.com.cn:80/resources/cinema_icon_3.png","free_ca_mode":0},{"program_number":302,"service_provider_name":"","service_name":"院线同步","icon_url":"http://fes.gvmedia.com.cn:80/resources/cinema_icon_3.png","free_ca_mode":0}]
     */

    private int version_number;
    private List<SdtArrBean> sdt_arr;

    public int getVersion_number() {
      return version_number;
    }

    public void setVersion_number(int version_number) {
      this.version_number = version_number;
    }

    public List<SdtArrBean> getSdt_arr() {
      return sdt_arr;
    }

    public void setSdt_arr(List<SdtArrBean> sdt_arr) {
      this.sdt_arr = sdt_arr;
    }

    @Override
    public String toString() {
      return "SdtBean{" +
              "version_number=" + version_number +
              ", sdt_arr=" + sdt_arr +
              '}';
    }

    public static class SdtArrBean {
      /**
       * program_number : 1
       * service_provider_name : 中国国际广播电台
       * service_name : CRI劲曲调频
       * icon_url : http://fes.gvmedia.com.cn:80/resources/3004.png
       * free_ca_mode : 0
       */

      private int program_number;
      private String service_provider_name;
      private String service_name;
      private String icon_url;
      private int free_ca_mode;

      public int getProgram_number() {
        return program_number;
      }

      public void setProgram_number(int program_number) {
        this.program_number = program_number;
      }

      public String getService_provider_name() {
        return service_provider_name;
      }

      public void setService_provider_name(String service_provider_name) {
        this.service_provider_name = service_provider_name;
      }

      public String getService_name() {
        return service_name;
      }

      public void setService_name(String service_name) {
        this.service_name = service_name;
      }

      public String getIcon_url() {
        return icon_url;
      }

      public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
      }

      public int getFree_ca_mode() {
        return free_ca_mode;
      }

      public void setFree_ca_mode(int free_ca_mode) {
        this.free_ca_mode = free_ca_mode;
      }

      @Override
      public String toString() {
        return "SdtArrBean{" +
                "program_number=" + program_number +
                ", service_provider_name='" + service_provider_name + '\'' +
                ", service_name='" + service_name + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", free_ca_mode=" + free_ca_mode +
                '}';
      }
    }
  }

  public static class EitArrBean {
    /**
     * program_number : 1
     * version_number : 0
     * eit_event_arr : [{"event_id":1,"start_time":"2017-06-14 00:00:00","duration":21600,"running_status":4,"event_name":"The Night Mix","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":2,"start_time":"2017-06-14 06:00:00","duration":3600,"running_status":4,"event_name":"Morning Call","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":3,"start_time":"2017-06-14 07:00:00","duration":10800,"running_status":4,"event_name":"Hit Morning Show","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":4,"start_time":"2017-06-14 10:00:00","duration":10800,"running_status":4,"event_name":"At Work Network","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":5,"start_time":"2017-06-14 13:00:00","duration":10800,"running_status":4,"event_name":"Lazy Afternoon","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":6,"start_time":"2017-06-14 16:00:00","duration":10800,"running_status":4,"event_name":"Big Drive Home","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":7,"start_time":"2017-06-14 19:00:00","duration":10800,"running_status":4,"event_name":"New Music Express","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0},{"event_id":8,"start_time":"2017-06-14 22:00:00","duration":7200,"running_status":0,"event_name":"Night Vibe","text":"","icon_url":"","stream_url":"","content_nibble_level_1":6,"content_nibble_level_2":0}]
     */

    private int program_number;
    private int version_number;
    private List<EitEventArrBean> eit_event_arr;

    public int getProgram_number() {
      return program_number;
    }

    public void setProgram_number(int program_number) {
      this.program_number = program_number;
    }

    public int getVersion_number() {
      return version_number;
    }

    public void setVersion_number(int version_number) {
      this.version_number = version_number;
    }

    public List<EitEventArrBean> getEit_event_arr() {
      return eit_event_arr;
    }

    public void setEit_event_arr(List<EitEventArrBean> eit_event_arr) {
      this.eit_event_arr = eit_event_arr;
    }

    @Override
    public String toString() {
      return "EitArrBean{" +
              "program_number=" + program_number +
              ", version_number=" + version_number +
              ", eit_event_arr=" + eit_event_arr +
              '}';
    }

    public static class EitEventArrBean {
      /**
       * event_id : 1
       * start_time : 2017-06-14 00:00:00
       * duration : 21600
       * running_status : 4
       * event_name : The Night Mix
       * text :
       * icon_url :
       * stream_url :
       * content_nibble_level_1 : 6
       * content_nibble_level_2 : 0
       */

      private int event_id;
      private String start_time;
      private int duration;
      private int running_status;
      private String event_name;
      private String text;
      private String icon_url;
      private String stream_url;
      private int content_nibble_level_1;
      private int content_nibble_level_2;

      public int getEvent_id() {
        return event_id;
      }

      public void setEvent_id(int event_id) {
        this.event_id = event_id;
      }

      public String getStart_time() {
        return start_time;
      }

      public void setStart_time(String start_time) {
        this.start_time = start_time;
      }

      public int getDuration() {
        return duration;
      }

      public void setDuration(int duration) {
        this.duration = duration;
      }

      public int getRunning_status() {
        return running_status;
      }

      public void setRunning_status(int running_status) {
        this.running_status = running_status;
      }

      public String getEvent_name() {
        return event_name;
      }

      public void setEvent_name(String event_name) {
        this.event_name = event_name;
      }

      public String getText() {
        return text;
      }

      public void setText(String text) {
        this.text = text;
      }

      public String getIcon_url() {
        return icon_url;
      }

      public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
      }

      public String getStream_url() {
        return stream_url;
      }

      public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
      }

      public int getContent_nibble_level_1() {
        return content_nibble_level_1;
      }

      public void setContent_nibble_level_1(int content_nibble_level_1) {
        this.content_nibble_level_1 = content_nibble_level_1;
      }

      public int getContent_nibble_level_2() {
        return content_nibble_level_2;
      }

      public void setContent_nibble_level_2(int content_nibble_level_2) {
        this.content_nibble_level_2 = content_nibble_level_2;
      }

      @Override
      public String toString() {
        return "EitEventArrBean{" +
                "event_id=" + event_id +
                ", start_time='" + start_time + '\'' +
                ", duration=" + duration +
                ", running_status=" + running_status +
                ", event_name='" + event_name + '\'' +
                ", text='" + text + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", stream_url='" + stream_url + '\'' +
                ", content_nibble_level_1=" + content_nibble_level_1 +
                ", content_nibble_level_2=" + content_nibble_level_2 +
                '}';
      }
    }
  }
}
