package com.example.demo.controller;

import com.example.demo.model.File;
import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    private final String path = "C:\\files\\";

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index1() {
        return "login";
    }

    /**
     * 进入index页面
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //权限校验
        if(user == null){
            return "login";
        }
        return "index";
    }

    //进入login页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //进入注册页面
    @RequestMapping("/regist")
    public String regist() {
        return "regist";
    }

    //注册功能，先判断用户名是否存在
    @PostMapping("/check")
    @ResponseBody
    public Result check(User user) {
        User u = userService.check(user.getName());
        if (u == null) {
            if (userService.save(user)) {
                return Result.success("注册成功", null);
            } else {
                return Result.error("注册失败", null);
            }
        } else {
            return Result.error("用户名重复", null);
        }
    }

    //从session中获取用户信息
    @PostMapping("/getUser")
    @ResponseBody
    public Result getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        return Result.success("", u);
    }

    //跟新用户在线状态信息
    @PostMapping("/inline")
    @ResponseBody
    public Result inline() {
        return Result.success("", userService.inline());
    }

    //用户登录功能，登陆后保存信息到session
    @PostMapping("/loginpost")
    @ResponseBody
    public Result login(User user, HttpServletRequest request) {
        User u = userService.checkpass(user);
        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
            userService.layout(u.getId(),1);
            return Result.success("登陆成功", null);
        } else {
            return Result.error("用户名或密码错误", null);
        }
    }

    //获取文件列表信息
    @PostMapping("/getFiles")
    @ResponseBody
    public Result getFiles(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        return Result.success(u.getId() + ":" + u.getRole(), userService.getFiles());
    }

    //删除文件
    @PostMapping("/delFile")
    @ResponseBody
    public Result delFile(String id) {
        userService.delFile(id);
        return Result.success("删除成功", null);
    }

    //保存文件
    @RequestMapping("/saveFile")
    public void saveFile(String id, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = userService.saveFile(id);
        java.io.File file1 = new java.io.File(file.getUrl());
        response.setContentType("application/multipart/form-data");
        response.setContentType("application/x-download;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(file1.getName().getBytes("utf-8"),"iso8859-1"));
        try{
            /* 用流将数据写给前端 */
            OutputStream os = response.getOutputStream();
            os.write(Utils.File2byte(file1));
            os.flush();
            os.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //登出
    @PostMapping("/layout")
    @ResponseBody
    public Result layout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        session.invalidate();
        userService.layout(u.getId(),0);
        return Result.success("退出成功", "");
    }

    //获取用户上传的所有文件
    @PostMapping("/getMyFiles")
    @ResponseBody
    public Result getMyFiles(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        return Result.success(u.getId() + ":" + u.getRole(), userService.getMyFiles(u.getId()));
    }

    //用户上传文件功能
    @PostMapping("/setFile")
    @ResponseBody
    public Result setFile(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        File file = new File();
        for(Map.Entry entry:fileMap.entrySet()){
            MultipartFile mFile = (MultipartFile) entry.getValue();
            file.setId(UUID.randomUUID().toString());
            file.setName(mFile.getOriginalFilename());
            file.setUser(u.getId());
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            file.setTime(dateFormat.format(date));
            file.setSize(mFile.getSize());
            file.setIp(Utils.getIP(request));
            java.io.File file1 = new java.io.File(path + mFile.getOriginalFilename());
            if (!file1.exists()) {//判断文件目录的存在
                file1.mkdirs();
            }
            mFile.transferTo(file1);
            file.setUrl(path + mFile.getOriginalFilename());
        }
        try{
            userService.setFiles(file);
        }catch(Exception e){
            return Result.error("上传失败", null);
        }
        return Result.success("上传成功", null);
    }


}
