package com.ptit.d20cntt.datn.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;
@Data
@Builder
public class VNPayResponse {

    public String code;
    public String message;
    public String paymentUrl;

}
