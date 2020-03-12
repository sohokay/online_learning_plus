package top.musuixin.onlinelearningwebstudent.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.musuixin.commons.util.UploadPicture;

/**
 * @author musuixin
 * @date 2020-03-03 18:14
 */
@RestController
@CrossOrigin
@RequestMapping("/hi")
public class Hello {
    @PostMapping("/hi")
    public String Hi(MultipartFile file) {
        System.err.println(file);
        String up = UploadPicture.up(file);
        return "hihihihihi";
    }
}
