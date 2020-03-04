package org.smartwork.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.smartwork.dal.entity.ZGTask;

import java.util.List;

public interface ZGTaskExtMapper extends BaseMapper<ZGTask> {

    /***
     * selectAllTask方法概述:获取最新成交动态
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGTask>>
     * @创建人 Tom
     * @创建时间 2020/3/2 18:20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<ZGTask> selectAllTask();

}