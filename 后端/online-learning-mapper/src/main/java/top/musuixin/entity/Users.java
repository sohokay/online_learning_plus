package top.musuixin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author musuixin
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户自增id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */

    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户身份“学生”、"管理员'、”教师“、'root'
     */
    @JsonIgnore
    private String userRole;

    /**
     * 用户上次登录的地理位置
     */
    private String loginAddress;

}
