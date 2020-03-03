package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTindTypeService;
import org.smartwork.dal.entity.ZGTindType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName IndustryControler
 * @Description 行业类型
 * @Author xfx
 * @Date 2020/2/28 17:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/industry")
@Api(tags={"行业类型"})
@Slf4j
public class IndustryControler {

    @Autowired
    private IZGTindTypeService tindTypeService;

    /**
     * @Author xfx
     *  @Date 18:05 2020/2/28
     * @Param []
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGTindType>>
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询行业类型")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = org.smartwork.comm.vo.Result.INDUSTRY_ERROR),
            @ApiResponse(code = 200, message = org.smartwork.comm.vo.Result.INDUSTRY)
    })
    public Result<List<ZGTindType>> list(){
        Result<List<ZGTindType>> result=new Result<List<ZGTindType>>();
        List<ZGTindType> zgTindTypeList=tindTypeService.list();
        result.setResult(zgTindTypeList);
        return result;
    }
}

