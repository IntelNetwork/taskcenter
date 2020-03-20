package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.TaskAttachColumnConstant;
import org.smartwork.comm.constant.TaskColumnConstant;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskHitstateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.model.*;
import org.smartwork.comm.vo.ZGTaskCountVo;
import org.smartwork.comm.vo.ZGTaskVo;
import org.smartwork.dal.entity.*;
import org.smartwork.dal.mapper.ZGTaskAttachMapper;
import org.smartwork.dal.mapper.ZGTaskBidMapper;
import org.smartwork.dal.mapper.ZGTaskMapper;
import org.smartwork.dal.mapper.ZGTaskRelTagMapper;
import org.smartwork.dal.mapper.ext.ZGTaskExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Service
public class ZGTaskServiceImpl extends ServiceImpl<ZGTaskMapper, ZGTask> implements IZGTaskService {

    @Autowired
    ZGTaskExtMapper zgTaskExtMapper;

    @Autowired
    ZGTaskAttachMapper zgTaskAttachMapper;

    @Autowired
    ZGTaskRelTagMapper zgTaskRelTagMapper;

    @Autowired
    ZGTaskBidMapper zgTaskBidMapper;

    /***
     * addZGTask方法概述: 添加任务
     * @param taskDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addZGTask(ZGTaskDto taskDto) {
        ZGTask task = new ZGTask();
        BeanCopier.create(ZGTaskDto.class, ZGTask.class, false)
                .copy(taskDto, task, null);
        baseMapper.insert(task);

        //任务标签关联
        List<ZGTaskRelTagDto> zgTaskRelTagDtos = taskDto.getZgTaskRelTagDtos();
        if (ConvertUtils.isNotEmpty(zgTaskRelTagDtos)) {
            Long taskId = task.getId();
            ZGTaskRelTag attach = new ZGTaskRelTag();
            zgTaskRelTagDtos.stream().forEach(temp -> {
                attach.setTaId(temp.getTaId());
                attach.setTaskId(taskId);
                attach.setName(temp.getName());
                //执行添加
                zgTaskRelTagMapper.insert(attach);
            });
        }

        //任务附件关联
        List<ZGTaskAttachDto> zgTaskAttachDtos = taskDto.getZgTaskAttachDtos();
        if (ConvertUtils.isNotEmpty(zgTaskAttachDtos)) {
            Long taskId = task.getId();
            ZGTaskAttach attach = new ZGTaskAttach();
            zgTaskAttachDtos.stream().forEach(temp -> {
                attach.setId(taskId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                //执行添加
                zgTaskAttachMapper.insert(attach);
            });
        }

        //任务竞标记录关联(指定服务方)
        ZGTaskBidDto zgTaskBidDto = taskDto.getZgTaskBidDto();
        if (ConvertUtils.isNotEmpty(zgTaskBidDto)) {
            ZGTaskBid taskBid = new ZGTaskBid();
            BeanCopier.create(ZGTaskBidDto.class, ZGTaskBid.class, false)
                    .copy(zgTaskBidDto, taskBid, null);
            taskBid.setHitState(TaskHitstateEnum.HITSTATE.getCode());
            zgTaskBidMapper.insert(taskBid);
        }

    }


    /***
     * 获取最新成交动态
     */
    @Override
    public List<ZGTask> selectAllTask() {
        return zgTaskExtMapper.selectAllTask();
    }


    /***
     * updateTask方法概述:任务编辑
     * @param zgTaskDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGTaskDto>
     * @创建人 Tom
     * @创建时间 2020/3/3 10:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTask(ZGTaskDto zgTaskDto) {
        ZGTask zgTask = new ZGTask();
        BeanCopier.create(ZGTaskDto.class, ZGTask.class, false).copy(zgTaskDto, zgTask, null);
        baseMapper.updateById(zgTask);
        //删除任务标签和任务附件
        zgTaskRelTagMapper.delete(new QueryWrapper<ZGTaskRelTag>().eq(TaskColumnConstant.TASKID, zgTaskDto.getId()));
        zgTaskAttachMapper.delete(new QueryWrapper<ZGTaskAttach>().eq(TaskColumnConstant.TASKID, zgTaskDto.getId()));
        Long zgTaskId = zgTask.getId();
        //任务标签
        List<ZGTaskRelTagDto> zgTaskRelTagDtos = zgTaskDto.getZgTaskRelTagDtos();
        if (ConvertUtils.isNotEmpty(zgTaskRelTagDtos)) {
            zgTaskRelTagDtos.stream().forEach(zgTaskRelTagDto -> {
                ZGTaskRelTag zgTaskRelTag = new ZGTaskRelTag();
                zgTaskRelTag.setTaskId(zgTaskId);
                zgTaskRelTag.setTaId(zgTaskRelTagDto.getTaId());
                zgTaskRelTag.setName(zgTaskRelTagDto.getName());
                zgTaskRelTagMapper.insert(zgTaskRelTag);
            });
        }
        //任务附件
        List<ZGTaskAttachDto> zgTaskAttachDtos = zgTaskDto.getZgTaskAttachDtos();
        if (ConvertUtils.isNotEmpty(zgTaskAttachDtos)) {
            zgTaskAttachDtos.stream().forEach(zgTaskAttachDto -> {
                ZGTaskAttach zgTaskAttach = new ZGTaskAttach();
                zgTaskAttach.setTaskId(zgTaskId);
                zgTaskAttach.setCnName(zgTaskAttachDto.getCnName());
                zgTaskAttach.setSuffix(zgTaskAttachDto.getSuffix());
                zgTaskAttach.setFilePath(zgTaskAttachDto.getFilePath());
                zgTaskAttachMapper.insert(zgTaskAttach);
            });
        }
    }

    /***
     * pageTasks方法概述:任务分页查询
     * @param zgTaskPageDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/2 13:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public IPage<ZGTaskCountVo> pageTasks(IPage<ZGTaskCountVo> page, ZGTaskPageDto zgTaskPageDto) {
        return zgTaskExtMapper.pageTasks(page, zgTaskPageDto);
    }

    /***
     * getByRelease方法概述:通过会员id查询已发布任务信息
     * @param memberId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public IPage<ZGTaskVo> getRelease(IPage<ZGTaskVo> page,Long memberId) {
        return zgTaskExtMapper.getRelease(page,memberId);
    }

    /***
     * getByRelease方法概述:通过会员id查询已完成任务信息
     * @param memberId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public IPage<ZGTaskVo> getPass(IPage<ZGTaskVo> page,Long memberId) {
        return zgTaskExtMapper.getPass(page,memberId);
    }

    @Override
    public IPage<ZGTaskVo> getCheck(IPage<ZGTaskVo> page, Long memberId) {
        return zgTaskExtMapper.getCheck(page,memberId);
    }
}