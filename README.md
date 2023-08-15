Lucas Guimarães Miranda, RA: 11070916
https://youtu.be/6xh7HBke8YM

# Glorious IsiLanguage Compiler (GIC)
Projeto de compilador para a IsiLanguage, feito como projeto final na disciplina de Compiladores da UFABC ministrada pelo Prof. Dr. Francisco Isidro Massetto.

## Como usar?
Após adicionar o Antlr 4.13.0 ao classpath e buildar o projeto, ao executar o método `main` da `MainClass` passe os seguintes argumentos pela linha de comando:

`<PATH_INPUT> (<I> | (<C> (<C|JAVA|JS|ALL>))`

- `<PATH_INPUT>`: Caminho do arquivo `.isi` de input para o compilador;
- `I`: Executa em modo Runtime;
- `C`: Executa em modo Compilação:
    - `C`: Compila para a linguagem alvo *C*;
    - `JAVA`: Compila para a linguagem alvo *Java*;
    - `JS`: Compila para a linguagem alvo *Java Script*;
    - `ALL`: Compila para todas as linguagens alvo acima.

Se executado em modo `I` o runtime irá iniciar.

Se executado em modo `C` em caso de sucesso de compilação serão gerados os arquivos da linguagem alvo, homônimos ao arquivo de input a não ser pela extensão, na mesma pasta do arquivo de input.

## Feito com:
- Java 17;
- Antlr 4.13.0.

## Checkilist
Os arquivos de checkilist da entrega estão na pasta `checklist` sepados em subpastas.

## Features Obrigatórias
- Tipos:
    - `TEXTO`;
    - `INTEIRO`;
    - `REAL`;
- Estrutura if;
- Estruturas de controle:
    - While;
    - DoWhile.
- Expressões aritméticas;
- Atribuições:
    - Só é possível atribuição entre dados do mesmo tipo.
- IO:
    - Entrada;
    - Saida.
- Números decimais;
- Erro semântico ao tentar usar variável não declarada.
- Aviso ao declarar variável e não utilizar.
- Erro semântico ao usar variável que não recebeu um valor inicial.

## Features Opcionais
- [Runtime](../../src/ast/RuntimeEntity.java): Passe o argumento `I`` na linha de comando;
- [Avaliador de expressões aritmética (Arvore de expressão aritimética)](../../src/expressions/ExpressionTree.java);
- Geração de código pretty printed;
- Geração de código para diversos alvos: Use os argumentos `<C|JAVA|JS|ALL>` na linha de comando.
- Mostra linha e coluna da posição onde ocorre Erros ou Warnings.

## Especificações da linguagem
- Números negativos devem sempre estar entre parênteses.
- Para usar uma variável ela deve ter sido iniciada no bloco principal (externo a todos blocos ifs, while ou do while).
```
programa  : 'programa' decl* cmd* 'fimprog.'
		  ;
		  
decl	  : tipo lista_var PF
		  ;
		 
tipo	  : 'INTEIRO' 
          | 'REAL'
          | 'TEXTO'
          ;

lista_var : ID (VIRG ID)*
   		  ;
   		  
cmd		  : cmdAttr | cmdRead | cmdWrite | cmdIf | cmdWhile cmdDoWhile
		  ;

cmdDoWhile : 'execute' cmd+ 'enquanto' AP bRelationalExpr FP PF
           ;

cmdWhile  : 'enquanto' AP bRelationalExpr FP 'execute' cmd+ 'fimenquanto' PF
          ;
		  
cmdIf     : 'se' AP bRelationalExpr FP 'execute' cmd+ ('senao' cmd+)? 'fimse' PF 
		  ; 
		  
cmdRead   : 'leia' AP ID FP PF
		  ;		 
		  
cmdWrite  : 'escreva' AP (ID | TEXT) FP PF
          ;		      		  
   		  
cmdAttr   : ID ATTR expr PF
		  ;   		  
		  
expr	  : termo exprl*
          ;

bRelationalExpr: expr  OPREL expr
               ;
          
termo     : (NUMBER | NUMBERDEC) | TEXT | ID | AP (expr | (NEG_NUMBER | NEG_NUMBER_DEC)) FP
		  ;
		  
exprl     : (SUM | SUB | MUL | DIV) termo

          ;		         
		  
NUMBER	  : [0-9]+
		  ;

NEG_NUMBER : '-'[0-9]+
           ;

NUMBERDEC : [0-9]+('.'[0-9]+)
          ;

NEG_NUMBER_DEC : '-'[0-9]+('.'[0-9]+)
               ;
		  
TEXT 	  : '"' ([a-z]|[A-Z]|[0-9]|' '|'\t'|'!'|'-'|'<'|'>'|'='|'.')* '"'
		  ;		  
		  
ATTR	  : ':='
   		  ;		
   		  
SUM	      : '+'
		  ;
		  
SUB		  : '-'		     		    
          ;

MUL       : '*'
          ;

DIV       : '/'
          ;
          
OPREL     : '>' | '>=' | '<' | '<=' | '==' | '!='
 		  ;          
   		  
ID		  : [a-z] ([a-z]|[A-Z]|[0-9])*
          ;
          
VIRG      : ','
          ;
          
PF        : '.'
          ;
          
AP	      : '('          
	      ;
	      
FP 		  : ')'
          ;	      
BLANK     : (' '| '\t' | '\n' | '\r') -> skip
          ;
```
