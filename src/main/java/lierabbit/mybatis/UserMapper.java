package lierabbit.mybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//这是一个MyBatis的数据库操作接口
public interface UserMapper
{
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM user WHERE name LIKE #{name}")
    List<User> findByNameLike(@Param("name") String name);

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE user SET age = #{age} WHERE name = #{name}")
    int update(@Param("name") String name, @Param("age") Integer age);

    @Delete("DELETE FROM user WHERE name = #{name}")
    int delete(@Param("name") String name);

    @Select("SELECT COUNT(*) FROM user")
    int countAll();
}
