/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Voucher;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kienb
 */
public class VoucherDAO {

    public Voucher getVoucher(double total) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("Select top 1 * from Voucher where VoucherStatus = 1 and DateStart <= GETDATE() and GETDATE() <= DateEnd\n"
                        + "and Condition < ? order by Condition desc");
                ptm.setDouble(1, total);
                rs = ptm.executeQuery();
            }
            while (rs.next()) {
                Voucher v = new Voucher();
                v.setVoucherID(rs.getInt(1));
                v.setValue(rs.getInt(7));
                return v;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            };
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
}
