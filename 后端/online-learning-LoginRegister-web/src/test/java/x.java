import cn.hutool.http.HttpRequest;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author musuixin
 * @date 2020-04-03 18:36
 */
public class x {
    @Test
    public void x() {
        System.err.println(HttpRequest.get("https://api.github.com/user").header("Accept", "application/json").header("Authorization", "token " + " 51530705a2a854d2d1ed165cdbfc67375fedb8f4").setReadTimeout(3000000).setConnectionTimeout(3000000).execute().body());
    }

}
