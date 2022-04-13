package com.harsh.authentication;

public class Model {
    private byte[] avatar;
    private String title;
    private String description;

    public Model(int id,byte[] avatar,String title,String description){
        this.id = id;
        this.avatar = avatar;
        this.title = title;
        this.description = description;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
