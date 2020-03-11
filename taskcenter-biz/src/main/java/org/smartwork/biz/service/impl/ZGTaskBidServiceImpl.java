package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.enums.TaskHitstateEnum;
import org.smartwork.comm.enums.YesNoEnum;
import org.smartwork.comm.model.ZGBigAttachDto;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.dal.entity.ZGBigAttach;
import org.smartwork.dal.entity.ZGTaskBid;
import org.smartwork.dal.mapper.ZGBigAttachMapper;
import org.smartwork.dal.mapper.ZGTaskBidMapper;
import org.smartwork.dal.mapper.ext.ZGTaskBidExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGTaskBidServiceImpl extends ServiceImpl<ZGTaskBidMapper, ZGTaskBid> implements IZGTaskBidService {

    @Autowired
    ZGBigAttachMapper zgBigAttachMapper;

    @Autowired
    ZGTaskBidExtMapper taskBidExtMapper;

    /***
     * Bidding方法概述:立即竞标关联附件
     * @param taskBidDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void Bidding(ZGTaskBidDto taskBidDto) {

        ZGTaskBid taskBid = new ZGTaskBid();
        BeanCopier.create(ZGTaskBidDto.class, ZGTaskBid.class, false)
                .copy(taskBidDto, taskBid, null);
        baseMapper.insert(taskBid);


        //任务竞标附件关联
        List<ZGBigAttachDto> zgBigAttachDtos = taskBidDto.getZgBigAttachDtos();
        if (ConvertUtils.isNotEmpty(zgBigAttachDtos)) {
            Long taskBidId = taskBid.getId();
            ZGBigAttach attach = new ZGBigAttach();
            zgBigAttachDtos.stream().forEach(temp -> {
                attach.setBidId(taskBidId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                //执行添加操作
                zgBigAttachMapper.insert(attach);
            });
        }

    }


    /***
     * TaskBiding方法概述:服务方确认竞标结果(选标)
     * @param taskBidDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void TaskBiding(ZGTaskBidDto taskBidDto) {
        ZGTaskBid taskBid = new ZGTaskBid();
        BeanCopier.create(ZGTaskBidDto.class, ZGTaskBid.class, false)
                .copy(taskBidDto, taskBid, null);
        baseMapper.updateById(taskBid);

        //其他参与该任务的人将被通知未竞标成功
        QueryWrapper<ZGTaskBid> query = new QueryWrapper<>();
        query.eq(DataColumnConstant.TASKID, taskBidDto.getTaskId());
        query.ne(DataColumnConstant.HITSTATE, TaskHitstateEnum.HITSTATE.getCode());
        List<ZGTaskBid> zgTaskBids = baseMapper.selectList(query);
        zgTaskBids.forEach(zgTaskBid->{
            zgTaskBid.setHitState(TaskHitstateEnum.HITSTATE_NO.getCode());
            baseMapper.updateById(zgTaskBid);
        });

    }


    /***
     * taskBidDetail方法概述:查看竞标详情
     * @param id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public ZGTaskBidDto taskBidDetail(Long id) {
        return taskBidExtMapper.taskBidDetail(id);
    }


}