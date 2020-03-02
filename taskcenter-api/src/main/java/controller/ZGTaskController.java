package controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.model.ZGTaskPageDto;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzw
 * @date 2020/3/2 12:38
 */
@RestController
@RequestMapping("/api-task")
@Api(tags={"任务管理"})
@Slf4j
public class ZGTaskController {

    @Autowired
    IZGTaskService izgTaskService;

    /***
     * pageTasks方法概述:任务分页查询
     * @param basePageDto, zgTaskPageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.smartwork.dal.entity.ZGTask>>
     * @创建人 Tom
     * @创建时间 2020/3/2 13:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiOperation("任务分页查询")
    public Result<IPage<ZGTask>> selectUserList(BasePageDto basePageDto, ZGTaskPageDto zgTaskPageDto){
        log.debug("传入的参数为"+JSON.toJSONString(basePageDto));
        Result<IPage<ZGTask>> result = new Result<>();
        QueryWrapper<ZGTask> qw = new QueryWrapper<ZGTask>();
        if(ConvertUtils.isNotEmpty(zgTaskPageDto)){
            if(ConvertUtils.isNotEmpty(zgTaskPageDto.getTTypeName())){
                qw.eq(PermsCommonConstant.ROLE_NAME,zgTaskPageDto.getTTypeName());
            }
            if(ConvertUtils.isNotEmpty(zgTaskPageDto.getName())){
                qw.eq(PermsCommonConstant.ROLE_CODE,zgTaskPageDto.getName());
            }
        }
        IPage<ZGTask> page = new Page<ZGTask>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGTask> s = izgTaskService.page(page,qw);
        result.setResult(s);
        log.info("返回值为:"+JSON.toJSONString(result.getResult()));
        return result;
    }

}
