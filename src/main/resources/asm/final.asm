include macros.asm
include number.asm

.MODEL LARGE
.386
.STACK 200h

.DATA
__aux_sum dd ?
__aux_res dd ?
__aux_mul dd ?
__aux_div dd ?
__aux_uno dd 1.0
__figuales_pivot dd ?
__figuales_cont dd ?
__figuales_exp dd ?
a1 dd ?
b1 dd ?
a dd ?
b dd ?
c dd ?
d dd ?
e dd ?
f dd ?
g dd ?
i dd ?
j dd ?
h dd ?
k dd ?
l dd ?
n dd ?
m dd ?
o dd ?
q dd ?
r dd ?
s dd ?
t dd ?
u dd ?
v dd ?
p dd ?
w dd ?
x dd ?
y dd ?
z dd ?
variable1 dd ?
variable2 dd ?
id dd ?
j1 db ?
j2 db ?
j3 db ?
p1 db ?
p2 db ?
p3 db ?
numvar db ?
var1 db ?
var2 db ?
_1 dd 1.0
_2 dd 2.0
_3 dd 3.0
_10 dd 10.0
_18 dd 18.0
_ASD db "ASD",0

.CODE
main:
mov ax,@DATA
mov ds,ax
mov es,ax

fld _1
fstp a
fld _2
fstp b
fld _3
fstp z
fld a
fld a
fcom
fstsw ax
sahf
jna LBL20
fld z
fld b
fcom
fstsw ax
sahf
jae LBL20
fld _10
fstp a
fld _18
fld a
fadd
fstp __aux_sum
fld __aux_sum
fstp z
LBL20:
fld _1
fstp b
STRCPY j1,"ASD"

mov ax,4C00h
int 21h
END main