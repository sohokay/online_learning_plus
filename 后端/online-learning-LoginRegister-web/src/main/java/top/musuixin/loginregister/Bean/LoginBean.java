package top.musuixin.loginregister.Bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.musuixin.entity.Users;

/**
 * @author musuixin
 * @date 2020-03-26 20:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginBean extends Users {
    private String verification;


}
