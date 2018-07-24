package jspring.web.servlet.handler;

import javax.servlet.http.HttpServletRequest;

import jspring.web.servlet.HandlerExecutionChain;
import jspring.web.servlet.HandlerMapping;

/**
 * 简单的地址映射
 * @author wills
 *
 */
public class SimpleUrlHanderMapping implements HandlerMapping{

	public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		return null;
	}

}
