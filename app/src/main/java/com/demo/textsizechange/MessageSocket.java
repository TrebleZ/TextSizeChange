package com.demo.textsizechange;

import java.io.Serializable;

/**
 * Created by shaxiao on 16/9/20.
 */
public class MessageSocket implements Serializable{
    public MessageSocket(int id, String content1, String content2, Object object) {
        this.id = id;
        this.content1 = content1;
        this.content2 = content2;
        this.object = object;
    }

    public MessageSocket(int id) {
       this(id,"","",null);
    }

    public MessageSocket(int id, String content1) {
       this(id,content1,"",null);
    }
    public MessageSocket(int id, Object object) {
       this(id,"","",object);
    }

    public MessageSocket(int id, String content1, String content2) {
       this(id,content1,content2,null);
    }
    public MessageSocket(int id, String content1, Object object) {
       this(id,content1,"",object);
    }

    public int id;
    public String content1;
    public String content2;
    public Object object;
}
