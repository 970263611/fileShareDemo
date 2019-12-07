package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.File;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User check(String name) {
        return dao.check(name);
    }

    @Override
    public boolean save(User user) {
        int a = dao.save(user);
        if (a == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User checkpass(User user) {
        return dao.checkpass(user);
    }

    @Override
    public List<File> getFiles() {
        return dao.getFiles();
    }

    @Override
    public void setFiles(File file) {
        dao.setFiles(file);
    }

    @Override
    public List<File> getMyFiles(int id) {
        return dao.getMyFiles(id);
    }

    @Override
    public List<User> inline() {
        return dao.inline();
    }

    @Override
    public void layout(int id,int state) {
        dao.layout(id,state);
    }

    @Override
    public void delFile(String id) {
        dao.delFile(id);
    }

    @Override
    public File saveFile(String id) {
        return dao.saveFile(id);
    }
}
