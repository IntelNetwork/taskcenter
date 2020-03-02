package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskAttach;
import org.smartwork.dal.mapper.ZGTaskMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.smartwork.comm.model.ZGTaskAttachDto;
import org.smartwork.comm.model.ZGTaskDto;

import java.util.List;

@Service
public class ZGTaskServiceImpl extends ServiceImpl<ZGTaskMapper, ZGTask> implements IZGTaskService {

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
            zgTaskAttachDtos.stream().forEach(temp-> {
                attach.setId(taskId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
            });
        }


        return null;
    }
}