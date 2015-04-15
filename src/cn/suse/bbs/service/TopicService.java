package cn.suse.bbs.service;

import java.util.List;

import cn.suse.bbs.base.DaoSupport;
import cn.suse.bbs.domain.Forum;
import cn.suse.bbs.domain.PageBean;
import cn.suse.bbs.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/**
	 * 查询指定版块中的所有主题，排序：所有置顶帖在最上面，并按最后更新时间排序，让新状态的在上面。
	 * 
	 * @param forum
	 * @return
	 */
	@Deprecated
	List<Topic> findByForum(Forum forum);

	/**
	 * 查询分页信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param forum
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

}
