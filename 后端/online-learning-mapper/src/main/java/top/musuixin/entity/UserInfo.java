package top.musuixin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author jobob
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId(value = "user_id")
	private Integer userId;

	/**
	 * 用户的创建时间
	 */
	private LocalDateTime creatTime;

	/**
	 * 用户生日
	 */
	private LocalDate birthday;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 用户头像
	 */
	private String portrait;

}
