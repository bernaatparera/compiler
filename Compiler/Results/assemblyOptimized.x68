	ORG	$1000	;Starting address
START:
	MOVE.L	 #1,D0	;Insert value into D0
	MOVE.L	D0,V0	;Save value into destination
	MOVE.L	V0,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V1	;Save value into destination
	MOVE.L	 #1,D0	;Insert value into D0
	MOVE.L	D0,V2	;Save value into destination
	MOVE.L	 #1,D0	;Insert value into D0
	MOVE.L	D0,V3	;Save value into destination
	MOVE.L	V2,D0	;Insert value into D0
	MOVE.L	V3,D1	;Insert value into D1
	ADD.L	D1,D0	;ADD of values of D1 and D0, result in D0
	MOVE.L	D0,V4	;Save value into destination
	MOVE.L	V4,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V5	;Save value into destination
	MOVE.L	V1,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V6	;Save value into destination
	MOVE.L	V5,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V7	;Save value into destination
	MOVE.L	V6,D0	;Insert value into D0
	MOVE.L	V7,D1	;Insert value into D1
	ADD.L	D1,D0	;ADD of values of D1 and D0, result in D0
	MOVE.L	D0,V8	;Save value into destination
	MOVE.L	V8,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V10	;Save value into destination
	MOVE.L	V10,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	MOVE.L	 #0,D0	;Insert value into D0
	MOVE.L	D0,V12	;Save value into destination
	MOVE.L	V12,D0	;Insert value into D0
	MOVE.L	V1,D1	;Insert value into D1
	SUB.L	D1,D0	;SUB of values of D1 and D0, result in D0
	MOVE.L	D0,V11	;Save value into destination
	MOVE.L	V11,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V13	;Save value into destination
	MOVE.L	V13,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	MOVE.L	V1,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V14	;Save value into destination
	MOVE.L	V5,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V15	;Save value into destination
	MOVE.L	V14,D0	;Insert value into D0
	MOVE.L	V15,D1	;Insert value into D1
	SUB.L	D1,D0	;SUB of values of D1 and D0, result in D0
	MOVE.L	D0,V16	;Save value into destination
	MOVE.L	V16,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V17	;Save value into destination
	MOVE.L	V17,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	MOVE.L	 #-2,D0	;Insert value into D0
	MOVE.L	D0,V18	;Save value into destination
	MOVE.L	 #2,D0	;Insert value into D0
	MOVE.L	D0,V19	;Save value into destination
	MOVE.L	V18,D0	;Insert value into D0
	MOVE.L	V19,D1	;Insert value into D1
	MULU	D1,D0	;Product of values of D1 and D0, result in D0
	MOVE.L	D0,V20	;Save value into destination
	MOVE.L	V20,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V21	;Save value into destination
	MOVE.L	V21,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	MOVE.L	V5,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V22	;Save value into destination
	MOVE.L	 #2,D0	;Insert value into D0
	MOVE.L	D0,V23	;Save value into destination
	MOVE.L	V22,D0	;Insert value into D0
	MOVE.L	V23,D1	;Insert value into D1
	DIVU	D1,D0	;Divition of values of D1 and D0, result in D0
	MOVE.L	D0,V24	;Save value into destination
	MOVE.L	V24,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V25	;Save value into destination
	MOVE.L	V25,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	MOVE.L	 #0,D0	;Insert value into D0
	MOVE.L	D0,V27	;Save value into destination
	MOVE.L	V27,D0	;Insert value into D0
	MOVE.L	V1,D1	;Insert value into D1
	SUB.L	D1,D0	;SUB of values of D1 and D0, result in D0
	MOVE.L	D0,V26	;Save value into destination
	MOVE.L	 #1,D0	;Insert value into D0
	MOVE.L	D0,V28	;Save value into destination
	MOVE.L	V26,D0	;Insert value into D0
	MOVE.L	V28,D1	;Insert value into D1
	ADD.L	D1,D0	;ADD of values of D1 and D0, result in D0
	MOVE.L	D0,V29	;Save value into destination
	MOVE.L	V29,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V30	;Save value into destination
	MOVE.L	V30,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	MOVE.L	 #0,D0	;Insert value into D0
	MOVE.L	D0,V32	;Save value into destination
	MOVE.L	V32,D0	;Insert value into D0
	MOVE.L	V1,D1	;Insert value into D1
	SUB.L	D1,D0	;SUB of values of D1 and D0, result in D0
	MOVE.L	D0,V31	;Save value into destination
	MOVE.L	 #7,D0	;Insert value into D0
	MOVE.L	D0,V33	;Save value into destination
	MOVE.L	V31,D0	;Insert value into D0
	MOVE.L	V33,D1	;Insert value into D1
	ADD.L	D1,D0	;ADD of values of D1 and D0, result in D0
	MOVE.L	D0,V34	;Save value into destination
	MOVE.L	 #10,D0	;Insert value into D0
	MOVE.L	D0,V35	;Save value into destination
	MOVE.L	 #2,D0	;Insert value into D0
	MOVE.L	D0,V36	;Save value into destination
	MOVE.L	V35,D0	;Insert value into D0
	MOVE.L	V36,D1	;Insert value into D1
	MULU	D1,D0	;Product of values of D1 and D0, result in D0
	MOVE.L	D0,V37	;Save value into destination
	MOVE.L	V34,D0	;Insert value into D0
	MOVE.L	V37,D1	;Insert value into D1
	ADD.L	D1,D0	;ADD of values of D1 and D0, result in D0
	MOVE.L	D0,V38	;Save value into destination
	MOVE.L	V38,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V9	;Save value into destination
	MOVE.L	V9,D0	;Insert value into D0
	MOVE.L	V0,D1	;Insert value into D1
	MOVE.L	D0,V39	;Save value into destination
	MOVE.L	V39,D1	;Message to D1
	EXT.L	D1	;Message to D1
	MOVE.L	#3,D0	;Preparation
	TRAP	#15	
	MOVE.L	#11,D0	;
	MOVE.W	#$00FF,D1	;
	TRAP	#15	
	ADD.W	#1,D1	;
	AND.W	#$00FF,D1	;
	TRAP	#15	
	SIMHALT	;Stop execution
;VARS
V0:	DS.L 1
V1:	DS.L 1
V2:	DS.L 1
V3:	DS.L 1
V4:	DS.L 1
V5:	DS.L 1
V6:	DS.L 1
V7:	DS.L 1
V8:	DS.L 1
V9:	DS.L 1
V10:	DS.L 1
V11:	DS.L 1
V12:	DS.L 1
V13:	DS.L 1
V14:	DS.L 1
V15:	DS.L 1
V16:	DS.L 1
V17:	DS.L 1
V18:	DS.L 1
V19:	DS.L 1
V20:	DS.L 1
V21:	DS.L 1
V22:	DS.L 1
V23:	DS.L 1
V24:	DS.L 1
V25:	DS.L 1
V26:	DS.L 1
V27:	DS.L 1
V28:	DS.L 1
V29:	DS.L 1
V30:	DS.L 1
V31:	DS.L 1
V32:	DS.L 1
V33:	DS.L 1
V34:	DS.L 1
V35:	DS.L 1
V36:	DS.L 1
V37:	DS.L 1
V38:	DS.L 1
V39:	DS.L 1
	END START		;-- END --
