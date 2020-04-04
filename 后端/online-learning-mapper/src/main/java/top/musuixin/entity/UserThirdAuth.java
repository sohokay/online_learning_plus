package top.musuixin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 第三方认证表
 * </p>
 *
 * @author jobob
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserThirdAuth implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 第三方认证id
	 */
	@TableId(value = "auth_id", type = IdType.AUTO)
	private Integer authId;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 第三方认证id
	 */
	private Integer thirdId;

	/**
	 * 第三方认证类型
	 */
	private String thirdType;

}
