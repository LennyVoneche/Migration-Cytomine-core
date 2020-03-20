package cytomine.core.api.ontology

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

import cytomine.core.api.RestController
import cytomine.core.image.SliceInstance
//import org.restapidoc.annotation.RestApi
//import org.restapidoc.annotation.RestApiMethod
//import org.restapidoc.annotation.RestApiParam
//import org.restapidoc.annotation.RestApiParams
//import org.restapidoc.pojo.RestApiParamType

//@RestApi(name = "Ontology | annotation index services", description = "Methods for managing annotation index. Its auto index that store entries <slice,user,nbAnnotation,nbReviewed>")
class RestAnnotationIndexController extends RestController {

    def annotationIndexService
    def sliceInstanceService

//    @RestApiMethod(description="Get all index entries for a slice", listing=true)
//    @RestApiParams(params=[
//        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH,description = "The slice id")
//    ])
    def listBySlice() {
        SliceInstance slice = sliceInstanceService.read(params.long('id'))
        if (slice) {
            responseSuccess(annotationIndexService.list(slice))
        }
        else {
            responseNotFound("SliceInstance", params.id)
        }
    }
}
