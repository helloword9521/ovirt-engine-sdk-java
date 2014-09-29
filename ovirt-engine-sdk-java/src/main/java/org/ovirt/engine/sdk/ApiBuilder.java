//
// Copyright (c) 2014 Red Hat, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.ovirt.engine.sdk;

import java.io.IOException;

import org.ovirt.engine.sdk.exceptions.ServerException;
import org.ovirt.engine.sdk.exceptions.UnsecuredConnectionAttemptError;

/**
 * This class is intended to simplify the creation of a {@link org.ovirt.engine.sdk.Api} object. Instead of using a long
 * list of constructor arguments, most of then with value {@code null},  this can be used with a fluent style, for
 * example:
 *
 * <pre>
 * Api api = new ApiBuilder()
 *     .url("https://ovirt.example.com/ovirt-engine/api")
 *     .user("admin@internal")
 *     .password("******")
 *     .debug(true)
 *     .build();
 * </pre>
 */
@SuppressWarnings("unused")
public class ApiBuilder {
    private String url;
    private String user;
    private String password;
    private String sessionId;
    private Integer requestTimeout;
    private Integer sessionTimeout;
    private Boolean persistentAuth;
    private Boolean noHostVerification;
    private String keyStorePath;
    private String keyStorePassword;
    private Boolean filter;
    private Boolean debug;
    private Boolean kerberos;

    /**
     * Sets the URL that will be used to build the API object, for example
     * {@code https://ovirt.example.com/ovirt-engine/api}.
     */
    public ApiBuilder url(String url) {
        this.url = url;
        return this;
    }

    /**
     * Sets the name of the user that will be used to authenticate to the server, for example {@code admin@internal}.
     * This should usually be used together with the {@link #password(String)} method, as most authentication methods
     * require both an user name and a password.
     */
    public ApiBuilder user(String user) {
        this.user = user;
        return this;
    }

    /**
     * Sets the password that will be used to authenticate to the server.
     */
    public ApiBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Sets the session identifier that will be used when sending requests to the server. This is useful if a session
     * has been established (and authenticated) before and you want to reuse it without authenticating again.
     */
    public ApiBuilder sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * Sets the timeout (in seconds) using when waiting for responses from the server. If a request takes longer than
     * this it will be cancelled and an exception will be thrown.
     */
    public ApiBuilder requestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    /**
     * Sets the timeout (in minutes) of the session with the server. If the session is idle longer than this the server
     * will destroy it.
     */
    public ApiBuilder sessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

    /**
     * Enables or disables persistent authentication using cookies. It is enabled by default, so calling this method is
     * required only if you wish to disable it.
     */
    public ApiBuilder persistentAuth(Boolean persistentAuth) {
        this.persistentAuth = persistentAuth;
        return this;
    }

    /**
     * Enables or disables verification of the host name inside the SSL certificate presented by the server. By default
     * the certificate is verified, and the connection will be rejected if the host name isn't correct. If you want to
     * disable this verification you need to explicitly pass {@code true} to this method.
     */
    public ApiBuilder noHostVerification(Boolean noHostVerification) {
        this.noHostVerification = noHostVerification;
        return this;
    }

    /**
     * Sets the location of the file containing the CA certificates used to verify the certificate presented by the
     * server. This must be combined with the {@link #keyStorePassword(String)} method.
     */
    public ApiBuilder keyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
        return this;
    }

    /**
     * Sets the password used to decrypt the key store file indicated by the {@link #keyStorePath} method.
     */
    public ApiBuilder keyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
        return this;
    }

    /**
     * If set to {@code true} enables the user mode API, where users will only see the objects that they have
     * permissions for. By default this is {@code false}, which means that users will see all objects.
     */
    public ApiBuilder filter(Boolean filter) {
        this.filter = filter;
        return this;
    }

    /**
     * Enables or disables debug output. By default debug output is disabled.
     */
    public ApiBuilder debug(Boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * Enables or disables Kerberos authentication. By default it is disabled, to enable it you need to pass
     * {@code true} to this method. In addition, the environment needs to be configured correctly, doing the following:
     *
     * <ul>
     * <li>Create a JAAS configuration file readable by the application, with the following content:
     * <pre>
     * com.sun.security.jgss.login {
     *   com.sun.security.auth.module.Krb5LoginModule required client=true useTicketCache=true;
     * };
     *
     * com.sun.security.jgss.initiate {
     *   com.sun.security.auth.module.Krb5LoginModule required client=TRUE useTicketCache=true;
     * };
     *
     * com.sun.security.jgss.accept {
     *   com.sun.security.auth.module.Krb5LoginModule required client=TRUE useTicketCache=true;
     * };
     * </pre>
     * <li>Add the following system properties to your environment, either as command line options to the {@code java}
     * command or using the {@link System#setProperty(String, String)} method:
     * <pre>
     * -Djava.security.auth.login.config=&lt;location of the JAAS configuration file&gt;
     * -Djava.security.krb5.conf=&lt;location of the Kerberos configuration file&gt;
     * -Djavax.security.auth.useSubjectCredsOnly=false
     * </pre>
     * </li>
     * </ul>
     *
     * Note that with this configuration you will be responsible for populating the Kerberos credentials cache before
     * running the Java program, usually with the {@code kinit} command. The SDK will then take your credentials from
     * that cache and use them to authenticate to the server.
     *
     * Note also that the Kerberos implementation in the JVM only supports the traditional file based credentials
     * cache, so make sure that if you have either don't {@code default_ccache_name} parameter in your Kerberos
     * configuration file or its value starts with {@code FILE}, as this is the only type of credential cache
     * currently supported by the JVM.
     *
     * If you have problems with this configuration it is very useful to add the {@code debug} parameter to all the
     * entries in the JAAS configuration file:
     *
     * <pre>
     * com.sun.security.jgss.login {
     *   com.sun.security.auth.module.Krb5LoginModule ... debug=true;
     * };
     * </pre>
     *
     * It is also useful to enable debug output of the Kerberos implementation of the JVM:
     *
     * <pre>
     * -Dsun.security.krb5.debug=true
     * </pre>
     */
    public ApiBuilder kerberos(Boolean kerberos) {
        this.kerberos = kerberos;
        return this;
    }

    /**
     * Creates an API entry point object.
     *
     * @throws ServerException Signals that an API error has occurred
     * @throws IOException Signals that an I/O exception of some sort has occurred
     * @throws UnsecuredConnectionAttemptError Signals that attempt of connecting to SSL secured site using HTTP
     *     protocol has occurred
     */
    public Api build() throws ServerException, UnsecuredConnectionAttemptError, IOException {
        return new Api(
            url,
            user,
            password,
            sessionId,
            null, // The port should be inside the URL, the builder doesn't support changing it.
            requestTimeout,
            sessionTimeout,
            persistentAuth,
            noHostVerification,
            keyStorePath,
            keyStorePassword,
            filter,
            debug,
            kerberos
        );
    }
}

