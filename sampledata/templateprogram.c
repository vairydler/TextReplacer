/*****************************************/
/* %rep%
 * @param 
 *
 */
/*****************************************/
EN_SB_RAM_RET SB_SysRam_Get_%rep%(const ST_SB_Cmn* a_pstCmn, %rep%* a_p%rep%Value)
{
	EN_SB_RAM_RET eRet;
	BOOL					bProcContinue = TRUE;

	/* �A�N�Z�X���`�F�b�N */
	if(bProcContinue)
	{
		eRet = NG;
	}
	
	/* �͈̓`�F�b�N */
	if(bProcContinue)
	{
		eRet = SAFE;
	}
	
	/* RAM�i�[ */
	if(bProcContinue)
	{
		if(A)
		{
			*a_p%rep%Value = stSysRamNa.%rep%;
			eRet = OK;
		}
		else
		{
			*a_p%rep%Value = stSysRamLi.%rep%;
			eRet = OK;
		}
	}

	return eRet;
}

