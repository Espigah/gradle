/*
 * Copyright 2013 the original author or authors.
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

package org.gradle.nativeplatform.toolchain.internal.gcc;

import org.gradle.internal.Transformers;
import org.gradle.internal.operations.BuildOperationProcessor;
import org.gradle.nativeplatform.toolchain.internal.ArgsTransformerFactory;
import org.gradle.nativeplatform.toolchain.internal.CommandLineToolInvocationWorker;
import org.gradle.nativeplatform.toolchain.internal.CommandLineToolContext;
import org.gradle.nativeplatform.toolchain.internal.DefaultCompilerArgsTransformerFactory;
import org.gradle.nativeplatform.toolchain.internal.ObjectFileExtensionCalculator;
import org.gradle.nativeplatform.toolchain.internal.compilespec.ObjectiveCppCompileSpec;

class ObjectiveCppCompiler extends GccCompatibleNativeCompiler<ObjectiveCppCompileSpec> {

    ObjectiveCppCompiler(BuildOperationProcessor buildOperationProcessor, CommandLineToolInvocationWorker commandLineToolInvocationWorker, CommandLineToolContext invocationContext, ObjectFileExtensionCalculator objectFileExtensionCalculator, boolean useCommandFile) {
        super(buildOperationProcessor, commandLineToolInvocationWorker, invocationContext, getArgsTransformerFactory(), Transformers.<ObjectiveCppCompileSpec>noOpTransformer(), objectFileExtensionCalculator, useCommandFile);
    }

    private static class ObjectiveCppCompileArgsTransformer extends GccCompilerArgsTransformer<ObjectiveCppCompileSpec> {
        protected String getLanguage() {
            return "objective-c++";
        }
    }

    private static class ObjectiveCppPCHCompileArgsTransformer extends GccCompilerArgsTransformer<ObjectiveCppCompileSpec> {
        protected String getLanguage() {
            return "objective-c++-header";
        }
    }

    private static ArgsTransformerFactory<ObjectiveCppCompileSpec> getArgsTransformerFactory() {
        return new DefaultCompilerArgsTransformerFactory<ObjectiveCppCompileSpec>(
                new ObjectiveCppCompileArgsTransformer(),
                new ObjectiveCppPCHCompileArgsTransformer()
        );
    }
}
