package com.example;

import com.example.imperial.court.dao.BaseDao;
import com.example.imperial.court.entity.Emp;
import com.example.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ImperialCourtTest {
    private final BaseDao baseDao = new BaseDao<>();

    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection = " + connection);

        JDBCUtils.releaseConnection(connection);
    }

    @Test
    public void testGetSingleBean() {
        String sql = "select emp_id empId, emp_name empName, emp_position empPosition, login_account loginAccount, " +
                "login_password loginPassword from t_emp where emp_id = ?";
        Emp emp = (Emp) baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println("emp = " + emp);
    }

    @Test
    public void testGetBeanList() {
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount," +
                "login_password loginPassword from t_emp";
        List<Emp> empList = baseDao.getBeanList(sql, Emp.class);

        for (Emp emp : empList) {
            System.out.println("emp = " + emp);
        }
    }

    @Test
    public void testUpdate() {
        String sql = "update t_emp set emp_position=? where emp_id=?";

        String empPosition = "minister";
        String empId = "3";

        int affectedRowNumber = baseDao.update(sql, empPosition, empId);
        System.out.println("affectedRowNumber = " + affectedRowNumber);
    }

    @Test
    public void testSubString() {
        String substring = "aaa.png".substring("aaa.png".lastIndexOf("."));
        System.out.println("substring = " + substring);
    }
}
