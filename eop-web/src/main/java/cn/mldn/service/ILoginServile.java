package cn.mldn.service;

import cn.mldn.vo.Emp;

import java.util.Map;

public interface ILoginServile {

    /**
     * 用户登录处理，根据用户名和密码进行登录操作
     *
     * @param vo 包含有用户名和密码（加密）
     * @return 登录成功emp信息，否则返回null
     * @throws Exception SQL
     */
    Map<String, Object> login(Emp vo) throws Exception;
}
