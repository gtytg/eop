package cn.mldn.service;

public interface IUserService {

    boolean exitPassword(String oldpwd,String newpwd,String eid)throws Exception;
}
