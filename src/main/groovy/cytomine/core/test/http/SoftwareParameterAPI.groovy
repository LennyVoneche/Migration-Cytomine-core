package cytomine.core.test.http

/*
* Copyright (c) 2009-2019. Authors: see NOTICE file.
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

import cytomine.core.processing.SoftwareParameter
import cytomine.core.test.Infos
import grails.converters.JSON

/**
 * User: lrollus
 * Date: 6/12/11
 * This class implement all method to easily get/create/update/delete/manage SoftwareParameterAPI to Cytomine with HTTP request during functional test
 */
class SoftwareParameterAPI extends DomainAPI {

    static def show(Long id, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/softwareparameter/" + id + ".json"
        return doGET(URL, username, password)
    }

    static def list(String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/softwareparameter.json"
        return doGET(URL, username, password)
    }

    static def listBySoftware(Long id, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/software/$id/parameter.json"
        return doGET(URL, username, password)
    }

    static def create(String json, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/softwareparameter.json"
        def result = doPOST(URL,json,username,password)
        result.data = SoftwareParameter.get(JSON.parse(result.data)?.softwareparameter?.id)
        return result
    }

    static def update(def id, def jsonSoftwareParameter, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/softwareparameter/" + id + ".json"
        return doPUT(URL,jsonSoftwareParameter,username,password)
    }

    static def delete(def id, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/softwareparameter/" + id + ".json"
        return doDELETE(URL,username,password)
    }
}
