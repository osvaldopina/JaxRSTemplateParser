package org.entitatemindex.jaxrs.resource.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.uri.UriResource;
import org.entitatemindex.utils.StringUtils;

public class NodeData {

	private List<JaxrsResource> jaxrsResources = new ArrayList<JaxrsResource>();

	private UriResource uriResource;

	protected NodeData(UriResource uriResource) {
		this.uriResource = uriResource;
	}

	public List<JaxrsResource> getJaxrsResources() {
		return Collections.unmodifiableList(jaxrsResources);
	}

	protected void addJaxrsResource(JaxrsResource jaxrsResource) {
		jaxrsResources.add(jaxrsResource);
	}

	public UriResource getUriResource() {
		return uriResource;
	}

	@Override
	public String toString() {
		StringBuffer tmp = new StringBuffer();

		tmp.append(uriResource);
		if (jaxrsResources.size() > 0) {
			tmp.append("  [");
			boolean first = true;
			for (JaxrsResource jaxrsesource : jaxrsResources) {
				first = StringUtils.addIfNotFirst(first, tmp, ", ");
				tmp.append(jaxrsesource.getHttpMethod());
				tmp.append(" ");
				tmp.append(jaxrsesource.getTemplate());
			}
			tmp.append("]");
		}
		return tmp.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeData other = (NodeData) obj;
		if (uriResource == null) {
			if (other.uriResource != null)
				return false;
		} else if (!uriResource.equals(other.uriResource))
			return false;
		return true;
	}
	
	

}
