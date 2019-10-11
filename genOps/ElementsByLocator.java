package genOps;

import org.openqa.selenium.By;

public class ElementsByLocator {

	//**--login page elements--**
	public By loginUserFieldLabel = By.xpath("//form[@id='login']/fieldset/div/label");
	public By loginPassFieldLabel = By.xpath("//form[@id='login']/fieldset/div[2]/label");
	public By usernameField = By.id("login_username");
	public By passwordField = By.id("login_password");
	public By loginBtn = By.id("loginbutton");
	public By serviceBtn = By.id("serviceDesk");
	public By forgotBtn = By.id("forgotButton");
	public By registerBtn = By.id("registerButton");
	public By loginFailureTitle = By.xpath("//*[@id='dialog-message-success']/h1");
	public By loginFailureMsg = By.xpath("//*[@id='dialog-message-success']/label");
	public By frgtPassBtn = By.id("forgotButton");

	//**--forgot password page elements--**
	public By frgtEmailField = By.id("forgot_password_email");
	public By frgtPassSubmitBtn = By.id("forgotPasswordButton");
	public By frgtSuccessMsg = By.xpath("//*[@id='dialog-message-success']/label");
	public By frgtFailureMsg = By.xpath("//*[@id='dialog-message-success']/h1");

	//**--register page elements--**
	public By signUpLabel = By.xpath("//*[@id='register']/h1");
	public By regUserFieldLabel = By.xpath("//*[@id='register']/fieldset/div[1]/label");
	public By regEmailFieldLabel = By.xpath("//*[@id='register']/fieldset/div[2]/label");
	public By regUserField = By.id("register_username");
	public By regEmailField = By.id("register_email");
	public By signUpBtn = By.id("signupButton");
	public By regSuccessMsg = By.xpath("//*[@id='dialog-message-success']/label");
	public By regMsgBoxTitle = By.xpath("//*[@id='dialog-message-success']/h1");
	public By regMsgBoxText = By.xpath("//*[@id='dialog-message-success']/label");
	public By regMsgBoxOkBtn = By.cssSelector("#dialog-message-success > button > span");
	public By signUpSuccessOkBtn = By.id("signupSuccessButton");
	public By regCancelBtn = By.id("signupCancelButton");

	//**--home screen elements--**
	public By xpathToLogout = By.xpath("/html/body/div[1]/div[3]/ul[1]/li[9]");
	public By mapWindowHeader = By.id("Header_0");
	public By mapWindowMax = By.id("map_max_window_0");
	public By mapWindowRestoreDown = By.id("map_min_window_0");
	public By windowsMenu = By.id("windowsDrop");
	public By searchBox = By.id("searchbox_0");
	public By autoCompleteSuggestion = By.className("autocomplete-suggestion");
	public By mapSettingsBtn = By.id("map_settings_open_button_0");
	public By zoomInBtn =By.xpath("//a[@title='Zoom in']");
	public By zoomOutBtn = By.xpath("//a[@title='Zoom out']");
	public By zoomLevelValue = By.xpath("//*[@id='map_0']/div[3]/div[1]/div[2]");
	public String xPathToTools = "//*[@id='map_0']/div[3]/div[1]/div[3]/div[1]/div/a";
	public By availableTools = By.xpath(xPathToTools);
	public By layerSelector = By.xpath("//*[@id='map_0']/div[3]/div[2]/div[2]/a");
	public String strXpathToAvailableLayers = "//*[@id='overlays_0']/option";
	public By xpathAvailableLayers = By.xpath(strXpathToAvailableLayers);
	public By layeNameBottomRightMap = By.xpath("//*[@id='map_0']/div[3]/div[4]/div[2]");
	public By streeViewIcon = By.id("streetView");
	public By streeViewContextMenu = By.xpath("//*[@id='map_0']/div[4]/a[7]");
	public By birdEyeContextMenu = By.xpath("//*[@id='map_0']/div[4]/a[8]");
	public By settingsBtnFooter = By.xpath("//*[@id='settings-footer-0']");
	public By mapWindowCrossBtn = By.xpath("//*[@id='Header_0']/div[2]/div");
	public By moreInfoIcon = By.xpath("//*[@id='moreInfo']/i");
	public By showInfoContextMenu = By.xpath("//*[@id='map_0']/div[4]/a[1]");
	public By streeViewContextMenu1 = By.xpath("//*[@id='map_0']/div[4]/a[4]");
	public By AddtoTerrainProfileContextMenu = By.xpath("//*[@id='map_0']/div[4]/a[9]");
	public By minRestoreMapWindow = By.xpath("//*[@id='Header_0']/div[3]/div");
	public By siteInfoIcon = By.xpath("//*[@id='moreInfo']/i");
	public By siteContextMenuCloseBtn = By.xpath("//*[@id='map_0']/div[2]/div[2]/div[4]/div/a");
	public By toolsLinkInMenuBar = By.xpath("//*[@id='toolsDrop']");

