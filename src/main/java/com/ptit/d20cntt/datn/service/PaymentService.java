package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.dto.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

	public VNPayResponse createVnPayPayment(HttpServletRequest request, int orderTotal, String orderInfo, String vnPayReturnUrl);

}
