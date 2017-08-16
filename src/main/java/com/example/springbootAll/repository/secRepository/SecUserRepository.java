package com.example.springbootAll.repository.secRepository;

import com.example.springbootAll.entity.secEntity.SecUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:29 2017/8/8
 */
@Repository
//UserRepository 继承JpaRepository 具备了一些jpa自带的增删改查方法
public interface SecUserRepository extends JpaRepository<SecUser, Long> {

    //自定义根据用户名的简单查询
    SecUser findByUserName(String userName);

    //在根据根据用户名的简单查询的基础上增加关键字Or
    SecUser findByUserNameOrEmail(String username, String email);

    //使用Like关键字---模糊查询
    List<SecUser> findByEmailLike(String email);

    //使用IgnoreCase关键字 ------根据当前字符集映射的所有字符更改为大写
    SecUser findByUserNameIgnoreCase(String userName);

    //OrderBy关键字----排序
    List<SecUser> findByUserNameOrderByEmailDesc(String email);

    Page<SecUser> findByUserName(String userName, Pageable pageable);


    //限制查询(有时候只需要查询前N个元素，或者支取前一个实体)

    //获得符合查询条件的前1条数据
    SecUser findFirstByOrderByRegTimeAsc();

    SecUser findTopByOrderByRegTimeDesc();

    Page<SecUser> queryFirst10ByUserName(String userName, Pageable pageable);

    //获得符合查询条件的前10条数据
    List<SecUser> findFirst10ByUserName(String userName, Sort sort);

    List<SecUser> findTop10ByUserName(String userName, Pageable pageable);

/*

    //自定义SQL查询  在SQL的查询方法上面使用@Query注解，如涉及到删除和修改在需要加上@Modifying，这样框架最终会生成一个更新的操作，而非查询
    @Modifying
    @Query("update User u set u.userName = ?1 where c.id = ?2")
    int modifyByIdAndUserId(String  userName, Long id);

    //可以根据需要添加 @Transactional 对事物的支持，查询超时的设置
    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteByUserId(Long id);

    @Transactional(timeout = 10)
    @Query("select u from User u where u.emailAddress = ?1")
    User findByEmailAddress(String emailAddress);
*/


}
