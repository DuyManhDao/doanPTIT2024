package com.ptit.d20cntt.datn.dto;

import com.ptit.d20cntt.datn.entity.GioHangChiTiet;

import java.util.List;

//@Data
//giohangcontroller
public class GioHangWrapper {
    //đóng gói hoặc tổng hợp các đối tượng
    private List<GioHangChiTiet> listGioHangChiTiet;

    public List<GioHangChiTiet> getListGioHangChiTiet() {
        return listGioHangChiTiet;
    }

    public void setListGioHangChiTiet(List<GioHangChiTiet> listGioHangChiTiet) {
        this.listGioHangChiTiet = listGioHangChiTiet;
    }
}
