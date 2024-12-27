package com.ptit.d20cntt.datn.service;


import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// excel export
public interface EmpExcelService {

    void exportToExcel(HttpServletResponse response) throws IOException;
}
