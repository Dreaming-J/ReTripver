package com.retripver.global.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.retripver.plan.dto.OptimizeCourseRequest;

@Component
public class OptimizeUtil {

	private class Node {
		int to;
		int time;
		
		public Node(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}

	//해밀턴 경로(시작과 끝 고정)
	public int[] findOptimRouteFixStartAndEnd(List<OptimizeCourseRequest> courses) {
		int[] newOrder = new int[courses.size()];
		
		
		
		return newOrder;
	}
	
	//다익스트라(시작 고정)
	public int[] findOptimRouteFixStart(List<OptimizeCourseRequest> courses) {
		int[] newOrder = new int[courses.size()];
		
		
		
		return newOrder;
	}
	
	private List<Node>[] makeGraphList(List<OptimizeCourseRequest> courses) {
		List<Node>[] graphList = new ArrayList[courses.size()];
		
		
		
		
		return graphList;
	}
}
