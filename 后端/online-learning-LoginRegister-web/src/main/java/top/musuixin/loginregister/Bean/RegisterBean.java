package top.musuixin.loginregister.Bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.musuixin.entity.Users;

/**
 * @author musuixin
 * @date 2020-03-27 09:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterBean extends Users {
    private String verification;
    private String rePassword;

}
