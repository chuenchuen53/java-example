package com.example.imperial.court.service.impl;

import com.example.imperial.court.dao.api.EmpDao;
import com.example.imperial.court.dao.impl.EmpDaoImpl;
import com.example.imperial.court.entity.Emp;
import com.example.imperial.court.exception.LoginFailedException;
import com.example.imperial.court.service.api.EmpService;
import com.example.imperial.court.util.ImperialCourtConst;
import com.example.imperial.court.util.MD5Util;

public class EmpServiceImpl implements EmpService {
    private final EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {
        // 1、对密码执行加密
        String encodedLoginPassword = MD5Util.encode(loginPassword);

        // 2、根据账户和加密密码查询数据库
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount, encodedLoginPassword);

        // 3、检查 Emp 对象是否为 null
        if (emp != null) {
            //	①不为 null：返回 Emp
            return emp;
        } else {
            //	②为 null：抛登录失败异常
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
