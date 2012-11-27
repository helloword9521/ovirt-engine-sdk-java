//
// Copyright (c) 2012 Red Hat, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//           http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

// *********************************************************************
// ********************* GENERATED CODE - DO NOT MODIFY ****************
// *********************************************************************

package org.ovirt.engine.sdk.decorators;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.ovirt.engine.sdk.entities.Action;
import org.ovirt.engine.sdk.entities.Response;
import org.ovirt.engine.sdk.exceptions.ServerException;
import org.ovirt.engine.sdk.utils.HttpHeaderUtils;
import org.ovirt.engine.sdk.web.HttpProxyBroker;

/**
 * Group decorator.
 */
@SuppressWarnings("unused")
public class Group extends
        org.ovirt.engine.sdk.entities.Group {

    private HttpProxyBroker proxy;

    private GroupRoles groupRoles;
    private GroupPermissions groupPermissions;
    private GroupTags groupTags;


    /**
     * @param proxy HttpProxyBroker
     */
    public Group(HttpProxyBroker proxy) {
        this.proxy = proxy;
    }

    /**
     * @return HttpProxyBroker
     */
    private HttpProxyBroker getProxy() {
        return proxy;
    }

    /**
     * Gets the value of the GroupRoles property.
     * 
     * @return
     *     possible object is
     *     {@link GroupRoles }
     *     
     */
    public synchronized GroupRoles getRoles() {
        if (this.groupRoles == null) {
            this.groupRoles = new GroupRoles(proxy, this);
        }
        return groupRoles;
    }
    /**
     * Gets the value of the GroupPermissions property.
     * 
     * @return
     *     possible object is
     *     {@link GroupPermissions }
     *     
     */
    public synchronized GroupPermissions getPermissions() {
        if (this.groupPermissions == null) {
            this.groupPermissions = new GroupPermissions(proxy, this);
        }
        return groupPermissions;
    }
    /**
     * Gets the value of the GroupTags property.
     * 
     * @return
     *     possible object is
     *     {@link GroupTags }
     *     
     */
    public synchronized GroupTags getTags() {
        if (this.groupTags == null) {
            this.groupTags = new GroupTags(proxy, this);
        }
        return groupTags;
    }


    /**
     * Deletes object.
     *
     * @return
     *     {@link Response }
     *
     * @throws ClientProtocolException
     * @throws ServerException
     * @throws IOException
     * @throws JAXBException
     */
    public Response delete() throws ClientProtocolException,
            ServerException, IOException, JAXBException {
        String url = this.getHref();
        return getProxy().delete(url, Response.class);
    }

}
