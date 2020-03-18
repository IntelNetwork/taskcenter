package org.smartwork.service;

import org.forbes.comm.model.SysUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnProperty(name = "spring.application.usercenter")
@FeignClient(name = "${spring.application.usercenter}", url = " http://122.51.238.114:8864/usercenter")
public interface SysUserService {

    /***
     * getUserByName方法慨述:根据用户名查询用户
     * @param username
     * @return SysUser
     * @创建人 huanghy
     * @创建时间 2019年11月15日 下午2:10:16
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    SysUser getUserByName(String username);

}