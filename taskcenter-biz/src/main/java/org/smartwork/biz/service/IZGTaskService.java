package org.smartwork.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.SysUserDto;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.UserVo;
import org.smartwork.comm.model.ZGTaskPageDto;
import org.smartwork.comm.vo.ZGTaskCountVo;
import org.smartwork.comm.vo.ZGTaskVo;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.comm.model.ZGTaskDto;

import java.util.List;

/***
 * 任务接口层
 */
public interface IZGTaskService extends IService<ZGTask> {


    /***
     * addZGTask方法概述:添加任务
     * @param  taskDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addZGTask(ZGTaskDto taskDto);


    /***
     * trustReward方法概述:托管赏金
     * @param  task
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Result<ZGTaskDto> trustReward(ZGTaskDto task);


    /***
     * removeTask方法概述:删除任务,关联附件
     * @param id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void removeTask(String id);


    /***
     * removeTasks方法概述:批量删除任务,关联附件
     * @param ids
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void removeTasks(String ids);

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

    /***
     * updateTask方法概述:任务编辑
     * @param zgTaskDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGTaskDto>
     * @创建人 Tom
     * @创建时间 2020/3/3 10:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateTask(ZGTaskDto zgTaskDto);

    /***
     * pageTasks方法概述:任务分页查询
     * @param zgTaskPageDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/2 13:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ZGTaskCountVo> pageTasks(IPage<ZGTaskCountVo> page, ZGTaskPageDto zgTaskPageDto);
}