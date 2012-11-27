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
 * Snapshot decorator.
 */
@SuppressWarnings("unused")
public class VMSnapshot extends
        org.ovirt.engine.sdk.entities.Snapshot {

    private HttpProxyBroker proxy;

    private VMSnapshotNics vMSnapshotNics;
    private VMSnapshotDisks vMSnapshotDisks;
    private VMSnapshotCdRoms vMSnapshotCdRoms;


    /**
     * @param proxy HttpProxyBroker
     */
    public VMSnapshot(HttpProxyBroker proxy) {
        this.proxy = proxy;
    }

    /**
     * @return HttpProxyBroker
     */
    private HttpProxyBroker getProxy() {
        return proxy;
    }

    /**
     * Gets the value of the VMSnapshotNics property.
     * 
     * @return
     *     possible object is
     *     {@link VMSnapshotNics }
     *     
     */
    public synchronized VMSnapshotNics getNics() {
        if (this.vMSnapshotNics == null) {
            this.vMSnapshotNics = new VMSnapshotNics(proxy, this);
        }
        return vMSnapshotNics;
    }
    /**
     * Gets the value of the VMSnapshotDisks property.
     * 
     * @return
     *     possible object is
     *     {@link VMSnapshotDisks }
     *     
     */
    public synchronized VMSnapshotDisks getDisks() {
        if (this.vMSnapshotDisks == null) {
            this.vMSnapshotDisks = new VMSnapshotDisks(proxy, this);
        }
        return vMSnapshotDisks;
    }
    /**
     * Gets the value of the VMSnapshotCdRoms property.
     * 
     * @return
     *     possible object is
     *     {@link VMSnapshotCdRoms }
     *     
     */
    public synchronized VMSnapshotCdRoms getCdRoms() {
        if (this.vMSnapshotCdRoms == null) {
            this.vMSnapshotCdRoms = new VMSnapshotCdRoms(proxy, this);
        }
        return vMSnapshotCdRoms;
    }


    /**
     * Performs restore action.
     *
     * @param action
     *
     * @return
     *     {@link Action }
     *
     * @throws ClientProtocolException
     * @throws ServerException
     * @throws IOException
     * @throws JAXBException
     */
    public Action restore(Action action) throws ClientProtocolException,
            ServerException, IOException, JAXBException {
        String url = this.getHref() + "/restore";
        return getProxy().action(url, action, Action.class, Action.class);
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
