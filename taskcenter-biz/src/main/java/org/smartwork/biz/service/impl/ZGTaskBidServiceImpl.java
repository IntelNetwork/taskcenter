package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.enums.TaskHitstateEnum;
import org.smartwork.comm.model.ZGBigAttachDto;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.comm.vo.ZGTaskBidVo;
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


   /**
    * @description 修改其它未竞标人状态
    * @author xfx
    * @date 2020/3/11 14:35
    * @parameter
    * @return
    */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void TaskBiding(long taskId) {
        QueryWrapper<ZGTaskBid> query = new QueryWrapper<>();
        query.eq(DataColumnConstant.TASKID,taskId);
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

    /***
     * selectByTaskId方法概述:根据任务id查询已中标会员
     * @param taskId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskBid>
     * @创建人 Tom
     * @创建时间 2020/3/16 9:57
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public ZGTaskBid selectByTaskId(Long taskId) {
        return taskBidExtMapper.selectByTaskId(taskId);
    }


    /**
     * @description 已参与任务竞标返回视图
     * @author xfx
     * @date 2020/3/13 17:57
     * @parameter
     * @return 
     */
    @Override
    public List<ZGTaskBidVo> taskBidlist(List<ZGTaskBidVo> zgTaskBidVos) {
        zgTaskBidVos.stream().forEach(zgTaskBidVoTemp ->{
            QueryWrapper<ZGTaskBid> query = new QueryWrapper<>();
            query.eq(DataColumnConstant.MEMBERID,zgTaskBidVoTemp.getMemberId());
            zgTaskBidVoTemp.setAmount(taskBidExtMapper.selectCount(query));
        } );
        return zgTaskBidVos;
    }
}