	//**--settings pop-up elements--**
	public By settingsPopUpTitle = By.xpath("//*[@id='map_settings_window_0']/div/div[1]/div[1]");
	public String xPathToSetingsPopUpTabs = "//*[@id='map_settings_tabs_0']/div[1]/ul/li";
	public By setingsPopUpTabs = By.xpath(xPathToSetingsPopUpTabs);
	public By nextTabScrollBtn = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/div[2]");
	public By settingsPopUpCloseBtn = By.xpath("//*[@id='map_settings_window_0']/div/div[1]/div[2]/div");

	public By generalTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[1]");
	public By layersTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[2]");
	public By driveTestTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[3]");
	public By benchMarkingTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[4]");
	public By kpiTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[5]");
	public By kpiDTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[6]");
	public By kpiCTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[7]");
	public By multiTechnologyTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[8]");
	public By capacityOffenderTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[9]");
	public By monitoringTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[10]");
	public By neighborListingTab = By.xpath("//*[@id='map_settings_tabs_0']/div[1]/ul/li[11]");

	//--general tab elements
	public By generalShowToolsCheckBox = By.id("showTools_0");
	public By generalDisableClusteringCheckBox  = By.xpath("//*[@class='btn btn-default btn-xs dropdown-toggle']");
	public By generalShowToolsLabel = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/p[1]");
	public By generalDisableClusteringLabel = By.cssSelector("#map_settings_tabs_0 > div.jqx-tabs-content.jqx-widget-content.jqx-rc-b > div:nth-child(1) > div > span:nth-child(1)");
	public By generalDisableClusterDropdown = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/div/span[2]/div");
	public String xpathToDisableClusterDropdown = "//*[@id='map_settings_tabs_0']/div[2]/div[1]/div/span[2]/div/ul/li";
	public By generalDisableClusterDropdownVal = By.xpath(xpathToDisableClusterDropdown);

	//--layer tab elements
	public By addCustomLayerBtn = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[2]/div[1]/div[2]/button[1]");
	public By addCustomLayerheader = By.xpath("/html/body/div[6]/div/div/div[1]/h4");
	public By layerNameLabel = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[1]/label");
	public By layerNameTextBox = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[1]/div/input");		
	public By idColumnLabel = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[2]/label");
	public By idColumnTextBox = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[2]/div/input");
	public By pointDataLabel = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[3]/label[1]");
	public By pointDataCheckBox = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[3]/div[1]/input");
	public By publicLayerLabel = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[3]/label[2]");
	public By publicLayerCheckBox = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[3]/div[2]/input");
	public By fileInputLabel = By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/form/div[4]/label");
	public By fileInputUploadfield = By.className("file_input0");
	public By saveBtn = By.xpath("/html/body/div[6]/div/div/div[3]/button[2]");
	public By closeBtn = By.xpath("/html/body/div[6]/div/div/div[3]/button[1]");
	public By crossBtn = By.xpath("/html/body/div[6]/div/div/div[1]/button/span");
	public By addPolygonLayerBtn = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[2]/div[1]/div[2]/button[2]"); 
	public By addPolygonLayerheader = By.xpath("/html/body/div[7]/div/div/div[1]/h4"); //--add layer pop-up elements started--
	public By polygonlayerNameLabel = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[1]/label");
	public By polygonlayerNameTextBox = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[1]/div/input");
	public By geomColumnLabel = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[2]/label");
	public By geomColumnTextBox = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[2]/div/input");
	public By polygonPublicLayerLabel = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[3]/label");
	public By polygonPublicLayerCheckBox = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[3]/div/input");
	public By polygonFileInputLabel = By.xpath("/html/body/div[7]/div/div/div[2]/div/div[1]/form/div[4]/label");
	public By polygonFileInputUploadfield = By.className("poly-file_input0");
	public By polygonSaveBtn = By.xpath("/html/body/div[7]/div/div/div[3]/button[2]");
	public By polygonCloseBtn = By.xpath("/html/body/div[7]/div/div/div[3]/button[1]");
	public By polygonCrossBtn = By.xpath("/html/body/div[7]/div/div/div[1]/button/span");

