package com.getgoods.inventoryservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail
{
    String message;
    String desc;
    Date timeStamp;
}
