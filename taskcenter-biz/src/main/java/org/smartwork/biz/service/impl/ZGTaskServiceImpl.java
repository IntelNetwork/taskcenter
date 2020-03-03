package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.TaskAttachColumnConstant;
import org.smartwork.comm.constant.TaskColumnConstant;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskAttach;
import org.smartwork.dal.mapper.ZGTaskAttachMapper;
import org.smartwork.dal.mapper.ZGTaskMapper;
import org.smartwork.dal.mapper.ext.ZGTaskExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.smartwork.comm.model.ZGTaskAttachDto;
import org.smartwork.comm.model.ZGTaskDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Service
public class ZGTaskServiceImpl extends ServiceImpl<ZGTaskMapper, ZGTask> implements IZGTaskService {

    @Autowired
    ZGTaskExtMapper zgTaskExtMapper;
    @Autowired
    ZGTaskAttachMapper zgTaskAttachMapper;

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
    public Result<ZGTask> addZGTask(ZGTaskDto taskDto) {
        ZGTask task = new ZGTask();
        BeanCopier.create(ZGTaskDto.class, ZGTask.class, false)
                .copy(taskDto, task, null);
        baseMapper.insert(task);

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
            });
        }

        return null;
    }


    /***
     * removeTask方法概述:删除任务,关联附件
     * @param id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeTask(String id) {
        baseMapper.deleteById(id);
        //同时删除任务附件
        zgTaskAttachMapper.delete(new QueryWrapper<ZGTaskAttach>().eq(TaskAttachColumnConstant.TASK_ID, id));
    }


    /***
     * removeTasks方法概述:批量删除任务,关联附件
     * @param ids
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeTasks(String ids) {
        List idList = Arrays.asList(ids.split(CommonConstant.SEPARATOR));
        idList.stream().forEach(id -> {
            baseMapper.deleteById((Serializable) id);
            //删除所有附件
            zgTaskAttachMapper.delete(new QueryWrapper<ZGTaskAttach>().eq(TaskAttachColumnConstant.TASK_ID, id));
        });
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
        //删除 zgTaskAttachMapper
        zgTaskAttachMapper.delete(new QueryWrapper<ZGTaskAttach>().eq(TaskColumnConstant.ID, zgTaskDto.getId()));
        Long zgTaskId = zgTask.getId();
        List<ZGTaskAttachDto> zgTaskAttachDtos = zgTaskDto.getZgTaskAttachDtos();
        if (ConvertUtils.isNotEmpty(zgTaskAttachDtos)) {
            zgTaskAttachDtos.stream().forEach(zgTaskAttachDto -> {
                ZGTaskAttach zgTaskAttach = new ZGTaskAttach();
                zgTaskAttach.setTaskId(zgTaskId);
                zgTaskAttach.setCnName(zgTaskAttach.getCnName());
                zgTaskAttach.setSuffix(zgTaskAttach.getSuffix());
                zgTaskAttach.setFilePath(zgTaskAttach.getFilePath());
                zgTaskAttachMapper.insert(zgTaskAttach);
            });
        }
    }
}