import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import ij.plugin.*;
import ij.measure.*;
import ij.io.FileSaver;

public class Counting_Array implements PlugIn, DialogListener {
	private static String[] colors = {"Red","Green","Blue","Magenta","Cyan","Yellow","Orange","Black","White"};
	private static String color = "Cyan";
	private final static int LINES=0, HLINES=1, CROSSES=2, POINTS=3, NONE=4;
	//private static String[] types = {"Lines","Horizontal Lines", "Crosses", "Points", "None"};
	//private static String type = types[LINES];
	private static double areaPerPoint = 0.0;
	//private Random random = new Random(System.currentTimeMillis());	
	private ImagePlus imp;
	private int rectWidth = 0, rectHeight = 0;
	private int xcenter = 0, ycenter = 0;
	private int xoffset = 0,yoffset = 0;
	private int xspace = 0, yspace = 0;
//	private int linesV, linesH;
	private int width = 0, height = 0;
	private double pixelWidth=1.0, pixelHeight=1.0;
	private String units = "pixels";
	private String path;

	public void run(String arg) {
		if (IJ.versionLessThan("1.43u"))	 		return;
		imp = IJ.getImage();
		path = imp.getOriginalFileInfo().directory;
		showDialog();
	}
		
	void showGrid(Overlay overlay) {
		if (overlay==null)
			imp.setOverlay(null);
		else
			imp.setOverlay(overlay);
	}

	void drawArray()  {
		
		Overlay overlay = new Overlay();
		
		overlay.add(new Roi((int)xcenter-rectWidth/2,				(int)ycenter-rectHeight/2,					rectWidth,rectHeight));  	//Center

		overlay.add(new Roi((int)xcenter+(xspace*0)-rectWidth/2,	(int)ycenter-(yspace*1)-rectHeight/2,		rectWidth,rectHeight));		//U1
		overlay.add(new Roi((int)xcenter+(xspace*0)-rectWidth/2,	(int)ycenter-(yspace*2)-rectHeight/2,		rectWidth,rectHeight));  	//U2
		overlay.add(new Roi((int)xcenter+(xspace*0)-rectWidth/2,	(int)ycenter+(yspace*1)-rectHeight/2,		rectWidth,rectHeight));  	//D1
		overlay.add(new Roi((int)xcenter+(xspace*0)-rectWidth/2,	(int)ycenter+(yspace*2)-rectHeight/2,		rectWidth,rectHeight));  	//D2

		overlay.add(new Roi((int)xcenter-(xspace*1)-rectWidth/2,	(int)ycenter-(yspace*0)-rectHeight/2,		rectWidth,rectHeight));  	//L1
		overlay.add(new Roi((int)xcenter-(xspace*2)-rectWidth/2,	(int)ycenter-(yspace*0)-rectHeight/2,		rectWidth,rectHeight));  	//L2
		overlay.add(new Roi((int)xcenter+(xspace*1)-rectWidth/2,	(int)ycenter-(yspace*0)-rectHeight/2,		rectWidth,rectHeight));  	//R1
		overlay.add(new Roi((int)xcenter+(xspace*2)-rectWidth/2,	(int)ycenter-(yspace*0)-rectHeight/2,		rectWidth,rectHeight));  	//R2

		overlay.add(new Roi((int)xcenter-(xspace*0.75)-rectWidth/2,	(int)ycenter-(yspace*0.75)-rectHeight/2,	rectWidth,rectHeight));  	//UL1
		overlay.add(new Roi((int)xcenter-(xspace*1.5)-rectWidth/2,	(int)ycenter-(yspace*1.5)-rectHeight/2,		rectWidth,rectHeight));  	//UL2
		overlay.add(new Roi((int)xcenter-(xspace*0.75)-rectWidth/2,	(int)ycenter+(yspace*0.75)-rectHeight/2,	rectWidth,rectHeight));  	//DL1
		overlay.add(new Roi((int)xcenter-(xspace*1.5)-rectWidth/2,	(int)ycenter+(yspace*1.5)-rectHeight/2,		rectWidth,rectHeight));  	//DL2

		overlay.add(new Roi((int)xcenter+(xspace*0.75)-rectWidth/2,	(int)ycenter-(yspace*0.75)-rectHeight/2,	rectWidth,rectHeight));  	//UR1
		overlay.add(new Roi((int)xcenter+(xspace*1.5)-rectWidth/2,	(int)ycenter-(yspace*1.5)-rectHeight/2,		rectWidth,rectHeight));  	//UR2
		overlay.add(new Roi((int)xcenter+(xspace*0.75)-rectWidth/2,	(int)ycenter+(yspace*0.75)-rectHeight/2,	rectWidth,rectHeight));  	//DR1
		overlay.add(new Roi((int)xcenter+(xspace*1.5)-rectWidth/2,	(int)ycenter+(yspace*1.5)-rectHeight/2,		rectWidth,rectHeight));  	//DR2
		
		for(int i = 0; i<overlay.size(); i++) {	
			overlay.get(i).setStrokeWidth(6);
		}
		
		overlay.setStrokeColor(getColor());
		
		showGrid(overlay);
	}
	
	
	void showDialog() {
		width = imp.getWidth();
		height = imp.getHeight();
		Calibration cal = imp.getCalibration();
		int places;
		if (cal.scaled()) {
			pixelWidth = cal.pixelWidth;
			pixelHeight = cal.pixelHeight;
			units = cal.getUnits();
			places = 2;
		} else {
			pixelWidth = 1.0;
			pixelHeight = 1.0;
			units = "pixels";
			places = 0;
		}
		if (areaPerPoint==0.0)
			areaPerPoint = (width*cal.pixelWidth*height*cal.pixelHeight)/100.0;
		
		xspace = width / 5;
		yspace = height / 5;
		
		if(xspace<yspace) yspace = xspace;
		else xspace = yspace;
		
		ImageWindow win = imp.getWindow();
		GenericDialog gd = new GenericDialog("Array Overlay for Counts");
		gd.addNumericField("Area per Region:", areaPerPoint, places, 7, units+"^2");
		gd.addChoice("Color:", colors, color);
		//gd.addCheckbox("Random Offset", randomOffset);
		//gd.addNumericField("Offset X:",xoffset,2,6, units);
		//gd.addNumericField("Offset Y:",yoffset,2,6, units);
		//gd.addNumericField("Spacing X:",xspace,2,6, units);
		//gd.addNumericField("Spacing Y:",yspace,2,6, units);
		gd.addSlider("Offset X",-width*pixelWidth/3,width*pixelWidth/3,xoffset);
		gd.addSlider("Offset Y",-height*pixelWidth/3,height*pixelWidth/3,xoffset);
		gd.addSlider("Spacing X",0.0,width*pixelWidth/4.5,xspace);
		gd.addSlider("Spacing Y",0.0,height*pixelWidth/4.5,yspace);
		
		
		gd.addDialogListener(this);
		dialogItemChanged(gd,null);
		gd.showDialog();
		
		if (gd.wasOKed()) {
			ImagePlus burnin = imp.flatten();
			burnin.setTitle(imp.getShortTitle()+"_count");
			burnin.show();
			//new FileSaver(burnin).saveAsTiff(path+img.getShortTitle()+".tif"));
			new FileSaver(burnin).saveAsTiff();
		}
		showGrid(null);
		gd = null;
	}

