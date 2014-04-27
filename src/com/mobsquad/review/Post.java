package com.mobsquad.review;

/**
 * Created by Student on 4/22/14.
 */
public class Post {

    private String description;
    private int iconId;

    Post(String description, int iconId){

        this.description = description;
        this.iconId = iconId;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
