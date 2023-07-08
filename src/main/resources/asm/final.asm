include macros2.asm
include number.asm

.MODEL LARGE
.386
.STACK 200h

MAXTEXTSIZE equ 50

.DATA

	e dd ?
	_11 dd 11.0
	p2 dd ?
	_3 dd 3.0
	_10 dd 10.0
	_25 dd 25.0
	d dd ?
	a dd ?
	p1 dd ?
	c dd ?
	cad2 db ?
	_21 dd 21.0
	cad1 db ?
	cad3 db ?
	b dd ?
	cadInput db ?
	_0 dd 0.0
	_1 dd 1.0
	p3 dd ?
	_2 dd 2.0
	p4 dd ?

.CODE

START:
	fld _10
	fstp a
	fld _11
	fstp b
	fld a
	fld _0
	fxch
	fcom
	fstsw ax
	sahf
	JBE LABEL24
	fld a
	fld _21
	fxch
	fcom
	fstsw ax
	sahf
	JBE LABEL22
	fld a
	fld _25
	fxch
	fcom
	fstsw ax
	sahf
	JBE LABEL19
	fld _1
	fstp a
	JMP LABEL21
LABEL19:
	fld _2
	fstp a
LABEL21:
	JMP LABEL24
LABEL22:
	fld _3
	fstp a
LABEL24:
	fld _2
	fstp b

mov ax,4C00h
int 21h
END START
