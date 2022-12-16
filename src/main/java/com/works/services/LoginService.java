package com.works.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class LoginService {

    final DB db;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest request;
    /*
    public boolean login(String email, String passowrd) {
        boolean status = false;
        try {
            String sql = "select * from admin where email = '"+email+"' and password = '"+passowrd+"' ";
            // select * from admin where email = 'i@i.com' and password = '' or 1 = 1 --'
            System.out.println(sql);
            Statement st = db.dataSource().getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            status = rs.next();
        }catch (Exception ex) {
            System.err.println("Login Error : " + ex);
        }
        return status;
    }
     */

    public boolean login(String email, String passowrd) {
        boolean status = false;
        try {
            String sql = "select * from admin where email = ? ";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String dbPass = rs.getString("password");
                String pass = tinkEncDec.decrypt(dbPass);
                if (  pass.equals(passowrd) ) {
                    request.getSession().setAttribute("user", email);
                    status = true;
                }
            }
        }catch (Exception ex) {
            System.err.println("Login Error : " + ex);
        }
        return status;
    }


}
