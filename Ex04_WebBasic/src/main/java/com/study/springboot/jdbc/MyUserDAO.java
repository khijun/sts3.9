package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void saveUser(MyUserDTO user) {
    	String query = "insert into dept(deptno, dname, loc) "
    			+ "values (?, ?, ?)";
    	jdbcTemplate.update(query, user.getDeptno(), user.getDname(), user.getLoc());
    }
    
    public int countDept() {
    	String query = "select count(*) from dept";
    	return jdbcTemplate.queryForObject(query, Integer.class);
    }
 
    public List<MyUserDTO> list() {
        String query = "select * from dept";
        List<MyUserDTO> list = jdbcTemplate.query(
                query, (rs, rowNum)->{
                	MyUserDTO myuserDTO = new MyUserDTO(
                			rs.getInt("deptno"),
                			rs.getString("dname"),
                			rs.getString("loc"));
                	return myuserDTO;
                });
        
        //for(UserDTO my : list) {
        //    System.out.println(my);  
        //}   
        
        return list;
    }

}
