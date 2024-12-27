package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.DeGiay;
import com.ptit.d20cntt.datn.request.DeGiayRequest;

import java.util.List;

public interface DeGiayService {
    List<DeGiay> getAllDeGiay();

    DeGiay add(DeGiayRequest deGiayRequest);

    String delete(Long id);

    DeGiay getOne(Long id);

    DeGiay findById(Long id);

    DeGiay update(DeGiay deGiay);
}
