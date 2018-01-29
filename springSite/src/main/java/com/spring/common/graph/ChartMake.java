package com.spring.common.graph;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.spring.common.file.FileUploadUtil;

public class ChartMake {
	
	static Logger logger = Logger.getLogger(ChartMake.class);
	
	public static void barChart(HttpServletRequest request, Map<String, Integer> resultMap) {
		
		String docRoot = request.getSession().getServletContext().getRealPath("/graph");
		FileUploadUtil.makeDir(docRoot);
		logger.info("업로드할 파일 경로(docRoot) : " + docRoot);
		
		File file = new File(docRoot + "/barChart.jpg");
		FileOutputStream fos = null;
		
		try {
			// 데이터로 사용할 카테고리 데이터 셋을 생성
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (Map.Entry<String, Integer> result : resultMap.entrySet()) {
				logger.info(result.getKey() + "=" + result.getValue());
				dataset.addValue(result.getValue(), result.getKey(), result.getKey());
			}
			
			JFreeChart chart = ChartFactory.createBarChart("회원 성별 통계", "성별", "인원수(단위 : 명)", dataset, PlotOrientation.VERTICAL, true, true, false);
			chart.setBackgroundPaint(Color.white);
			chart.getTitle().setFont(new Font("sansserif", Font.BOLD, 16));
			
			Font font = new Font("sansserif", Font.BOLD, 12);
			chart.getLegend().setItemFont(font);
			
			CategoryPlot plot = chart.getCategoryPlot();
			
			// X축 라벨
			plot.getDomainAxis().setLabelFont(font); // 성별
			
			// X축 도메인
			plot.getDomainAxis().setTickLabelFont(font); // 남자.여자
			
			// Y축 라벨
			plot.getRangeAxis().setLabelFont(font); // 인원수(단위 : 명)
			
			// Y축 범위
			plot.getRangeAxis().setTickLabelFont(font);
			
			fos = new FileOutputStream(file);
			ChartUtilities.writeChartAsJPEG(fos, chart, 500, 280);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if(fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
	}
	
	public static void pieChart(HttpServletRequest request, Map<String, Integer> resultMap) {
		
		String docRoot = request.getSession().getServletContext().getRealPath("/graph");
		FileUploadUtil.makeDir(docRoot);
		logger.info("업로드할 파일 경로(docRoot) : " + docRoot);
		
		File file = new File(docRoot + "/pieChart.jpg");
		FileOutputStream fos = null;
		
		try {
			DefaultPieDataset dataset = new DefaultPieDataset();
			
			for(Map.Entry<String, Integer> result : resultMap.entrySet()) {
				
				logger.info(result.getKey() + "=" + result.getValue());
				dataset.setValue(result.getKey(), result.getValue());
				
			}
			
			// 제목, 데이터, 범례, 툴팁, url
			JFreeChart chart = ChartFactory.createPieChart("연령대별 통계", dataset, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);
			chart.getTitle().setFont(new Font("sansserif", Font.BOLD, 16));
			chart.getLegend().setItemFont(new Font("sansserif", Font.BOLD, 12));
			
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("sansserif", Font.BOLD, 14));
			
			fos = new FileOutputStream(file);
			ChartUtilities.writeChartAsJPEG(fos, chart, 480, 280);
	
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
		
	}

}
