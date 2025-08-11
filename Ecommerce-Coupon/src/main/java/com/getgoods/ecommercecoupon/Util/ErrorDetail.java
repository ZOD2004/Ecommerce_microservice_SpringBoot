package com.getgoods.ecommercecoupon.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String message;
    private String desc;
    private Date timeStamp;
}
