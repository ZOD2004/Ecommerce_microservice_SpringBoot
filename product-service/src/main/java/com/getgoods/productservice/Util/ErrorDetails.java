package com.getgoods.productservice.Util;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDetails {
    String message;
    String desc;
    Date timestamp;
}
