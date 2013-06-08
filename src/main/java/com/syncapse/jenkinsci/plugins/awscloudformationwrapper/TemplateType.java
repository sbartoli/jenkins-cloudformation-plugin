package com.syncapse.jenkinsci.plugins.awscloudformationwrapper;

public enum TemplateType {
	Template_File("Template_File", ""),
	Template_URL("Template_URL", "");
	
	public final String readableName;
	public final String endPoint;
	
	private TemplateType(String readdableName, String endPoint){
		this.readableName = readdableName;
		this.endPoint = endPoint;
	}

	public static TemplateType getDefault() {
		return Template_File;
	}
	
}
