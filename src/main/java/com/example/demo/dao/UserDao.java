package com.example.demo.dao;

import com.example.demo.model.File;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from test_user where name = #{name}")
    User check(@Param("name") String name);

    @Insert("insert into test_user (name,password,role,inline) values (#{user.name},#{user.password},1,1)")
    int save(@Param("user")User user);

    @Select("select * from test_user where name = #{user.name} and password = #{user.password}")
    User checkpass(@Param("user")User user);

    @Select("select a.*,b.name username from test_file a left join test_user b on a.user = b.id")
    List<File> getFiles();

    @Insert("insert into test_file values (#{file.id},#{file.name},#{file.url},#{file.user},#{file.time},#{file.ip},#{file.size})")
    void setFiles(@Param("file")File file);

    @Select("select a.*,b.name username from test_file a left join test_user b on a.user = b.id where a.user = #{id}")
    List<File> getMyFiles(@Param("id")int id);

    @Select("select * from test_user")
    List<User> inline();

    @Update("update test_user set inline = #{state} where id = #{id}")
    void layout(@Param("id") int id,@Param("state") int state);

    @Delete("delete from test_file where id = #{id}")
    void delFile(@Param("id")String id);

    @Select("select * from test_file where id = #{id}")
    File saveFile(@Param("id")String id);
}
