package utility;

import com.utility.constants;

public class ObjectRepository {

	public static final String GMOonlineTitle =constants.XPATH+"&"+"//font[contains(text(),'GMO OnLine')]";
	
	public static final String EnterGmoOnlineFlag=constants.XPATH+"&"+"//input[@name='bSubmit']";
	public static final String submitEnterGmoOnline=constants.XPATH+"&"+"//input[@name='bSubmit' and @value='Enter GMO OnLine']";
	public static final String onlineCatalogueHeading=constants.XPATH+"&"+"//*[contains(text(),'currently')]";
	public static final String QTY_BACKPACKS=constants.XPATH+"&"+"//input[@name='QTY_BACKPACKS']";
	public static final String PlaceAnOrder=constants.XPATH+"&"+"//input[@name='bSubmit']";
	public static final String ExternalFrameBackpackOrderUnitprice=constants.XPATH+"&"+"//td[contains(text(),'$ 179.95 ')]";
	public static final String AutalTotalPrice=constants.XPATH+"&"+"//table/tbody/tr[2]/td[5]";
	public static final String AlertConfirmButton=constants.ID+"&"+"confirmButton";
	public static final String ToolsQaAlert=constants.CLASS+"&"+"text-success";
	public static final String promtButton=constants.ID+"&"+"promtButton";
	public static final String promptResultText=constants.XPATH+"&"+"//span[@class='text-success' and @id='promptResult']";
	
	public static final String singleFrame=constants.XPATH+"&"+"//a[@href='#Single']";
	public static final String singleframeTextbox=constants.XPATH+"&"+"//input[@type='text']";
	public static final String frameWithInFrame=constants.XPATH+"&"+"//a[contains(text(),'Iframe with in an Iframe')]";
	public static final String multipleFrames=constants.XPATH+"&"+"//iframe[@src='MultipleFrames.html']";
	
	public static final String singleframInmultipleFrames=constants.XPATH+"&"+"//iframe[@src='SingleFrame.html']";
	public static final String secondframetextbox=constants.XPATH+"&"+"//input[@type='text']";
	
	public static final String webtable=constants.XPATH+"&"+"//div[@class='dataTables_wrapper no-footer']/table/tbody/tr/td[3]";
	
	public static final String rightClick=constants.XPATH+"&"+"//span[contains(text(),'right click me')]";
	public static final String copy=constants.XPATH+"&"+"(//span[contains(text(),'Copy')])[2]";

	public static final String frame=constants.XPATH+"&"+"//iframe";
	public static final String doubleclick=constants.XPATH+"&"+"//span[contains(text(),'Double click the block')]/preceding::div";
	public static final String draggable=constants.ID+"&"+"draggable";
	public static final String droppable=constants.ID+"&"+"droppable";
	
	public static final String fileUpload=constants.XPATH+"&"+"//input[@id='input-4']";
	
	public static final String brokenlinks=constants.TAGNAME+"&"+"a";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
