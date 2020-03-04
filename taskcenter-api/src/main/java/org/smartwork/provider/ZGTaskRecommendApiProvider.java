package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TaskRecommendController
 * @Description 相关任务推荐
 * @Author xfx
 * @Date 2020/3/3 14:32
 * @Version 1.0
 */

@RestController
@RequestMapping("/${smartwork.verision}/recommend")
@Api(tags={"任务推荐"})
@Slf4j
public class ZGTaskRecommendApiProvider {


    @Autowired
    private IZGTaskService izgTaskService;


    /**
     * @description 根据行业id 查询任务
     * @author xfx
     * @date 2020/3/3 14:43
     * @parameter [industryId]
     * @return org.smartwork.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGTask>>
     */
    @RequestMapping(value = "/list/{industryId}", method = RequestMethod.GET)
    @ApiOperation("查询相关任务推荐")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code = 200, message = Result.COMM_ACTION_MSG)
    })
    public Result<IPage<ZGTask>>  list(BasePageDto basePageDto, @PathVariable long industryId){
        log.debug(JSON.toJSONString("传入参数为："+industryId));
        Result<IPage<ZGTask>> result=new  Result<IPage<ZGTask>>();
        if(ConvertUtils.isEmpty(industryId)){
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        IPage<ZGTask> page = new Page<ZGTask>(basePageDto.getPageNo(),basePageDto.getPageSize());
        page.setSize(4);
        IPage<ZGTask> zgTasks = izgTaskService.page(page,new QueryWrapper<ZGTask>().eq(DataColumnConstant.INDUSTRYID,industryId));
        result.setResult(zgTasks);
        return  result;
    }
}
