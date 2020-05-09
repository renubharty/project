package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.intcomcorp.intcomcorpApplication.dto.request.HostCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.HostGroup;
import com.intcomcorp.intcomcorpApplication.dto.request.HostInterface;
import com.intcomcorp.intcomcorpApplication.dto.request.HostMacro;
import com.intcomcorp.intcomcorpApplication.dto.request.JsHostCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.Template;
import com.intcomcorp.intcomcorpApplication.service.Adaptor;

@Component
public class HostAdaptor implements Adaptor<HostCreate, JsHostCreate> {

	@Override
	public HostCreate adapt(JsHostCreate createHostRequest) {
		HostCreate create = new HostCreate();
		create.setHost(createHostRequest.getHostname());
		HostGroup hostGroup = new HostGroup();
		hostGroup.setGroupid(createHostRequest.getGroupid());
		Set<HostGroup> hostGroups = new HashSet<HostGroup>();
		hostGroups.add(hostGroup);
		create.setHostGroup(hostGroups);
		Set<HostInterface> hostInterfaces = new HashSet<HostInterface>();
		HostInterface hostInterface = new HostInterface();
		BeanUtils.copyProperties(createHostRequest, hostInterface);
		hostInterface.setDns(createHostRequest.getDns());
		hostInterface.setIp(createHostRequest.getIp());
		hostInterface.setMain(Integer.parseInt(createHostRequest.getMain()));
		hostInterface.setPort(createHostRequest.getPort());
		hostInterface.setUseip(Integer.parseInt(createHostRequest.getUseip()));
		hostInterface.setType(Integer.parseInt(createHostRequest.getType()));
		hostInterfaces.add(hostInterface);
		create.setHostInterface(hostInterfaces);
		HostMacro hostMacro = new HostMacro();
		hostMacro.setMacro(createHostRequest.getMacro());
		hostMacro.setValue(createHostRequest.getValue());
		Set<HostMacro> hostMacros = new HashSet<HostMacro>();
		hostMacros.add(hostMacro);
		create.setHostMacro(hostMacros);
		Template template = new Template();
		template.setTemplateid(createHostRequest.getTemplateid());
		Set<Template> templates = new HashSet<Template>();
		templates.add(template);
		create.setTemplates(templates);

		return create;
	}

}
