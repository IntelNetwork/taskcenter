package org.smartwork.comm.vo;

import lombok.Data;
import org.forbes.comm.constant.CommonConstant;

import java.io.Serializable;

/***
 * Result概要说明：统一返回错误
 * @author Huanghy
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String message = "操作成功！";

	/****任务返回消息****/
	public static final String TASK_ADD = "添加任务成功！";
	public static final String TASK_ADD_ERROR = "添加任务失败！";

	public static final String TASK_SELECT = "查询任务成功！";
	public static final String TASK_SELECT_ERROR = "查询任务失败！";

	public static final String TASK_RELEASE = "发布任务成功！";
	public static final String TASK_RELEASE_ERROR = "发布任务失败！";

	public static final String TASK_START = "开始任务成功！";
	public static final String TASK_START_ERROR = "开始任务失败！";

	public static final String TASK_UPDATE = "编辑任务成功！";
	public static final String TASK_UPDATE_ERROR = "编辑任务失败！";

	public static final String TASK_DELETE = "删除任务成功！";
	public static final String TASK_DELETE_ERROR = "删除任务失败！";

	public static final String TASK_CONFIRM_RESULT = "确认竞标任务成功！";
	public static final String TASK_CONFIRM_RESULT_ERROR = "确认竞标任务失败！";

	/****行业类型返回消息****/
	public static final String INDUSTRY="行业类型查询成功";
	public static final String INDUSTRY_ERROR="行业类型查询失败";

	/****任务详情返回消息****/
	public static final String TASK_DETAIL="任务详情查询成功";
	public static final String TASK_DETAIL_ERROR="任务详情查询失败";
	/****行业类型返回消息****/
	public static final String TASK_TYPE="任务类型查询成功";
	public static final String TASK_TYPE_ERROR="任务类型查询失败";

	/****任务推荐返回消息****/
	public static final String TASK_RECOMMEND="任务推荐查询成功";
	public static final String TASK_RECOMMEND_ERROR="任务推荐查询失败";

	/****竞标审核结果****/
	public static final String EXAMINE="竞标审核成功";
	public static final String EXAMINE_ERROR="竞标审核失败";

	/**
	 * 返回代码
	 */
	private Integer code = CommonConstant.SC_OK_200;
	private String  bizCode = "";
	
	/**
	 * 返回数据对象 data
	 */
	private T result;

	public Result() {
		
	}
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public void error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
	}

	public void success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
	}
	
	public static org.forbes.comm.vo.Result<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static org.forbes.comm.vo.Result<Object> error(int code, String msg) {
		org.forbes.comm.vo.Result<Object> r = new org.forbes.comm.vo.Result<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
	
	/***
	 * error方法慨述:
	 * @param bizCode
	 * @param msg
	 * @return Result<Object>
	 * @创建人 huanghy
	 * @创建时间 2019年12月7日 下午4:07:04
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	public  void error(String bizCode, String msg) {
		this.bizCode = bizCode;
		this.message = msg;
		this.success = false;
	}
	
	public static org.forbes.comm.vo.Result<Object> ok(String msg) {
		org.forbes.comm.vo.Result<Object> r = new org.forbes.comm.vo.Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static org.forbes.comm.vo.Result<Object> ok(Object data) {
		org.forbes.comm.vo.Result<Object> r = new org.forbes.comm.vo.Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}
}

