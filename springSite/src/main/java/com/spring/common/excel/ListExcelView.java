package com.spring.common.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import net.sf.jxls.transformer.XLSTransformer;

public class ListExcelView extends AbstractExcelView {
	
	static Logger logger = Logger.getLogger(ListExcelView.class);
	
	/**
	 * 참고 : "Content-disposition : attachment"은 브라우저 인식 파일
	 * 확장자를 포함하여 모든 확장자의 파일들에 대해,
	 * 다운로드시 무조건 "파일 다운로드" 대화상자를 보여주도록
	 * 헤더속성이라 할 수 있다.
	 */

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook arg1, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("content-Disposition", "attachment;fileName=\"" + model.get("file_name") + "_" + new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".xlsx" + "\"");
		response.setContentType("application/x-msexcel; charset=UTF-8");
		
		/**
		 * 참고 : jXLS은 엑셀파일 포맷의 템플릿을 이용하여
		 * 엑셀 파일을 손쉽게 생성하기 위한 패키지
		 * (jXLS은 템플릿을 기반으로 최종 엑셀파일을 생성)
		 */
		
		String docRoot = request.getSession().getServletContext().getRealPath("/WEB-INF/excel/");
		logger.info("경로 체크(docRoot) : " + docRoot);
		InputStream is = new BufferedInputStream(new FileInputStream(docRoot + model.get("template")));
		
		XLSTransformer trans = new XLSTransformer();
		Workbook workbook = trans.transformXLS(is, model);
		is.close();
		
		workbook.write(response.getOutputStream());
		
	}

}
