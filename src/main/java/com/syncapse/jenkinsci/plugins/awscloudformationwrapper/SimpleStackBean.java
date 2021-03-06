package com.syncapse.jenkinsci.plugins.awscloudformationwrapper;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.AbstractProject;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

import java.io.IOException;

import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * 
 * 
 * @author erickdovale
 * 
 */
public class SimpleStackBean extends AbstractDescribableImpl<SimpleStackBean> {

	/**
	 * The name of the stack.
	 */
	private String stackName;

	/**
	 * The access key to call Amazon's APIs
	 */
	private TemplateLocation awsTemplateLocation;

	/**
	 * The access key to call Amazon's APIs
	 */
	private String awsAccessKey;

	/**
	 * The secret key to call Amazon's APIs
	 */
	private String awsSecretKey;

	/**
	 * The AWS Region to work against.
	 */
	private Region awsRegion;

	@DataBoundConstructor
	public SimpleStackBean(String stackName, TemplateLocation awsTemplateLocation, 
			String awsAccessKey,
			String awsSecretKey, Region awsRegion) {
		this.stackName = stackName;
		this.awsTemplateLocation = awsTemplateLocation != null ? awsTemplateLocation : TemplateLocation.getDefault();
		this.awsAccessKey = awsAccessKey;
		this.awsSecretKey = awsSecretKey;
		this.awsRegion = awsRegion != null ? awsRegion : Region.getDefault();
	}

	public String getStackName() {
		return stackName;
	}

	public TemplateLocation getAwsTemplateLocation() {
		return awsTemplateLocation;
	}
	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public String getParsedAwsAccessKey(EnvVars env) {
		return env.expand(getAwsAccessKey());
	}

	public String getParsedAwsSecretKey(EnvVars env) {
		return env.expand(getAwsSecretKey());
	}

	public Region getAwsRegion() {
		return awsRegion;
	}

	@Extension
	public static final class DescriptorImpl extends
			Descriptor<SimpleStackBean> {

		@Override
		public String getDisplayName() {
			return "Cloud Formation";
		}

		public FormValidation doCheckStackName(
				@AncestorInPath AbstractProject<?, ?> project,
				@QueryParameter String value) throws IOException {
			if (0 == value.length()) {
				return FormValidation.error("Empty stack name");
			}
			return FormValidation.ok();
		}
		public ListBoxModel doFillawsTemplateLocationItems() {
			ListBoxModel items = new ListBoxModel();
			for (TemplateLocation templateLocation : TemplateLocation.values()) {
				items.add(templateLocation.readableName, templateLocation.name());
			}
			return items;
		}
		public FormValidation doCheckAwsAccessKey(
				@AncestorInPath AbstractProject<?, ?> project,
				@QueryParameter String value) throws IOException {
			if (0 == value.length()) {
				return FormValidation.error("Empty aws access key");
			}
			return FormValidation.ok();
		}

		public FormValidation doCheckAwsSecretKey(
				@AncestorInPath AbstractProject<?, ?> project,
				@QueryParameter String value) throws IOException {
			if (0 == value.length()) {
				return FormValidation.error("Empty aws secret key");
			}
			return FormValidation.ok();
		}

		public ListBoxModel doFillAwsRegionItems() {
			ListBoxModel items = new ListBoxModel();
			for (Region region : Region.values()) {
				items.add(region.readableName, region.name());
			}
			return items;
		}
	}

}