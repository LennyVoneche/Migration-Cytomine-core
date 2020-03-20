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

import cytomine.core.test.Infos
import cytomine.core.AnnotationDomain
import cytomine.core.CytomineDomain
import cytomine.core.image.ImageInstance
import cytomine.core.meta.TagDomainAssociation
import cytomine.core.ontology.AlgoAnnotation
import cytomine.core.ontology.ReviewedAnnotation
import cytomine.core.ontology.UserAnnotation
import cytomine.core.processing.Job
import cytomine.core.processing.Software
import cytomine.core.project.Project
import grails.converters.JSON

class TagDomainAssociationAPI extends DomainAPI {

    static def show(Long id, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/tag_domain_association/${id}.json"
        return doGET(URL,username,password)
    }

    static def create(def json, String domainClassName, Long domainIdent, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/domain/"+domainClassName+"/"+domainIdent+"/tag_domain_association.json"
        def result = doPOST(URL,json,username,password)
        result.data = TagDomainAssociation.get(JSON.parse(result.data)?.tagdomainassociation?.id)
        return result
    }

    static def delete(Long id, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/tag_domain_association/${id}.json"
        return doDELETE(URL,username,password)
    }

    static def search(def searchParameters, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/tag_domain_association.json?${convertSearchParameters(searchParameters)}"
        return doGET(URL, username, password)
    }

    static def listByDomain(CytomineDomain domain, String username, String password) {
        String URL = Infos.CYTOMINEURL + "api/domain/"
        // TODO when RESTE normalization replace the switch by domain.getClass().simpleName converted to snake case
        switch (domain.getClass()){
            case ImageInstance :
            case Project :
            case Software :
            case Job :
                URL += domain.getClass().name+"/${domain.id}/"
                break;
            case UserAnnotation :
            case AlgoAnnotation :
            case ReviewedAnnotation :
                URL += AnnotationDomain.name+"/${domain.id}/"
                break;
        }
        URL += "tag_domain_association.json"
        return doGET(URL, username, password)
    }

}