	public boolean dialogItemChanged(GenericDialog gd, AWTEvent e) {
		

		
		width = imp.getWidth();
		height = imp.getHeight();
		areaPerPoint = gd.getNextNumber();
		xoffset = (int)(gd.getNextNumber()/pixelWidth);
		yoffset = (int)(gd.getNextNumber()/pixelHeight);
		xspace = (int)(gd.getNextNumber()/pixelWidth);
		yspace = (int)(gd.getNextNumber()/pixelHeight);
		color = gd.getNextChoice();
		
		double minArea= (width*height)/50000.0;
;
		if (minArea<16)	minArea = 16.0;
		
		if (areaPerPoint/(pixelWidth*pixelHeight)<minArea) {
			String err = "\"Area per Region\" too small";
			if (gd.wasOKed())
				IJ.error("Grid", err);
			else
				IJ.showStatus(err);
			return true;
		}
		double rectSize_um = Math.sqrt(areaPerPoint);
		rectWidth = (int)(rectSize_um/pixelWidth);
		rectHeight = (int)(rectSize_um/pixelHeight);
		xcenter = (int)(width/2 + xoffset);
		ycenter = (int)(height/2 + yoffset);

		
		
		if (gd.invalidNumber())
			return true;
		else
			drawArray();
        	return true;
	}

	Color getColor() {
		Color c = Color.cyan;
		if (color.equals(colors[0])) c = Color.red;
		else if (color.equals(colors[1])) c = Color.green;
		else if (color.equals(colors[2])) c = Color.blue;
		else if (color.equals(colors[3])) c = Color.magenta;
		else if (color.equals(colors[4])) c = Color.cyan;
		else if (color.equals(colors[5])) c = Color.yellow;
		else if (color.equals(colors[6])) c = Color.orange;
		else if (color.equals(colors[7])) c = Color.black;
		else if (color.equals(colors[8])) c = Color.white;
		return c;
	}
}
