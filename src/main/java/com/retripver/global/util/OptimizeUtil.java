package com.retripver.global.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.retripver.plan.dto.OptimizeCourseRequest;
import com.retripver.plan.dto.OptimizeCoursesRequest;

@Component
public class OptimizeUtil {
	
	private static int size;
	private static int[] tempOrder;
	private static int[] newOrder;
	private static int[][] graph;
	private static int minTime;
	
	public int[] optimeRoute(OptimizeCoursesRequest optimizeCoursesRequest) {
		List<OptimizeCourseRequest> courses = optimizeCoursesRequest.getOptimizeCourses();
		
		size = optimizeCoursesRequest.getSize();
		tempOrder = new int[size];
		newOrder = new int[size];
		graph = makeGraph(courses);
		minTime = Integer.MAX_VALUE;
		
		if (optimizeCoursesRequest.isFixFirst() && optimizeCoursesRequest.isFixLast())
			createOptimRouteFixStartAndEnd(courses);
		else if (optimizeCoursesRequest.isFixFirst())
			createOptimRouteFixStart(courses);
		else if (optimizeCoursesRequest.isFixLast())
			createOptimRouteFixEnd(courses);
		else
			createOptimRouteFixNone(courses);
		
		return newOrder;
	}
	
	private int[][] makeGraph(List<OptimizeCourseRequest> courses) {
		int size = courses.size();
		
		int[][] graph = new int[size][size];
		
		for (OptimizeCourseRequest course : courses) {
			int from = course.getFrom();
			int to = course.getTo();
			int time =  course.getTime();
			
			graph[from][to] = time;
		}
		
		return graph;
	}

	//시작과 끝 고정
	private int[] createOptimRouteFixStartAndEnd(List<OptimizeCourseRequest> courses) {
		tempOrder[0] = 0;
		tempOrder[size - 1] = size - 1;
		
		findOptimRouteFixStartAndEnd(0, 0, 1 << 0 | 1 << (size - 1));
		
		return newOrder;
	}
	
	private void findOptimRouteFixStartAndEnd(int from, int time, int visitedBit) {
		if (time > minTime)
			return;

		if (visitedBit == (1 << size) - 1) {
			if ((time += graph[from][size - 1]) < minTime) {
				minTime = time;
				
				for (int idx = 0; idx < size; idx++)
					newOrder[idx] = tempOrder[idx];
			}
			
			return;
		}
		
		for (int to = 0; to < size; to++) {
			if ((visitedBit & 1 << to) != 0)
				continue;
			
			tempOrder[Integer.bitCount(visitedBit) - 1] = to;
			findOptimRouteFixStartAndEnd(to, time + graph[from][to], visitedBit | 1 << to);
		}
	}
	
	//시작 고정
	private int[] createOptimRouteFixStart(List<OptimizeCourseRequest> courses) {
		tempOrder[0] = 0;
		
		findOptimRouteFixStart(0, 0, 1 << 0);
		
		return newOrder;
	}
	
	private void findOptimRouteFixStart(int from, int time, int visitedBit) {
		if (time > minTime)
			return;
		
		if (visitedBit == (1 << size) - 1) {
			if (time < minTime) {
				minTime = time;
				
				for (int idx = 0; idx < size; idx++)
					newOrder[idx] = tempOrder[idx];
			}
			
			return;
		}
		
		for (int to = 0; to < size; to++) {
			if ((visitedBit & 1 << to) != 0)
				continue;
			
			tempOrder[Integer.bitCount(visitedBit)] = to;
			findOptimRouteFixStart(to, time + graph[from][to], visitedBit | 1 << to);
		}
	}

	//끝 고정
	private int[] createOptimRouteFixEnd(List<OptimizeCourseRequest> courses) {
		tempOrder[size - 1] = size - 1;
		
		findOptimRouteFixEnd(size - 1, 0, 1 << (size - 1));
		
		return newOrder;
	}
	
	private void findOptimRouteFixEnd(int to, int time, int visitedBit) {
		if (time > minTime)
			return;
		
		if (visitedBit == (1 << size) - 1) {
			if (time < minTime) {
				minTime = time;
				
				for (int idx = 0; idx < size; idx++)
					newOrder[idx] = tempOrder[idx];
			}
			
			return;
		}
		
		for (int from = 0; from < size; from++) {
			if ((visitedBit & 1 << from) != 0)
				continue;
			
			tempOrder[(size - 1) - Integer.bitCount(visitedBit)] = from;
			findOptimRouteFixEnd(from, time + graph[from][to], visitedBit | 1 << from);
		}
	}

	//고정 X
	private int[] createOptimRouteFixNone(List<OptimizeCourseRequest> courses) {
		for (int from = 0; from < size; from++) {
			tempOrder[0] = from;
			
			findOptimRouteFixNone(from, 0, 1 << from);
		}
		
		return newOrder;
	}
	
	private void findOptimRouteFixNone(int from, int time, int visitedBit) {
		if (time > minTime)
			return;
		
		if (visitedBit == (1 << size) - 1) {
			if (time < minTime) {
				minTime = time;
				
				for (int idx = 0; idx < size; idx++)
					newOrder[idx] = tempOrder[idx];
			}
			
			return;
		}
		
		for (int to = 0; to < size; to++) {
			if ((visitedBit & 1 << to) != 0)
				continue;
			
			tempOrder[Integer.bitCount(visitedBit)] = to;
			findOptimRouteFixStart(to, time + graph[from][to], visitedBit | 1 << to);
		}
	}
}
