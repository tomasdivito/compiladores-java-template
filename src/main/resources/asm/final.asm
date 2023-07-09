include macros2.asm
include number.asm

.MODEL LARGE
.386
.STACK 200h

MAXTEXTSIZE equ 50

.DATA

	cadInput db ?
	_3 dd 3.0
	_b_no_es_mayor_que_a db "b no es mayor que a",0
	_cadena_de_entrada: db "cadena de entrada:",0
	_1 dd 1.0
	_Hello db "Hello",0
	e dd ?
	_world db "world",0
	_2.5 dd 2.5
	_0 dd 0.0
	_150 dd 150.0
	a dd ?
	_a_no_es_0_con_ifs_anidados db "a no es 0 con ifs anidados",0
	_lold db ""lold"",0
	_b_es_mayor_que_a db "b es mayor que a",0
	c dd ?
	cad3 db ?
	_2 dd 2.0
	_a_es_0_con_ifs_anidados db "a es 0 con ifs anidados",0
	p2 dd ?
	p1 dd ?
	_loop_de_ciclo db "loop de ciclo",0
	b dd ?
	cad1 db ?
	p3 dd ?
	_1.0 dd 1.0
	d dd ?
	_4.9 dd 4.9
	cad2 db ?
	_b_es_mayor_que_a_pero_ahora_con_else db "b es mayor que a pero ahora con else",0
	_cadena_con_recorte: db "cadena con recorte:",0
	p4 dd ?

.CODE

START:
LABEL0:
	fld _1
	fstp a
LABEL2:
	fld _2
	fstp b
LABEL4:
	fld _3
	fstp c
LABEL6:
	fld _1.0
	fstp p1
LABEL8:
	fld _2.5
	fstp p2
LABEL10:
	fld _4.9
	fstp p3

	 STRCPY cad1,"Hello"

	 STRCPY cad2,"world"
LABEL16:
	fld a
LABEL17:
	fld b
	fadd
LABEL19:
	fld c
	fdiv
	fstp d
LABEL22:
	fld d
LABEL23:
	fld _150
	fadd
	fstp e
LABEL26:
	fld p1
LABEL27:
	fld p3
	fmul
LABEL29:
	fld p2
	fsub
	fstp p4

	 STRCPY cad3,"lold"
LABEL34:
displayString _cadena_con_recorte:
newLine
LABEL35:
displayString cad3
newLine
LABEL36:
	fld b
LABEL37:
	fld a
	fxch
	fcom
	fstsw ax
	sahf
	JBE LABEL41
LABEL40:
displayString _b_es_mayor_que_a
newLine
LABEL41:
	fld a
LABEL42:
	fld b
	fxch
	fcom
	fstsw ax
	sahf
	JBE LABEL47
LABEL45:
displayString _b_no_es_mayor_que_a
newLine
	JMP LABEL48
LABEL47:
displayString _b_es_mayor_que_a_pero_ahora_con_else
newLine
LABEL48:
	fld a
LABEL49:
	fld _0
	fxch
	fcom
	fstsw ax
	sahf
	JE LABEL62
LABEL52:
	fld a
LABEL53:
	fld _0
	fxch
	fcom
	fstsw ax
	sahf
	JE LABEL62
LABEL56:
	fld a
LABEL57:
	fld _0
	fxch
	fcom
	fstsw ax
	sahf
	JNE LABEL62
LABEL60:
displayString _a_es_0_con_ifs_anidados
newLine
	JMP LABEL63
LABEL62:
displayString _a_no_es_0_con_ifs_anidados
newLine
LABEL63:
	fld c
LABEL64:
	fld _0
	fxch
	fcom
	fstsw ax
	sahf
	JE LABEL73
LABEL67:
displayString _loop_de_ciclo
newLine
LABEL68:
	fld c
LABEL69:
	fld _1
	fsub
	fstp c
	JMP LABEL63
LABEL73:
displayString cadInput
newLine
LABEL74:
displayString _cadena_de_entrada:
newLine
LABEL75:
displayString cadInput
newLine
LABEL76:

mov ax,4C00h
int 21h
END START
