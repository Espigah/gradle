/*
 * Copyright 2015 the original author or authors.
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

package org.gradle.internal.resolve.result

import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier
import spock.lang.Specification

import static org.gradle.internal.resolve.result.BuildableComponentSelectionResult.State.*

class DefaultBuildableComponentSelectionResultTest extends Specification {
    DefaultBuildableComponentSelectionResult result = new DefaultBuildableComponentSelectionResult()

    def "has no matching state by default"() {
        expect:
        result.state == Unknown
        !result.match
        !result.hasResult()
    }

    def "can mark matching"() {
        given:
        ModuleComponentIdentifier moduleComponentIdentifier = DefaultModuleComponentIdentifier.newId('org.company', 'foo', '1.5')

        when:
        result.matches(moduleComponentIdentifier)

        then:
        result.state == Match
        result.match == moduleComponentIdentifier
        result.hasResult()
    }

    def "can mark no match"() {
        when:
        result.noMatchFound()

        then:
        result.state == NoMatch
        result.hasResult()
        !result.match
    }

}
