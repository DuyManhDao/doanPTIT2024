package com.ptit.d20cntt.datn.controller;

import com.ptit.d20cntt.datn.dto.GioHangWrapper;
import com.ptit.d20cntt.datn.dto.OrderDataDTO;
import com.ptit.d20cntt.datn.entity.HoaDon;
import com.ptit.d20cntt.datn.service.BanHangService;
import com.ptit.d20cntt.datn.service.BanHangOnlineCtService;
import com.ptit.d20cntt.datn.service.impl.PaymentServiceImpl;
import com.ptit.d20cntt.datn.worker.SpingSecurity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class VNpayController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private BanHangOnlineCtService banHangCustomerService;

    private SpingSecurity spingsecurity = new SpingSecurity();

    @Autowired
    private BanHangService banHangService;



//     sau khi thanh toán thành công
    @RequestMapping(value = "/vnpay-payment-return-client", method = RequestMethod.GET)
    public String paymentCompleted(HttpServletRequest request, Model model){
        int paymentStatus =paymentService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        Long idKhachHang = spingsecurity.getCurrentUserId();

//        thanh toán thành công , ghi vô db
        if(paymentStatus == 1) {

            OrderDataDTO orderData =  banHangCustomerService.getOrderData();
            GioHangWrapper gioHangWrapper = banHangCustomerService.getGioHangWrapper();

            String diaChiCuThe = orderData.getDiaChi() + "," + orderData.getXaPhuong() + "," + orderData.getQuanHuyen() + "," + orderData.getThanhPho();

            banHangCustomerService.datHangItems(gioHangWrapper, idKhachHang, orderData.getTen(),  diaChiCuThe, orderData.getSdt(), orderData.getGhiChu(), orderData.getShippingFee(),  BigDecimal.valueOf(Double.valueOf(orderData.getTongTienHang())), orderData.getTotalAmount(), orderData.getTienGiamGia() , orderData.getSelectedVoucherId(), orderData.getDiemTichLuy(), orderData.getUseAllPointsHidden());
        }

        return paymentStatus == 1 ? "customer-template/orderSuccess" : "templates/customer-template/orderFail.html";
    }

    @RequestMapping(value = "/vnpay-payment-return-employee", method = RequestMethod.GET)
    public String paymentCompletedEmployee(HttpServletRequest request, Model model){
        int paymentStatus =paymentService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);


        System.out.println("In employee controller return payement ");

        HttpSession session = request.getSession();

        // Retrieve the values from the session
        String idHoaDon = (String) session.getAttribute("idHoaDon");
        String tongTien = (String) session.getAttribute("tongTien");
        String thanhTien = (String) session.getAttribute("thanhTien");
        Boolean xuTichDiem = (Boolean) session.getAttribute("xuTichDiem");

        System.out.println("value all " + idHoaDon +  " " + tongTien  + " "   + thanhTien + " " + xuTichDiem );


        System.out.println("Paymenet paymentStatus " + paymentStatus);

        if(paymentStatus == 1) {

            HoaDon hoaDon = banHangService.getOneById(Long.valueOf(idHoaDon));
            BigDecimal tienGiamGia = banHangService.voucher(Long.valueOf(idHoaDon), BigDecimal.valueOf(Double.valueOf(tongTien)));
            banHangService.checkXuHoaDon(Long.valueOf(idHoaDon), tongTien, thanhTien, xuTichDiem);
            banHangService.thanhToanHoaDon(Long.valueOf(idHoaDon), tienGiamGia);
            banHangService.updateGiamGia(Long.valueOf(idHoaDon));
            banHangService.tichDiem(hoaDon.getKhachHang().getId(), thanhTien);

        }


        return paymentStatus == 1 ? "customer-template/orderSuccess" : "templates/customer-template/orderFail.html";
    }

}
