package com.example.demo.service;

import com.example.demo.model.File;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    User check(String name);

    boolean save(User user);

    User checkpass(User user);

    List<File> getFiles();

    void setFiles(File file);

    List<File> getMyFiles(int id);

    List<User> inline();

    void layout(int id,int state);

    void delFile(String id);

    File saveFile(String id);
}
