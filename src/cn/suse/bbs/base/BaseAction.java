package cn.suse.bbs.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.suse.bbs.domain.User;
import cn.suse.bbs.filter.KeywordFilter;
import cn.suse.bbs.service.DepartmentService;
import cn.suse.bbs.service.ForumService;
import cn.suse.bbs.service.PrivilegeService;
import cn.suse.bbs.service.ReplyService;
import cn.suse.bbs.service.RoleService;
import cn.suse.bbs.service.TopicService;
import cn.suse.bbs.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	// =============== ModelDriven的支持 ==================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	
	protected KeywordFilter keyWordfilter = getKeyWord();

	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	
	// ============== 获取对象并初始化 =============
	private KeywordFilter getKeyWord() {
		KeywordFilter filter = new KeywordFilter();
		filter.initfiltercode();
		return filter;
	}
	// ============== 分页用的参数 =============
	protected int pageNum = 1; // 当前页
	protected int pageSize = 10; // 每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
