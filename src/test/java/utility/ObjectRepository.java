package utility;

import utility.constants;

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
	
	public static final String fileUpload=constants.XPATH+"&"+"//input[@id='input-4']/preceding-sibling::span";
	
	public static final String brokenlinks=constants.TAGNAME+"&"+"a";
	
	public static final String AutoamtionRegistercloseIcon=constants.XPATH+"&"+"//span[@class='ui-icon ui-icon-close']";
	public static final String AutoamtionRegisterFirstName=constants.XPATH+"&"+"(//input[@type='text'])[1]";
	public static final String AutoamtionRegisterLastName=constants.XPATH+"&"+"(//input[@type='text'])[2]";
	
	public static final String AutoamtionRegisterAddress=constants.XPATH+"&"+"//textarea[@rows='3']";
	public static final String AutoamtionRegisteremail=constants.XPATH+"&"+"//input[@type='email']";
	public static final String AutoamtionRegisterphonenumber=constants.XPATH+"&"+"//input[@type='tel']";
	public static final String AutoamtionRegisterGenderMale=constants.XPATH+"&"+"(//input[@name='radiooptions'])[1]";
	public static final String AutoamtionRegisterGenderFemale=constants.XPATH+"&"+"(//input[@name='radiooptions'])[2]";
	public static final String AutoamtionRegisterHobbiesCricket=constants.XPATH+"&"+"//input[@value='Cricket']";
	public static final String AutoamtionRegisterHobbiesMovies=constants.XPATH+"&"+"//input[@value='Movies']";
	public static final String AutoamtionRegisterHobbiesHockey=constants.XPATH+"&"+"//input[@value='Hockey']";
	public static final String AutoamtionRegisterLangDrpDwn=constants.XPATH+"&"+"//div[@id='msdd']";
	public static final String AutoamtionRegisterlanguagevalue=constants.XPATH+"&"+"//div[@id='msdd']/following-sibling::div/ul/li";
	public static final String AutoamtionRegisterskills=constants.XPATH+"&"+"//select[@id='Skills']";
	public static final String AutoamtionRegisterCountry=constants.XPATH+"&"+"//select[@id='countries']";
	public static final String AutoamtionRegisterselectCountry=constants.XPATH+"&"+"//span[@id='select2-country-container']/..";
	public static final String AutoamtionRegisterselectcountryTextbox=constants.XPATH+"&"+"//input[@class='select2-search__field']";
	public static final String AutoamtionRegisterDOBYY=constants.XPATH+"&"+"//select[@id='yearbox']";
	public static final String AutoamtionRegisterDOBMM=constants.XPATH+"&"+"//select[@type='text' and @placeholder='Month']";
	public static final String AutoamtionRegisterDOBDD=constants.XPATH+"&"+"//*[@id='daybox']";
	public static final String AutoamtionRegisterfirstpassword=constants.XPATH+"&"+"//input[@id='firstpassword']";
	public static final String AutoamtionRegistersecondpassword=constants.XPATH+"&"+"//input[@id='secondpassword']";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
