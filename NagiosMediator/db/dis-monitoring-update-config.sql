UPDATE
	DIS_MONITORING.TBL_CONFIG A
SET
	A.CFG_VAL = 'ENTER DESIRED VALUE'
WHERE
	A.AGENT_TYPE = 'MED'
	AND A.CFG_KEY = 'ENTER DESIRED KEY';

COMMIT;