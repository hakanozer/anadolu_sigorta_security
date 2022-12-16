package com.works.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class LoginService {

    final DB db;
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
            String sql = "select * from admin where email = ? and password = ? ";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, passowrd);
            ResultSet rs = st.executeQuery();
            status = rs.next();
        }catch (Exception ex) {
            System.err.println("Login Error : " + ex);
        }
        return status;
    }


}
