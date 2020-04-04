package top.musuixin.loginregister.Bean;

import lombok.Data;

/**
 * @author musuixin
 * @date 2020-04-01 22:22
 */
@Data
public class SendStatusSet {
    private String SerialNo;
    private String Message;
    private String Fee;
    private String PhoneNumber;
    private String SessionContext;
    private String Code;
}
