package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class CalculatorBorrowModel implements Serializable {
    private String kiHanThanhToan;
    private String duNoDauKi;
    private String laiThanhToanHangThang;
    private String gocThanhToanHangThang;
    private String duNoCuoiKi;
    private String khoanTienTraHangThang;

    public CalculatorBorrowModel(String kiHanThanhToan, String duNoDauKi, String
            laiThanhToanHangThang, String gocThanhToanHangThang, String duNoCuoiKi, String
            khoanTienTraHangThang) {
        this.kiHanThanhToan = kiHanThanhToan;
        this.duNoDauKi = duNoDauKi;
        this.laiThanhToanHangThang = laiThanhToanHangThang;
        this.gocThanhToanHangThang = gocThanhToanHangThang;
        this.duNoCuoiKi = duNoCuoiKi;
        this.khoanTienTraHangThang = khoanTienTraHangThang;
    }

    public String getKiHanThanhToan() {
        return kiHanThanhToan;
    }

    public void setKiHanThanhToan(String kiHanThanhToan) {
        this.kiHanThanhToan = kiHanThanhToan;
    }

    public String getDuNoDauKi() {
        return duNoDauKi;
    }

    public void setDuNoDauKi(String duNoDauKi) {
        this.duNoDauKi = duNoDauKi;
    }

    public String getLaiThanhToanHangThang() {
        return laiThanhToanHangThang;
    }

    public void setLaiThanhToanHangThang(String laiThanhToanHangThang) {
        this.laiThanhToanHangThang = laiThanhToanHangThang;
    }

    public String getGocThanhToanHangThang() {
        return gocThanhToanHangThang;
    }

    public void setGocThanhToanHangThang(String gocThanhToanHangThang) {
        this.gocThanhToanHangThang = gocThanhToanHangThang;
    }

    public String getDuNoCuoiKi() {
        return duNoCuoiKi;
    }

    public void setDuNoCuoiKi(String duNoCuoiKi) {
        this.duNoCuoiKi = duNoCuoiKi;
    }

    public String getKhoanTienTraHangThang() {
        return khoanTienTraHangThang;
    }

    public void setKhoanTienTraHangThang(String khoanTienTraHangThang) {
        this.khoanTienTraHangThang = khoanTienTraHangThang;
    }
}
