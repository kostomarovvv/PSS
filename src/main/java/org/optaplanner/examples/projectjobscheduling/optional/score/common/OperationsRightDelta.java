/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

package org.optaplanner.examples.projectjobscheduling.optional.score.common;

import java.util.Objects;

import org.optaplanner.examples.projectjobscheduling.domain.Allocation;

public class OperationsRightDelta {

    private final Allocation allocation;
    private final int rightDelta;

    public OperationsRightDelta (Allocation allocation, int rightDelta) {
        this.allocation = allocation;
        this.rightDelta = rightDelta;
    }

    public Allocation getAllocation() {
        return allocation;
    }

    public int getRightDelta() {
        return rightDelta;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OperationsRightDelta other = (OperationsRightDelta) o;
        return rightDelta == other.rightDelta &&
                Objects.equals(allocation, other.allocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allocation, rightDelta);
    }

    @Override
    public String toString() {
        return allocation + " on " + rightDelta;
    }

}
