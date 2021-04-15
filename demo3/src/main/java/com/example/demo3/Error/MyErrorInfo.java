package com.example.demo3.Error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 *  <ul>
 *  <li>timestamp - The time that the errors were extracted</li>
 *  <li>status - The status code</li>
 *  <li>error - The status error reason</li>
 *  <li>exception - The class name of the root exception (if configured)</li>
 *  <li>path - The URL path when the exception was raised</li>
 *
 *  <li>code - 用户错误码</li>
 *  <li>message - 用户错误描述The exception message</li>
 *  </ul>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyErrorInfo {
    private Integer status;// HttpStatus or 0
    private Integer code; // 自定义code

    private Date timestamp; // Final
    private String error; // Final
    private String path; // Final
    private String exception; // Final , maybe
    private Object message; // Exception Message, 开发模式下

    public MyErrorInfo() {
        timestamp = new Date();
    }

    public void setStatus(int status) {
        HttpStatus s = HttpStatus.valueOf(status);
        this.error = s.getReasonPhrase();
        this.status = s.value();
    }
}
