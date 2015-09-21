package com.solid.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.QA.Model.Baike;
import com.QA.Util.DBUtil;

public class BaikeService {

	public ArrayList<Baike> getBaikes(String name){
		ArrayList<Baike> baikes = new ArrayList<Baike>();
		String sql = "select id, title,answer from baikeview where baikeview.word=?";
		ArrayList<Object>params=new ArrayList<Object>();
		params.add(name);
		try {
			ResultSet rs = DBUtil.query(sql, params);
			while(rs.next()){
				Baike b = new Baike(rs.getInt("id"),rs.getString("title"),rs.getString("answer"));
				baikes.add(b);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return baikes;
	}
}
