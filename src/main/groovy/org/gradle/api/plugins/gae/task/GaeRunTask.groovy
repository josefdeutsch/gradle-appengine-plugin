/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.plugins.gae.task

import com.google.appengine.tools.KickStart
import org.gradle.api.GradleException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Google App Engine task starting up a local development server.
 *
 * @see <a href="http://code.google.com/appengine/docs/java/tools/devserver.html#Running_the_Development_Web_Server">Documentation</a>
 * @author Benjamin Muschko
 */
class GaeRunTask extends GaeWebAppDirTask {
    static final Logger logger = LoggerFactory.getLogger(GaeRunTask.class)
    private Integer httpPort

    @Override
    void executeTask() {
        startLocalDevelopmentServer()
    }

    void startLocalDevelopmentServer() {
        try {
            logger.info "Starting local development server..."

            String[] params = ["com.google.appengine.tools.development.DevAppServerMain", "--port=" + getHttpPort(), getWebAppSourceDirectory().getCanonicalPath()] as String[]
            KickStart.main(params)
        }
        catch(Exception e) {
            throw new GradleException("An error occurred starting the local development server.", e)
        }
        finally {
            logger.info "Local development server exiting."
        }
    }

    public Integer getHttpPort() {
        httpPort
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort
    }
}
