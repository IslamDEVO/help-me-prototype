package com.example.root.helpme;

/**
 * Created by root on 11/6/18.
 */

public class LoginHelper {
    public boolean check_id(String id){
        if(id.equals("123456") || id.equals("122333")){
            return true;
        }else
            return false;
    }

    public boolean have_password (String id) {
        if(id.equals("123456")){
            return true;
        }else
            return false;
    }
}
