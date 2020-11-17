
package com.fertifa.app.stomp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseResponse<T> {
    @CreationTimestamp
    private Date date;
    private HttpStatus status;
    private int code;
    private T data;
}
