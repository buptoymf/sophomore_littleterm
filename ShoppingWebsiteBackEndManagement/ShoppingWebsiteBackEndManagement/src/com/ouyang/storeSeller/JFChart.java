package com.ouyang.storeSeller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class JFChart{

	public static void main(String[] args) 
	{
	    //创建主题样式 ,以下代码用于解决中文乱码问题
	    StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
	    //设置标题字体  
	    standardChartTheme.setExtraLargeFont(new Font("宋体",Font.BOLD,20));  
	    //设置图例的字体  
	    standardChartTheme.setRegularFont(new Font("宋体",Font.PLAIN,15));  
	    //设置轴向的字体  
	    standardChartTheme.setLargeFont(new Font("宋体",Font.PLAIN,15));  
	    //应用主题样式  
	    ChartFactory.setChartTheme(standardChartTheme);
	 
	    // 柱状图数据源
	    DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();
	    
	    
	    barDataSet.addValue(800, "数量", "1月");
	    barDataSet.addValue(600, "数量", "2月");
	    barDataSet.addValue(200, "数量", "3月");
	    barDataSet.addValue(2000, "数量", "4月");
	 
	    //折线图数据源
	    DefaultCategoryDataset lineDataSet = new DefaultCategoryDataset();
	    lineDataSet.addValue(0.2, "销量", "1月");
	    lineDataSet.addValue(0.35, "销量", "2月");
	    lineDataSet.addValue(0.8, "销量", "3月");
	    lineDataSet.addValue(1.8, "销量", "4月");
	    
	    BarRenderer3D barRender = new BarRenderer3D();
	 
	    //展示柱状图数值
	    barRender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    barRender.setBaseItemLabelsVisible(true);
	    barRender.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
	       ItemLabelAnchor.OUTSIDE1, TextAnchor.BASELINE_CENTER)); 
	 
	    //最短的BAR长度，避免数值太小而显示不出
	    barRender.setMinimumBarLength(0.5);     
	 
	    // 设置柱形图上的文字偏离值 
	    barRender.setItemLabelAnchorOffset(10D);
	 
	    //设置柱子的最大宽度 
	    barRender.setMaximumBarWidth(0.03); 
	    barRender.setItemMargin(0.000000005); 
	 
	    LineAndShapeRenderer lineRender = new LineAndShapeRenderer();
	 
	    //展示折线图节点值
	    lineRender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    lineRender.setBaseItemLabelsVisible(true);
	    lineRender.setBasePositiveItemLabelPosition(
	            new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
	 
	    // 设置柱形图上的文字偏离值 
	    lineRender.setItemLabelAnchorOffset(5D);
	 
	    BasicStroke brokenLine = new BasicStroke(2f,//线条粗细  
	              BasicStroke.CAP_SQUARE,           //端点风格  
	              BasicStroke.JOIN_MITER,           //折点风格  
	              8.f,                              //折点处理办法 ,如果要实线把该参数设置为NULL 
	              new float[]{8.0f },               //虚线数组  
	              0.0f);
	 
	    //设置第一条折线的风格
	    lineRender.setSeriesStroke(0,brokenLine);
	 
	    CategoryPlot plot = new CategoryPlot();
	    plot.setDataset(0, barDataSet);
	    plot.setDataset(1, lineDataSet);
	    plot.setRenderer(0, barRender);
	    plot.setRenderer(1, lineRender);
	    plot.setDomainAxis(new CategoryAxis());
	 
	    //设置水平方向背景线颜色  
	    plot.setRangeGridlinePaint(Color.gray);   
	 
	    //设置是否显示水平方向背景线,默认值为true  
	    plot.setRangeGridlinesVisible(true);   
	 
	    //设置垂直方向背景线颜色  
	    plot.setDomainGridlinePaint(Color.gray);   
	 
	    //设置是否显示垂直方向背景线,默认值为true  
	    plot.setDomainGridlinesVisible(true); 
	 
	    //设置图表透明图0.0~1.0范围。0.0为完全透明，1.0为完全不透明。    
	    plot.setForegroundAlpha(0.7F);
	 
	    plot.setRangeAxis(new NumberAxis());
	 
	    //设置Y轴刻度
	    plot.setRangeAxis(1, new NumberAxis());
	 
	    // 设置折线图数据源应用Y轴右侧刻度
	    plot.mapDatasetToRangeAxis(1, 1);
	 
	    for (int i = 0; i < plot.getRangeAxisCount(); i++)
	    {
	        ValueAxis rangeAxis = plot.getRangeAxis(i);
	 
	        //设置最高的一个柱与图片顶端的距离
	        rangeAxis.setUpperMargin(0.25);
	    }
	 
	    CategoryAxis categoryAxis = plot.getDomainAxis();
	 
	    //X轴分类标签以45度倾斜
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	 
	    JFreeChart chart = new JFreeChart(plot);
	    chart.setTitle("数量/销量走势图");
	    chart.setBackgroundPaint(SystemColor.WHITE);
	 
	    ChartFrame chartFrame=new ChartFrame("数量/销量走势图",chart);   
	 
	    //以合适的大小展现图形  
	    chartFrame.pack();
	 
	    //图形是否可见
	    chartFrame.setVisible(true);
	}
}

