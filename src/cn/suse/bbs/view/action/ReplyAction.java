package cn.suse.bbs.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.suse.bbs.base.BaseAction;
import cn.suse.bbs.domain.Reply;
import cn.suse.bbs.domain.Topic;
import cn.suse.bbs.util.HtmlUtil;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	private Long topicId;

	/** 发表新回复页面 */
	public String addUI() throws Exception {
		// 准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/** 发表新回复 */
	public String add() throws Exception {
		if(keyWordfilter.isContentKeyWords(HtmlUtil.ConvertToText(model.getContent()))){//判断是否有敏感词汇
			model.setContent(keyWordfilter.getReplaceKeyWords(model.getContent()));//敏感词和谐
		}
		model.setTopic(topicService.getById(topicId));
		//  当前信息
		model.setAuthor(getCurrentUser()); // 当前用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date()); // 当前时间

		// 保存
		replyService.save(model);

		return "toTopicShow"; // 转到新回复所在主题的显示页面
	}

	// ---

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