	//--drive test elements
	public By nameLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[1]/label");
	public By nameTextBox = By.xpath("//*[@id='dt_settings_name_0']");
	public By marketLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[2]/label");
	public By marketField = By.xpath("//*[@id='dt_settings_markets_0']/button");
	public By technologyLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[3]/label");
	public By technologyField = By.xpath("//*[@id='dt_settings_technology_0']/button");
	public By EVDOTechnology = By.xpath("//*[@id='dt_settings_technology_0']/ul/li[1]");
	public By LTETechnology = By.xpath("//*[@id='dt_settings_technology_0']/ul/li[2]");
	public By voiceTechnology = By.xpath("//*[@id='dt_settings_technology_0']/ul/li[3]");
	public By typeLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[4]/label");
	public By typeField = By.xpath("//*[@id='dt_settings_drive_test_types_0']/button");
	public By dateLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[5]/label");
	public By dateField = By.xpath("//*[@id='dt_settings_dates_0']/button");
	public By parameterLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[6]/label");
	public By parameterField = By.xpath("//*[@id='dt_settings_parameters_0']/button");
	public By autoThresholdsLabel = By.xpath("//*[@id='dt_settings_form_0']/div[1]/div[7]/label");
	public By autoThresholdsField = By.xpath("//*[@id='dt_settings_threshold_autopopulate_0']/button");
	public By horizontalOffsetLabel = By.xpath("//*[@id='dt_settings_form_0']/div[2]/div[1]/label");
	public By horizontalOffsetField = By.xpath("//*[@id='dt_settings_offset_hor_0']");
	public By verticalOffsetLabel = By.xpath("//*[@id='dt_settings_form_0']/div[2]/div[2]/label");
	public By verticalOffsetField = By.xpath("//*[@id='dt_settings_offset_ver_0']");
	public By submitBtn = By.xpath("//*[@id='dt_settings_form_0']/div[2]/div[4]/div/button");

	//--benchmarking tab elements
	public By nameLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[1]/label");
	public By nameTextBoxBenchmarking = By.xpath("//*[@id='bm_settings_name_0']");
	public By marketLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[2]/label");
	public By marketFieldBenchmarking = By.xpath("//*[@id='bm_settings_markets_0']/button");
	public By technologyLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[3]/label");
	public By technologyFieldBenchmarking = By.xpath("//*[@id='bm_settings_technology_0']/button");
	public By EVDOTechnologyBenchmarking = By.xpath("//*[@id='bm_settings_technology_0']/ul/li[1]");
	public By LTETechnologyBenchmarking = By.xpath("//*[@id='bm_settings_technology_0']/ul/li[2]");
	public By voiceTechnologyBenchmarking = By.xpath("//*[@id='bm_settings_technology_0']/ul/li[3]");
	public By typeLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[4]/label");
	public By typeFieldBenchmarking = By.xpath("//*[@id='bm_settings_type_0']/button");
	public By dateLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[5]/label");
	public By dateFieldBenchmarking = By.xpath("//*[@id='bm_settings_dates_0']/button");
	public By wirelessCarrierLabellBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[6]/label");
	public By wirelessCarrierFieldBenchmarking = By.xpath("//*[@id='bm_settings_wireless_0']/button");
	public By parameterLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[7]/label");
	public By parameterFieldBenchmarking = By.xpath("//*[@id='bm_settings_parameters_0']/button");
	public By autoThresholdsLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[1]/div[8]/label");
	public By autoThresholdsparameterFieldBenchmarking = By.xpath("//*[@id='bm_settings_threshold_autopopulate_0']/button");
	public By horizontalOffsetLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[2]/div[1]/label");
	public By horizontalOffsetFieldBenchmarking = By.xpath("//*[@id='bm_settings_offset_hor_0']");
	public By verticalOffsetLabelBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[2]/div[2]/label");
	public By verticalOffsetparameterFieldBenchmarking = By.xpath("//*[@id='bm_settings_offset_ver_0']");
	public By submitBtnBenchmarking = By.xpath("//*[@id='bm_settings_form_0']/div[2]/div[4]/div/button");

