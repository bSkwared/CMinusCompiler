.data
.comm	a,4,4

.text
	.align 4
.globl  addThem
addThem:
addThem_bb2:
	movl	%ESI, %EAX
addThem_bb3:
	addl	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%EDI, %EAX
addThem_bb4:
	ret
.globl  main
main:
main_bb2:
	pushq	%R15
main_bb3:
	movl	$5, %EAX
	movl	%EAX, %EDI
	movl	$5, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb6
main_bb4:
	movl	$3, %EAX
	movl	%EAX, a(%RIP)
main_bb5:
	movl	$0, %EAX
	movl	%EAX, %R15D
	movl	$1, %EAX
	movl	%EAX, %EDX
main_bb7:
	movl	$8, %EAX
	cmpl	%EAX, %EDX
	jg	main_bb9
main_bb8:
	movl	%R15D, %EAX
	addl	%EDX, %EAX
	movl	%EAX, %R15D
	movl	$1, %ESI
	movl	%EDX, %EAX
	addl	%ESI, %EAX
	movl	%EAX, %EDX
	jmp	main_bb7
main_bb9:
	movl	$3, %ESI
	movl	$0, %EDX
	movl	%R15D, %EAX
	idivl	%ESI, %EAX
	movl	$4, %ESI
	imull	%ESI, %EAX
	movl	%EAX, %R15D
	movl	%EDI, %ESI
	movl	a(%RIP), %EAX
	movl	%EAX, %EDI
	call	addThem
	addl	%R15D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$10, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	$0, %EAX
main_bb10:
	popq	%R15
	ret
main_bb6:
	movl	$4, %EAX
	movl	%EAX, a(%RIP)
	jmp	main_bb5
