/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for  additional infor mation regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for  the specif ic language governing permissions and
 * limitations under the License.
 */

package io.github.maropu;

import java.io.*;
import java.nio.charset.StandardCharsets;

import io.github.maropu.lljvm.*;
import io.github.maropu.lljvm.runtime.*;

public class LLJVMExample {

  private static byte[] resourceToBytes(String resource) throws Exception {
    final InputStream in =
      Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    byte [] buffer = new byte[1024];
    while(true) {
      int len = in.read(buffer);
      if(len < 0) {
        break;
      }
      bout.write(buffer, 0, len);
    }
    return bout.toByteArray();
  }

  public static void main(String args[]) {
    try {
      // Initialize LLJVM runtime first
      LLJVMRuntime.initialize();

      // `test.bc` is LLVM bitcode compiled from C code below;
      //
      // $ cat test.c
      // int test(int x, int y) {
      //  return x + y;
      // }
      // $ clang -c -O2 -emit-llvm -o test.bc test.c
      // $ ls
      // test.bc
      // test.c
      final byte[] code = resourceToBytes("test.c");
      System.out.println("#### Source Code ####\n" + new String(code, StandardCharsets.UTF_8));
      final byte[] bitcode = resourceToBytes("test.bc");
      System.out.println("#### LLVM Assembly Code ####");
      System.out.println(LLJVMUtils.asLLVMAssemblyCode(bitcode));
      System.out.println("#### JVM Assembly Code ####");
      System.out.println(LLJVMUtils.asJVMAssemblyCode(bitcode));
      Class<?> clazz = LLJVMClassLoader.currentClassLoader.get().loadClassFromBitcode(bitcode);
      System.out.println("#### Result ####\ntest(3, 6) => " + LLJVMUtils.invoke(clazz, 3, 6));
      System.out.print("\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

