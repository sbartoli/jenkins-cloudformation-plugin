package com.syncapse.jenkinsci.plugins.awscloudformationwrapper;

public enum TemplateLocation {
	Template_File("File"),
	Template_URL("URL");
	
	public final String readableName;
	
	private TemplateLocation(String readdableName){
		this.readableName = readdableName;
	}

	public static TemplateLocation getDefault() {
		return Template_File;
	}
	
}
