This is example code of [lljvm-translator](https://github.com/maropu/lljvm-translator).

## Run the example code

    $ ./bin/run-example.sh
    #### Source Code ####
    int test(int x, int y) {
      return x + y;
    }

    #### LLVM Assembly Code ####
    source_filename = "test.c"
    target datalayout = "e-m:o-i64:64-f80:128-n8:16:32:64-S128"
    target triple = "x86_64-apple-macosx10.12.0"

    ; Function Attrs: norecurse nounwind readnone ssp uwtable
    define i32 @test(i32, i32) local_unnamed_addr #0 {
      %3 = add nsw i32 %1, %0
      ret i32 %3
    }

    attributes #0 = { norecurse nounwind readnone ssp uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+fxsr,+mmx,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

    !llvm.module.flags = !{!0}
    !llvm.ident = !{!1}

    !0 = !{i32 7, !"PIC Level", i32 2}
    !1 = !{!"Apple LLVM version 9.0.0 (clang-900.0.39.2)"}

    #### JVM Assembly Code ####
    .class public final GeneratedClass20180731HMKjwzxmew
    .super java/lang/Object

    ; Fields

    ; External methods

    ; Constructor
    .method public <init>()V
    	aload_0
    	invokespecial java/lang/Object/<init>()V
    	return
    .end method

    .method public <clinit>()V
    	.limit stack 4
    	invokestatic io/github/maropu/lljvm/runtime/VMemory/resetHeap()V

    	; allocate global variables

    	; initialise global variables
    	return
    .end method


    .method public static _test(II)I
    	iconst_0
    	istore 2
    begin_method:
    	invokestatic io/github/maropu/lljvm/runtime/VMemory/createStackFrame()V
    label1:
    	iload_1 ; _1
    	iload_0 ; _0
    	iadd
    	istore_2 ; _2
    	invokestatic io/github/maropu/lljvm/runtime/VMemory/destroyStackFrame()V
    	iload_2 ; _2
    	ireturn
    	.limit stack 16
    	.limit locals 3
    end_method:
    .end method

    #### Result ####
    test(3, 6) => 9

