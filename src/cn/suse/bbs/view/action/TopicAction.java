package cn.suse.bbs.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.suse.bbs.base.BaseAction;
import cn.suse.bbs.domain.Forum;
import cn.suse.bbs.domain.PageBean;
import cn.suse.bbs.domain.Reply;
import cn.suse.bbs.domain.Topic;
import cn.suse.bbs.util.HtmlUtil;
import cn.suse.bbs.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	private Long forumId;

	/** 显示单个主题（主帖+回帖列表） */
	public String show() throws Exception {
		// 准备数据：topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备分页信息
		new QueryHelper(Reply.class, "r")//
				.addCondition("r.topic=?", topic)//
				.addOrderProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum, pageSize);

		return "show";
	}

	/** 发表新主题页面 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** 发表新主题 */
	public String add() throws Exception {
		if(keyWordfilter.isContentKeyWords(HtmlUtil.ConvertToText(model.getContent()))){//判断是否有敏感词汇
			model.setContent(keyWordfilter.getReplaceKeyWords(model.getContent()));//敏感词和谐
		}
		model.setForum(forumService.getById(forumId));
		model.setAuthor(getCurrentUser()); // 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 当前请求中的IP
		model.setPostTime(new Date()); // 当前时间
		
		// 保存
		topicService.save(model);

		return "toShow"; // 转到新主题的显示页面
	}

	// ---

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
