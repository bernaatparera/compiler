# Compiler
Made for the subject 21780. Compiladors of UIB

The statement for the practice is in the 'extra' folder. The final grade was 10 out of 10, and the completed work is attached. However, there are certain aspects that could be improved.

One of them is that our arithmetic and boolean expressions don't handle parenthesis or you can't handle tuple calls in the expressions

The compiler must be executed in IntelliJ IDEA, following a structured sequence of phases to transform the source code into assembly code. The main stages of the compilation process are:
## Lexical Analysis
In this phase, tokens are identified using JFlex. The lexer scans the source code and converts it into a sequence of tokens, which are the basic building blocks of the program.
## Syntactic Analysis
Using Java CUP, the syntactic analyzer (parser) processes the token sequence and ensures that the code follows the correct structure defined by a Context-Free Grammar (CFG). This step constructs a parse tree or an abstract syntax tree (AST).

## Semantic Analysis
In this stage, the compiler checks for semantic correctness. This includes type checking, variable scope verification, and ensuring that operations are valid. Errors related to improper usage of identifiers or type mismatches are detected here.

## Three-Address Code (TAC) Generation
After semantic validation, an intermediate representation known as Three-Address Code (TAC) is generated. This representation simplifies the later conversion into assembly code.

## Assembly Code Generation
The compiler translates the TAC into assembly code, specifically designed for execution in Easy68K, an assembler for the Motorola 68000 processor. The generated code follows the Easy68K syntax and conventions.

## Assembly Code Optimization
Finally, the generated assembly code undergoes optimizations to improve efficiency. This includes:

Removing unreachable code
Eliminating redundant or unnecessary jumps
These phases ensure a structured and efficient compilation process, resulting in optimized assembly code that can be executed in Easy68K.

