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
import org.smartwork.dal.entity.ZGTaskOrder;

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

    /***
     * getByRelease方法概述:通过会员id查询已发布任务信息
     * @param memberId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ZGTaskVo> getRelease(IPage<ZGTaskCountVo> page,Long memberId);

    /***
     * getByRelease方法概述:通过会员id查询已完成任务信息
     * @param memberId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ZGTaskVo> getPass(IPage<ZGTaskCountVo> page,Long memberId);

    IPage<ZGTaskVo> getCheck(IPage<ZGTaskCountVo> page,Long memberId);
}