	//--kpi tab elements
	public By kpiTechLabel = By.xpath("//*[@id='kpi_settings_0']/table/tbody/tr[1]/td[1]");           
	public By kpiStartDateLabel = By.xpath("//*[@id='kpi_settings_0']/table/tbody/tr[2]/td[1]");      
	public By kpiEndDateLabel = By.xpath("//*[@id='kpi_settings_0']/table/tbody/tr[3]/td[1]");        
	public By kpiKpisLabel = By.xpath("//*[@id='kpi_settings_0']/table/tbody/tr[4]/td[1]");       
	public By kpiStartDateField = By.xpath("//*[@id='kpi_settings_0']//*[@id='ksd_dd_0']");   
	public By kpiEndDateField = By.xpath("//*[@id='kpi_settings_0']//*[@id='ksd_end_dd_0']");  
	public String xpathtechFieldKpi = "//*[@id='kpi_settings_0']//*[@id='kpi_settings_technologies_0']/select/option";
	public By kpiTechField = By.xpath(xpathtechFieldKpi);
	public String xpathKpisFieldKpi = "//*[@id='kpi_settings_0']//*[@id='kpi_settings_kpi_list_0']/select/option";
	public By kpiKpisField = By.xpath(xpathKpisFieldKpi);         
	public By kpiThresholdSelector = By.id("kpi_threshold_num_0");
	public By kpiSaveBtn = By.xpath("//*[@id='kpi_settings_0']//*[@id='kpi_settings_run_button_0']");

	//--kpi-D tab elements
	public By kpiDTechLabel = By.xpath("//*[@id='kpi-d_settings_0']/table/tbody/tr[1]/td[1]"); 
	public String xpathtechFieldKpiD = "//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_technologies_0']/select/option";
	public By kpiDTechField = By.xpath(xpathtechFieldKpiD);
	public By kpiDStartDateLabel1 = By.xpath("//*[@id='kpi-d_settings_0']/table/tbody/tr[2]/td[1]");      
	public By kpiDEndDateLabel1 = By.xpath("//*[@id='kpi-d_settings_0']/table/tbody/tr[3]/td[1]");        
	public By kpiDStartDateField1 = By.xpath("//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_kpi_date1_0']/input");   
	public By kpiDEndDateField1 = By.xpath("//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_kpi_end_date1_0']/input");  
	public By kpiDStartDateLabel2 = By.xpath("//*[@id='kpi-d_settings_0']/table/tbody/tr[4]/td[1]");    
	public By kpiDEndDateLabel2 = By.xpath("//*[@id='kpi-d_settings_0']/table/tbody/tr[5]/td[1]");        
	public By kpiDStartDateField2 = By.xpath("//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_kpi_date2_0']/input");   
	public By kpiDEndDateField2 = By.xpath("//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_kpi_end_date2_0']/input"); 
	public By kpiDKpisLabel = By.xpath("//*[@id='kpi-d_settings_0']/table/tbody/tr[6]/td[1]");  
	public String xpathKpisFieldKpiD = "//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_kpi_list_0']/select/option";
	public By kpiDKpisField = By.xpath(xpathKpisFieldKpiD); 
	public By kpiDThresholdSelector = By.id("kpi-d_threshold_num_0");
	public By kpiDSaveBtn = By.xpath("//*[@id='kpi-d_settings_0']//*[@id='kpi-d_settings_run_button_0']"); 

