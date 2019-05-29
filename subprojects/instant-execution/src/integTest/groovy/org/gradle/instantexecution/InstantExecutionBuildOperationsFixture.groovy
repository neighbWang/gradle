/*
 * Copyright 2019 the original author or authors.
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

package org.gradle.instantexecution

import org.gradle.integtests.fixtures.BuildOperationsFixture
import org.gradle.integtests.fixtures.executer.GradleExecuter
import org.gradle.internal.operations.trace.BuildOperationRecord
import org.gradle.test.fixtures.file.TestDirectoryProvider

import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.CoreMatchers.nullValue
import static org.junit.Assert.assertThat


class InstantExecutionBuildOperationsFixture {

    final BuildOperationsFixture operations

    InstantExecutionBuildOperationsFixture(GradleExecuter executer, TestDirectoryProvider projectDir) {
        operations = new BuildOperationsFixture(executer, projectDir)
    }

    void assertStateLoaded() {
        assertThat(loadOperation(), notNullValue())
        assertThat(storeOperation(), nullValue())
    }

    void assertStateStored() {
        assertThat(loadOperation(), nullValue())
        assertThat(storeOperation(), notNullValue())
    }

    void assertNoInstantExecution() {
        assertThat(loadOperation(), nullValue())
        assertThat(storeOperation(), nullValue())
    }

    private BuildOperationRecord loadOperation() {
        operations.first("Load instant execution state")
    }

    private BuildOperationRecord storeOperation() {
        operations.first("Store instant execution state")
    }
}