	//--kpi-C tab elements
	public String xpathtechFieldKpiC = "//*[@id='kpi_settings_0']//*[@id='kpi-c_settings_technologies_0']/select/option";
	public By kpiCTechField = By.xpath(xpathtechFieldKpiD);
	public By kpiCStartDateField = By.xpath("//*[@id='kpi_settings_0']//*[@id='kpi-c_settings_kpi_date_0']/input");   
	public By kpiCEndDateField = By.xpath("//*[@id='kpi_settings_0']//*[@id='kpi-c_settings_kpi_end_date_0']/input");  
	public String xpathKpisFieldKpiC = "//*[@id='kpi_settings_0']//*[@id='kpi-c_settings_kpi_list_0']/select/option";
	public By kpiCKpisField = By.xpath(xpathKpisFieldKpiD); 
	public By kpiCThresholdSelector = By.id("kpi-c_threshold_num_0");
	public By kpiCSaveBtn = By.xpath("//*[@id='kpi_settings_0']//*[@id='kpi-c_settings_run_button_0']");

	//--multi technology elements
	public By thresholdsMultiTechnology = By.xpath("//*[@id='mws_threshold_num_0']");
	public By saveButton = By.xpath("//*[@id='mws_settings_run_button_0']");

	//--capacity offender elements
	public By technology = By.xpath("//*[@id='capoff_0']");
	public By technologyLTE = By.xpath("//*[@id='capoff_0']/option[1]");
	public By technologyEVDO = By.xpath("//*[@id='capoff_0']/option[2]");
	public By saveBtnCapOffender = By.xpath("//*[@id='capoff_settings_run_button_0']");

	//--monitoring elements
	public By dropdownFieldOnMonitoringTab = By.xpath("//*[@id='monitoring_type_0']");
	public By deviantsMonitoring = By.xpath("//*[@id='monitoring_type_0']/option[1]");
	public By alarmsMonitoring = By.xpath("//*[@id='monitoring_type_0']/option[2]");
	public By tramsMonitoring = By.xpath("//*[@id='monitoring_type_0']/option[3]");
	public By monitoringTechnologyLabel = By.xpath("//*[@id='monitoring_settings_0']/table/tbody/tr[2]/td/div/div[1]/p");
	public By monitoringTechnologyField = By.xpath("//*[@id='monitoring_tech_0']");
	public By monitoringTechnologyLTE = By.xpath("//*[@id='monitoring_tech_0']/option[2]");
	public By monitoringTechnologyLTETDD = By.xpath("//*[@id='monitoring_tech_0']/option[3]");
	public By monitoringTechnologyEVDO = By.xpath("//*[@id='monitoring_tech_0']/option[4]");
	public By monitoringTechnologyCDMA1X = By.xpath("//*[@id='monitoring_tech_0']/option[5]");
	public By monitoringTechnologyCDMAVoice = By.xpath("//*[@id='monitoring_tech_0']/option[6]");
	public By monitoringStartingDateLabel = By.xpath("//*[@id='monitoring_settings_0']/table/tbody/tr[2]/td/div/div[2]/p");
	public By monitoringStartingDateField = By.xpath("//*[@id='mon_start_dd_0']");
	public By monitoringEndingDateLabel = By.xpath("//*[@id='monitoring_settings_0']/table/tbody/tr[2]/td/div/div[3]/p");
	public By monitoringEndingDateField = By.xpath("//*[@id='mon_end_dd_0']");
	public By monitoringParameterLabel = By.xpath("//*[@id='monitoring_settings_title_0']");
	public By monitoringAllParametersCheckBox = By.xpath("//*[@id='monitoring_params_all_0']");


}
